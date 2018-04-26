package project001.admin.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;
import project001.admin.entity.Admin;
import project001.admin.entity.Banner;
import project001.admin.entity.Image;
import project001.admin.entity.Tag;
import project001.admin.impl.BannerImpl;
import project001.admin.impl.HibernateConfig;
import project001.admin.impl.ImageImpl;
import project001.admin.impl.TagImpl;
import project001.admin.model.BannerModel;

@Controller
@RequestMapping("/admin/system/i/banner")
public class BannerController {
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(ModelMap modelMap) {
		//ArrayList<Banner> list = BannerImpl.getList();
		//modelMap.put("bannerList", list);
		return "/admin/banner/banner";
	}
	@RequestMapping(value="/bannerList",method=RequestMethod.POST)
	public @ResponseBody String bannerList(ModelMap modelMap) {
		Map<String,Object> m = new HashMap<String,Object>();
		
		ArrayList<Banner> list = BannerImpl.getList();
		m.put("list", list);
		
		JSONObject jsonobject = JSONObject.fromObject(m);
		return jsonobject.toString();
	}
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public ModelAndView update(int id,ModelMap modelMap) {
		Banner banner = BannerImpl.getById(id, Banner.class);
		/*BannerModel bannerModel = new BannerModel();
		bannerModel.setId(banner.getId());
		bannerModel.setTitle(banner.getTitle());
		bannerModel.setSort(banner.getSort());
		bannerModel.setUrl(banner.getUrl());*/
		modelMap.put("imageUrl", banner.getSrc());
		return new ModelAndView("/admin/banner/update","command",banner);
	}
	@RequestMapping(value="/iupdate",method=RequestMethod.POST)
	public String iupdate(ModelMap modelMap,Banner bannerModel,Model model,HttpServletRequest req,HttpSession httpSession) throws IOException {
		if(!((Admin)httpSession.getAttribute("admin")).getUser().equals("admin")) {
			model.addAttribute("info","您没有权限操作");
			model.addAttribute("code","0");
			model.addAttribute("backUrl","system/i/banner/index");
			return "redirect:/admin/msg";
		}
		
		Banner banner = BannerImpl.getById(bannerModel.getId(), Banner.class);
		/***图片上传***/
		MultipartFile mFile = bannerModel.getImage();
		if(!mFile.isEmpty()) {
			//根目录
			String webRoot = req.getSession().getServletContext().getRealPath("/");
			String relativePath = "/static/upload/";
			//String uuidImageToTag = UUID.randomUUID().toString().replaceAll("-", "");
			//banner.setUuid(uuidImageToTag);
			Map<String,String> image = ImageImpl.upload(webRoot,relativePath, mFile, banner.getUuid());
			if(image.get("status").equals("0")) {
				model.addAttribute("info",image.get("info"));
				model.addAttribute("code","0");
				model.addAttribute("backUrl","system/i/banner/index");
				return "redirect:/admin/msg";
			}else {
				banner.setSrc(image.get("src"));
			}
		}
		/***图片上传 End***/
		banner.setTitle(bannerModel.getTitle());
		banner.setSort(bannerModel.getSort());
		banner.setUrl(bannerModel.getUrl());
		BannerImpl.update(banner);
		
		model.addAttribute("info","操作成功");
		model.addAttribute("code","1");
		model.addAttribute("backUrl","system/i/banner/index");
		return "redirect:/admin/msg";
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add() {

		return new ModelAndView("/admin/banner/add","command",new Banner());
	}
	@RequestMapping(value="/iadd",method=RequestMethod.POST)
	public String iadd(Banner bannerModel,Model model,HttpServletRequest req,HttpSession httpSession) throws IOException {
			
			if(!((Admin)httpSession.getAttribute("admin")).getUser().equals("admin")) {
				model.addAttribute("info","您没有权限操作");
				model.addAttribute("code","0");
				model.addAttribute("backUrl","system/i/banner/index");
				return "redirect:/admin/msg";
			}
		
			Banner banner = new Banner();
			/***图片上传***/
			MultipartFile mFile = bannerModel.getImage();
			String uuidImageToTag = UUID.randomUUID().toString().replaceAll("-", "");
			if(!mFile.isEmpty()) {
				//根目录
				String webRoot = req.getSession().getServletContext().getRealPath("/");
				String relativePath = "/static/upload/";
				Map<String,String> image = ImageImpl.upload(webRoot,relativePath, mFile, uuidImageToTag);
				if(image.get("status").equals("0")) {
					model.addAttribute("info",image.get("info"));
					model.addAttribute("code","0");
					model.addAttribute("backUrl","system/i/banner/index");
					return "redirect:/admin/msg";
				}else {
					banner.setSrc(image.get("src"));
				}
			}
			/***图片上传 End***/
			
			
			banner.setTitle(bannerModel.getTitle());
			banner.setUrl(bannerModel.getUrl());
			banner.setSort(bannerModel.getSort());
			banner.setUuid(uuidImageToTag);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			banner.setCreateTime(simpleDateFormat.format(new Date()));
			HibernateConfig.add(banner);
			model.addAttribute("info","操作成功");
			model.addAttribute("code","1");
			model.addAttribute("backUrl","system/i/banner/index");
			return "redirect:/admin/msg";


	}
}

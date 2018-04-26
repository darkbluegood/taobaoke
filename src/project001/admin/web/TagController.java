package project001.admin.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import project001.admin.entity.Admin;
import project001.admin.entity.Banner;
import project001.admin.entity.Category;
import project001.admin.entity.Image;
import project001.admin.entity.Tag;
import project001.admin.entity.Type;
import project001.admin.entity.WebInfo;
import project001.admin.impl.BannerImpl;
import project001.admin.impl.CategoryImpl;
import project001.admin.impl.ImageImpl;
import project001.admin.impl.TagImpl;
import project001.admin.model.TagModel;
import project001.admin.model.TagParent;

@Controller
@RequestMapping("/admin/system/i/tag")
public class TagController {
	@RequestMapping("index")
	public String index(ModelMap modelMap){
		
		/*ArrayList<Map<String,Object>> tagList = new ArrayList<Map<String,Object>>();
		ArrayList<Type> type = TagImpl.getType();
		
		for(int i=0; i<type.size();i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("typeName", type.get(i).getTitle());
			map.put("tagList", TagImpl.getTagListV1(type.get(i).getId()));
			tagList.add(map);
		}
		modelMap.put("tagList", tagList);*/
		
		//modelMap.put("tagListV2", TagImpl.getTagListV2());
		
		
		
		return "/admin/tag/tag";
	}
	@RequestMapping(value="/tagList",method=RequestMethod.POST)
	public @ResponseBody String tagList() {
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("list", TagImpl.getTagListV2());
		JSONObject jsonobject = JSONObject.fromObject(m);
		return jsonobject.toString();
	}
	/*@RequestMapping(value="/sort",method=RequestMethod.POST)
	public String sort(int id,int sort,Model model) {
		Tag tag = new Tag();
		Tag c = TagImpl.getById(id, tag.getClass());
		c.setSort(sort);
		TagImpl.update(c);
		model.addAttribute("info","更新成功");
		model.addAttribute("code","1");
		model.addAttribute("backUrl","system/i/tag/index");

		return "redirect:/admin/msg";
	}*/
	@RequestMapping("add")
	public ModelAndView add(ModelMap modelMap,Tag tag,Model model){
		/*if(fid != 0){
			modelMap.put("type", type);
			modelMap.put("type_name", type_name);
			modelMap.put("subclass", subclass);
		}
		modelMap.put("fid", fid);*/
		/*modelMap.put("tag", tag);
		ArrayList<Type> itype = TagImpl.getType();
		modelMap.put("itype", itype);*/
		return new ModelAndView("/admin/tag/add","command",tag);
	}
	/*public ModelAndView add(ModelMap modelMap,int fid,String type,String type_name,String subclass,Model model){
		if(fid != 0){
			modelMap.put("type", type);
			modelMap.put("type_name", type_name);
			modelMap.put("subclass", subclass);
		}
		modelMap.put("fid", fid);
		ArrayList<Type> itype = TagImpl.getType();
		modelMap.put("itype", itype);
		return new ModelAndView("/admin/tag/add","command",new TagModel());
	}*/
	@RequestMapping("addTypeView")
	public ModelAndView addTypeView(){
		return new ModelAndView("/admin/tag/addtypeview","command",new Type());
	}
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public ModelAndView update(int id,ModelMap modelMap) {
		Tag tag = TagImpl.getById(id, Tag.class);
		/*TagModel tagModel = new TagModel();
		tagModel.setId(tag.getId());
		tagModel.setTitle(tag.getTitle());
		tagModel.setType(tag.getType());
		tagModel.setUrl(tag.getUrl());
		tagModel.setDescription(tag.getDescription());*/
		modelMap.put("src", tag.getSrc());

		
		return new ModelAndView("/admin/tag/update","command",tag);
	}
	@RequestMapping(value="/iupdate",method=RequestMethod.POST)
	public String iupdate(Tag tag,Model model,HttpServletRequest req,HttpSession httpSession) throws IOException {
		if(!((Admin)httpSession.getAttribute("admin")).getUser().equals("admin")) {
			model.addAttribute("info","您没有权限操作");
			model.addAttribute("code","0");
			model.addAttribute("backUrl","system/i/tag/index");
			return "redirect:/admin/msg";
		}
		Tag itag = TagImpl.getById(tag.getId(), Tag.class);
		if(TagImpl.checkExistForUpdateV1(tag.getId(),tag.getTitle(),tag.getFid()) >= 1){
			model.addAttribute("info","标签已经存在,无法修改!");
			model.addAttribute("code","0");
			model.addAttribute("backUrl","system/i/tag/index");
			return "redirect:/admin/msg";
		}else {
			itag.setTitle(tag.getTitle());
			itag.setUrl(tag.getUrl());
			itag.setDescription(tag.getDescription());
			itag.setSort(tag.getSort());
			itag.setKeyword(tag.getKeyword());
			itag.setColor(tag.getColor());
			/***图片上传***/
			MultipartFile mFile = tag.getImage();
			if(!mFile.isEmpty()) {
				String webRoot = req.getSession().getServletContext().getRealPath("/");
				String relativePath = "/static/upload/";
				Map<String,String> image = ImageImpl.upload(webRoot,relativePath, mFile, itag.getUuid());
				if(image.get("status").equals("0")) {
					model.addAttribute("info",image.get("info"));
					model.addAttribute("code","0");
					model.addAttribute("backUrl","system/i/tag/index");
					return "redirect:/admin/msg";
				}else {
					itag.setSrc(image.get("src"));
				}
			}
			/***图片上传 End***/
			TagImpl.update(itag);
			model.addAttribute("info","操作成功");
			model.addAttribute("code","1");
			model.addAttribute("backUrl","system/i/tag/index");
			return "redirect:/admin/msg";
		}

	}
	@RequestMapping("addType")
	public String addType(Type type,Model model){
		if(TagImpl.checkExistForType(type.getTitle()) >= 1){
			model.addAttribute("info","标签已经存在,无法重复添加!");
			model.addAttribute("code","0");
			model.addAttribute("backUrl","system/i/tag/index");
			return "redirect:/admin/msg";
		}else{
			Type itype = new Type();
			itype.setTitle(type.getTitle());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			itype.setCreateTime(simpleDateFormat.format(new Date()));
			TagImpl.add(itype);
			model.addAttribute("info","操作成功");
			model.addAttribute("code","1");
			model.addAttribute("backUrl","system/i/tag/index");
			return "redirect:/admin/msg";
		}
	}
	
	@RequestMapping(value="/executeAdd1",method=RequestMethod.POST)
	public String executeAdd1(ModelMap modelMap,Tag tag,Model model,HttpServletRequest req,HttpSession httpSession) throws IOException{
		if(!((Admin)httpSession.getAttribute("admin")).getUser().equals("admin")) {
			model.addAttribute("info","您没有权限操作");
			model.addAttribute("code","0");
			model.addAttribute("backUrl","system/i/tag/index");
			return "redirect:/admin/msg";
		}
		
		if(TagImpl.checkExistV1(tag.getTitle(),tag.getFid()) >= 1){
			model.addAttribute("info","标签已经存在,无法重复添加!");
			model.addAttribute("code","0");
			model.addAttribute("backUrl","system/i/tag/index");
			return "redirect:/admin/msg";
		}else{
			/***图片上传***/
			MultipartFile mFile = tag.getImage();
			String uuidImageToTag = UUID.randomUUID().toString().replaceAll("-", "");
			if(!mFile.isEmpty()) {
				//根目录
				String webRoot = req.getSession().getServletContext().getRealPath("/");
				String relativePath = "/static/upload/";
				Map<String,String> image = ImageImpl.upload(webRoot,relativePath, mFile, uuidImageToTag);
				if(image.get("status").equals("0")) {
					model.addAttribute("info",image.get("info"));
					model.addAttribute("code","0");
					model.addAttribute("backUrl","system/i/tag/index");
					return "redirect:/admin/msg";
				}else {
					tag.setSrc(image.get("src"));
				}
			}
			/***图片上传 End***/
			tag.setUuid(uuidImageToTag);

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			tag.setCreateTime(simpleDateFormat.format(new Date()));
			TagImpl.add(tag);
			model.addAttribute("info","操作成功");
			model.addAttribute("code","1");
			model.addAttribute("backUrl","system/i/tag/index");
			return "redirect:/admin/msg";
		}
	}
	
	/*@RequestMapping(value="/executeAdd1",method=RequestMethod.POST)
	public String executeAdd1(ModelMap modelMap,Tag tagModel,Model model,HttpServletRequest req) throws IOException{
		if(TagImpl.checkExistV1(tagModel.getTitle(),tagModel.getFid()) >= 1){
			model.addAttribute("info","标签已经存在,无法重复添加!");
			model.addAttribute("code","0");
			model.addAttribute("backUrl","system/i/tag/index");
			return "redirect:/admin/msg";
		}else{
			Tag tag = new Tag();
			*//***图片上传***//*
			MultipartFile mFile = tagModel.getImage();
			String uuidImageToTag = UUID.randomUUID().toString().replaceAll("-", "");
			if(!mFile.isEmpty()) {
				//根目录
				String webRoot = req.getSession().getServletContext().getRealPath("/");
				String relativePath = "/static/upload/";
				Map<String,String> image = ImageImpl.upload(webRoot,relativePath, mFile, uuidImageToTag);
				if(image.get("status").equals("0")) {
					model.addAttribute("info",image.get("info"));
					model.addAttribute("code","0");
					model.addAttribute("backUrl","system/i/tag/index");
					return "redirect:/admin/msg";
				}else {
					tag.setSrc(image.get("src"));
				}
			}
			*//***图片上传 End***//*
			tag.setUuid(uuidImageToTag);
			tag.setDescription(tagModel.getDescription());
			tag.setFid(tagModel.getFid());
			tag.setTitle(tagModel.getTitle());
			tag.setKeyword(tagModel.getKeyword());
			tag.setSort(tagModel.getSort());
			tag.setUrl(tagModel.getUrl());

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			tag.setCreateTime(simpleDateFormat.format(new Date()));
			TagImpl.add(tag);
			model.addAttribute("info","操作成功");
			model.addAttribute("code","1");
			model.addAttribute("backUrl","system/i/tag/index");
			return "redirect:/admin/msg";
		}
	}*/
	
	@RequestMapping(value="/executeAdd",method=RequestMethod.POST)
	public String executeAdd(ModelMap modelMap,Tag tagModel,Model model,HttpServletRequest req) throws IOException{
		if(TagImpl.checkExist(tagModel.getTitle(),tagModel.getType().split(":")[0]) >= 1){
			model.addAttribute("info","标签已经存在,无法重复添加!");
			model.addAttribute("code","0");
			model.addAttribute("backUrl","system/i/tag/index");
			return "redirect:/admin/msg";
		}else{
			Tag tag = new Tag();
			/***图片上传***/
			MultipartFile mFile = tagModel.getImage();
			String uuidImageToTag = UUID.randomUUID().toString().replaceAll("-", "");
			if(!mFile.isEmpty()) {
				//根目录
				String webRoot = req.getSession().getServletContext().getRealPath("/");
				String relativePath = "/static/upload/";
				Map<String,String> image = ImageImpl.upload(webRoot,relativePath, mFile, uuidImageToTag);
				if(image.get("status").equals("0")) {
					model.addAttribute("info",image.get("info"));
					model.addAttribute("code","0");
					model.addAttribute("backUrl","system/i/tag/index");
					return "redirect:/admin/msg";
				}else {
					tag.setSrc(image.get("src"));
				}
			}
			/***图片上传 End***/
			tag.setUuid(uuidImageToTag);
			tag.setDescription(tagModel.getDescription());
			tag.setFid(tagModel.getFid());
			tag.setTitle(tagModel.getTitle());
			tag.setKeyword(tagModel.getKeyword());
			tag.setSort(tagModel.getSort());
			tag.setUrl(tagModel.getUrl());
			tag.setType(tagModel.getType().split(":")[0]);
			tag.setType_name(tagModel.getType().split(":")[1]);
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			tag.setCreateTime(simpleDateFormat.format(new Date()));
			TagImpl.add(tag);
			model.addAttribute("info","操作成功");
			model.addAttribute("code","1");
			model.addAttribute("backUrl","system/i/tag/index");
			return "redirect:/admin/msg";
		}
		
	}
	
	public static void main(String[] args) {

	}
}

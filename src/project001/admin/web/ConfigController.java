package project001.admin.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import project001.admin.entity.Admin;
import project001.admin.entity.Image;
import project001.admin.entity.WebInfo;
import project001.admin.impl.HibernateConfig;
import project001.admin.impl.ImageImpl;
import project001.admin.impl.WebPropImpl;
import project001.admin.model.WebInfoModel;


@Controller
@RequestMapping("/admin/system/i/config")
public class ConfigController {
	private String getBasePath(HttpServletRequest req){
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String path = req.getContextPath();
		String basePath = scheme+"://"+serverName+":"+serverPort+path+"/";
		return basePath;
	}
	@RequestMapping("/index")
	public ModelAndView index(ModelMap modelMap,HttpServletRequest req) throws IOException{
		WebInfoModel webInfoModel = new WebInfoModel();
		Map<String,String> m = WebPropImpl.get(req.getSession().getServletContext().getRealPath("/")+"base.properties");
		webInfoModel.setTitle(m.get("title"));
		webInfoModel.setKeyword(m.get("keyword"));
		webInfoModel.setDescription(m.get("description"));
		webInfoModel.setCopyright(m.get("copyright"));
		
		modelMap.put("logoUrl", m.get("logoUrl"));
		return new ModelAndView("/admin/config/index","command",webInfoModel);
	}
	@RequestMapping(value="/submit",method = RequestMethod.POST)
	public String submit(Model model,WebInfoModel webInfoModel,HttpServletRequest req,HttpSession httpSession) throws IOException{
		if(!((Admin)httpSession.getAttribute("admin")).getUser().equals("admin")) {
			model.addAttribute("info","您没有权限操作");
			model.addAttribute("code","0");
			model.addAttribute("backUrl","system/i/config/index");
			return "redirect:/admin/msg";
		}
		String webRoot = req.getSession().getServletContext().getRealPath("/");
		Map<String,String> map = new HashMap<String,String>();
		map.put("title", webInfoModel.getTitle());
		map.put("keyword", webInfoModel.getKeyword());
		map.put("description", webInfoModel.getDescription());
		map.put("copyright", webInfoModel.getCopyright());
		
		MultipartFile mFile = webInfoModel.getLogo();
		if(!mFile.isEmpty()) {
			String relativePath = "/static/upload/";
			//String uuidImageToTag = UUID.randomUUID().toString().replaceAll("-", "");
			Map<String,String> image = ImageImpl.upload(webRoot,relativePath, mFile, "webLogo");
			if(image.get("status").equals("0")) {
				model.addAttribute("info",image.get("info"));
				model.addAttribute("code","0");
				model.addAttribute("backUrl","system/i/config/index");
				return "redirect:/admin/msg";
			}else {
				map.put("logoUrl", image.get("src"));
			}
			
		}
		
		WebPropImpl.save(map,webRoot+"base.properties");
		
		model.addAttribute("info","操作成功");
		model.addAttribute("code","1");
		model.addAttribute("backUrl","system/i/config/index");
		return "redirect:/admin/msg";
	}
	/*@RequestMapping(value="/uploadImage",method = RequestMethod.POST)
	public void uploadImage(@RequestParam("file") MultipartFile file,PrintWriter pw,ModelMap modelMap,HttpServletRequest req) throws IOException {
		String[] pointer = file.getOriginalFilename().split("\\.");
		int len = pointer.length;
		String type = null;
		if(len >1){
			type = pointer[len-1].toLowerCase();
		}
		String t=Thread.currentThread().getContextClassLoader().getResource("").getPath(); 
		int num=t.indexOf(".metadata");
		String path=t.substring(1,num).replace('/', '\\')+"project001\\WebContent\\static\\admin\\common\\upload\\";
		
		String fileName = "logo"+new Date().getTime()+"."+type;
		if(type.equals("png") || type.equals("jpg") || type.equals("gif") || type.equals("jpeg")){
			File f = new File(path+fileName);
			FileOutputStream out = new FileOutputStream(f);
			out.write(file.getBytes());
			out.flush();
			out.close();
			
			//FileUtils.writeByteArrayToFile(new File(path+fileName),file.getBytes());
			//modelMap.put("msg", "上传成功");
			pw.print("{\"code\":1,\"msg\":\"上传成功\",\"url\":\""+getBasePath(req)+"static/admin/common/upload/"+fileName+"\"}");
		}else{
			//modelMap.put("msg", "上传失败,仅支持png,jpg,gif,jpeg格式文件.");
			pw.print("{\"code\":-1,\"msg\":\"上传失败,仅支持png,jpg,gif,jpeg格式文件.\",\"url\":null}");
		}
		
		
		
	}*/
	
	public static void main(String[] args) throws IOException {
		
	}
}

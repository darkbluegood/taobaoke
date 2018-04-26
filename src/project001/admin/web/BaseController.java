package project001.admin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import project001.admin.entity.Admin;

@Controller
@RequestMapping("/admin/system/i")
public class BaseController {
	private String getBasePath(HttpServletRequest req){
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String path = req.getContextPath();
		String basePath = scheme+"://"+serverName+":"+serverPort+path+"/";
		return basePath;
	}
	@RequestMapping("/index")
	public String index(ModelMap modelMap,HttpServletRequest req,HttpSession httpSession){
		String basePath = getBasePath(req);
		modelMap.put("basePath",basePath);
		modelMap.put("adminPath", basePath+"admin/system/i/");
		modelMap.put("staticPath", basePath+"static/admin/common/");
		
		modelMap.put("admin", (Admin)httpSession.getAttribute("admin"));
		return "admin/index";
	}
	@RequestMapping("/home")
	public String home(){
		return "admin/home";
	}
}

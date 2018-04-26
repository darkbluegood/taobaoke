package project001.admin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project001.admin.impl.LoginImpl;

@Controller
@RequestMapping("/admin")
public class LoginController {
	private String getBasePath(HttpServletRequest req){
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String path = req.getContextPath();
		String basePath = scheme+"://"+serverName+":"+serverPort+path+"/";
		return basePath;
	}
	@RequestMapping("/login")
	public String index(ModelMap modelMap,HttpServletRequest req,HttpSession httpSession){
		String basePath = getBasePath(req);
		modelMap.put("basePath",basePath);
		modelMap.put("adminPath", basePath+"admin/");
		modelMap.put("staticPath", basePath+"static/admin/common");
		
		if(httpSession.getAttribute("admin") != null) {
			return "redirect:/admin";
		}
		return "admin/login";
	}
	@RequestMapping(value="/iLogin",method=RequestMethod.POST)
	public String iLogin(ModelMap modelmap,String userName,String password,HttpSession httpSession,Model model) {
		if(LoginImpl.checkUsername(userName)<=0) {
			model.addAttribute("info","用户名不存在");
			model.addAttribute("code","0");
			model.addAttribute("backUrl","login");
			return "redirect:/admin/msg";
		}else {
			if(LoginImpl.checkPassword(userName,password)<=0) {
				model.addAttribute("info","密码错误");
				model.addAttribute("code","0");
				model.addAttribute("backUrl","login");
				return "redirect:/admin/msg";
			}
		}
		httpSession.setAttribute("admin", LoginImpl.getUserInfo(userName,password));
		return "redirect:/admin/system/i/index";
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(ModelMap modelMap,HttpServletRequest req,HttpSession httpSession,Model model) {
		String basePath = getBasePath(req);
		if(httpSession.getAttribute("admin") != null) {
			httpSession.removeAttribute("admin");
			model.addAttribute("info","成功登出");
			model.addAttribute("code","1");
			model.addAttribute("backUrl","login");
			return "redirect:/admin/msg";
		}
		return "redirect:/admin/login";
	}
}




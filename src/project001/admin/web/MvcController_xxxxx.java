package project001.admin.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import project001.admin.entity.Admin;
import project001.admin.entity.ItemInfo;
import project001.admin.entity.WebInfo;
import project001.admin.impl.AdminDaoImpl;
import project001.admin.impl.ItemInfoImpl;
import project001.admin.impl.TbkItemInfoGetImpl;
import project001.admin.impl.webInfoImpl_xxxxx;
import project001.admin.model.ProductModel;
import project001.util.Md5;

@Controller
public class MvcController_xxxxx {
	
	private String getBasePath(HttpServletRequest req){
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String path = req.getContextPath();
		String basePath = scheme+"://"+serverName+":"+serverPort+path+"/";
		return basePath;
	}
	
	/*@RequestMapping("/admin")
	public String index(ModelMap modelMap,HttpServletRequest req){
		String basePath = getBasePath(req);
		modelMap.put("basePath",basePath);
		modelMap.put("adminPath", basePath+"admin/");
		modelMap.put("staticPath", basePath+"static/admin/common");
		System.out.println("sd");
		return "admin/index";
	}*/
	
	
	/*@RequestMapping("/admin/login")
	public String login(ModelMap modelMap,HttpServletRequest req){
		String basePath = getBasePath(req);
		modelMap.put("basePath",basePath);
		modelMap.put("adminPath", basePath+"admin/");
		modelMap.put("staticPath", basePath+"static/admin/common");
		return "admin/login";
	}*/
	@RequestMapping("/admin/website")
	public String website(ModelMap modelMap,HttpServletRequest req){
		String basePath = getBasePath(req);
		modelMap.put("basePath",basePath);
		modelMap.put("adminPath", basePath+"admin/");
		WebInfo Website = webInfoImpl_xxxxx.get(1);
		modelMap.put("website", Website);
		return "admin/website";
	}
	@RequestMapping("/admin/navi111")
	public String navi(ModelMap modelMap,HttpServletRequest req){
		String basePath = getBasePath(req);
		modelMap.put("basePath",basePath);
		modelMap.put("adminPath", basePath+"admin/");
		return "admin/navi";
	}
	@RequestMapping("/admin/navi_edit")
	public String navi_edit(ModelMap modelMap,HttpServletRequest req){
		String basePath = getBasePath(req);
		modelMap.put("basePath",basePath);
		modelMap.put("adminPath", basePath+"admin/");
		return "admin/navi_edit";
	}
	@RequestMapping(value="/admin/echo_msg",method=RequestMethod.GET)
	public String echo_msg(String contextPath,String msg,ModelMap modelMap,HttpServletRequest req) throws UnsupportedEncodingException{
		modelMap.put("contextPath",contextPath);
		modelMap.put("msg",new String(msg.getBytes("ISO-8859-1"),"utf-8"));
		String basePath = getBasePath(req);
		modelMap.put("basePath",basePath);
		modelMap.put("adminPath", basePath+"admin/");
		modelMap.put("staticPath", basePath+"static/admin/common");
		return "admin/echo_msg";
	}
	
}

package project001.tbk.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import project001.admin.entity.WebInfo;
import project001.tbAPI.TaobaoTbkItemGet;




@Controller
public class project001Controller_xxxxx {
	private String getBasePath(HttpServletRequest req){
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String path = req.getContextPath();
		String basePath = scheme+"://"+serverName+":"+serverPort+path+"/";
		return basePath;
	}
	
	
	@RequestMapping("/ddd")
	public String index(ModelMap modelMap,HttpServletRequest req) throws IOException{
		String basePath = getBasePath(req);
		modelMap.put("basePath", basePath);
		modelMap.put("staticPath", basePath+"static/tbk/");
		
		return "tbk/index";
	}
	@RequestMapping("/s")
	public String q(String id,ModelMap modelMap,HttpServletRequest req) throws IOException{
		
		String basePath = getBasePath(req);
		modelMap.put("basePath", basePath);
		modelMap.put("staticPath", basePath+"static/tbk/");
		
		
		return "tbk/q";
	}
	
	public static void main(String[] args) throws IOException{

		String fields = "num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick";
		String q1 = "Ůװ";
		String page_no = "1";
		String page_size = "30";
		String cat = "16,18";
		String getTbkItemData = TaobaoTbkItemGet.getTbkItem(fields,q1,page_no,page_size,cat);
		System.out.println(getTbkItemData);

	}
}





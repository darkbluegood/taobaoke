package project001.admin.web;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class MsgController {
	@RequestMapping(value="/msg",method=RequestMethod.GET)
	public String msg(ModelMap modelMap,String info,String code,String backUrl,Model model){
		//http.setCharacterEncoding("utf-8");
		//System.out.println(info);
		
		/*Map<String,Object> map = model.asMap();
		
		System.out.println(map.size());
		Iterator<Map.Entry<String,Object>> iter = map.entrySet().iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next().getKey());
		}*/
		modelMap.put("info", info);
		modelMap.put("code", code);
		modelMap.put("backUrl", backUrl);
		return "/admin/msg/msg";
	}
}

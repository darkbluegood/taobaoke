package project001.admin.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import project001.admin.entity.Admin;
import project001.admin.entity.ItemInfo;
import project001.admin.entity.Type;
import project001.admin.impl.CategoryImpl;
import project001.admin.impl.ItemInfoImpl;
import project001.admin.impl.TagImpl;
import project001.admin.impl.TbkItemInfoGetImpl;
import project001.admin.model.ProductModel;

@Controller
@RequestMapping("/admin/system/i/product")
public class ProductController {
	@RequestMapping("/list")
	public String list(ModelMap modelMap,int pageNo){
		final int pageSize = 10;
		int first = pageNo-1;
		ArrayList<ItemInfo> list = ItemInfoImpl.listSort(first*pageSize,pageSize);
		Long count = ItemInfoImpl.getCount();
		int pages = (int)Math.ceil((double)ItemInfoImpl.getCount() / pageSize);
		
		modelMap.put("list",list);     //列表
		modelMap.put("count",count);   //条数
		modelMap.put("pages",pages);   //页数
		modelMap.put("pageNo",pageNo); //当前页数

		return "/admin/product/list";
	}
	
	@RequestMapping(value="/productList",method=RequestMethod.POST)
	public @ResponseBody String productList(int pageNo) {
		final int pageSize = 10;
		int first = pageNo-1;
		
		Map<String,Object> m = new HashMap<String,Object>();
		
		ArrayList<ItemInfo> list = ItemInfoImpl.listSort(first*pageSize,pageSize);

		m.put("list", list);
		m.put("count",new Long(ItemInfoImpl.getCount()).intValue());
		JSONObject jsonobject = JSONObject.fromObject(m);
		//System.out.println(jsonobject1.toString());
		//JSONArray jsonobject=JSONArray.fromObject(list);
		return jsonobject.toString();
	}
	@RequestMapping(value="/productSearch",method=RequestMethod.POST)
	public @ResponseBody String productSearch(int pageNo,ItemInfo product){
		final int pageSize = 10;
		int first = pageNo-1;
		
		Map<String,Object> m = new HashMap<String,Object>();
		
		ArrayList<ItemInfo> list = ItemInfoImpl.listById(first*pageSize,pageSize,product);
		m.put("list", list);
		m.put("count",new Long(ItemInfoImpl.listByIdCount(product.getNum_iid())).intValue());
		JSONObject jsonobject = JSONObject.fromObject(m);
		
		return jsonobject.toString();
		
		
	}
	
	/*@RequestMapping(value="/pages",method=RequestMethod.POST)
	public @ResponseBody String pages() {
		Map<String,Integer> m = new HashMap<String,Integer>();
		m.put("pages", (int)Math.ceil((double)ItemInfoImpl.getCount() / 10));
		m.put("count",new Long(ItemInfoImpl.getCount()).intValue());
		JSONObject jsonobject = JSONObject.fromObject(m);
		return jsonobject.toString();
	}*/
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public void delete(int id,HttpServletResponse res) throws IOException{
		res.setContentType("text/html;charset=utf-8");
		//ItemInfoImpl.delete(ItemInfoImpl.getById(id));
		res.getWriter().print("{\"msg\":\"删除成功\"}");	 
	}
	
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public ModelAndView form(HttpSession httpSession){
		ModelAndView m = new ModelAndView("/admin/product/form","command",new ItemInfo());
		//分类
		//m.addObject("categoryList",CategoryImpl.getCategoryList());
		
		//标签
		/*ArrayList<Map<String,Object>> tagList = new ArrayList<Map<String,Object>>();
		ArrayList<Type> type = TagImpl.getType();
		for(int i=0; i<type.size();i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("typeName", type.get(i).getTitle());
			map.put("tagList", TagImpl.getTagListV1(type.get(i).getId()));
			tagList.add(map);
		}*/
		m.addObject("tagList", TagImpl.getTagListV2());
		m.addObject("author",httpSession.getAttribute("admin") == null ? "" : ((Admin)httpSession.getAttribute("admin")).getUser());

		return m; 
	}
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public ModelAndView update(int id) {
		//标签
		/*ArrayList<Map<String,Object>> tagList = new ArrayList<Map<String,Object>>();
		ArrayList<Type> type = TagImpl.getType();
		for(int i=0; i<type.size();i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("typeName", type.get(i).getTitle());
			map.put("tagList", TagImpl.getTagListV1(type.get(i).getId()));
			tagList.add(map);
		}*/
		ModelAndView m = new ModelAndView("/admin/product/update","command",ItemInfoImpl.getById(id));
		m.addObject("tagList", TagImpl.getTagListV2());
		return m;
	}
	
	@RequestMapping(value="/iupdate",method=RequestMethod.POST)
	public String iupdate(ItemInfo itemInfo,Model model,HttpSession httpSession) {
		if(!((Admin)httpSession.getAttribute("admin")).getUser().equals("admin")) {
			model.addAttribute("info","您没有权限操作");
			model.addAttribute("code","0");
			model.addAttribute("backUrl","system/i/product/list?pageNo=1");
			return "redirect:/admin/msg";
		}
		//System.out.println(itemInfo.getTag_f_id() +":"+ itemInfo.getTag_f_title() +" - "+itemInfo.getTag_id()+":"+itemInfo.getTag_title());
		if((TagImpl.checkExistForTag("id = "+itemInfo.getTag_f_id()+" and title = '"+itemInfo.getTag_f_title()+"'") == 1 && itemInfo.getTag_id() ==0 && itemInfo.getTag_title().trim().isEmpty()) 
				|| TagImpl.checkExistForTag("id = "+itemInfo.getTag_f_id()+" and title = '"+itemInfo.getTag_f_title()+"'"+" or id="+itemInfo.getTag_id()+ " and title='"+itemInfo.getTag_title()+"'") == 2
				) {
			ItemInfo item = ItemInfoImpl.getById(itemInfo.getId());
			item.setTag_f_id(itemInfo.getTag_f_id());
			item.setTag_id(itemInfo.getTag_id());
			item.setTag_f_title(itemInfo.getTag_f_title());
			item.setTag_title(itemInfo.getTag_title());
			item.setKeyword(itemInfo.getKeyword());
			item.setTitle(itemInfo.getTitle());
			item.setDescription(itemInfo.getDescription());
			item.setColor(itemInfo.getColor());
			ItemInfoImpl.update(item);
			model.addAttribute("info","更新成功");
			model.addAttribute("code","1");
			model.addAttribute("backUrl","system/i/product/list?pageNo=1");
			return "redirect:/admin/msg";
			
		}else {
			model.addAttribute("info","非法操作");
			model.addAttribute("code","0");
			model.addAttribute("backUrl","system/i/product/list?pageNo=1");
			return "redirect:/admin/msg";
		}
		
		
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String get(ModelMap modelMap,ItemInfo productModel,Model model,HttpSession httpSession){
		if(!((Admin)httpSession.getAttribute("admin")).getUser().equals("admin")) {
			model.addAttribute("info","您没有权限操作");
			model.addAttribute("code","0");
			model.addAttribute("backUrl","system/i/product/form");
			return "redirect:/admin/msg";
		}
		if(true || httpSession.getAttribute("admin") != null && ((Admin)httpSession.getAttribute("admin")).getUser().equals(productModel.getAuthor())) {
			if((TagImpl.checkExistForTag("id = "+productModel.getTag_f_id()+" and title = '"+productModel.getTag_f_title()+"'") == 1 && productModel.getTag_id() ==0 && productModel.getTag_title().trim().isEmpty()) 
					|| TagImpl.checkExistForTag("id = "+productModel.getTag_f_id()+" and title = '"+productModel.getTag_f_title()+"'"+" or id="+productModel.getTag_id()+ " and title='"+productModel.getTag_title()+"'") == 2
					) {
				productModel.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				productModel.setCreateTime(df.format(new Date()));
				ItemInfoImpl.add(productModel);
				
				model.addAttribute("info","发布成功");
				model.addAttribute("code","1");
				model.addAttribute("backUrl","system/i/product/form");
				return "redirect:/admin/msg";
			}else {
				model.addAttribute("info","非法操作");
				model.addAttribute("code","0");
				model.addAttribute("backUrl","system/i/product/form");
				return "redirect:/admin/msg";
			}
		}else {
			model.addAttribute("info","亲,出错拉!");
			model.addAttribute("code","0");
			model.addAttribute("backUrl","system/i/product/form");
			return "redirect:/admin/msg";
		}
	}

	@RequestMapping(value="/itemInfoData",method=RequestMethod.POST)
	public void getItemInfoData(HttpServletResponse res,String num_iid) throws IOException{
		res.setContentType("text/html;charset=utf-8");
		res.getWriter().print(TbkItemInfoGetImpl.getTbkItemInfoToJson(num_iid));
	}
	
	
	
	
	public static void main(String[] args){
		System.out.println(TagImpl.checkExistForTag("id = 93 and title = '女装'"));
	}
}

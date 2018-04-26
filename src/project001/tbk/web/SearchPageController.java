package project001.tbk.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project001.admin.entity.ItemInfo;
import project001.admin.entity.Tag;
import project001.admin.impl.ItemInfoImpl;
import project001.admin.impl.TagImpl;
import project001.admin.impl.WebPropImpl;

@Controller
public class SearchPageController {
	@RequestMapping(value="search",method=RequestMethod.GET)
	public String search(ModelMap modelMap,HttpServletRequest req,String keyword,String sort) throws IOException{
		//网站信息
		Map<String,String> m = WebPropImpl.get(req.getSession().getServletContext().getRealPath("/")+"base.properties");
		modelMap.put("webInfo", m);
		
		String sorts = (sort == null) ? "" : sort;
		//排序
		modelMap.put("sort", sorts);
		
		//关键字
		modelMap.put("keyword", keyword);
		
		//搜索列表
		if(keyword.length() <= 0) {
			ArrayList<ItemInfo> list = new ArrayList<ItemInfo>();
			modelMap.put("list",list);
		}else {
			ArrayList<ItemInfo> list = ItemInfoImpl.search(keyword,sorts);
			modelMap.put("list",list);
		}
		
		//猜您喜欢
		ArrayList<Tag> likeToYou = TagImpl.getLikeToYou();
		modelMap.put("likeToYou",likeToYou);
		
		
		List<Tag> tag = new TagImpl().getNavi();
		modelMap.put("navi", tag);
		
		List<Tag> searchKeyword = new TagImpl().getSearchKeyword();
		modelMap.put("searchKeyword", searchKeyword);
		return "tbk/search_page";
	}
}

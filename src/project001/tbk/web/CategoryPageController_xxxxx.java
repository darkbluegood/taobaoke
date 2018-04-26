package project001.tbk.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import project001.admin.entity.ItemInfo;
import project001.admin.entity.Tag;
import project001.admin.impl.ItemInfoImpl;
import project001.admin.impl.TagImpl;

@Controller
public class CategoryPageController_xxxxx {
	@RequestMapping("category")
	public String navi(ModelMap modelMap,String fid){
		List<Tag> navi = new TagImpl().getNavi();
		modelMap.put("navi", navi);
		List<ItemInfo> list = ItemInfoImpl.getCategoryList(fid);
		modelMap.put("list",list);
		
		return "tbk/list";
	}
}

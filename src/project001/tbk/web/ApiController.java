package project001.tbk.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import project001.admin.entity.ItemInfo;
import project001.admin.impl.ItemInfoImpl;
import project001.admin.model.ProductModel;
import project001.admin.model.TagModel;

@Controller
@RequestMapping("/api")
public class ApiController {
	@RequestMapping(value="/best",method=RequestMethod.POST)
	public void best(int page,int max,PrintWriter pw) {
		ArrayList<ItemInfo> list = ItemInfoImpl.getBest(page*40,max);
		ArrayList<ItemInfo> checkNextlist = ItemInfoImpl.getBest((page+1)*40,max);
		
		Map<String,Object> map = new HashMap<String,Object>();

		if(checkNextlist.size() > 0) {
			map.put("isMore", true);
		}else {
			map.put("isMore", false);
		}

		ArrayList<ProductModel> products = new ArrayList<ProductModel>();
		for(ItemInfo item : list) {
			ProductModel productModel = new ProductModel();
			productModel.setTitle(item.getTitle());
			productModel.setItem_url(item.getItem_url());
			productModel.setPict_url(item.getPict_url());
			productModel.setReserve_price(item.getReserve_price());
			productModel.setZk_final_price(item.getZk_final_price());
			productModel.setVolume(item.getVolume());
			products.add(productModel);
		}
		map.put("products", products);
		pw.print(JSONObject.fromObject(map));
	}
}

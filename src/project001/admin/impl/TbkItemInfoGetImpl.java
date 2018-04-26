package project001.admin.impl;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;









import project001.admin.model.ProductModel;
import project001.admin.model.ProductSmallImageModel;
import project001.tbAPI.TaobaoTbkItemInfoGet;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


public class TbkItemInfoGetImpl {
	
	public static ProductModel getTbkItemInfo(String num_iid) throws IOException{
		String fields = "num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,volume,seller_id,nick";
		String platform = "1";
		String taobaoTbkItemInfoGet = TaobaoTbkItemInfoGet.getTbkItem(fields, platform, num_iid);
		
		JSONObject taobaoJson = JSONObject.fromObject(taobaoTbkItemInfoGet);
		String tufigr = taobaoJson.getString("tbk_item_info_get_response");
		JSONObject taobaoJson1 = JSONObject.fromObject(tufigr);
		String resultsStr = taobaoJson1.getString("results");
		JSONObject taobaoJson2 = JSONObject.fromObject(resultsStr);
		String arrayStr = taobaoJson2.getString("n_tbk_item");
		JSONArray jsonArr = JSONArray.fromObject(arrayStr);
		ArrayList<ProductModel> list1 = (ArrayList<ProductModel>)JSONArray.toList(jsonArr,ProductModel.class);
		return list1.get(0);
	}
	
	public static String getTbkItemInfoToJson(String num_iid) throws IOException{
		String arrayStr = null;
		if(ItemInfoImpl.checkExist(num_iid) == 1){
			arrayStr = "[{\"code\":-1,\"msg\":\"该商品已经存在\"}]";
		}else{
			String fields = "num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,volume,seller_id,nick";
			String platform = "1";
			String taobaoTbkItemInfoGet = TaobaoTbkItemInfoGet.getTbkItem(fields, platform, num_iid);
			
			if(taobaoTbkItemInfoGet.indexOf("n_tbk_item") != -1){
				JSONObject taobaoJson = JSONObject.fromObject(taobaoTbkItemInfoGet);
				String tufigr = taobaoJson.getString("tbk_item_info_get_response");
				JSONObject taobaoJson1 = JSONObject.fromObject(tufigr);
				String resultsStr = taobaoJson1.getString("results");
				JSONObject taobaoJson2 = JSONObject.fromObject(resultsStr);
				arrayStr = taobaoJson2.getString("n_tbk_item");
			}else{
				arrayStr = "[]";
			}
		}
		
		return arrayStr;
	}
	
	public static void main(String[] arg) throws IOException{
		String i = TbkItemInfoGetImpl.getTbkItemInfoToJson("538412776132");
	}
}

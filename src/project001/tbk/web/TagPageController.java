package project001.tbk.web;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import project001.admin.entity.Category;
import project001.admin.entity.ItemInfo;
import project001.admin.entity.Tag;
import project001.admin.entity.WebInfo;
import project001.admin.impl.CategoryImpl;
import project001.admin.impl.ItemInfoImpl;
import project001.admin.impl.TagImpl;
import project001.admin.impl.WebPropImpl;


@Controller
public class TagPageController {
	@RequestMapping("tag")
	public String navi(ModelMap modelMap,Tag tag,HttpServletRequest req) throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		

		modelMap.put("tag",tag);
		modelMap.put("q",tag.getQ());
		
		//网站信息
		Map<String,String> m = WebPropImpl.get(req.getSession().getServletContext().getRealPath("/")+"base.properties");
		modelMap.put("webInfo", m);
		
		//网站导航
		List<Tag> navi = TagImpl.getNavi();
		modelMap.put("navi", navi);
		
		/*Tag iTag = TagImpl.getById(tag.getId(), Tag.class);
		iTag.setSort(tag.getSort());
		iTag.setFid(tag.getFid());
		iTag.setQ(tag.getQ());*/
		
		//分类
		List<Tag> subTag = TagImpl.sortSubList(tag);
		modelMap.put("subTag", subTag);
		
		//商品输出列表
		//List<ItemInfo> list = ItemInfoImpl.getListForPage(iTag);
		ArrayList<ItemInfo> list = ItemInfoImpl.getListForQ(tag);
		modelMap.put("list",list);
		
		//搜索关键字
		List<Tag> searchKeyword = TagImpl.getSearchKeyword();
		modelMap.put("searchKeyword", searchKeyword);
		
		return "tbk/page";
	}
	public static void main(String[] arg) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		/*System.out.println("11");
		String a = "aad";
		System.out.println(a.substring(0, 1).toUpperCase()+a.substring(1));*/
		/*Class tag = Class.forName("project001.admin.entity.Tag");
		Class[] parameterTypes = new Class[1];
		parameterTypes[0] = String.class;
		Method method = tag.getMethod("setTitle", parameterTypes);
		Object[] args = new Object[1];
		String argments = "hello";
		args[0] = argments;
		
		
		
		method.invoke(tag.newInstance(), args);*/
		/*Tag tag = new Tag();

		Class<?> clazz = tag.getClass();
		Method d = clazz.getMethod("setId",int.class);
		d.invoke(tag,0);
		
		Method m = clazz.getMethod("getId");
		System.out.println((int)m.invoke(tag) != 0);*/
		
		String a = "fid=75&title=女装&";
		System.out.println(a.substring(0, a.length()-1));
		
	}
}

package project001.tbk.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import project001.admin.entity.Banner;
import project001.admin.entity.Category;
import project001.admin.entity.Image;
import project001.admin.entity.ItemInfo;
import project001.admin.entity.Tag;
import project001.admin.entity.WebInfo;
import project001.admin.impl.BannerImpl;
import project001.admin.impl.CategoryImpl;
import project001.admin.impl.HibernateConfig;
import project001.admin.impl.ImageImpl;
import project001.admin.impl.ItemInfoImpl;
import project001.admin.impl.TagImpl;
import project001.admin.impl.WebPropImpl;
import project001.admin.model.BannerModel;
import project001.admin.model.TagModel;
import project001.admin.model.TagParent;


@Controller
public class IndexController extends HibernateConfig {
	private String getBasePath(HttpServletRequest req){
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String path = req.getContextPath();
		String basePath = scheme+"://"+serverName+":"+serverPort+path+"/";
		return basePath;
	}
	@RequestMapping("/")
	public String index(ModelMap modelMap,HttpServletRequest req) throws IOException{
		//网站信息
		Map<String,String> m = WebPropImpl.get(req.getSession().getServletContext().getRealPath("/")+"base.properties");
		modelMap.put("webInfo", m);
		
		//分类
		ArrayList<Tag> aCategory = new ArrayList<Tag>();
		ArrayList<ArrayList<Tag>> bCategory = new ArrayList<ArrayList<Tag>>();

		ArrayList<Tag> category = TagImpl.getCategoriesSubclass(186);

		for(int i=1; i<=category.size();i++) {
				aCategory.add(category.get(i-1));
			if(i % 4 == 0 || i == category.size()) {
				bCategory.add(aCategory);
				aCategory = new ArrayList<Tag>();
			}
		}
		modelMap.put("category", bCategory);
		
		//分类弹出框
		ArrayList<ArrayList<TagParent<Tag>>> popupList = new ArrayList<ArrayList<TagParent<Tag>>>();
		for(ArrayList<Tag> a : bCategory) {
			ArrayList<TagParent<Tag>> popupContentList = new ArrayList<TagParent<Tag>>();
			for(Tag c : a) {
					TagParent<Tag> tagParent = new TagParent<Tag>();
					ArrayList<Tag> subclassCategory = TagImpl.getCategoriesSubclass(c.getId());
					tagParent.setId(c.getId());
					tagParent.setFid(c.getFid());
					tagParent.setTitle(c.getTitle());
					tagParent.setSubset(subclassCategory);
					popupContentList.add(tagParent);
			}
			popupList.add(popupContentList);
		}
		modelMap.put("popupList", popupList);
		
		
		
		/*ArrayList<Category> aCategory = new ArrayList<Category>();
		ArrayList<ArrayList<Category>> bCategory = new ArrayList<ArrayList<Category>>();

		ArrayList<Category> category = CategoryImpl.getCategoryParent();

		for(int i=1; i<=category.size();i++) {
				aCategory.add(category.get(i-1));
			if(i % 4 == 0 || i == category.size()) {
				bCategory.add(aCategory);
				aCategory = new ArrayList<Category>();
			}
		}
		modelMap.put("category", bCategory);*/
		
		//分类弹出框
		/*ArrayList<ArrayList<TagParent<Category>>> popupList = new ArrayList<ArrayList<TagParent<Category>>>();
		for(ArrayList<Category> a : bCategory) {
			ArrayList<TagParent<Category>> popupContentList = new ArrayList<TagParent<Category>>();
			for(Category c : a) {
					TagParent<Category> tagParent = new TagParent<Category>();
					ArrayList<Category> subclassCategory = CategoryImpl.getCategorysub(String.valueOf(c.getId()));
					tagParent.setId(c.getId());
					tagParent.setFid(c.getFid());
					tagParent.setTitle(c.getTitle());
					tagParent.setSubset(subclassCategory);
					popupContentList.add(tagParent);
			}
			popupList.add(popupContentList);
		}
		modelMap.put("popupList", popupList);*/
		
		//幻灯片
		ArrayList<Banner> banner = BannerImpl.getList();
		modelMap.put("banner", banner);
		
		//精选好货-分类
		ArrayList<Tag> choiceTag = TagImpl.getCategoriesSubclass(95);
		modelMap.put("choiceTag", choiceTag);
		
		//导航
		List<Tag> navi = TagImpl.getNavi();
		modelMap.put("navi", navi);
		
		//菜单
		ArrayList<Tag> menu = TagImpl.getMenu();
		modelMap.put("menu", menu);
		
		//右上图片标签
		Tag imageTag = TagImpl.getById(176, Tag.class);
		modelMap.put("imageModel", imageTag);
		
		
		
		//关键字
		modelMap.put("keyword", "酒");
		
		//搜索关键字
		List<Tag> searchKeyword = TagImpl.getSearchKeyword();
		modelMap.put("searchKeyword", searchKeyword);
		
		/*//热卖
		ArrayList<Tag> hot = TagImpl.getHot();
		modelMap.put("hot",hot);*/
		//有好货
		ArrayList<ItemInfo> hot = ItemInfoImpl.getYouHaoHuo();
		modelMap.put("hot",hot);
		return "tbk/index";
	}
	
	public static void main(String[] main) throws IOException{
		System.out.println("hello 11");

			
		
	}
}

package project001.admin.web;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import project001.admin.entity.Category;
import project001.admin.entity.Tag;
import project001.admin.impl.CategoryImpl;
import project001.admin.impl.TagImpl;

@Controller
@RequestMapping("/admin/system/i/category")
public class CategoryController {
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView m = new ModelAndView("/admin/category/category");
		m.addObject("categoryList", CategoryImpl.getCategoryList());
		return m;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add(int id,String fid_title){
		ModelAndView m = new ModelAndView("/admin/category/add","command",new Category());
		m.addObject("fid",id);
		m.addObject("fid_title",fid_title);
		return m;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public ModelAndView update(int id) {
		Category category = CategoryImpl.getById(id, Category.class);
		ModelAndView m = new ModelAndView("/admin/category/update","command",category);
		return m;
	}
	
	@RequestMapping(value="/iupdate",method=RequestMethod.POST)
	public String iupdate(Category updateCategory,Model model) {
		System.out.println(CategoryImpl.checkExistForUpdate(updateCategory.getId(),updateCategory.getFid(),updateCategory.getTitle()));
		if(CategoryImpl.checkExistForUpdate(updateCategory.getId(),updateCategory.getFid(),updateCategory.getTitle()) >= 1) {
			model.addAttribute("info","标签已经存在,无法修改!");
			model.addAttribute("code","0");
			model.addAttribute("backUrl","system/i/category/index");
			return "redirect:/admin/msg";
		}else {
			Category category = CategoryImpl.getById(updateCategory.getId(), Category.class);
			category.setTitle(updateCategory.getTitle());
			category.setSort(updateCategory.getSort());
			category.setKeyword(updateCategory.getKeyword());
			CategoryImpl.update(category);
			model.addAttribute("info","操作成功");
			model.addAttribute("code","1");
			model.addAttribute("backUrl","system/i/category/index");
			return "redirect:/admin/msg";
		}
	}
	
	/*@RequestMapping(value="/sort",method=RequestMethod.POST)
	public String sort(int id,int sort,Model model) {
		Category category = new Category();
		Category c = CategoryImpl.getById(id, category.getClass());
		c.setSort(sort);
		CategoryImpl.update(c);
		model.addAttribute("info","更新成功");
		model.addAttribute("code","1");
		model.addAttribute("backUrl","system/i/category/index");
		return "redirect:/admin/msg";
	}*/
	
	@RequestMapping(value="/executeAdd",method=RequestMethod.POST)
	public String executeAdd(ModelMap modelMap,Category addCategory,PrintWriter pw,Model model){
		if(CategoryImpl.checkExist(addCategory.getTitle(),addCategory.getFid()) == 1){
			model.addAttribute("info","分类已经存在,无法重复添加!");
			model.addAttribute("code","0");
			model.addAttribute("backUrl","system/i/category/index");
			return "redirect:/admin/msg";
		}else{
			Category category = new Category();
			category.setFid(addCategory.getFid());
			category.setTitle(addCategory.getTitle());
			category.setFid_title(addCategory.getFid_title());
			category.setSort(addCategory.getSort());
			category.setKeyword(addCategory.getKeyword());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			category.setCreateTime(simpleDateFormat.format(new Date()));
			CategoryImpl.add(category);
			model.addAttribute("info","操作成功");
			model.addAttribute("code","1");
			model.addAttribute("backUrl","system/i/category/index");
			return "redirect:/admin/msg";
		}
	}
}


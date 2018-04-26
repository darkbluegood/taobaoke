package project001.test;

import java.util.ArrayList;
import java.util.List;

import project001.admin.entity.Category;
import project001.admin.impl.CategoryImpl;
import project001.admin.model.TagParent;

public class Student {
	private int age;
	private String name;
	private String sex;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public static void main(String[] arg) {
		String[] nums = {"2","4","10"};
		ArrayList<TagParent> list = new ArrayList();
		for(int i=0;i<nums.length;i++) {
			Category category = CategoryImpl.getCategoryById(nums[i]);
			TagParent<Category> categoryParnet = new TagParent<Category>();
			categoryParnet.setFid(category.getFid());
			categoryParnet.setTitle(category.getTitle());
			categoryParnet.setId(category.getId());
			list.add(categoryParnet);
			
			List<Category> subclass = CategoryImpl.getCategorysub(String.valueOf(categoryParnet.getFid()));
			//categoryParnet.getSubset(subclass);
		}
		
		for(TagParent c : list) {
			System.out.println(c.getTitle());
		}
	}
	
}

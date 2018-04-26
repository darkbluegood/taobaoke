package project001.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import project001.admin.entity.Category;
import project001.admin.model.TagParent;


public class CategoryImpl extends HibernateConfig {
	
	
	private static List<Category> get(){
		Session session = CategoryImpl.getSession();
		List<Category> list = session.createQuery("from Category").list();
		return list;
	}
	
	public static ArrayList<Category> getCategoryParent(){
		Session session = CategoryImpl.getSession();
		ArrayList<Category> list = (ArrayList)session.createQuery("from Category where fid=0 order by sort asc").list();
		return list;
	}
	
	public static Category getCategoryById(String id){
		Session session = CategoryImpl.getSession();
		Query query = session.createQuery("from Category where fid=0 and id=?");
		query.setString(0, id);
		return (Category)query.getSingleResult();
	}
	
	public static ArrayList<Category> getCategorysub(String fid){
		Session session = CategoryImpl.getSession();
		Query query = session.createQuery("from Category where fid=? order by sort asc");
		query.setString(0, fid);
		ArrayList<Category> list = (ArrayList)query.list();
		
		return list;
	}
	
	public static Long checkExist(String title,int fid){
		Session session = CategoryImpl.getSession();
		Query query = session.createQuery("select count(*) from Category where title = ? and fid = ?");
		query.setString(0, title);
		query.setInteger(1, fid);
		return (Long)query.uniqueResult();
	}
	public static Long checkExistForUpdate(int id,int fid,String title) {
		Session session = CategoryImpl.getSession();
		Query query = session.createQuery("select count(*) from Category where title=? and fid = ? and id not in (?)");
		query.setString(0,title);
		query.setInteger(1, fid);
		query.setInteger(2, id);
		return (Long)query.uniqueResult();
	}
	
	public static ArrayList<TagParent<Category>> getCategoryList(){
		ArrayList<Category> list = CategoryImpl.getCategoryParent();
		
		ArrayList<TagParent<Category>> arraylist = new ArrayList<TagParent<Category>>();

		for(Category category : list){
			TagParent<Category> categoryParnet = new TagParent<Category>();
			categoryParnet.setId(category.getId());
			categoryParnet.setFid(category.getFid());
			categoryParnet.setTitle(category.getTitle());
			categoryParnet.setFid_title(category.getFid_title());
			categoryParnet.setSort(category.getSort());
			categoryParnet.setSubset(CategoryImpl.getCategorysub(String.valueOf(categoryParnet.getId())));
			arraylist.add(categoryParnet);
		}
		
		/*for(int i=0; i<list.size();i++){
			Category category = (Category)list.get(i);
			
			if(category.getFid() == 0){
				TagParent<Category> categoryParnet = new TagParent<Category>();
				categoryParnet.setId(category.getId());
				categoryParnet.setFid(category.getFid());
				categoryParnet.setTitle(category.getTitle());
				categoryParnet.setFid_title(category.getFid_title());
				arraylist.add(categoryParnet);
			}
		}
		
		for(TagParent<Category> cp : arraylist){
			ArrayList<Category> categorySubsetArr = new ArrayList<Category>();
			for(int i=0; i<list.size();i++){
				Category category = (Category)list.get(i);
				if(category.getFid() == cp.getId()){
					categorySubsetArr.add(category);
				}
			}
			cp.setSubset(categorySubsetArr);
		}*/
		return arraylist;
	}
	
	public static void main(String[] args){
		/*for(int i=0;i<10;i++){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(uuid);
		}*/
		
		/*for(CategoryParent cp : CategoryImpl.getCategoryList()){
			System.out.println(cp.getName()+":");
			for(Category subset : cp.getSubset()){
				System.out.println("---"+subset.getName());
			}
		}*/

		/*Category category = new Category();
		category.setFid(1);
		category.setName("夹克");
		CategoryImpl.add(category);*/
	}
}

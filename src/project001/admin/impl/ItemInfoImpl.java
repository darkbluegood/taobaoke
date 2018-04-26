package project001.admin.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javassist.bytecode.Descriptor.Iterator;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import project001.admin.entity.ItemInfo;
import project001.admin.entity.Tag;
import project001.util.Base;

public class ItemInfoImpl extends HibernateConfig {
	public static List<ItemInfo> list(){
		Session session = ItemInfoImpl.getSession();
		List<ItemInfo> list = session.createQuery("from ItemInfo").list();
		return list;
	}
	public static List<ItemInfo> list(int first){
		Session session = ItemInfoImpl.getSession();
		Query query = session.createQuery("from ItemInfo");
		query.setFirstResult(first);
		return query.list();
	}
	public static List<ItemInfo> list(int first,int size){
		Session session = ItemInfoImpl.getSession();
		Query query = session.createQuery("from ItemInfo");
		query.setFirstResult(first);
		query.setMaxResults(size);
		return query.list();
	}
	public static ArrayList<ItemInfo> listSort(int first,int size){
		Session session = ItemInfoImpl.getSession();
		Query query = session.createQuery("from ItemInfo order by id desc");
		query.setFirstResult(first);
		query.setMaxResults(size);
		return (ArrayList<ItemInfo>)query.list();
	}
	public static ArrayList<ItemInfo> listById(int first,int size,ItemInfo product){
		Session session = ItemInfoImpl.getSession();
		Query query = session.createQuery("from ItemInfo where num_iid = ? order by id desc");
		query.setFirstResult(first);
		query.setMaxResults(size);
		query.setLong(0, product.getNum_iid());
		return (ArrayList<ItemInfo>)query.list();
	}
	public static Long listByIdCount(long id){
		Session session = ItemInfoImpl.getSession();
		Query query = session.createQuery("select count(*) from ItemInfo where num_iid = ?");
		query.setLong(0, id);
		Long count = (Long)query.uniqueResult();
		return count;
	}
	public static List<ItemInfo> getSortList(){
		Session session = ItemInfoImpl.getSession();
		Query query = session.createQuery("from ItemInfo order by id desc");
		return query.list();
	}
	public static Long getCount(){
		Session session = ItemInfoImpl.getSession();
		Query query = session.createQuery("select count(*) from ItemInfo");
		Long count = (Long)query.uniqueResult();
		return count;
	}
	public static ArrayList<ItemInfo> getBest(int first,int max){
		Session session = ItemInfoImpl.getSession();
		Query query = session.createQuery("from ItemInfo where tag_f_id=69 order by id desc");
		query.setFirstResult(first);
		query.setMaxResults(max);
		ArrayList<ItemInfo> list = (ArrayList<ItemInfo>)query.list();
		return list;
	}
	/*public static Long checkBestMore(int first,int max){
		Session session = ItemInfoImpl.getSession();
		Query query = session.createQuery("select count(*) from ItemInfo where tag_f_id=69");
		query.setFirstResult(first);
		query.setMaxResults(max);
		return (Long)query.uniqueResult();
	}*/
	public static Long checkExist(String id){
		Session session = ItemInfoImpl.getSession();
		Query query = session.createQuery("select count(*) from ItemInfo where num_iid = ?");
		query.setString(0, id);
		Long count = (Long)query.uniqueResult();
		return count;
	}
	public static void get(){
		Session session = ItemInfoImpl.getSession();
		List<ItemInfo> list = session.createQuery("from ItemInfo").list();
		ItemInfo itemInfo = list.get(0);
	}
	public static ItemInfo getById(int id){
		Session session = ItemInfoImpl.getSession();
		return (ItemInfo)session.get(ItemInfo.class, new Integer(id));
	}
	
	
	public static List<ItemInfo> getSubTagList(String fid,String cid){
		Session session = ItemInfoImpl.getSession();
		Query query = session.createQuery("from ItemInfo where tag_f_id=? and tag_c_id=? order by id desc");
		query.setString(0, fid);
		query.setString(1, cid);
		return query.list();
	}
	public static List<ItemInfo> getTagList(String id){
		Session session = ItemInfoImpl.getSession();
		Query query = session.createQuery("from ItemInfo where tag_f_id=? order by id desc");
		query.setString(0, id);
		return query.list();
	}
	public static List<ItemInfo> getSubCategoryList(String fid,String cid){
		Session session = ItemInfoImpl.getSession();
		Query query = session.createQuery("from ItemInfo where category_f_id=? and category_c_id=? order by id desc");
		query.setString(0, fid);
		query.setString(1, cid);
		return query.list();
	}
	public static List<ItemInfo> getCategoryList(String id){
		Session session = ItemInfoImpl.getSession();
		Query query = session.createQuery("from ItemInfo where category_f_id=? order by id desc");
		query.setString(0, id);
		return query.list();
	}
	
	public static List<ItemInfo> getSortVolume(){
		Session session = ItemInfoImpl.getSession();
		Query query = session.createQuery("from ItemInfo s order by s.volume");
		return query.list();
	}
	
	//有好货
	public static ArrayList<ItemInfo> getYouHaoHuo(){
		Session session = ItemInfoImpl.getSession();
		Query query = session.createQuery("from ItemInfo where tag_f_id=170 order by id desc");
		return (ArrayList<ItemInfo>)query.list();
	}
	/*
	from ItemInfo where tag_f_id = 75 order by id desc
	from ItemInfo where tag_f_id = 75 and (title like '%好%') order by id desc
	from ItemInfo where tag_f_id = 75 and (title like '%好%' or title like '%是%') order by id desc
	from ItemInfo where tag_f_id = 75 and (tag_id = 86) order by id desc
	from ItemInfo where tag_f_id = 75 and (tag_id = 86 or title like '%好%') order by id desc
	from ItemInfo where tag_f_id = 75 and (tag_id = 86 or title like '%好%' or title like '%是%' ) order by id desc
	*/
	
	public static ArrayList<ItemInfo> getListForQ(Tag tag){
		Session session = ItemInfoImpl.getSession();
		
		String sql = null;
		//System.out.println("111");
		switch(tag.getSort()) {
			//销量
			case 1:
				sql = "from ItemInfo where tag_f_id = "+tag.getFid()+" and title like '%"+tag.getQ()+"%' or tag_f_id = "+tag.getFid()+" and keyword like '%"+tag.getQ()+"%' order by volume desc";
			break;
			//价格从低到高
			case 2:
				sql = "from ItemInfo where tag_f_id = "+tag.getFid()+" and title like '%"+tag.getQ()+"%' or tag_f_id = "+tag.getFid()+" and keyword like '%"+tag.getQ()+"%' order by zk_final_price ASC";
			break;
			////价格从高到低
			case 3:
				sql = "from ItemInfo where tag_f_id = "+tag.getFid()+" and title like '%"+tag.getQ()+"%' or tag_f_id = "+tag.getFid()+" and keyword like '%"+tag.getQ()+"%' order by zk_final_price desc";
			break;
			//综合
			default:
				sql = "from ItemInfo where tag_f_id = "+tag.getFid()+" and title like '%"+tag.getQ()+"%' or tag_f_id = "+tag.getFid()+" and keyword like '%"+tag.getQ()+"%' order by id desc";
			break;
		}
		
		//String sql = "from ItemInfo where tag_f_id = "+tag.getFid()+" and title like '%"+tag.getQ()+"%' or keyword like '%"+tag.getQ();
		
		Query query = session.createQuery(sql);
		/*query.setInteger(0, tag.getFid());
		query.setString(1, "%"+tag.getQ()+"%");
		query.setString(2, "%"+tag.getQ()+"%");*/
		
		/*if(keyword != null && keyword.length() != 0 && !keyword.equals(",")) {

			if(keyword.indexOf(",") != -1 && keyword.length() > 0) {
				
				String[] keywordArr = keyword.split(",");
				for(int i=0; i<keywordArr.length; i++) {
					if(!keywordArr[i].trim().isEmpty()) {
						
					}
				}
			}
	
			if(keyword.indexOf(",") == -1 && keyword.length() > 0) {
				if(tag.getId() != tag.getFid()) {
					sql.append(" or title like '%"+keyword+"%'");
					
				}else {
					sql.append(" and (title like '%"+keyword+"%'");
				}
				sql.append(")");
			}
		}*/
		
		return (ArrayList<ItemInfo>)query.list();
	}
	
	public static List<ItemInfo> getListForPage(Tag tag){
		Session session = ItemInfoImpl.getSession();
		String keyword = tag.getKeyword();
		StringBuilder sql = new StringBuilder();
		
		sql.append("from ItemInfo where tag_f_id = ");
		sql.append(tag.getFid());
		
		
		if(tag.getId() != tag.getFid()) {
			sql.append(" and (tag_id=");
			sql.append(tag.getId());
		}
		
		int flag = 0;
		
		if(keyword != null && keyword.length() != 0 && !keyword.equals(",")) {

			if(keyword.indexOf(",") != -1 && keyword.length() > 0) {
				
				String[] keywordArr = keyword.split(",");
				for(int i=0; i<keywordArr.length; i++) {
					if(!keywordArr[i].trim().isEmpty()) {
						if(tag.getId() != tag.getFid()) {
							sql.append(" or title like '%"+keywordArr[i]+"%'");
						}else {
							if(flag == 0) {
								sql.append(" and (title like '%"+keywordArr[i]+"%'");
								flag = 1;
							}else {
								sql.append(" or title like '%"+keywordArr[i]+"%'");
							}
						}
					}
				}
				if(flag == 1 || tag.getId() != tag.getFid()) {
					sql.append(")");
				}
			}
	
			if(keyword.indexOf(",") == -1 && keyword.length() > 0) {
				if(tag.getId() != tag.getFid()) {
					sql.append(" or title like '%"+keyword+"%'");
					
				}else {
					sql.append(" and (title like '%"+keyword+"%'");
				}
				sql.append(")");
			}
		}else {
			if(tag.getId() != tag.getFid()) {
				sql.append(")");
			}
		}
		
		System.out.println(sql+" order by id desc");
		Query query = null;
		switch(tag.getSort()) {
			case 1:  //销量
				query = session.createQuery(sql+" order by volume desc");
				break;
			case 2:  //价格从低到高
				query = session.createQuery(sql+" order by zk_final_price ASC");
				break;
			case 3:  //价格从高到低
				query = session.createQuery(sql+" order by zk_final_price desc");
				break;
			default: //综合
				//query = session.createQuery("from ItemInfo where tag_f_id = ? "+likeSql+" order by id desc");
				query = session.createQuery(sql+" order by id desc");
				break;
		}
		return query.list();
	}
	public static ArrayList<ItemInfo> search(String keyword,String sort){
		Session session = ItemInfoImpl.getSession();
		Query query = null;
		switch(sort) {
			case "sale":
				query = session.createQuery("from ItemInfo s where s.title like ? order by volume desc");
				break;
			case "price_down":
				query = session.createQuery("from ItemInfo s where s.title like ? order by zk_final_price ASC");
				break;
			case "price_up":
				query = session.createQuery("from ItemInfo s where s.title like ? order by zk_final_price desc");
				break;
				default:
					query = session.createQuery("from ItemInfo s where s.title like ? order by id desc");
		}
		
		query.setString(0, "%"+keyword+"%");
		return (ArrayList<ItemInfo>)query.list();
	}
	public static void main(String[] args){
		String aa = "";
		System.out.println(aa.trim().isEmpty());
		
		/*String tag = "v,a";
		if(tag.equals(",")) {
			System.out.println("null");
		}else {
			if(tag.indexOf(",") != -1) {
				String[] tagArr = tag.split(",");
				for(int i=0; i<tagArr.length; i++) {
					if(!tagArr[i].trim().isEmpty()) {
						System.out.println(tagArr[i]);
					}
					
				}
			}else {
				System.out.println(tag);
			}
		}*/
		
		
		/*List<ItemInfo> l = ItemInfoImpl.getListForPage("10","11","tag","");
		for(int i=0; i<l.size();i++){
			ItemInfo itemInfo = l.get(i);
			System.out.println(itemInfo.getTitle());
		}*/

		

		//System.out.println(ItemInfoImpl.getCount()/5);
		//System.out.println((int)Math.ceil((double)ItemInfoImpl.getCount()/5));
		//System.out.println((int)Math.ceil(3.4));
		//ItemInfoImpl.delete(ItemInfoImpl.getById(2));
		
	}
}




package project001.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import project001.admin.entity.Category;
import project001.admin.entity.Image;
import project001.admin.entity.Tag;
import project001.admin.entity.Type;
import project001.admin.model.TagParent;

public class TagImpl extends HibernateConfig {
	public static List<Tag> list(){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("from Tag");
		List<Tag> list = query.list();
		return list;
	}
	public static ArrayList<Tag> getHot(){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("from Tag where type = ? order by sort asc");
		query.setString(0, "13");
		ArrayList<Tag> list = (ArrayList<Tag>)query.list();
		return list;
	}
	public static List<Tag> getSearchKeyword(){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("from Tag where type = ? order by sort asc");
		query.setString(0, "3");
		List<Tag> list = query.list();
		return list;
	}
	public static ArrayList<Tag> getLikeToYou(){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("from Tag where type = 15 order by sort asc");
		ArrayList<Tag> list = (ArrayList<Tag>)query.list();
		return list;
	}
	public static ArrayList<Tag> getCategories(){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("from Tag where type = 16 and fid = 0 order by sort asc");
		ArrayList<Tag> list = (ArrayList<Tag>)query.list();
		return list;
	}
	public static ArrayList<Tag> getCategoriesSubclass(int fid){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("from Tag where fid=? order by sort asc");
		query.setInteger(0, fid);
		ArrayList<Tag> list = (ArrayList)query.list();
		return list;
	}
	public static List<Tag> getNavi(){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("from Tag where type = ? and fid = ? order by sort asc");
		query.setString(0, "1");
		query.setString(1, "0");
		List<Tag> list = query.list();
		return list;
	}
	public static List<Tag> sortSubList(Tag tag){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("from Tag where fid = ? order by sort asc");
		query.setInteger(0, tag.getFid());
		List<Tag> list = query.list();
		return list;
	}
	public static List getSubList(String fid){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("from Tag where fid = ? order by sort asc");
		query.setString(0, fid);
		List list = query.list();
		return list;
	}
	public static ArrayList<Tag> getSubclassTag(String fid){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("from Tag where fid = ? order by sort desc");
		query.setString(0, fid);
		ArrayList list = (ArrayList)query.list();
		return list;
	}
	public static ArrayList<Tag> getFidTag(){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("from Tag where fid = 0 order by sort asc");
		ArrayList list = (ArrayList)query.list();
		return list;
	}
	public static Long checkExist(String title,String type){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("select count(*) from Tag where title = ? and type=?");
		query.setString(0, title);
		query.setString(1, type);
		return (Long)query.uniqueResult();
	}
	public static Long checkExistV1(String title,int fid){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("select count(*) from Tag where title = ? and fid=?");
		query.setString(0, title);
		query.setInteger(1, fid);
		return (Long)query.uniqueResult();
	}
	public static Long checkExistForUpdate(int id,String title,String type) {
		Session session = TagImpl.getSession();
		Query query = session.createQuery("select count(*) from Tag where title=? and type=? and id not in (?)");
		query.setString(0,title);
		query.setString(1, type);
		query.setInteger(2, id);
		return (Long)query.uniqueResult();
	}
	public static Long checkExistForUpdateV1(int id,String title,int fid) {
		Session session = TagImpl.getSession();
		Query query = session.createQuery("select count(*) from Tag where title=? and fid=? and id not in (?)");
		query.setString(0,title);
		query.setInteger(1, fid);
		query.setInteger(2, id);
		return (Long)query.uniqueResult();
	}
	public static Long checkExistForTag(String sql) {
		Session session = TagImpl.getSession();
		Query query = session.createQuery("select count(*) from Tag where "+sql);
		return (Long)query.uniqueResult();
	}
	public static Long checkExistForType(String title){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("select count(*) from Type where title = ?");
		query.setString(0, title);
		return (Long)query.uniqueResult();
	}
	public static ArrayList<Type> getType(){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("from Type order by id desc");
		return (ArrayList<Type>)query.list();
	}
	public static ArrayList<Tag> getTheDifferentTag(int type){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("from Tag where type = ? and fid = 0 order by sort asc");
		query.setInteger(0, type);
		return (ArrayList)query.list();
	}
	public static ArrayList<Tag> getChoice(){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("from Tag where type=12 order by sort asc");
		ArrayList<Tag> list = (ArrayList<Tag>)query.list();
		return list;
	}
	public static ArrayList<Tag> getMenu(){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("from Tag where fid=160 order by sort asc");
		ArrayList<Tag> list = (ArrayList<Tag>)query.list();
		return list;
	}
	
	public static ArrayList<TagParent<Tag>> getTagListV1(int type){
		
		ArrayList<TagParent<Tag>> arraylist = new ArrayList<TagParent<Tag>>();
		ArrayList<Tag> list = TagImpl.getTheDifferentTag(type);
		
		for(Tag tag : list){
			TagParent<Tag> tagParnet = new TagParent<Tag>();
			tagParnet.setId(tag.getId());
			tagParnet.setFid(tag.getFid());
			tagParnet.setTitle(tag.getTitle());
			tagParnet.setFid_title(tag.getType_name());
			tagParnet.setSort(tag.getSort());
			tagParnet.setType(tag.getType());
			tagParnet.setSrc(tag.getSrc());
			tagParnet.setSubset(TagImpl.getSubclassTag(String.valueOf(tagParnet.getId())));
			arraylist.add(tagParnet);
		}
		return arraylist;
	}
	public static ArrayList<Tag> getFidByInt(int fid){
		Session session = TagImpl.getSession();
		Query query = session.createQuery("from Tag where fid = ? order by sort asc");
		query.setInteger(0, fid);
		ArrayList list = (ArrayList)query.list();
		return list;
	}
	public static ArrayList<Tag> getTagListV2(){
		
		ArrayList<Tag> getFidTag = TagImpl.getFidTag();
		
		for(Tag tag : getFidTag) {
			tag.setSubset(TagImpl.getFidByInt(tag.getId()));
		}
		
		for(Tag t : getFidTag) {
			for(Tag tag : t.getSubset()) {
				tag.setSubset(TagImpl.getFidByInt(tag.getId()));
			}
		}
		
		
		return getFidTag;
	}
	
	
	public static ArrayList<TagParent<Tag>> getTagList(){
		ArrayList<TagParent<Tag>> arraylist = new ArrayList<TagParent<Tag>>();
		ArrayList<Tag> list = TagImpl.getFidTag();
		for(Tag tag : list){
			TagParent<Tag> tagParnet = new TagParent<Tag>();
			tagParnet.setId(tag.getId());
			tagParnet.setFid(tag.getFid());
			tagParnet.setTitle(tag.getTitle());
			tagParnet.setFid_title(tag.getType_name());
			tagParnet.setSubset(TagImpl.getSubclassTag(String.valueOf(tagParnet.getId())));
			arraylist.add(tagParnet);
		}
		return arraylist;
	}
	public static void main(String[] args){
		
		ArrayList<Tag> arraylist = new ArrayList<Tag>();
		//ArrayList<ArrayList<TagParent<Tag>>> arraylists = new ArrayList<ArrayList<TagParent<Tag>>>();
		ArrayList<Tag> getFidTag = TagImpl.getFidTag();
		
		for(Tag tag : getFidTag) {
			tag.setSubset(TagImpl.getFidByInt(tag.getId()));
		}
		
		for(Tag t : getFidTag) {
			for(Tag tag : t.getSubset()) {
				tag.setSubset(TagImpl.getFidByInt(tag.getId()));
			}
		}
		
		
	}
}

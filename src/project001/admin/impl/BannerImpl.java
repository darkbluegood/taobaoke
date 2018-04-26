package project001.admin.impl;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import project001.admin.entity.Banner;

public class BannerImpl extends HibernateConfig{
	public static ArrayList<Banner> getList(){
		Session session = BannerImpl.getSession();
		Query query = session.createQuery("from Banner order by sort asc");
		ArrayList<Banner> arraylist = (ArrayList<Banner>)query.list();
		return arraylist;
	}
}

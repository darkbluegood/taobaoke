package project001.admin.impl;


import java.util.Iterator;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import project001.admin.dao.AdminDao;
import project001.admin.entity.Admin;
import project001.util.Md5;

public class AdminDaoImpl extends HibernateConfig{
	
	public static List<Admin> check(String user){
		Session session = AdminDaoImpl.getSession();
		String sql = "from Admin where user=?";
		List<Admin> list = session.createQuery(sql).setString(0, user).list();
		return list;
	}
	
	public static void main(String[] args){
		
		Admin admin = new Admin();
		admin.setUser("root");
		admin.setPassword(Md5.pdwMD5("1111"));
		AdminDaoImpl.add(admin);
		/*List<Admin> list = AdminDaoImpl.check("admin");
		Iterator iterator = list.iterator();
		Admin admin = new Admin();
		if(iterator.hasNext()){
			admin = (Admin)iterator.next();
			if(Md5.pdwMD5("123456").equals(admin.getPassword())){
				System.out.println("success");
			}
		}else{
			System.out.println("not found");
		}*/
		
		/*Admin admin = new Admin();
		admin.setId(2);
		admin.setUser("raowensheng");
		admin.setPassword(Md5.pdwMD5("3324180"));
		AdminDaoImpl.add(admin);
		System.out.println("ִ�гɹ�");*/
	}
}

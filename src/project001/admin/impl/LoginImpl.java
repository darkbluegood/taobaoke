package project001.admin.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import project001.admin.entity.Admin;
import project001.util.Md5;

public class LoginImpl extends HibernateConfig {
	
	public static Long checkUsername(String username) {
		Session session = LoginImpl.getSession();
		Query query = session.createQuery("select count(*) from Admin where user=?");
		query.setString(0, username);
		return (Long)query.uniqueResult();
	}
	public static Long checkPassword(String userName,String password) {
		Session session = LoginImpl.getSession();
		Query query = session.createQuery("select count(*) from Admin where user=? and password=?");
		query.setString(0, userName);
		query.setString(1, Md5.pdwMD5(password));
		return (Long)query.uniqueResult();
	}
	public static Admin getUserInfo(String username,String password) {
		Session session = LoginImpl.getSession();
		Query query = session.createQuery("from Admin where user=? and password=?");
		query.setString(0, username);
		query.setString(1, Md5.pdwMD5(password));
		Admin admin = (Admin)query.getSingleResult();
		return admin;
	}
	public static void main(String[] args) {
		System.out.println(LoginImpl.getUserInfo("admin","11").getUser());
		//System.out.println(LoginImpl.checkPassword(Md5.pdwMD5("1233")));
	}
}

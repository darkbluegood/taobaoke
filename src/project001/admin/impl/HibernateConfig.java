package project001.admin.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import project001.admin.entity.Category;
import project001.admin.entity.ItemInfo;


public class HibernateConfig {
	private static SessionFactory sessionFactory;
	static{
		Configuration configuration = new Configuration().configure();
		sessionFactory = configuration.buildSessionFactory();
	}
	
	public static Session getSession(){
		return sessionFactory.openSession();
	}
	
	public static<T> void add(T obj){
		Session session = HibernateConfig.getSession();
		session.beginTransaction();
		session.save(obj);
		session.getTransaction().commit();
		if(session != null){
			session.close();
		}
	}
	public static<T> T getById(int id,Class<T> obj){
        Session session = HibernateConfig.getSession();
        return (T)session.get(obj, new Integer(id));
    }
	public static<T> void update(T obj) {
		Session session = HibernateConfig.getSession();
		session.beginTransaction();
		session.update(obj);
		session.getTransaction().commit();
		if(session != null){
			session.close();
		}
	}
	public static<T> void delete(T obj){
		Session session = HibernateConfig.getSession();
		session.beginTransaction();
		session.delete(obj);
		session.getTransaction().commit();
		if(session != null){
			session.close();
		}
	}
	
	
}

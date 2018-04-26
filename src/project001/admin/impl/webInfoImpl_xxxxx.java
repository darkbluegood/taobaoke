package project001.admin.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import project001.admin.entity.WebInfo;

public class webInfoImpl_xxxxx {
	private static SessionFactory sessionFactory;
	static{
		Configuration configuration = new Configuration().configure();
		sessionFactory = configuration.buildSessionFactory();
	}
	
	public static Session getSession(){
		return sessionFactory.openSession();
	}
	
	public static void add(WebInfo webInfo){
		Session session = webInfoImpl_xxxxx.getSession();
		session.beginTransaction();
		session.save(webInfo);
		session.getTransaction().commit();
		if(session != null){
			session.close();
		}
	}
	public static void update(WebInfo website){
		Session session = webInfoImpl_xxxxx.getSession();
		session.beginTransaction();
		session.update(website);
		session.getTransaction().commit();
		if(session != null){
			session.close();
		}
	}
	public static WebInfo get(int id){
		Session session = webInfoImpl_xxxxx.getSession();
		return (WebInfo)session.get(WebInfo.class,new Integer(id));
	}
	public static void main(String[] args){
		/*Website website = new Website();
		website.setId(1);
		website.setTitle("�?80购物�?");
		website.setKeyword("返利,优惠,便宜");
		website.setDescription("这是�?个购物网�?!");
		WebsiteDaoImpl.add(website);*/
		WebInfo webInfo = webInfoImpl_xxxxx.get(0);
		System.out.println(webInfo.getTitle());
	}
}

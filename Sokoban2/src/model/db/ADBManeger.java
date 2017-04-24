package model.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.loader.custom.Return;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class ADBManeger implements iDBManager {
	
	SessionFactory factory;
	
	public ADBManeger() {
		//to show the severe msg
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		
		//reading the xml so he can connect to the Db
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
	}
	
	@Override
	public void add(Object o) throws Exception{

		Session session=null;
		Transaction tx=null;
		
		try{
			session=factory.openSession();
			tx=session.beginTransaction();
			session.save(o);
			tx.commit();
		}
		catch (Exception e) {
			throw new Exception("wrong input");
		}
		finally {
				if(session!=null)
					session.close();
				System.out.println("fin");
			}
	}

	@Override
	public void update(Object o)throws Exception {

		Session session=null;
		Transaction tx=null;
		
		try{
			session=factory.openSession();
			tx=session.beginTransaction();
			session.update(o);
			
			tx.commit();
			
		}
		catch(HibernateException ex){
			if(tx!=null)
				tx.rollback();
		}
		finally{
			if(session!=null)
				session.close();
		}
		
	}

	
	@Override
	public List selectScore(String query) {
		Session session = factory.openSession();
		List<Object> list=new ArrayList<Object>();
		try {
			
		    Query<Object> myQuery = session.createQuery("from "+query);
		    list = (List<Object>) myQuery.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	

}

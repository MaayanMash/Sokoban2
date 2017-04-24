package model.db;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class SokobanDBManager extends ADBManeger{

		//add register User To Level
		/*public UserLevel registerUserToLevel(Users u, Levels l){
			
			Transaction tx = null;		
			Session session =null;
			UserLevel reg = new UserLevel(u.getName(), l.getName());
			if (reg==null)
				System.out.println("null");
			
			try {
				session = factory.openSession();
				tx = session.beginTransaction();
				session.save(reg);
				tx.commit();
				
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
			return reg;
		}
		
        public UserLevel registerUserToLevel(String userName, String levelName){
			
			Transaction tx = null;		
			Session session =null;
			UserLevel reg =null;
			try {
				session = factory.openSession();
				tx = session.beginTransaction();
				reg = new UserLevel(userName, levelName); 
				session.save(reg);
				tx.commit();
				
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
				return reg;
			}*/
		}
		
		


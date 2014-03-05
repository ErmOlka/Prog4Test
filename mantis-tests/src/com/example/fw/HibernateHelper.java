package com.example.fw;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.fw.User;
import com.example.utils.HibernateUtil;
import com.example.utils.SortedListOf;

public class HibernateHelper extends HelperBase {

	public HibernateHelper(ApplicationManager manager) {
	  super(manager);
	}
	
	public List<User> listUsers() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		try {
          return new SortedListOf<User>(
        		  (List<User>) session.createQuery("from User").list());
		} finally {
          trans.commit();
		}
	}
	
}

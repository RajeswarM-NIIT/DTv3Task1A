package com.mysite1.userbackend1.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysite1.userbackend1.model.UserDetails;

@Repository("userDetailsDAOInt")
@Transactional
public class UserDetailsDAOImpl implements UserDetailsDAOInt {

	@Autowired
	private SessionFactory sessionFactory;

	public UserDetails addUser(UserDetails userDetails) {
		Session ses = sessionFactory.getCurrentSession();
		userDetails.setUserid(generateUserID());
		ses.save(userDetails);
		ses.flush();
		return userDetails;
	}
	
	
	public UserDetails loginCheck(UserDetails userDetails) {
		Session ses = sessionFactory.openSession();
		Query qry = ses.createQuery("from UserDetails where userid=? and password=? and enabled=true");
		qry.setString(0, userDetails.getUserid());
		qry.setString(1, userDetails.getPassword());
		UserDetails validuser = (UserDetails)qry.uniqueResult();		
		ses.close();
		return validuser;
	}

	
	
	
	private String generateUserID() {

		String newUid = "";
		Session s = sessionFactory.openSession();
		Query qr = s.createQuery("from UserDetails");
		List<UserDetails> data = qr.list();
		s.close();
		if (data.size() == 0) { // if table is empty
			newUid = "USR00001";
		} else { // if table is not empty
			Session ss = sessionFactory.openSession();
			Query q = ss.createQuery("select max(userid) from UserDetails");
			String prevId = q.list().get(0).toString();
			System.out.print("\nExisting : " + prevId);
			int id = Integer.parseInt(prevId.substring(3));
			System.out.print("\nExisting id : " + id);
			id = id + 1;
			if (id <= 9)
				newUid = "USR0000" + id;
			else if (id <= 99)
				newUid = "USR000" + id;
			else if (id <= 999)
				newUid = "USR00" + id;
			else if (id <= 9999)
				newUid = "USR0" + id;
			else
				newUid = "USR" + id;
			System.out.print("\nGenerated : " + newUid);
			ss.close();
		}
		return newUid;
	}






	
}

package com.vnit.api.repo;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vnit.api.entity.screenMst;
@Transactional
@Repository
public class screenRepo{
@Autowired
EntityManager em;
public screenMst getscreen(Integer id) {
		try {
			if (id == null) 
				return null;
			
			return em.find(screenMst.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	public Integer postscreen(screenMst screen) {
		try {
screenMst data = getscreen(screen.getscreenid());
			if (data == null)
				em.persist(screen);
			else
				em.merge(screen);
			
			em.flush();
			return screen.getscreenid();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0;
	}
public Integer deletescreen(Integer id) {
		try {
screenMst data = getscreen(id);
			if (data != null) {
				em.remove(data);
				em.flush();
				return 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0;
	}
}

package com.vnit.api.repo;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vnit.api.entity.customerMst;
@Transactional
@Repository
public class customerRepo{
@Autowired
EntityManager em;
public customerMst getcustomer(Integer id) {
		try {
			if (id == null) 
				return null;
			
			return em.find(customerMst.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	public Integer postcustomer(customerMst customer) {
		try {
customerMst data = getcustomer(customer.getccode());
			if (data == null)
				em.persist(customer);
			else
				em.merge(customer);
			
			em.flush();
			return customer.getccode();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return 0;
	}
public Integer deletecustomer(Integer id) {
		try {
customerMst data = getcustomer(id);
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

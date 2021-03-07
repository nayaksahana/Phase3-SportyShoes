package com.sahana.sportyshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sahana.sportyshoes.dao.PurchaseDAO;
import com.sahana.sportyshoes.model.Purchase;

@Component
public class PurchaseService {


	 @Autowired
	 private PurchaseDAO purchaseDAO;
	 
	 
	 public void setPurchaseDAO(PurchaseDAO purchaseDAO) {
		this.purchaseDAO = purchaseDAO;
	}


	/*@Transactional
	public int updatePurchase(Purchase purchase) {
		return purchaseDAO.updatePurchase(purchase);
	}



	@Transactional
	public List<Purchase> getAllItemsByUserId(int userId) {
		return purchaseDAO.getAllItemsByUserId(userId);
	}*/

}

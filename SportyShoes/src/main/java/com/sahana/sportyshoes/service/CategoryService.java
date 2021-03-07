package com.sahana.sportyshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sahana.sportyshoes.dao.CategoryDAO;
import com.sahana.sportyshoes.model.Category;

@Component
public class CategoryService {
	
	@Autowired
	 private CategoryDAO categoryDAO;

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@Transactional
	public Category getCategoryById(int categoryId) {
		return categoryDAO.getCategoryById(categoryId);
	}
	

	public String getCategoriesDropDown(int categoryId) {
		 StringBuilder sb = new StringBuilder("");
		 List<Category> list = categoryDAO.getCategories();
		 for(Category cat: list) {
				 sb.append("<option value=" + String.valueOf(cat.getId()) + ">" + cat.getName() + "</option>");
				 
		 }
		 return sb.toString();
	}

}

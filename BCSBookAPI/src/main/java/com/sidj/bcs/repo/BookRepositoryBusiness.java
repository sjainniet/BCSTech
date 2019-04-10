package com.sidj.bcs.repo;

import java.util.List;

import com.sidj.bcs.modal.BookDetails;

public interface BookRepositoryBusiness {
	
	public List<BookDetails> findBooksByParams(String sortBy, String filterBy, String filterParam,String order, int limit);  


}

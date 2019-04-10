/**
 * 
 */
package com.sidj.bcs.repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sidj.bcs.modal.BookDetails;
import com.sidj.bcs.services.BookServiceImpl;

/**
 * @author Siddharth Jain
 *
 */
public class BookRepositoryImpl implements BookRepositoryBusiness {
	
	
	@Autowired
	private EntityManager entityManager;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
	private String MethodName;
	
	public List<BookDetails> findBooksByParams(String sortBy, String filterBy,String filterParam, String order, int limit)
	{
		MethodName = "findBooksByParams";
		LOGGER.debug(" ********* STARTING Method******* "+MethodName);
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<BookDetails> query = criteriaBuilder.createQuery(BookDetails.class);
	   
	    Root<BookDetails> book = query.from(BookDetails.class);
	    List<Predicate> predicates = new ArrayList<>();
	    
	    Order orderBy;
	    String sort=null;
	    
		if(sortBy!=null && "price".equalsIgnoreCase(sortBy))
		{	
			sort = "bookPrice";
			/*if(order!=null && "asc".equalsIgnoreCase(order))
			{
				criteriaBuilder.asc(book.get(sort));
			}
			if(order!=null && "desc".equalsIgnoreCase(order))
			{
				criteriaBuilder.desc(book.get("bookPrice"));
			}*/
			
			//orderBy = order!=null?criteriaBuilder.asc(book.get("bookPrice")):criteriaBuilder.desc(book.get("bookPrice"));
		}

		else if(sortBy!=null && "date".equalsIgnoreCase(sortBy))
		{
			 sort="createdOn";
			//orderBy = criteriaBuilder.asc(book.get("createdOn"));
			//orderBy = order!=null?criteriaBuilder.asc(book.get("createdOn")):criteriaBuilder.desc(book.get("createdOn"));
			
		}
		else 
		{
			sort = "updatedOn";
			//orderBy = criteriaBuilder.desc(book.get("updatedOn"));
		}
		
		if(order!=null && "asc".equalsIgnoreCase(order))
		{
			orderBy = criteriaBuilder.asc(book.get(sort));
		}
		else if(order!=null && "desc".equalsIgnoreCase(order))
		{
			orderBy = criteriaBuilder.desc(book.get(sort));
		}
		else
		{
			orderBy = criteriaBuilder.asc(book.get(sort));
		}
	 
	    
	    
	    if (filterBy != null) {
	        predicates.add(criteriaBuilder.equal(book.get(filterBy), filterParam));
	        query.where(predicates.toArray(new Predicate[0])).orderBy(orderBy);
	    }
	    else
	    {
	    	 query.orderBy(orderBy);
	    }
	    //query.where(predicates.toArray(new Predicate[0])).orderBy(orderBy);
	    
	    LOGGER.debug(" ********* RETURNING from Method******* "+MethodName);
	    
	    return entityManager.createQuery(query).setMaxResults(limit).getResultList();

	}


}

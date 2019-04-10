/**
 * 
 */
package com.sidj.bcs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sidj.bcs.modal.BookDetails;

/**
 * @author Siddharth Jain
 *
 */
@Repository
public interface BookRepository extends JpaRepository<BookDetails, Integer>, BookRepositoryBusiness {
	

}

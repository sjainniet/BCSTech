package com.sidj.bcs.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidj.bcs.dto.ResponseDto;
import com.sidj.bcs.modal.BookDetails;
import com.sidj.bcs.repo.BookRepository;

/**
 * @author Siddharth Jain
 *
 */
@Service
public class BookServiceImpl implements BookService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
	private String MethodName;

	
	@Autowired
	private BookRepository bookRepo;

	@Override
	public ResponseDto<List<BookDetails>> getAllBooks() {
		
		MethodName = "getAllBooks";
		LOGGER.debug(" ********* STARTING Method******* "+MethodName);
		
		ResponseDto<List<BookDetails>> responseDto = new ResponseDto<>();
		List<BookDetails> bookList = null;
		try
		{
			bookList = bookRepo.findAll();
			if(!bookList.isEmpty())
				{
					responseDto.setBody(bookList);
					responseDto.setResponseCode(9001);
					responseDto.setResponseDescription("BOOKS FETCHED SUCCESFULLY");
				}
			else
				{
					responseDto.setResponseCode(9002);
					responseDto.setResponseDescription("No Book Found");
				}
		}
		catch (Exception e) {
			responseDto.setExceptionCode(3005);
			responseDto.setExceptionDescription("Exception : "+e.getMessage());
		}
		
		LOGGER.debug(" ********* RETURNING from Method******* "+MethodName);
		return responseDto;
		
	}

	@Override
	public ResponseDto<BookDetails> addNewBook(BookDetails book) {
		// TODO Auto-generated method stub
		MethodName = "addNewBook";
		LOGGER.debug(" ********* STARTING Method******* "+MethodName);
		
		BookDetails savedBook = null;
		ResponseDto<BookDetails> responseDto = new ResponseDto<>();
		try
		{
			book.setCreatedOn(Date.valueOf(LocalDate.now()));
			book.setCreatedBy("App-User");
			savedBook = bookRepo.save(book);
			if(savedBook!=null)
			{
				responseDto.setBody(savedBook);
				responseDto.setResponseCode(9001);
				responseDto.setResponseDescription("Book saved Successfully");
			}
			else
			{
				responseDto.setResponseCode(9002);
				responseDto.setResponseDescription("Book Not saved ");
			}
		}
		catch(Exception e)
		{
			responseDto.setExceptionCode(3001);;
			responseDto.setExceptionDescription("Exception Occurred "+e.getMessage());
		}
		LOGGER.debug(" ********* RETURNING from Method******* "+MethodName);
		return responseDto;
	}

	@Override
	public ResponseDto<?> deleteBookById(int bookId) {
		// TODO Auto-generated method stub
		MethodName = "deleteBookById";
		LOGGER.debug(" ********* STARTING Method******* "+MethodName);
		ResponseDto responseDto = new ResponseDto<>();
		try
		{
			Optional<BookDetails> book = bookRepo.findById(bookId);
			if(book.isPresent())
			{
				bookRepo.deleteById(bookId);
				responseDto.setResponseCode(9001);
				responseDto.setResponseDescription("Book Deleted Successfully");
			}
			else
			{
				responseDto.setResponseCode(9002);
				responseDto.setResponseDescription("Book Not Found with Book Id "+bookId);
			}
		}
		catch (Exception e) {
			responseDto.setExceptionCode(3005);
			responseDto.setExceptionDescription("EXCEPTION OCCURRED "+e.getMessage());
		}
		LOGGER.debug(" ********* RETURNING from Method******* "+MethodName);
		return responseDto;
	}

	@Override
	public ResponseDto<?> updateBookPrice(BookDetails book) {
		// TODO Auto-generated method stub
		MethodName = "deleteBookById";
		LOGGER.debug(" ********* STARTING Method******* "+MethodName);
		
		BookDetails newBook = null;
		ResponseDto<BookDetails> responseDto = new ResponseDto<>();
		try
		{
			Optional<BookDetails> savedBook = bookRepo.findById(book.getBookId());
			if(savedBook.isPresent())
			{
				newBook = savedBook.get();
				newBook.setBookPrice(book.getBookPrice());
				Date dt = Date.valueOf(LocalDate.now());
				newBook.setUpdatedOn(dt);
				bookRepo.save(newBook);
				responseDto.setBody(newBook);
				responseDto.setResponseCode(9001);
				responseDto.setResponseDescription("Book updated Successfully ");
			}
			else
			{
				responseDto.setResponseCode(9002);
				responseDto.setResponseDescription("Book not updated");
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			responseDto.setExceptionCode(3001);
			responseDto.setExceptionDescription("Exception "+e.getMessage());
		}
		
		LOGGER.debug(" ********* RETURNING from Method******* "+MethodName);
		return responseDto;
	}

	@Override
	public ResponseDto<?> getBooksByParam(String sortBy, String filterBy, String filterParam, String order, String limit) {
		
		MethodName = "deleteBookById";
		LOGGER.debug(" ********* STARTING Method******* "+MethodName);
		
		ResponseDto<List<BookDetails>> responseDto = new ResponseDto<>();
		List<BookDetails> bookList = null;
		
		/*//Setting sorting criteria for the List
		String sort=null;
		if(sortBy!=null && sortBy=="price")
			sort = "bookPrice";
		else if(sortBy!=null && sortBy=="date")
			sort="createdOn";
		else sort = "updatedOn";*/
		
		// Setting order for the List 
		order = (order!=null?order:"desc");
		
		// Setting filter for the List 
		String filter = null;
		if(filterBy!=null && filterParam!=null && "author".equalsIgnoreCase(filterBy) )
			filter="bookAuthor";
		if(filterBy!=null && filterParam!=null && "name".equalsIgnoreCase(filterBy))
			filter="bookName";
		
		int limitResults= limit!=null?Integer.parseInt(limit):10; // MAX LIMIT SET TO 10 FOR DEFAULT
		
		bookList = bookRepo.findBooksByParams(sortBy, filter, filterParam, order,limitResults);
		
		if(!bookList.isEmpty())
		{
			responseDto.setBody(bookList);
			responseDto.setResponseCode(9001);
			responseDto.setResponseDescription("Books found Successfully ");
		}
		else
		{
			responseDto.setResponseCode(9002);
			responseDto.setResponseDescription("No Book Found for given criteria");
		}
		
		LOGGER.debug(" ********* RETURNING from Method******* "+MethodName);
		return responseDto;
	}
	
	
	
}

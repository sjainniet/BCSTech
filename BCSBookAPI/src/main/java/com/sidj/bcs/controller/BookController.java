package com.sidj.bcs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sidj.bcs.dto.ResponseDto;
import com.sidj.bcs.modal.BookDetails;
import com.sidj.bcs.services.BookService;

/**
 * The Applicaion allows user to perform various CRUD operations for Bookstore
 *	Filteration of BookList with various criteria and sorting is also available 
 * 
 * @author Siddharth Jain
 * 
 *
 */
@RestController
@RequestMapping("/api")
public class BookController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookService bookService;
	
	/* 
	 * Method for checking whether server is up or not 
	 */
	@GetMapping("/statusCheck")
	public String test()
	{
		LOGGER.debug("********* In status Check Method*******");
		return "Application is running..";
	}
	
	/* 
	 * Method for Getting all books stored in db without any filter 
	 */
	@GetMapping("/books")
	public ResponseDto<?> getAllBooks()
	{
		LOGGER.debug("********* In getAllBooks Method*******");
		return bookService.getAllBooks();
	}
	
	/* 
	 * Method for add new book in db  
	 */
	@PostMapping("/addBook")
	public ResponseDto<?> addNewBook(@RequestBody BookDetails book)
	{
		LOGGER.debug("********* In addNewBook Method*******");
		return bookService.addNewBook(book);
	}
	
	/* 
	 * Method for deleting book in db  
	 */
	@PostMapping("/deleteBook")
	public ResponseDto<?> deleteBook(@RequestParam int bookId)
	{
		LOGGER.debug("********* In deleteBook Method*******");
		return bookService.deleteBookById(bookId);
	}
	
	/* 
	 * Method for edit book price  
	 */
	@PostMapping("/updateBook")
	public ResponseDto<?> updateBookPrice(@RequestBody BookDetails book)
	{
		LOGGER.debug("********* In updateBookPrice Method*******");
		return bookService.updateBookPrice(book);
	}
	
	/* 
	 * Method for getting books List applying various filters and sorting  
	 */
	@GetMapping("/getBooks")
	public ResponseDto<?> getBooksByParams(@RequestParam(value="sortBy",required = false) String sortBy, @RequestParam(value="filterBy",required = false) String filterBy, @RequestParam(value="filterParam",required = false) String filterParam, @RequestParam(value="order",required = false) String order, @RequestParam(value="limit",required = false) String limit)
	{
		LOGGER.debug("********* In getBooksByParams Method*******");
		return bookService.getBooksByParam(sortBy, filterBy, filterParam, order,limit);
	}
	

}

package com.sidj.bcs.services;

import java.util.List;

import com.sidj.bcs.dto.ResponseDto;
import com.sidj.bcs.modal.BookDetails;

public interface BookService {
	
	public ResponseDto<List<BookDetails>> getAllBooks();
	
	public ResponseDto<BookDetails> addNewBook(BookDetails book);
	
	public ResponseDto<?> deleteBookById(int bookId);
	
	public ResponseDto<?> updateBookPrice(BookDetails book);
	
	public ResponseDto<?> getBooksByParam(String sortBy, String filterBy, String filterParam, String order, String limit);

}

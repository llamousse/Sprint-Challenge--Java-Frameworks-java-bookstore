package com.vyue.bookstore.controllers;

import com.vyue.bookstore.models.Authors;
import com.vyue.bookstore.models.Book;
import com.vyue.bookstore.services.AuthorsService;
import com.vyue.bookstore.services.BookService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AllController
{
	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorsService authorsService;

	// GET /books - returns a JSON object list of all the books and their authors
	// localhost:2019/books
	@ApiOperation(value = "returns all Books", response = Book.class, responseContainer = "List")
	@ApiImplicitParams({
	   @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
						 value = "Results page you want to retrieve (0..N)"),
	   @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
						 value = "Number of records per page."),
	   @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
						 value = "Sorting criteria in the format: property(,asc|desc). " +
								 "Default sort order is ascending. " +
								 "Multiple sort criteria are supported.")})
	@GetMapping(value = "/books", produces = {"application/json"})
	public ResponseEntity<?> listAllBooks(
			@PageableDefault(page = 0,
							 size = 3)
					Pageable pageable)
	{
		ArrayList<Book> myBooks = bookService.findAll(pageable);
		return new ResponseEntity<>(myBooks, HttpStatus.OK);
	}

	// GET /authors - returns a JSON object list of all the authors and their books
	// localhost:2019/authors
	@ApiOperation(value = "returns all Authors", response = Authors.class, responseContainer = "List")
	@GetMapping(value = "/authors", produces = {"application/json"})
	public ResponseEntity<?> listAllAuthors()
	{
		ArrayList<Authors> myAuthors = authorsService.findAll();
		return new ResponseEntity<>(myAuthors, HttpStatus.OK);
	}
}

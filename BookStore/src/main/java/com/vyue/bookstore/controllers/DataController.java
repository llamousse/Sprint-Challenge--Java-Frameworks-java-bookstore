package com.vyue.bookstore.controllers;

import com.vyue.bookstore.models.Authors;
import com.vyue.bookstore.models.Book;
import com.vyue.bookstore.models.ErrorDetail;
import com.vyue.bookstore.services.AuthorsService;
import com.vyue.bookstore.services.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/data")
public class DataController
{
	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorsService authorsService;

	// PUT /data/books/{id} - updates a books info (Title, Copyright, ISBN)
	// but does NOT have to assign authors to the books.
	@ApiOperation(value = "updates book info", response = Book.class)
	@ApiResponses(value = {
		// code created
		@ApiResponse(code = 201, message = "Book Successfully Updated",
					 response = void.class),
		// code error
		@ApiResponse(code = 500, message = "Error Updating Book",
					 response = void.class)
	})
	@PutMapping(value = "/books/{id}", consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<?> updateBook(
			@RequestBody
				Book updateBook,
			@PathVariable long id)
	{
		bookService.update(updateBook, id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// POST /data/books/{bookid}/authors/{authorid} - assigns a book already
	// in the system (bookid) to an author already in the system (authorid)
	// (see how roles are handled for users)
	@ApiOperation(value = "creating a book", response = Book.class)
	@ApiResponses(value = {
			// code created
			@ApiResponse(code = 201, message = "Book Successfully Created",
						 response = void.class),
			// code error
			@ApiResponse(code = 500, message = "Error Creating Book",
						 response = void.class)
	})
	@PostMapping(value = "/books/{bookid}/authors/{authorid}",
				 consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<?> assignBookToAuthor(
			@PathVariable long bookid,
			@PathVariable long authorid)
	{
		Authors authors = authorsService.findAuthorsById(authorid);
		Book book = bookService.findBookById(bookid);

		book.getAuthors().add(authors);
		book = bookService.save(book);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// DELETE /data/books/{id} - deletes a book and the book author combinations
	// - but does not delete the author records.
	@ApiOperation(value = "delete a book", response = void.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Book Successfully Deleted",
						 response = void.class),
			@ApiResponse(code = 500, message = "Error Deleting Book",
						 response = ErrorDetail.class)
	})
	@DeleteMapping(value = "/books/{id}")
	public ResponseEntity<?> deletebook(
			@PathVariable long id)
	{
		bookService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

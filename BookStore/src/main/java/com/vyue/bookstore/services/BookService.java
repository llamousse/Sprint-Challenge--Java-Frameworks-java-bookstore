package com.vyue.bookstore.services;

import com.vyue.bookstore.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface BookService
{
	ArrayList<Book> findAll(Pageable pageable);

	Book findBookById(long id);

	Book save(Book book);

	Book update(Book book, long id);

	void delete(long id);
}

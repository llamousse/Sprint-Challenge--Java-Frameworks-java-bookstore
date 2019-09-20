package com.vyue.bookstore.services;

import com.vyue.bookstore.models.Authors;
import com.vyue.bookstore.models.Book;
import com.vyue.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
	@Autowired
	private BookRepository bookrepos;

	@Override
	public ArrayList<Book> findAll(Pageable pageable)
	{
		ArrayList<Book> list = new ArrayList<>();
		bookrepos.findAll(pageable).iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public Book findBookById(long id)
	{
		return bookrepos.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
	}

	@Transactional
	@Override
	public Book save(Book book)
	{
		Book newBook = new Book();

		newBook.setTitle(book.getTitle());
		newBook.setISBN(book.getISBN());
		newBook.setCopy(book.getCopy());

		if (book.getAuthors().size() > 0)
		{
			for (Authors a : book.getAuthors())
			{
				newBook.getAuthors().add(a);
			}
		}

		return bookrepos.save(newBook);
	}

	@Transactional
	@Override
	public Book update(Book book, long id)
	{
		Book currentBook = bookrepos.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

		if (book.getTitle() != null)
		{
			currentBook.setTitle(book.getTitle());
		}

		if (book.getISBN() != null)
		{
			currentBook.setISBN(book.getISBN());
		}

		currentBook.setCopy(book.getCopy());

		if (book.getAuthors().size() > 0)
		{
			for (Authors a : book.getAuthors())
			{
				currentBook.getAuthors().add(a);
			}
		}

		return bookrepos.save(currentBook);
	}

	@Transactional
	@Override
	public void delete(long id) throws EntityNotFoundException
	{
		if (bookrepos.findById(id).isPresent())
		{
			bookrepos.deleteById(id);
		} else
		{
			throw new EntityNotFoundException(Long.toString(id));
		}
	}
}

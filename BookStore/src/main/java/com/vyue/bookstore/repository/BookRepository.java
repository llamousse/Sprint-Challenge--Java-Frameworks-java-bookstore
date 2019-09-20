package com.vyue.bookstore.repository;

import com.vyue.bookstore.models.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;

public interface BookRepository extends PagingAndSortingRepository<Book, Long>
{
}

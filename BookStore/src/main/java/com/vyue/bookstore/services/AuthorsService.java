package com.vyue.bookstore.services;

import com.vyue.bookstore.models.Authors;

import java.util.ArrayList;

public interface AuthorsService
{
	ArrayList<Authors> findAll();

	Authors findAuthorsById(long id);

//	Authors save(Authors authors);

//	Authors update(Authors authors, long id);

//	void delete(long id);
}

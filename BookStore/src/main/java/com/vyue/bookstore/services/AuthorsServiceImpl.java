package com.vyue.bookstore.services;

import com.vyue.bookstore.models.Authors;
import com.vyue.bookstore.repository.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "authorsService")
public class AuthorsServiceImpl implements AuthorsService
{
	@Autowired
	private AuthorsRepository authorsrepos;

	@Override
	public ArrayList<Authors> findAll()
	{
		ArrayList<Authors> list = new ArrayList<>();
		authorsrepos.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public Authors findAuthorsById(long id) throws EntityNotFoundException
	{
		return authorsrepos.findById(id)
				.orElseThrow(() -> new EntityNotFoundException((Long.toString(id))));
	}
}

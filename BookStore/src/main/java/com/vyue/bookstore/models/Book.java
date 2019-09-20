package com.vyue.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@ApiModel(value = "Book", description = "The Book Entity")
@Entity
@Table(name = "book")
public class Book
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bookid;

	private String booktitle;
	private String ISBN;
	private int copy;

	@ManyToMany(mappedBy ="books")
	@JsonIgnoreProperties("books")
	private List<Authors> authors = new ArrayList<>();

	public Book()
	{
	}

	public Book(String booktitle)
	{
		this.booktitle = booktitle;
	}

	public long getBookid()
	{
		return bookid;
	}

	public void setBookid(long bookid)
	{
		this.bookid = bookid;
	}

	public String getBooktitle()
	{
		return booktitle;
	}

	public void setBooktitle(String booktitle)
	{
		this.booktitle = booktitle;
	}

	public String getISBN()
	{
		return ISBN;
	}

	public void setISBN(String ISBN)
	{
		this.ISBN = ISBN;
	}

	public int getCopy()
	{
		return copy;
	}

	public void setCopy(int copy)
	{
		this.copy = copy;
	}

	public List<Authors> getAuthors()
	{
		return authors;
	}

	public void setAuthors(List<Authors> authors)
	{
		this.authors = authors;
	}
}
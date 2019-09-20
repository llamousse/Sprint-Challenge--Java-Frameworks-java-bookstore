package com.vyue.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@ApiModel(value = "Authors", description = "The Authors Entity")
@Entity
@Table(name = "author")
public class Authors extends Auditable
{
//	@ApiModelProperty(name = "authorid", value = "primary key for Authors",
//					  required = true, example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long authorid;

	private String lastname;
	private String firstname;

	// many authors to many books
//	@ApiModelProperty(name = "books", value = "List of Books")
	@ManyToMany
	@JsonIgnoreProperties("authors")
	private List<Book> books = new ArrayList<>();

	public Authors()
	{
	}

	public Authors(String lastname, String firstname)
	{
		this.lastname = lastname;
		this.firstname = firstname;
	}

	public long getAuthorid()
	{
		return authorid;
	}

	public void setAuthorid(long authorid)
	{
		this.authorid = authorid;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public List<Book> getBooks()
	{
		return books;
	}

	public void setBooks(List<Book> books)
	{
		this.books = books;
	}
}

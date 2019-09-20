package com.vyue.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Authors", description = "The Authors Entity")
@Entity
@Table(name = "authors")
public class Authors extends Auditable
{
	// fields
	@ApiModelProperty(name = "authorid", value = "primary key for Authors",
					  required = true, example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long authorid;

	@ApiModelProperty(name = "lname", value = "last name of Author",
					  required = true, example = "Jane")
	private String lname;

	@ApiModelProperty(name = "fname", value = "first name of Author",
					  required = true, example = "Will")
	private String fname;

	// many to many relationship declaration
	@ApiModelProperty(name = "book", value = "List of Books")
	@ManyToMany
	@JoinTable(name = "authors",
			   joinColumns = {@JoinColumn(name = "authorid")},
			   inverseJoinColumns = {@JoinColumn(name = "bookid")})
	@JsonIgnoreProperties("authors")
	private List<Book> books = new ArrayList<>();

	// default constructor
	public Authors()
	{
	}

	// constructor with params
	public Authors(String lname, String fname)
	{
		this.lname = lname;
		this.fname = fname;
	}

	// getters and setters
	public long getAuthorid()
	{
		return authorid;
	}

	public void setAuthorid(long authorid)
	{
		this.authorid = authorid;
	}

	public String getLname()
	{
		return lname;
	}

	public void setLname(String lname)
	{
		this.lname = lname;
	}

	public String getFname()
	{
		return fname;
	}

	public void setFname(String fname)
	{
		this.fname = fname;
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

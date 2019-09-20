package com.vyue.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Book", description = "The Book Entity")
@Entity
@Table(name = "books")
public class Book extends Auditable
{
	// fields
	@ApiModelProperty(name = "bookid", value = "primary key for Book",
					  required = true, example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bookid;

	@ApiModelProperty(name = "title", value = "title of Book",
					  required = true, example = "Harry Potter")
	private String title;

	@ApiModelProperty(name = "ISBN", value = "ISBN of Book",
					  required = true, example = "123123123")
	private String ISBN;

	@ApiModelProperty(name = "copy", value = "copyright year of Book",
					  required = true, example = "2019")
//	@Column(nullable = true)
	private int copy;

	// many to many relationship declaration
	@ApiModelProperty(name = "authors", value = "List of Authors")
	@ManyToMany
	@JoinTable(name = "wrote",
			   joinColumns = {@JoinColumn(name = "bookid")},
			   inverseJoinColumns = {@JoinColumn(name = "authorid")})
	@JsonIgnoreProperties("books")
	private List<Authors> authors = new ArrayList<>();

	// default constructor
	public Book()
	{
	}

	// constructor with params
	public Book(String title, String ISBN, int copy)
	{
		this.title = title;
		this.ISBN = ISBN;
		this.copy = copy;
	}

	// getters and setters
	public long getBookid()
	{
		return bookid;
	}

	public void setBookid(long bookid)
	{
		this.bookid = bookid;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
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
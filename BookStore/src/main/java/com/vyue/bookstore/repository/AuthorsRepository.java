package com.vyue.bookstore.repository;

import com.vyue.bookstore.models.Authors;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorsRepository extends PagingAndSortingRepository<Authors, Long>
{
}

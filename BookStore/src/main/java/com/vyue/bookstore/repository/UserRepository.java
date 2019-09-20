package com.vyue.bookstore.repository;

import com.vyue.bookstore.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}

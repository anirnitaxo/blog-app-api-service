package com.blog.backendapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.backendapi.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}

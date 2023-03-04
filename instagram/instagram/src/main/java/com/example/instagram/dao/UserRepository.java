package com.example.instagram.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.instagram.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}

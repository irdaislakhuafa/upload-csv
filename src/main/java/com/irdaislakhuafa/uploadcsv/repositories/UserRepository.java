package com.irdaislakhuafa.uploadcsv.repositories;

import com.irdaislakhuafa.uploadcsv.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}

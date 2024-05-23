package com.example.form1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.form1.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User findOneByEmailIdIgnoreCaseAndPassword(String emailId, String password);

}

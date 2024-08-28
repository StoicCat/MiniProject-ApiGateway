package com.miniProject.OlShop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniProject.OlShop.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	Optional<User> findByEmail(String email);
    
    Optional<User> findById(String id);
    
    List<User> findAll();
    
    List<User> findByRole(String roleCode);

}

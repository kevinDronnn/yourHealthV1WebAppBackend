package com.example.yourhealthv1.repository;

import com.example.yourhealthv1.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findUsersByName(String name);
    boolean existsUsersByEmail(String email);
    boolean existsUsersByName(String name);
    @Query(value = "SELECT u.email FROM Users u WHERE u.name = :username")
    String findEmailByName(@Param("username") String username);

    Users findUsersByNameAndEmail(String name,String email);
}

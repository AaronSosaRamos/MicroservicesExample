package com.microservices.usermicroservice.repositories;

import com.microservices.usermicroservice.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<Users,Integer>{
    
}

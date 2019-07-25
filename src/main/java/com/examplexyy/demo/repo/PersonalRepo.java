package com.examplexyy.demo.repo;

import com.examplexyy.demo.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalRepo extends JpaRepository<Personal,Long> {

    public Optional<Personal> findById(Long id);

    public Personal findTopById(Long id);

}

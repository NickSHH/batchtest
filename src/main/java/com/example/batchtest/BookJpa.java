package com.example.batchtest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpa extends JpaRepository<Book, Integer> {
}

package com.example.book.store.rest.repository;

import com.example.book.store.rest.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}

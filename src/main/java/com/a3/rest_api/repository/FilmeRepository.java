package com.a3.rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.a3.rest_api.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
	
}

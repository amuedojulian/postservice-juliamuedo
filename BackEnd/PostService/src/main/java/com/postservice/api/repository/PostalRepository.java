package com.postservice.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.postservice.api.model.Postal;

@Repository
public interface PostalRepository extends JpaRepository<Postal, Integer> {

	List<Postal> findByPessoaId(Integer id);
	
}

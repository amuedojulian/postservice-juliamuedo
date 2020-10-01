package com.postservice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.postservice.api.model.Lugar;

@Repository
public interface LugarRepository extends JpaRepository<Lugar, Integer>{

}

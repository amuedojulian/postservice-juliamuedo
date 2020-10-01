package com.postservice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.postservice.api.model.Destinatario;

@Repository
public interface DestinatarioRepository extends JpaRepository<Destinatario, Integer>{

}

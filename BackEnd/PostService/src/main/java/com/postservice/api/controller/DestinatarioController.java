package com.postservice.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.postservice.api.model.Destinatario;
import com.postservice.api.service.AllServices;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DestinatarioController {

	@Autowired
	private AllServices allServices;
	
	@GetMapping("/destinatarios")
	public List<Destinatario> getAllDestinatarios() {
		return allServices.findAllDestinatario();
	}
		
	@GetMapping("/destinatarios/{id}")
	public java.util.Optional<Destinatario> getDestinatariorById(@PathVariable Integer id) {
		return allServices.findDestinatarioById(id);
	}
	
	@PostMapping("/destinatarios/addNew")
	public void AddDestinatario(@RequestBody Destinatario destinatario) {
		
		try { allServices.AddDestinatario(destinatario); }
		catch (Exception e) { System.out.println(e.toString() + "Erro ao inserir o Destinatario, tente inserir os dados corretamente."); }
		
	}
	
	@PutMapping("/destinatarios/{id}/update")
	public void UpdateDestinatario(@RequestBody Destinatario destinatario) {
		
		try { allServices.UpdateDestinatario(destinatario); }
		catch (Exception e) { System.out.println(e.toString() + "Erro ao atualizar o Destinatario, tente inserir os dados corretamente."); }
		
	}
	
	@DeleteMapping("/destinatarios/{id}/delete")
	public void DeleteDestinatario(@PathVariable Integer id) {
		
		try { allServices.DeleteDestinatario(id); }
		catch (Exception e) { System.out.println(e.toString() + "Erro ao retirar o Destinatario, tente inserir os dados corretamente."); }
		
	}
	
}

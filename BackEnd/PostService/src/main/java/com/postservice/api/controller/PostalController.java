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
import com.postservice.api.model.Postal;
import com.postservice.api.service.AllServices;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostalController {

	@Autowired
	private AllServices allServices;

	@GetMapping("/postais")
	public List<Postal> getAllPosts() {
	    return allServices.findAllPostal();
	}

	@GetMapping("/postais/{id}")
	public java.util.Optional<Postal> getPostalById(@PathVariable Integer id) {
	    return allServices.findPostalById(id);
	}
	
	@GetMapping("/postais/user/{id}/posts")
	public List<Postal> getPostsByUser(@PathVariable Integer id) {
	    return allServices.findByPessoaId(id);
	}
	
	@PostMapping("/postais/addNew")
	public void AddPostal(@RequestBody Postal postal) {
		
		try { allServices.addPostal(postal); }
		catch (Exception e) { System.out.println(e.toString() + "Erro ao inserir a Postal, tente inserir os dados corretamente."); }
		
	}
	
	@PutMapping("/postais/{id}/update")
	public void UpdatePostal(@RequestBody Postal postal) {
		
		try { allServices.UpdatePostal(postal); }
		catch (Exception e) { System.out.println(e.toString() + "Erro ao atualizar a Postal, tente inserir os dados corretamente."); }
		
	}
	
	@DeleteMapping("/postais/{id}/delete")
	public void DeletePostal(@PathVariable Integer id) {
		
		try { allServices.DeletePostal(id); }
		catch (Exception e) { System.out.println(e.toString() + "Erro ao retirar a Postal, tente inserir os dados corretamente."); }
		
	}
}

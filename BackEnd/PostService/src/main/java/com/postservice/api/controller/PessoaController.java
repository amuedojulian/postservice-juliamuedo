package com.postservice.api.controller;

import java.util.List;
import java.util.Optional;

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
import com.postservice.api.model.Pessoa;
import com.postservice.api.service.AllServices;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PessoaController {
	
	@Autowired
	private AllServices allServices;

	@GetMapping("/pessoas")
	public List<Pessoa> getAllPessoas() {
		return allServices.getAllPessoa();
	}
	
	@GetMapping("/pessoa/{id}")
	public Optional<Pessoa> getUserById(@PathVariable Integer id) {
		return allServices.getPessoaById(id);
	}
	
	@GetMapping("/pessoa/{id}/postais")
	public List<Postal> getPostsByUser(@PathVariable Integer id) {
	    Optional<Pessoa> pessoa = allServices.getPessoaById(id);
	    if(pessoa.isPresent()) {
		return pessoa.get().getPosts();
	    }
	    return null;
	}
	
	@GetMapping("/pessoas/lugar/{id}/pessoas")
	public List<Pessoa> getUsersByLocation(@PathVariable Integer id) {
	    return allServices.getPessoasByLocation(id);
	}
	
	@PostMapping("/pessoas/addNew")
	public void AddPessoa(@RequestBody Pessoa pessoa)  {
	
		try { allServices.AddPessoa(pessoa); }
		catch (Exception e) { System.out.println(e.toString() + "Erro ao inserir a Pessoa, tente inserir os dados corretamente."); }
		
	}
	
	@PutMapping("/pessoas/{id}/update")
	public void UpdatePessoa(@RequestBody Pessoa pessoa ) {
		
		try { allServices.UpdatePessoa(pessoa); }
		catch (Exception e) { System.out.println(e.toString() + "Erro ao atualizar a Pessoa, tente inserir os dados corretamente."); }
		
	}
	
	@DeleteMapping("/pessoas/{id}/delete")
	public void DeletePessoa(@PathVariable Integer id) {
		
		try { allServices.DeletePessoa(id); }
		catch (Exception e) { System.out.println(e.toString() + "Erro ao retirar a Pessoa, tente inserir os dados corretamente."); }
		
	}
}

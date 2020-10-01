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

import com.postservice.api.model.Lugar;
import com.postservice.api.model.Pessoa;
import com.postservice.api.service.AllServices;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LugarController {

	@Autowired
	private AllServices allServices;
		
	@GetMapping("/lugares")
	public List<Lugar> getAllLugares() {
		return allServices.findAllLugar();
	}
		
	@GetMapping("/lugares/{id}")
	public java.util.Optional<Lugar> getLugarById(@PathVariable Integer id) {
		return allServices.findLugarById(id);
	}
	
	@GetMapping("/lugares/{id}/users")
	public List<Pessoa> GetPessoasByLocation(@PathVariable Integer id) {
	    java.util.Optional<Lugar> lugar = allServices.findLugarById(id);		
	    if(lugar.isPresent()) {
		return lugar.get().getPessoas();
	    } else {
	    	return null;
	    }
	}
	
	@PostMapping("/lugares/addNew")
	public void AddLocation(@RequestBody Lugar lugar) {
		
		try { allServices.AddLugar(lugar); }
		catch (Exception e) { System.out.println(e.toString() + "Erro ao inserir o Lugar, tente inserir os dados corretamente."); }
		
	}
	
	@PutMapping("/lugares/{id}/update")
	public void UpdateLugar(@RequestBody Lugar lugar) {
		;
		try { allServices.UpdateLugar(lugar); }
		catch (Exception e) { System.out.println(e.toString() + "Erro ao atualizar o Lugar, tente inserir os dados corretamente."); }
		
	}
	
	@DeleteMapping("/lugares/{id}/delete")
	public void DeleteLugar(@PathVariable Integer id) {
		
		try { allServices.DeleteLugar(id); }
		catch (Exception e) { System.out.println(e.toString() + "Erro ao retirar o Lugar, tente inserir os dados corretamente."); }
		
	}
	
}

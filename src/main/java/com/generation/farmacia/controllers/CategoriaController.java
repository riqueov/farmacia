package com.generation.farmacia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.CategoriaModel;
import com.generation.farmacia.repository.CategoriaRepository;

@RestController
@RequestMapping("farmacia/categoria")
@CrossOrigin("*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repositoryCategoria;
	
	@GetMapping("/todas")
	public ResponseEntity<List<CategoriaModel>> getAll(){
		
		return ResponseEntity.ok(repositoryCategoria.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaModel> getId(@PathVariable long id){
		
		return repositoryCategoria.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<CategoriaModel>> getDescription( @PathVariable String descricao){
		
		return ResponseEntity.ok(repositoryCategoria.FindAllByDescricaoCategoriaContainingIgnoreCase(descricao));
	}
	
	

}



package com.generation.farmacia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.CategoriaModel;
import com.generation.farmacia.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repositoryCategoria;
	
	@GetMapping("/todas")
	public ResponseEntity<List<CategoriaModel>> getAll(){
		
		return ResponseEntity.ok(repositoryCategoria.findAll());
	}
	
	@GetMapping("/{idCategoria}")
	public ResponseEntity<CategoriaModel> getId(@PathVariable Long id){
		
		return repositoryCategoria.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<CategoriaModel>> getDescription( @PathVariable String faixaEtaria){
		
		return ResponseEntity.ok(repositoryCategoria.findAllByFaixaEtariaContainingIgnoreCase(faixaEtaria));
	}
	
	@PostMapping
	public ResponseEntity<CategoriaModel> post(@RequestBody CategoriaModel categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(repositoryCategoria.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<CategoriaModel> put(@RequestBody CategoriaModel postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repositoryCategoria.save(postagem));
	}
	
	@DeleteMapping("/{idCategoria}")
	public void delete(@PathVariable long id) {
		repositoryCategoria.deleteById(null);
	}

}
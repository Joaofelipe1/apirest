package com.a3.rest_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a3.rest_api.model.Filme;
import com.a3.rest_api.service.FilmeService;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

	@Autowired
	private FilmeService filmeService;
	
	@GetMapping
	public List<Filme> getAllFilmes(){
		return filmeService.getAllFilmes();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Filme> getFilmeById(@PathVariable Long id){
		Optional<Filme> filme = filmeService.getFilmeById(id);
		return filme.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Filme createFilme(@RequestBody Filme filme) {
		return filmeService.createFilme(filme);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Filme> patchFilme(@PathVariable Long id, @RequestBody Filme updatedFields) {
	    Optional<Filme> existingFilmeOptional = filmeService.getFilmeById(id);
	    if (existingFilmeOptional.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

	    Filme existingFilme = existingFilmeOptional.get();

	    if (updatedFields.getNome() != null) {
	        existingFilme.setNome(updatedFields.getNome());
	    }
	    if (updatedFields.getAutor() != null) {
	        existingFilme.setAutor(updatedFields.getAutor());
	    }
	    if (updatedFields.getAno() != 0) {
	        existingFilme.setAno(updatedFields.getAno());
	    }

	    Filme savedFilme = filmeService.createFilme(existingFilme);
	    return ResponseEntity.ok(savedFilme);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteFilme(@PathVariable Long id){
		filmeService.deleteFilme(id);
		return ResponseEntity.noContent().build();
	}
}

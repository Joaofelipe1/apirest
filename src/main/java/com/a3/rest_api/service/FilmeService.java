package com.a3.rest_api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.a3.rest_api.model.Filme;
import com.a3.rest_api.repository.FilmeRepository;

@Service
public class FilmeService {

	private final FilmeRepository filmeRepository;
	
	@Autowired
	public FilmeService(FilmeRepository filmeRepository) {
		this.filmeRepository = filmeRepository;
	}
	
	public List<Filme> getAllFilmes(){
		return filmeRepository.findAll();
	}
	
	public Optional<Filme> getFilmeById(Long id){
		return filmeRepository.findById(id);
	}
	
	public Filme createFilme(Filme filme) {
		return filmeRepository.save(filme);
	}
	
	public void deleteFilme(Long id) {
		if (filmeRepository.existsById(id)) {
			filmeRepository.deleteById(id);
		} else {
			throw new RuntimeException("Filme não encontrado com ID " + id);
		}
	}
}

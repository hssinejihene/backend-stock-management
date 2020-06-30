package com.projet.stock.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import com.projet.stock.exception.ResourceNotFoundException;
import com.projet.stock.model.Scategorie;
import com.projet.stock.model.Scategorie;
import com.projet.stock.model.Scategorie;
import com.projet.stock.model.Scategorie;
import com.projet.stock.repository.ScategorieRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ScategorieController {
@Autowired
	
	ScategorieRepository repository;
	
	@GetMapping("/Scategories")
	public List<Scategorie> getAllScategories(){
		System.out.println("Get all Scategorie");
		List<Scategorie>Scategories=new ArrayList<>();
		repository.findAll().forEach(Scategories::add);
		return Scategories;
		
	}
	
	@GetMapping("/Scategories/{id}")
	public ResponseEntity<Scategorie>getScategorieById(@PathVariable(value="id") long ID)
	   throws ResourceNotFoundException{
		Scategorie Scat=repository.findById(ID)
				.orElseThrow(()->new ResourceNotFoundException("Scategorie Not found"));
				return ResponseEntity.ok().body(Scat);
		
	}
	
	@PostMapping("/Scategories")
	public Scategorie createScategorie(@Valid @RequestBody Scategorie Scategorie) {
	    return repository.save(Scategorie);
	}

	
	@DeleteMapping("/Scategories/{id}")
	public Map<String,Boolean>deleteScategorie(@PathVariable(value="id") long ID)
			throws ResourceNotFoundException{
		Scategorie Scat =repository.findById(ID)
				.orElseThrow(()->new ResourceNotFoundException("Scategorie Not found"));
		repository.delete(Scat);
		Map<String,Boolean>response=new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return response;
		
	}
	
	@DeleteMapping("/Scategories/delete")
	public ResponseEntity<String>deleteAllScategories(){
	System.out.println("Delete all Scategorie");
	repository.deleteAll();
	return new ResponseEntity<>("All Scategories have been deleted!",HttpStatus.OK);
		
	}
	
	
	@PutMapping("/Scategories/{id}")
	public Scategorie updateScategorie(@PathVariable(value = "id") Long Id,
            @Valid @RequestBody Scategorie Scategorie) {

Scategorie Scat = repository.findById(Id).orElseThrow(null);


Scat.setCode(Scategorie.getCode());
Scat.setLibelle(Scategorie.getLibelle());
Scat.setCode_categ(Scategorie.getCode_categ());
Scat.setId_cat(Scategorie.getId_cat());
Scategorie updatedScategorie = repository.save(Scat);
return updatedScategorie;
}
	
}

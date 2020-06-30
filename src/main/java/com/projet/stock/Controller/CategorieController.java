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
import com.projet.stock.model.Categorie;

import com.projet.stock.repository.CategorieRepository;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CategorieController {
@Autowired
	
	CategorieRepository repository;
	
	@GetMapping("/Categories")
	public List<Categorie> getAllCategories(){
		System.out.println("Get all Categorie");
		List<Categorie>Categories=new ArrayList<>();
		repository.findAll().forEach(Categories::add);
		return Categories;
		
	}
	
	@GetMapping("/Categories/{id}")
	public ResponseEntity<Categorie>getCategorieById(@PathVariable(value="id") long ID)
	   throws ResourceNotFoundException{
		Categorie Cat=repository.findById(ID)
				.orElseThrow(()->new ResourceNotFoundException("Categorie Not found"));
				return ResponseEntity.ok().body(Cat);
		
	}

	
	@PostMapping("/AddCategories")
	public Categorie createCategorie(@Valid @RequestBody Categorie Categorie) {
	    return repository.save(Categorie);
	}
	
	
	@DeleteMapping("/DelCategories/{id}")
	public Map<String,Boolean>deleteCategorie(@PathVariable(value="id") long ID)
			throws ResourceNotFoundException{
		Categorie Cat =repository.findById(ID)
				.orElseThrow(()->new ResourceNotFoundException("Categorie Not found"));
		repository.delete(Cat);
		Map<String,Boolean>response=new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return response;
		
	}
	
	@DeleteMapping("/Categories/delete")
	public ResponseEntity<String>deleteAllCategories(){
	System.out.println("Delete all Categorie");
	repository.deleteAll();
	return new ResponseEntity<>("All Categories have been deleted!",HttpStatus.OK);
		
	}
	
	
	
	@PutMapping("/PutCategories/{id}")
	public Categorie updateCategorie(@PathVariable(value = "id") Long Id,
            @Valid @RequestBody Categorie Categorie) {

Categorie Cat = repository.findById(Id).orElseThrow(null);


Cat.setCode(Categorie.getCode());
Cat.setLibelle(Categorie.getLibelle());

Categorie updatedCategorie = repository.save(Cat);
return updatedCategorie;
}
	
	
	
	
}

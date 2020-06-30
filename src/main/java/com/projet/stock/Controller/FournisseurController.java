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
import com.projet.stock.model.Fournisseur;

import com.projet.stock.repository.FournisseurRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FournisseurController {
	@Autowired
	
	FournisseurRepository repository;
	
	@GetMapping("/Fournisseurs")
	public List<Fournisseur> getAllFournisseurs(){
		System.out.println("Get all Fournisseur");
		List<Fournisseur>Fournisseurs=new ArrayList<>();
		repository.findAll().forEach(Fournisseurs::add);
		return Fournisseurs;
		
	}
	
	
	@GetMapping("/Fournisseurs/{id}")
	public ResponseEntity<Fournisseur>getFournisseurById(@PathVariable(value="id") long ID)
	   throws ResourceNotFoundException{
		Fournisseur Four=repository.findById(ID)
				.orElseThrow(()->new ResourceNotFoundException("Fournisseur Not found"));
				return ResponseEntity.ok().body(Four);
		
	}
	
	@PostMapping("/Fournisseurs")
	public Fournisseur createFournisseur(@Valid @RequestBody Fournisseur Fournisseur) {
	    return repository.save(Fournisseur);
	}
	
	
	@DeleteMapping("/Fournisseurs/{id}")
	public Map<String,Boolean>deleteFournisseur(@PathVariable(value="id") long ID)
			throws ResourceNotFoundException{
		Fournisseur Four =repository.findById(ID)
				.orElseThrow(()->new ResourceNotFoundException("Fournisseur Not found"));
		repository.delete(Four);
		Map<String,Boolean>response=new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return response;
		
	}
	
	@DeleteMapping("/Fournisseurs/delete")
	public ResponseEntity<String>deleteAllFournisseurs(){
	System.out.println("Delete all Fournisseur");
	repository.deleteAll();
	return new ResponseEntity<>("All Fournisseurs have been deleted!",HttpStatus.OK);
		
	}
	

	@PutMapping("/Fournisseurs/{id}")
	
	
	public Fournisseur updateFournisseur(@PathVariable(value = "id") Long Id,
            @Valid @RequestBody Fournisseur Fournisseur) {

Fournisseur Four = repository.findById(Id).orElseThrow(null);


Four.setCode(Fournisseur.getCode());
Four.setLibelle(Fournisseur.getLibelle());
Four.setContact(Fournisseur.getContact());
Four.setAdresse(Fournisseur.getAdresse());
Four.setTel(Fournisseur.getTel());
Four.setEmail(Fournisseur.getEmail());
Four.setMatfisc(Fournisseur.getMatfisc());
Four.setLogin(Fournisseur.getLogin());
Four.setPwd(Fournisseur.getPwd());
Four.setSolde_init(Fournisseur.getSolde_init());
Four.setSolde(Fournisseur.getSolde());


Fournisseur updatedFournisseur = repository.save(Four);
return updatedFournisseur;
}
	
}

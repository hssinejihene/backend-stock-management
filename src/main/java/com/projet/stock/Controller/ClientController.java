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
import com.projet.stock.model.Client;

import com.projet.stock.repository.ClientRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ClientController {
	
@Autowired
	
	ClientRepository repository;
	
	@GetMapping("/Clients")
	public List<Client> getAllClients(){
		System.out.println("Get all Client");
		List<Client>Clients=new ArrayList<>();
		repository.findAll().forEach(Clients::add);
		return Clients;
		
	}
	
	
	@GetMapping("/Clients/{id}")
	public ResponseEntity<Client>getClientById(@PathVariable(value="id") long ID)
	   throws ResourceNotFoundException{
		Client Cli=repository.findById(ID)
				.orElseThrow(()->new ResourceNotFoundException("Client Not found"));
				return ResponseEntity.ok().body(Cli);
		
	}
	
	
	@PostMapping("/AddClients")
	public Client createClient(@Valid @RequestBody Client Client) {
	    return repository.save(Client);
	}
	

	
	
	@DeleteMapping("/DelClients/{id}")
	public Map<String,Boolean>deleteClient(@PathVariable(value="id") long ID)
			throws ResourceNotFoundException{
		Client Cli =repository.findById(ID)
				.orElseThrow(()->new ResourceNotFoundException("Client Not found"));
		repository.delete(Cli);
		Map<String,Boolean>response=new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return response;
		
	}
	
	
	@DeleteMapping("/Clients/delete")
	public ResponseEntity<String>deleteAllClients(){
	System.out.println("Delete all Client");
	repository.deleteAll();
	return new ResponseEntity<>("All Clients have been deleted!",HttpStatus.OK);
		
	}
	
	
	@PutMapping("/Clients/{id}")
	public Client updateClient(@PathVariable(value = "id") Long Id,
            @Valid @RequestBody Client Client) {

Client Cli = repository.findById(Id).orElseThrow(null);



Cli.setLibelle(Client.getLibelle());

Cli.setAdresse(Client.getAdresse());
Cli.setTel(Client.getTel());
Cli.setEmail(Client.getEmail());




Client updatedClient = repository.save(Cli);
return updatedClient;
}
	
	
}

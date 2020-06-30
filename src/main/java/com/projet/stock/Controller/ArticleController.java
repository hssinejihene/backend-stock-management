package com.projet.stock.Controller;

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
import com.projet.stock.model.Article;
import com.projet.stock.repository.ArticleRepository;



import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ArticleController {
	@Autowired
	
	ArticleRepository repository;
	
	@GetMapping("/Articles")
	public List<Article> getAllArticles(){
		System.out.println("Get all article");
		List<Article>Articles=new ArrayList<>();
		repository.findAll().forEach(Articles::add);
		return Articles;
		
	}
	
	@GetMapping("/Articles/{id}")
	public ResponseEntity<Article>getArticleById(@PathVariable(value="id") long ID)
	   throws ResourceNotFoundException{
		Article Art=repository.findById(ID)
				.orElseThrow(()->new ResourceNotFoundException("Article Not found"));
				return ResponseEntity.ok().body(Art);
		
	}
	
	@PostMapping("/AddArticles")
	public Article createArticle(@Valid @RequestBody Article article) {
	    return repository.save(article);
	}
	
	
	@DeleteMapping("/DelArticles/{id}")
	public Map<String,Boolean>deleteArticle(@PathVariable(value="id") long ID)
			throws ResourceNotFoundException{
		Article Art =repository.findById(ID)
				.orElseThrow(()->new ResourceNotFoundException("Article Not found"));
		repository.delete(Art);
		Map<String,Boolean>response=new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return response;
		
	}
	
	@DeleteMapping("/Articles/delete")
	public ResponseEntity<String>deleteAllArticles(){
	System.out.println("Delete all article");
	repository.deleteAll();
	return new ResponseEntity<>("All Articles have been deleted!",HttpStatus.OK);
		
	}
	
	@PutMapping("/PutArticles/{id}")
	public Article updateArticle(@PathVariable(value = "id") Long Id,
            @Valid @RequestBody Article article) {

Article art = repository.findById(Id).orElseThrow(null);


art.setCode(article.getCode());
art.setDes(article.getDes());
art.setPa(article.getPa());


art.setQte(article.getQte());

art.setIdCat(article.getIdCat());

Article updatedArticle = repository.save(art);
return updatedArticle;
}


	
	}
	



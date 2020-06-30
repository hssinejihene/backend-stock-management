package com.projet.stock.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity    
@Table(name = "client")
public class Client {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String libelle;
	
	private String adresse;
    private String tel;
	private String email;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Client(long id, String libelle, String adresse, String tel, String email) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
	}
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", libelle=" + libelle + ", adresse=" + adresse + ", tel=" + tel + ", email="
				+ email + "]";
	}
	
	
	
	
}
	
	
	
	
	
	

package com.projet.stock.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity    
@Table(name = "article")
public class Article {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String code;
	private String Des;
	private float pa;//prix achat
	private int qte;
	
	private long idCat;//id categorie

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDes() {
		return Des;
	}

	public void setDes(String des) {
		Des = des;
	}

	public float getPa() {
		return pa;
	}

	public void setPa(float pa) {
		this.pa = pa;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public long getIdCat() {
		return idCat;
	}

	public void setIdCat(long idCat) {
		this.idCat = idCat;
	}

	public Article(long id, String code, String des, float pa, int qte, long idCat) {
		super();
		this.id = id;
		this.code = code;
		Des = des;
		this.pa = pa;
		this.qte = qte;
		this.idCat = idCat;
	}

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", code=" + code + ", Des=" + Des + ", pa=" + pa + ", qte=" + qte + ", idCat="
				+ idCat + "]";
	}
	
	
	
	
	
}

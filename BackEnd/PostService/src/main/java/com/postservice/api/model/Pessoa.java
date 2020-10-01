package com.postservice.api.model;

import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String sobrenome;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="lugarid", insertable = false, updatable=false)
	private Lugar lugar;

	private Integer lugarid;

	public Integer getLugarid() {
	    return lugarid;
	}

	public void setLocationid(Integer lugarid) {
	    this.lugarid = lugarid;
	}

	private String email;
	
	@OneToMany(mappedBy="pessoa", cascade = CascadeType.ALL)
	private List<Postal> postals;
	
	
	@JsonManagedReference()
	public List<Postal> getPosts() {
		return postals;
	}

	public void setPosts(List<Postal> postals) {
		this.postals = postals;
	}

	public Pessoa() {
		
	}
	
	public Pessoa(Integer id, String nome, String sobrenome, Lugar lugar, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.lugar = lugar;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApellido() {
		return sobrenome;
	}

	public void setApellido(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	@JsonBackReference
	public Lugar getLocation() {
		return lugar;
	}

	public void setLocation(Lugar lugar) {
		this.lugar = lugar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lugar == null) ? 0 : lugar.hashCode());
		result = prime * result + ((lugarid == null) ? 0 : lugarid.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((postals == null) ? 0 : postals.hashCode());
		result = prime * result + ((sobrenome == null) ? 0 : sobrenome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lugar == null) {
			if (other.lugar != null)
				return false;
		} else if (!lugar.equals(other.lugar))
			return false;
		if (lugarid == null) {
			if (other.lugarid != null)
				return false;
		} else if (!lugarid.equals(other.lugarid))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (postals == null) {
			if (other.postals != null)
				return false;
		} else if (!postals.equals(other.postals))
			return false;
		if (sobrenome == null) {
			if (other.sobrenome != null)
				return false;
		} else if (!sobrenome.equals(other.sobrenome))
			return false;
		return true;
	}
	
	
	
}

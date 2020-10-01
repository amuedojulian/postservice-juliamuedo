package com.postservice.api.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Postal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String postDate;
	private String detalhes;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="pessoaid", insertable=false, updatable=false)
	private Pessoa pessoa;
	
	private Integer pessoaid;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,  property = "id")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="destinatarioid", insertable=false, updatable=false)
	private Destinatario destinatario;
	
	private Integer destinatarioid;
	
	public Integer getDestinatarioid() {
		return destinatarioid;
	}

	public void setDestinatarioid(Integer destinatarioid) {
		this.destinatarioid = destinatarioid;
	}
	public Destinatario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}

	public Integer getPessoaid() {
		return pessoaid;
	}

	public void setPessoaid(Integer pessoaid) {
		this.pessoaid = pessoaid;
	}

	public Postal() {
		
	}
	
	public Postal(Integer id, String postDate, Pessoa pessoa, String detalhes, Destinatario destinatario) {
		super();
		this.id = id;
		this.postDate = postDate;
		this.pessoa = pessoa;
		this.detalhes = detalhes;
		this.destinatario = destinatario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	@JsonBackReference
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destinatario == null) ? 0 : destinatario.hashCode());
		result = prime * result + ((destinatarioid == null) ? 0 : destinatarioid.hashCode());
		result = prime * result + ((detalhes == null) ? 0 : detalhes.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result + ((pessoaid == null) ? 0 : pessoaid.hashCode());
		result = prime * result + ((postDate == null) ? 0 : postDate.hashCode());
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
		Postal other = (Postal) obj;
		if (destinatario == null) {
			if (other.destinatario != null)
				return false;
		} else if (!destinatario.equals(other.destinatario))
			return false;
		if (destinatarioid == null) {
			if (other.destinatarioid != null)
				return false;
		} else if (!destinatarioid.equals(other.destinatarioid))
			return false;
		if (detalhes == null) {
			if (other.detalhes != null)
				return false;
		} else if (!detalhes.equals(other.detalhes))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (pessoaid == null) {
			if (other.pessoaid != null)
				return false;
		} else if (!pessoaid.equals(other.pessoaid))
			return false;
		if (postDate == null) {
			if (other.postDate != null)
				return false;
		} else if (!postDate.equals(other.postDate))
			return false;
		return true;
	}
	
	
	
	
	
}

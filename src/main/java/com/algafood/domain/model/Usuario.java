package com.algafood.domain.model;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String email;
	
	private String senha;
	
	@CreationTimestamp
	private OffsetDateTime dataCadastro;
	
	@ManyToMany
	@JoinTable(name = "usuario_grupo"
	, joinColumns = @JoinColumn(name = "usuario_id")
	, inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private Set<Grupo> grupos = new HashSet<>();
	
	
	public boolean senhaCoincideCom(String senha) {
		return getSenha().equals(senha);
	}
	
	public boolean senhaNaoCoincideCom(String senha) {
	    return !senhaCoincideCom(senha);
	}
	
	public boolean adicionarGrupo(Grupo grupo) {
		return getGrupos().add(grupo);
	}
	
	public boolean removerGrupo(Grupo grupo) {
		return getGrupos().remove(grupo);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

}

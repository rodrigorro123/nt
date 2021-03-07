package br.com.nt.domain.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Pauta")
@Table(name = "tbl_pauta")
@Builder
@Data
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Pauta implements Serializable {
	
	private static final long serialVersionUID = -9109414221418128481L;

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pauta")
	 private Long id;
	
	@Column(name = "descricao")
	 private String descricao;
	
	@Column(name = "titulo")
	 private String titulo;

    
	@OneToMany(mappedBy="pauta")
	private Set<Sessao> sessoes;
	
}

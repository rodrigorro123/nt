package br.com.nt.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name= "Votacao")
@Table(name = Votacao.TABLE_NAME)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Votacao implements Serializable  {
 
	private static final long serialVersionUID = -2348089903917261471L;

	public static final String TABLE_NAME = "tbl_votacao";
	
	@Id
	@Column(name = "id_votacao")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	 private Long id;

	@Column(name = "id_sessao")
	 private Long idSessao;

	@Column(name = "cpf")
	 private String cpf;
		
	@Column(name = "voto")
	 private String voto;

}

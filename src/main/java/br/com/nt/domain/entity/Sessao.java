package br.com.nt.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name= "Sessao")
@Table(name = Sessao.TABLE_NAME)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Sessao  implements Serializable {
 
	private static final long serialVersionUID = 1884395550354356546L;


	public static final String TABLE_NAME = "tbl_sessao";
	
	@Id
	@Column(name = "id_sessao")
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	 private Long id;
	
	@Column(name = "titulo")
	 private String titulo;
	
	@Column(name = "descricao")
	 private String descricao;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "dt_validade")
	 private LocalDateTime dtValidade;

	
	@ManyToOne
    @JoinColumn(name="id_pauta", nullable=false)
	 private Pauta pauta;
 
	@Transient
    @OneToMany(mappedBy = "sessao", fetch = FetchType.EAGER, orphanRemoval=true)
    private Set<Votacao> votacao = new HashSet<>();	

	
}

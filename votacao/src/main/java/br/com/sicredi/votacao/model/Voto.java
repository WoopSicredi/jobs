package br.com.sicredi.votacao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_voto", schema = "votacao")
public class Voto implements Serializable {

	private static final long serialVersionUID = -3734493856861925768L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_voto")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_sessao", referencedColumnName = "id_sessao")
	private Sessao sessao;
	
	@ManyToOne
	@JoinColumn(name = "id_associado", referencedColumnName = "id_associado")
	private Associado associado;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "valor")
	private ValorVoto valorVoto;
	
}

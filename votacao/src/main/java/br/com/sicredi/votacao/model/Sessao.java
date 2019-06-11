package br.com.sicredi.votacao.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tb_sessao", schema = "votacao")
public class Sessao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4752405062651483232L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sessao")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_pauta", referencedColumnName = "id_pauta")
	private Pauta pauta;
	
	@Column(name = "dt_inicio", nullable = false)
	private LocalDateTime inicio;
	
	@Column(name = "dt_fim", nullable = false)
	private LocalDateTime fim;
	
	public boolean isClosed() {
		return LocalDateTime.now().isAfter(fim);
	}
	
}
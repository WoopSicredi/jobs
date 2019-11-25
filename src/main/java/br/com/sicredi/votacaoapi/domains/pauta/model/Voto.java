package br.com.sicredi.votacaoapi.domains.pauta.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ruddy Paz
 */
@Entity
@Getter @Setter
@NoArgsConstructor
public class Voto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pauta_id", referencedColumnName = "id")
	private Pauta pauta;
	
	@Column(name="usuario_id", nullable = false)
	private Long usuarioId;
	
	@Column(nullable = false)
	private Boolean decisao;
	
	public Voto(Pauta pauta, Long usuarioId, Boolean decisao) {
		this.pauta = pauta;
		this.usuarioId = usuarioId;
		this.decisao = decisao;
	}
	
}

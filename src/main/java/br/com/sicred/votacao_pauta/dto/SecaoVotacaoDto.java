package br.com.sicred.votacao_pauta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecaoVotacaoDto {
    private Long idPauta;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataEncerramento;
}

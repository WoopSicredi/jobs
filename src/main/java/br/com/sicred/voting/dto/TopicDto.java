package br.com.sicred.voting.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Pauta", description="Modelo que encapsula dados de pauta")
public class TopicDto {
    @NotEmpty
    @ApiModelProperty("Descrição da pauta")
    private String description;
}

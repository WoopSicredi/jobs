package com.sicredi.test.web.dto;

/**
 * Objeto de transfência de criação de um uma votação em uma pauta.
 */
public class PollCreationDto {

    private int durationInMinutes;

    public PollCreationDto() {
    }

    public PollCreationDto(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
}

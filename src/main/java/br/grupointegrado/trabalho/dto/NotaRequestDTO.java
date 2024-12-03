package br.grupointegrado.trabalho.dto;

import java.time.LocalDate;

public record NotaRequestDTO(Integer matriculaId, Integer disciplinaId, Double nota, LocalDate dataLancamento) {}

package br.grupointegrado.trabalho.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NotaRequestDTO(Integer matriculaId, Integer disciplinaId, BigDecimal nota, LocalDate dataLancamento) {}

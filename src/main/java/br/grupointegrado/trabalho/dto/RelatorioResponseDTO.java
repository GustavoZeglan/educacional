package br.grupointegrado.trabalho.dto;

import br.grupointegrado.trabalho.model.Nota;

import java.util.List;

public record RelatorioResponseDTO(List<NotaResponseDTO> notas) {
}

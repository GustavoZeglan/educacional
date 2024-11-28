package br.grupointegrado.trabalho.dto;

import java.sql.Date;

public record AlunoRequestDTO(String nome, String email, String matricula, Date dataNascimento) {}
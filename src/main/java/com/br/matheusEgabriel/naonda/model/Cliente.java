package com.br.matheusEgabriel.naonda.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class Cliente {
	
	private Long id;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar dataNascimento;
	private String genero;
	private String produtoInteresse;
	private Calendar dataCadastro;
	
	
	public Calendar getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getProdutoInteresse() {
		return produtoInteresse;
	}
	public void setProdutoInteresse(String produtoInteresse) {
		this.produtoInteresse = produtoInteresse;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
		
	public int getIdade() {
			
			int dia = dataNascimento.get(Calendar.DAY_OF_MONTH);
			int mes = dataNascimento.get(Calendar.MONTH);
			int ano = dataNascimento.get(Calendar.YEAR);
			LocalDate dataNasc = LocalDate.of(ano, mes+1, dia);
			LocalDate dataAtual = LocalDate.now();
			Period periodo = Period.between(dataNasc, dataAtual);
			return periodo.getYears();
		}
	
}

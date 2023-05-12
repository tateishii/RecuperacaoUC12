package br.com.recuperacao.domain;

import java.sql.Date;

public class Apapaconha {
	
	private Long idChamado;
	private String nomePessoa;
	private String departamento;
	private String descricao;
	private Date dataAbertura;
	private Date dataResolucao;
	private String statusChamado;
	private String observacao;
	private String nomeFuncionario;
	
	public Apapaconha() {
	}

	public Apapaconha(Long idChamado, String nomePessoa, String departamento, String descricao, Date dataAbertura,
			Date dataResolucao, String statusChamado, String observacao, String nomeFuncionario) {
		this.idChamado = idChamado;
		this.nomePessoa = nomePessoa;
		this.departamento = departamento;
		this.descricao = descricao;
		this.dataAbertura = dataAbertura;
		this.dataResolucao = dataResolucao;
		this.statusChamado = statusChamado;
		this.observacao = observacao;
		this.nomeFuncionario = nomeFuncionario;
	}

	public Long getIdChamado() {
		return idChamado;
	}

	public void setIdChamado(Long idChamado) {
		this.idChamado = idChamado;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataResolucao() {
		return dataResolucao;
	}

	public void setDataResolucao(Date dataResolucao) {
		this.dataResolucao = dataResolucao;
	}

	public String getStatusChamado() {
		return statusChamado;
	}

	public void setStatusChamado(String statusChamado) {
		this.statusChamado = statusChamado;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	

	
	
}

package br.com.recuperacao.dao;

import java.util.List;

public interface CRUD<T> {
	public String gravar(T obj);
	List<T> listar();
	List<T> lista(T obj);
	T atualizar(T obj);
	String apagar(T obj);

}

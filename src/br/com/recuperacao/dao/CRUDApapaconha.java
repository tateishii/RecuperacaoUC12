package br.com.recuperacao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.recuperacao.domain.Apapaconha;

public class CRUDApapaconha extends Conexao implements CRUD<Apapaconha> {

	@Override
	public String gravar(Apapaconha obj) {
		String msg = "";
		try {
			abrirConexao();
			String sql = "insert into chamado(nomepessoa,departamento,descricao,dataabertura,dataresolucao,statuschamado,observacao,nomefuncionario)values(?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, obj.getNomePessoa());
			pst.setString(2, obj.getDepartamento());
			pst.setString(3, obj.getDescricao());
			pst.setDate(4, obj.getDataAbertura());
			pst.setDate(5, obj.getDataResolucao());
			pst.setString(6, obj.getStatusChamado());
			pst.setString(7, obj.getObservacao());
			pst.setString(8, obj.getNomeFuncionario());
			
			int i = pst.executeUpdate();
			
			if(i>0) {
				msg = "Chamada registrada com sucesso!";
			}
			else {
				msg = "Não foi possível registrar a chamada";
			}
		}
		catch(SQLException se) {
			msg = "Erro de sql -> "+se.getMessage();
		}
		catch(Exception e) {
			msg = "Erro inêsperado."+e.getMessage();
		}
		finally {
			fecharConexao();
		}
		
		return msg;
	}

	@Override
	public List<Apapaconha> listar() {
		List<Apapaconha> lstChamado = new ArrayList<Apapaconha>();
		
		try {
			abrirConexao();
			String sql = "select * from chamado";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Apapaconha apapaconha = new Apapaconha();
				
				apapaconha.setIdChamado(rs.getLong(1));
				apapaconha.setNomePessoa(rs.getString(2));
				apapaconha.setDepartamento(rs.getString(3));
				apapaconha.setDescricao(rs.getString(4));
				apapaconha.setDataAbertura(rs.getDate(5));
				apapaconha.setDataResolucao(rs.getDate(6));
				apapaconha.setStatusChamado(rs.getString(7));
				apapaconha.setObservacao(rs.getString(8));
				apapaconha.setNomeFuncionario(rs.getString(9));
				
				lstChamado.add(apapaconha);
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			fecharConexao();
		}
		
		return lstChamado;
	}

	@Override
	public List<Apapaconha> lista(Apapaconha obj) {
		List<Apapaconha> lstChamado = new ArrayList<Apapaconha>();
		
		try {
			abrirConexao();
			String sql = "";
			
			if(obj.getIdChamado()!=null) {
				sql = "select * from chamado where idchamado="+obj.getIdChamado();
			}
			else if(obj.getNomePessoa()!=null) {
				sql = "select * from chamado where nomepessoa like "+obj.getNomePessoa()+"%";
			}
			else if(obj.getDepartamento()!=null) {
				sql = "select * from chamado where departamento like "+obj.getDepartamento()+"%";
			}
			else if(obj.getDescricao()!=null) {
				sql = "select * from chamado where descricao like %"+obj.getDescricao()+"%";
			}
			else {
				sql = "select * from chamado";
			}
			
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Apapaconha apapaconha = new Apapaconha();
				
				apapaconha.setIdChamado(rs.getLong(1));
				apapaconha.setNomePessoa(rs.getString(2));
				apapaconha.setDepartamento(rs.getString(3));
				apapaconha.setDescricao(rs.getString(4));
				apapaconha.setDataAbertura(rs.getDate(5));
				apapaconha.setDataResolucao(rs.getDate(6));
				apapaconha.setStatusChamado(rs.getString(7));
				apapaconha.setObservacao(rs.getString(8));
				apapaconha.setNomeFuncionario(rs.getString(9));
				
				lstChamado.add(apapaconha);
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			fecharConexao();
		}
		
		return lstChamado;
	}

	@Override
	public Apapaconha atualizar(Apapaconha obj) {
		Apapaconha Apapaconha = new Apapaconha();
		
		try {
			abrirConexao();
			String sql = "update chamado set=nomepessoa=?,departamento=?,descricao=?,dataabertura=?,dataresolucao=?,statuschamado=?,observacao=?,nomefuncionario=? where idcurso=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, obj.getNomePessoa());
			pst.setString(2, obj.getDepartamento());
			pst.setString(3, obj.getDescricao());
			pst.setDate(5, obj.getDataAbertura());
			pst.setDate(6, obj.getDataResolucao());
			pst.setString(7, obj.getObservacao());
			pst.setString(8, obj.getNomeFuncionario());
			pst.setLong(9, obj.getIdChamado());
			
			int i = pst.executeUpdate();
			if(i>0) {
				Apapaconha = obj;
			}
			else {
				throw new Exception("Não foi possível atualizar");
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			fecharConexao();
		}
		
		return Apapaconha;
	}

	@Override
	public String apagar(Apapaconha obj) {
		String msg = "";
		
		try {
			abrirConexao();
			String sql = "delete * from chamado where idchamado=?";
			pst = conn.prepareStatement(sql);
			pst.setLong(1, obj.getIdChamado());
			int i = pst.executeUpdate();
			if(i>0) {
				msg = "Chamada removida com sucesso!";
			}
			else {
				msg = "Não foi possível remover a chamada.";
			}
		}
		catch(SQLException se) {
			msg = "Erro na consulta de SQL -> "+se.getMessage();
		}
		catch(Exception e) {
			msg = "Erro inesperado -> "+e.getMessage();
		}
		finally {
			fecharConexao();
		}
		return msg;
	}

}

package com.br.matheusEgabriel.naonda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.br.matheusEgabriel.naonda.model.Cliente;

public class DaoCliente {

	private Connection conexao;

	public DaoCliente() {
		// VIA PARA ENVIAR COMANDOS AO BANCO
		conexao = ConnectionFactory.conectar();
	}

	public void inserir(Cliente cliente) {

		String sql = "insert into cliente"
				+ "(nome, endereco, telefone, email, dataNascimento, dataCadastro, genero, produtoInteresse)"
				+ "values(?,?,?,?,?,?,?,?)";
		// SUBSTITUINDO OS ? PELO VALOR
		// COMANDO PREPARADO PreparedStatement
		PreparedStatement stmt;
		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEndereco());
			stmt.setString(3, cliente.getTelefone());
			stmt.setString(4, cliente.getEmail());
			stmt.setDate(5, new Date(cliente.getDataNascimento().getTimeInMillis()));
			stmt.setTimestamp(6, new Timestamp(Calendar.getInstance().getTimeInMillis()));
			stmt.setString(7, cliente.getGenero());
			stmt.setString(8, cliente.getProdutoInteresse());

			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cliente> listar() {

		String sql = "select * from cliente order by id asc";
		PreparedStatement stmt;

		List<Cliente> lista = new ArrayList<Cliente>();

		try {

			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Cliente c = new Cliente();

				c.setId(rs.getLong("id"));
				c.setNome(rs.getString("nome"));
				c.setEndereco(rs.getString("endereco"));
				c.setEmail(rs.getString("email"));
				c.setGenero(rs.getString("genero"));
				c.setProdutoInteresse(rs.getString("produtoInteresse"));
				c.setTelefone(rs.getString("telefone"));
				// CRIAR O CALENDAR COM A DATA ATUAL
				Calendar nascimento = Calendar.getInstance();
				// EXTRAIR O java.sql.Date do BD
				Date nascDb = rs.getDate("dataNascimento");
				// PASSAR O LONG DO java.sql.Date PARA O CALENDAR
				nascimento.setTimeInMillis(nascDb.getTime());
				// SETAR O NASCIMENTO NO JOGADOR
				c.setDataNascimento(nascimento);
				System.out.println(c.getId());
				lista.add(c);

			}

			rs.close();
			stmt.close();
			conexao.close();
			return lista;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public void excluir(long idCliente) {

		String sql = "delete from cliente where id= ?";

		PreparedStatement stmt;

		try {
			// METODO DE EXCLUIR
			stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, idCliente);
			stmt.execute();
			stmt.close();
			conexao.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void atualizar(Cliente cliente) {

		String sql = "update cliente set nome = ?,endereco  = ?, telefone = ?, email = ?, dataNascimento = ?, genero = ?, produtoInteresse = ? where id = ?";
		// SUBSTITUINDO OS ? PELO VALOR
		// COMANDO PREPARADO PreparedStatement
		PreparedStatement stmt;
		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEndereco());
			stmt.setString(3, cliente.getTelefone());
			stmt.setString(4, cliente.getEmail());
			stmt.setDate(5, new Date(cliente.getDataNascimento().getTimeInMillis()));
			stmt.setString(6, cliente.getGenero());
			stmt.setString(7, cliente.getProdutoInteresse());
			stmt.setLong(8, cliente.getId());

			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Cliente buscar(Long idCliente) {

		String sql = "select * from cliente where id = ?";
		PreparedStatement stmt;

		Cliente c = null;

		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, idCliente);
			;
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				c = new Cliente();

				c.setId(rs.getLong("id"));
				c.setNome(rs.getString("nome"));
				c.setEndereco(rs.getString("endereco"));
				c.setEmail(rs.getString("email"));
				c.setGenero(rs.getString("genero"));
				c.setProdutoInteresse(rs.getString("produtoInteresse"));
				c.setTelefone(rs.getString("telefone"));
				// CRIAR O CALENDAR COM A DATA ATUAL
				Calendar nascimento = Calendar.getInstance();
				// EXTRAIR O java.sql.Date do BD
				Date nascDb = rs.getDate("dataNascimento");
				// PASSAR O LONG DO java.sql.Date PARA O CALENDAR
				nascimento.setTimeInMillis(nascDb.getTime());
				// SETAR O NASCIMENTO NO JOGADOR
				c.setDataNascimento(nascimento);

			}

			rs.close();
			stmt.close();
			conexao.close();
			return c;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public int contar(String genero) {

		String sql = "SELECT COUNT(genero) AS contador FROM `cliente` WHERE genero = ?";
		PreparedStatement stmt;

		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, genero);
			int qtd = 0;
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				qtd = rs.getInt("contador");
			}
			stmt.execute();
			stmt.close();
			conexao.close();
			return qtd;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int dia(Long idCliente) {

		String sql = "SELECT dataCadastro FROM `cliente` WHERE id = ?";
		PreparedStatement stmt;

		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, idCliente);

			ResultSet rs = stmt.executeQuery();

			int valorDiaSemana = 0;
			if (rs.next()) {
				Calendar dia = Calendar.getInstance();
				Date diaSql = rs.getDate("dataCadastro");
				dia.setTime(diaSql);
				valorDiaSemana = dia.get(Calendar.DAY_OF_WEEK);
			}
			stmt.execute();
			stmt.close();
			conexao.close();
			return valorDiaSemana;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int hora(Long idCliente) {

		String sql = "select HOUR(dataCadastro)AS 'horario de cadastro' from cliente WHERE id = ?";
		PreparedStatement stmt;

		try {

			stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, idCliente);

			ResultSet rs = stmt.executeQuery();

			int horarioCad = 0;
			if(rs.next()) {
			
				horarioCad = rs.getInt("horario de cadastro");

			}
			
			stmt.execute();
			stmt.close();
			conexao.close();
			return horarioCad;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}

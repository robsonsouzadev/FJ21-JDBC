package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.exception.DAOException;
import br.com.caelum.jdbc.modelo.Contato;

public class ContatoDao {

	private Connection connection;

	public ContatoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Contato contato) {
		String sql = "insert into contatos (nome, email, endereco, dataNascimento) values (?, ?, ?, ?)";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, contato.getNome());
			statement.setString(2, contato.getEmail());
			statement.setString(3, contato.getEndereco());
			statement.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));

			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public List<Contato> getLista() {
		String sql = "select * from contatos";

		try {
			List<Contato> contatos = new ArrayList<Contato>();

			PreparedStatement statement = this.connection.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Contato contato = new Contato();

				contato.setId(result.getLong("id"));
				contato.setNome(result.getString("nome"));
				contato.setEmail(result.getString("email"));
				contato.setEndereco(result.getString("endereco"));

				Calendar data = Calendar.getInstance();
				data.setTime(result.getDate("dataNascimento"));
				contato.setDataNascimento(data);

				contatos.add(contato);
			}

			result.close();
			statement.close();

			return contatos;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public Contato pesquisar(int id) {
		String sql = "select * from contatos where id = ?";

		try {
			PreparedStatement statement = this.connection.prepareStatement(sql);

			statement.setLong(1, id);

			ResultSet result = statement.executeQuery();

			result.next();

			Contato contato = new Contato();

			contato.setId(result.getLong("id"));
			contato.setNome(result.getString("nome"));
			contato.setEmail(result.getString("email"));
			contato.setEndereco(result.getString("endereco"));

			Calendar data = Calendar.getInstance();
			data.setTime(result.getDate("dataNascimento"));
			contato.setDataNascimento(data);

			result.close();
			statement.close();

			return contato;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public void altera(Contato contato) {
		String sql = "update contatos set nome = ?, email = ?, endereco = ?, dataNascimento = ? where id = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, contato.getNome());
			statement.setString(2, contato.getEmail());
			statement.setString(3, contato.getEndereco());
			statement.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			statement.setLong(5, contato.getId());

			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public void remove(Contato contato) {
		String sql = "delete from contatos where id = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setLong(1, contato.getId());

			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
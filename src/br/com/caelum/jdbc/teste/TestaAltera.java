package br.com.caelum.jdbc.teste;

import java.text.SimpleDateFormat;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaAltera {

	public static void main(String[] args) {
		ContatoDao dao = new ContatoDao();

		Contato contato = dao.pesquisar(1);
		contato.setNome("Caelum SP");

		dao.altera(contato);

		System.out.println("Alterado!\n");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println(contato.getId());
		System.out.println(contato.getNome());
		System.out.println(contato.getEmail());
		System.out.println(contato.getEndereco());
		System.out.println(sdf.format(contato.getDataNascimento().getTime()));
	}
}
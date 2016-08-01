package br.com.caelum.jdbc.exception;

@SuppressWarnings("serial")
public class DAOException extends RuntimeException {

	public DAOException(Exception e) {
		e.printStackTrace();
	}
}
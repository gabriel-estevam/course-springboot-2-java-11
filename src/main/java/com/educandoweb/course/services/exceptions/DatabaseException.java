package com.educandoweb.course.services.exceptions;
//classe para trabalhar com exceções personalizadas, essa exceções é que o banco de dados gera
//um exemplo é a IntegrityViolationException - erro de integridade de dados
public class DatabaseException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String msg) {
		super(msg);
	}
}

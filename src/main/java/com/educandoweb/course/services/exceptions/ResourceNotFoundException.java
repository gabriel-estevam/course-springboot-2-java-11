package com.educandoweb.course.services.exceptions; //nesse pacote vamos tratar as execeções (erros) que
//a camada de serviços (service) pode gerar, como por exemplo pesquisar um "Usuario user" onde seu Id não exista
//essa camada terá que tratar

public class ResourceNotFoundException extends RuntimeException 
{
//exceção personalizada de tempo de excecução (runtimeException)
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		//esse construtor tem como parametro um tipo "Object", no parametro virá o id do objeto não encontrado
		super("Resource not found. Id "+ id); //menssagem de erro que ele vai retornar
	}
}

package com.educandoweb.course.services.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler; //nesse pacote fica o tratamento manual das exceptions

import com.educandoweb.course.resources.exceptions.StandardError;
//nesse classe vamos tratar manuamente nossas exceções

//essa anotação vai intecerptar as exceções que acontecerem, para que o objeto possar executar o tratamento
@ControllerAdvice
public class ResourceExceptionHandler 
{
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request)
	{
		/*metodo para tratar o erro "Resource not found exception", esse metodo recebe como parametro
		 * 1 - uma execeção do tipo ResourNotFoudnException
		 * 2 - depois um objeto do tipo HttpServerLetRequest que é uma interface de requsição
		 * a anotação desse metodo é para informar o meu controllerAdvice jogar aqui, tem como argumento a classe da exceção
		 * ResourceNotFoudnException (por isso o .class)
		 * */
		String error = "Resource not found"; //String do erro que ele vai retornar
		HttpStatus status = HttpStatus.NOT_FOUND; //o status, o tipo de retorno do erro que no caso é o 404 not found
		//abaixo instaciamos um objeto do tipo StandError e com isso passamos como parametro os objetos acima e os que vem do pametro
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		//status.value porque é uma integer
		return ResponseEntity.status(status).body(err); //retorna a mensagem de erro no body
	}
}

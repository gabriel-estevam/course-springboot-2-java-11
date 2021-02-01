package com.educandoweb.course.resources; //essa camada é responsavel pelo funcionamento dos Restcontrollers da aplicação

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.User;

@RestController //aqui estamos dizendo que essa classe é uma RestController
@RequestMapping(value = "/users") /*aqui estamos dizendo que o resultado (requisção) vai ser lançado o recurso
nesse caso o resultado vai aparacer na pagina web na seção users, (se observamos a url no navegador fica: localhost:8080/users 
nessa seção ele vai mostrar os dados em um formato .json)
*/
public class UserResource {
	
	@GetMapping //anotação dizendo que o metodo vai retorna uma resposta
	public ResponseEntity<User> findAll() {
		//esse metodo vai ser responsavel por retorna uma respota, do tipo GET do HTTP ao Request 
		User u = new User(1L, "Maria", "maria@gmail.com", "999999", "12345"); //para teste
		return ResponseEntity.ok().body(u); //vai retornar no corpo da resposta o usuário "u" 
	}
}

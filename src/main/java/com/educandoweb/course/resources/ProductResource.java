package com.educandoweb.course.resources; //essa camada é responsavel pelo funcionamento dos Restcontrollers da aplicação

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.services.ProductService;

@RestController //aqui estamos dizendo que essa classe é uma RestController
@RequestMapping(value = "/products") /*aqui estamos dizendo que o resultado (requisção) vai ser lançado o recurso
nesse caso o resultado vai aparacer na pagina web na seção users, (se observamos a url no navegador fica: localhost:8080/users 
nessa seção ele vai mostrar os dados em um formato .json)
*/
public class ProductResource {
	
	@Autowired //declarado um dependencia para o service
	private ProductService service;
//essa dependencia vai se comunicar com a camada de serviços
//vamos usar essa dependencia dentro dos metodos conforme vamos desenvolvendo
//podemos concluir que mantendo esse padrão de arquitetura, cada metodo vai ter seu determinado mecanismo e esssa camada
//não ficara sobre-carregada de regra de negocio

	@GetMapping //anotação dizendo que o metodo vai retorna uma resposta
	public ResponseEntity<List<Product>> findAll() {
		//esse metodo vai ser responsavel por retorna uma respota, do tipo GET do HTTP ao Request 
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list); //vai retornar no corpo da resposta o usuário "u" 
	}
	
	@GetMapping(value = "/{id}") //aqui estamos dizendo que o metodo recebera um parametro como argumento
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		//metodo par retorna uma usuário pelo seu
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}

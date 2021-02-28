package com.educandoweb.course.resources; //essa camada é responsavel pelo funcionamento dos Restcontrollers da aplicação

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController //aqui estamos dizendo que essa classe é uma RestController
@RequestMapping(value = "/users") /*aqui estamos dizendo que o resultado (requisção) vai ser lançado o recurso
nesse caso o resultado vai aparacer na pagina web na seção users, (se observamos a url no navegador fica: localhost:8080/users 
nessa seção ele vai mostrar os dados em um formato .json)
*/
public class UserResource {
	
	@Autowired //declarado um dependencia para o service
	private UserService service;
//essa dependencia vai se comunicar com a camada de serviços
//vamos usar essa dependencia dentro dos metodos conforme vamos desenvolvendo
//podemos concluir que mantendo esse padrão de arquitetura, cada metodo vai ter seu determinado mecanismo e esssa camada
//não ficara sobre-carregada de regra de negocio

	@GetMapping //anotação dizendo que o metodo vai retorna uma resposta
	public ResponseEntity<List<User>> findAll() {
		//esse metodo vai ser responsavel por retorna uma respota, do tipo GET do HTTP ao Request 
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list); //vai retornar no corpo da resposta o usuário "u" 
	}
	
	@GetMapping(value = "/{id}") //aqui estamos dizendo que o metodo recebera um parametro como argumento
	
	public ResponseEntity<User> findById(@PathVariable Long id) {
		//metodo par retorna uma usuário pelo seu
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//anotação para inserir um usuario no banco de dados, no caso de inserção usamos o metodo POST
	//ao usar essa anotação estamos dizendo que o metodo vai receber o metodo POST do HTTP
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) 
	{
		/*metodo para inserir um  usuario no banco de dados (objeto), tem como
		 * parametro uma objeto do tipo User, o parametro utiliza a anotação RequestBody, 
		 * significa que ele vai receber informações no formato JSON, 
		 * exemplo:{id: 3, name:"Jose", phone:1234434"}*/
		obj = service.insert(obj); //o objeto do parametro recebe o resultado do Service fazendo a inseração
		/*Quando fazemos a inserção no banco de dados, é adequado termos como resposta o codigo
		 * 201 do HTTP, ele indica que foi inserido um novo recurso, esse codigo visualizamos la no PostMan
		 * As linhas abaixo fazem essa configuração*/
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri(); //variavel que guarda o endereço de inserção (url do browser)
		//detalhe so estamos fazendo a configuração acima pois, para retorna o codigo 201 vamos chamar o metodo created abaixo
		//essa metodo espera como parametro uma URI
		return ResponseEntity.created(uri).body(obj); //retorna objeto inserido (body) mais o codigo de inseração 201 (created)
	}
	
	@DeleteMapping(value = "/{id}") //anotação do spring boot para deletar
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		//metodo para deletar, não vai retornar nenhum body
		service.delete(id); //serviço chama o metodo delete do UserService
		return ResponseEntity.noContent().build(); //retorna uma resposta vazia, ja tratando 
		//o codigo 204 - resposta que não tem conteudo
	}
}

package com.educandoweb.course.services; //camada de serviço, criamos essa camada para implementar a regra de negocio 
//assim fica seperado as responsabilidades e não deixa que o controlador fique carregado demais de regra de negocio
//na nossa arquitetura a comada de controller depende de serviço, e a camada de serviço por sua vez depende da camada de repository

//Essa camada vai ficar "repasando" as operaçõs da camada de repository
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.resources.repositories.UserRepository;

@Service //essa anotação indica que essa classe é uma classe de serviço, assim fica mais semantico e facil de entender
public class UserService {
//dessa forma registramos a nossa classe para o freamework funcionar, é bom manter essa boa pratica nas demais classes	
	@Autowired //anotação dependencia, aqui dizemos que temos uma dependencia 
	//da classe UserRepository
	private UserRepository repository; //com essa dependencia, temos acesso a todos os metodos para acesso
	//a dados do Usuário
	
	public List<User> findAll()  {
		//metodo para retornar todos os usuários do banco de dados
		return repository.findAll();
	}
	
	public User findById(Long id) {
	//metodo para retornar um usuário pelo seu Id	
		Optional <User> obj = repository.findById(id);
		return obj.get();
	}
	
}


package com.educandoweb.course.services; //camada de serviço, criamos essa camada para implementar a regra de negocio 
//assim fica seperado as responsabilidades e não deixa que o controlador fique carregado demais de regra de negocio
//na nossa arquitetura a comada de controller depende de serviço, e a camada de serviço por sua vez depende da camada de repository

//Essa camada vai ficar "repasando" as operaçõs da camada de repository
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.resources.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

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
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
		/*mudado o .get para orElseThrow - esse metodo vai lançar uma exceção se não gerar o get
		 * dentro do metodo passamos uma expressão lambda que instancia a exceção ResourceNotFoundException*/
	}
	
	public User insert(User obj) {
	//metodo para salvar no banco de dados,
		return repository.save(obj); //retorna o usuário salvo
	}
	
	public void delete(Long id) {
	//metodo para deletar um usuario do banco de dados pelo seu id
	//abaixo estamos tratando a execeção do delete
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			/*Essa exceção trata quando o id do usuário a ser deletado não existe
			 * Usado a exceção RuntimeException e o printStackTrace e.printStackTrace();
			 * para verificar que tipo de erro o hibernete gera, isso é uma tecnica usada
			 * para descobrir que tipo de erro esta gerando para assim então tratar corretamente,
			 * descobrimos que a exceção gera é EmptyResultDataAccessException
			 */
			throw new ResourceNotFoundException(id); //lança a exceção de recuros não encontrado
		}
		catch(DataIntegrityViolationException e) {
			/* essa exceção trata quando o id do usuário esta associado com outros registros no banco de dados
			que é o erro de integridade e.printStackTrace(), indentificado que a exceção gerada é 
			o DataIntegrityViolationException exceção do Spring para lançar essa exceção foi necessário 
			criar uma classe (personalizada) para tratar de erro de integridade de dados */
			throw new DatabaseException(e.getMessage()); //lança a exceção de erro com o banco de dados
		}
	}
	
	public User update(Long id, User obj) {
	//Metodo para atualizar um usuario, tem como parametro um id e um objeto do tipo User
		User entity = repository.getOne(id); /*declarado essa variavel que sera nossa entidade
		monitorada pelo JPA, recebe o repositoy.getOne(id) que chama a função getOne - esse metodo
		instancia um usuário, porem ele não acessa o banco de dados ainda, isto é ele vai deixar
		um objeto JPA monitorando, e em seguida efetuar uma operação com o banco de dados*/
		updateData(entity, obj); //função para atualizar os dados do entity
		return repository.save(entity); //salva o objeto
	}

	private void updateData(User entity, User obj) {
		//metodo para atualizar os dados da entidade, com base
		//com que chegou do obj do parametro
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}


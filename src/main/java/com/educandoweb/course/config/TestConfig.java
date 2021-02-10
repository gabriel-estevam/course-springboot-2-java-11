package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.resources.repositories.OrderRepository;
import com.educandoweb.course.resources.repositories.UserRepository;

//Essa classe é uma classe especifica de configuração da aplicação, ele não faz parte de nenhuma camada

@Configuration //essa anotação indica que essa classe é uma classe de configuração
@Profile("test") //aqui indica o perfil da configuração, que foi definida no arquivo application.properties (spring.profiles.active=test)
public class TestConfig implements CommandLineRunner {
//essa configuração por enquanto sera utilizada para fazer o database seeding, que é a inserção no banco de dados
	
	@Autowired //essa anotação indica que existe uma dependencia
	private UserRepository userRepository; //Dependencia para salvar um objeto (insert) no banco de dados
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// esse metodo é da implementação da interface commandiLineRunner, que vamos usar para fazer o database seeding
		// tudo que for colocado aqui dentro sera executado quando a aplicação rodar
		
		//para fazer inseração (teste), instanciamos dois objetos 
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		//apos instanciar chamamos a dependencia (atributo) do userRepository chamando o metodo saveAll, 
		//esse metodo recebe um array de objetos e salva no banco de dados
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.PAID);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2, OrderStatus.WAITING_PAYMENT);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1, OrderStatus.WAITING_PAYMENT); 
		
		userRepository.saveAll(Arrays.asList(u1,u2)); //salvando os objetos no banco de dados
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
	}
}

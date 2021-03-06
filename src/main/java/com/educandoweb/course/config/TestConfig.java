package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.Payment;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.resources.repositories.CategoryRepository;
import com.educandoweb.course.resources.repositories.OrderItemRepository;
import com.educandoweb.course.resources.repositories.OrderRepository;
import com.educandoweb.course.resources.repositories.ProductRepository;
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
	
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// esse metodo é da implementação da interface commandiLineRunner, que vamos usar para fazer o database seeding
		// tudo que for colocado aqui dentro sera executado quando a aplicação rodar
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		//abaixo estamos fazendo as associções dos Produtos com suas Categorias, aqui estamos usando o paradigma orientado a objeto
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5)); //salva novamente no banco ja com os objetos associados
		
		//para fazer inseração (teste), instanciamos dois objetos 
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		//apos instanciar chamamos a dependencia (atributo) do userRepository chamando o metodo saveAll, 
		//esse metodo recebe um array de objetos e salva no banco de dados
		userRepository.saveAll(Arrays.asList(u1,u2)); //salvando os objetos no banco de dados
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.PAID);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2, OrderStatus.WAITING_PAYMENT);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1, OrderStatus.WAITING_PAYMENT); 
		
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice()); 
		
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));
		/*Inserindo um pagamento para o Order o1, pois o status dele esta como PAID
		 * para isso instanciamentos um Payment usando o construtor com argumentos, passando o id
		 como nulo, o instant sendo duas horas depois, indica que foi pago duas horas de pois e por
		 uiltimo o objeto que esta com status pago*/
		Payment pay1 = new Payment(null,Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1); /*Ao inves de passar um repository para o Payment, vamos setar ele
		direto no Order "o1" passando como paramento o pay1 - objeto pagamento*/
		
		orderRepository.save(o1); //salva novamente o pedido
	}
}

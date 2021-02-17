package com.educandoweb.course.resources.repositories; //esse package faz para da camanda resource

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Product;

//@Repository, poderia deixar essa anotação, mas como a interface ja herda do JpaRepositoy, então automaticamente essa classe ja esta registrada
public interface ProductRepository  extends JpaRepository<Product, Long> {
 //essa interface herda todos os recursos do Repository que o usuario tem, então essa interface sera responsavel por
//fazer operações com o objeto "Product".
//JpaRepositoy é uma ineterface generica, passamos o tipo da entidade que vamos utilizar e o tipo do id da entidade
//tem que ser do mesmo tipo que foi definida la na classe entidade
	
}

package com.educandoweb.course.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.educandoweb.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_order_item") //nome da tabela no banco de dados
public class OrderItem implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	//anotação que indica que o atributo é uma chave composta
	@EmbeddedId 
	private OrderItemPK id = new OrderItemPK(); //atributo identificador da item-pedido,
	//sempre devemos intanciar se não ele começa como nulo e vai gerar o erro nullPointerException
	
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
	}

	public OrderItem(Order order, Product product,Integer quantity, Double price) {
		super();
		id.setOrder(order); //seta o pedido para a classe auxiliar, usando o metodo set da classe
		id.setProduct(product); //seta o produto para a classe auxiliar, usando o metodo set da classe
		this.quantity = quantity;
		this.price = price;
	}
	

//calocado a anotação do jsonIgone nesse metodo pois é ele que retornar
	@JsonIgnore 
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}

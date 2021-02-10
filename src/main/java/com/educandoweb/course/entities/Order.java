package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_order") //no banco de dados essa classe se chamara "tb_order", pois order é uma palavra reservada do SQL
public class Order implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd 'T' HH:mm:ss 'Z'", timezone = "GMT")
	private Instant moment; //para trabalhar com datas, ficou disponivel apartir da versão java 8
	
	/*a anotação ManyToOne indica que um client (classe User) tem muitos pedidos (classe Order)
	 * Então podemos dizer que um cliente tem muitos pedidos.
	 * A anotação JoinColumn indica que la no banco de dados na tabela de tb_order
	 * tera uma FK chamada client_Id e que estamos juntando com a tb_user
	 * */
	
	private Integer orderStatus; /*internamente na classe esse atributo é inteiro, pois vou salvar o valor no banco de dados
	porem externamente (no transporte) ele é uma classe enum
	*/

	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client; //Relacionamento - um pedido tem um cliente
	
	public Order() {
		
	}

	public Order(Long id, Instant moment, User client, OrderStatus orderStatus) {
		super();
		this.id = id;
		this.moment = moment;
		this.client = client;
		setOrderStatus(orderStatus);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public OrderStatus getOrderStatus() {
	/*Macete para retornar um valor inteiro do status do pedido,
	 * retornamos a função da estatica da classe OrderStatus passando
	 * como parametro o atributo dessa classe*/
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
	//Macete para setar um status do pedido
		if(orderStatus != null) {
		/*Esse bloco testa se o obj informado não esta nulo,
		 * em seguida passa para o atributo orderStatus (integer) desse classe o valor
		 * code (obj) do parametro*/
			this.orderStatus = orderStatus.getCode();
		}
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}

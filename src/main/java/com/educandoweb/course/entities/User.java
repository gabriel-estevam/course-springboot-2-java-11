package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity //anotação para especificar que essa classe é uma entidade
@Table(name = "tb_user") //mantendo a boa pratica, vamos dizer que essa classe, no banco dados, se chamara "tb_user"
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id //estamos dizendo que o atributo id é uma chave-primaria
	@GeneratedValue (strategy = GenerationType.IDENTITY) //estamos dizendo que o id é auto_increment
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	/*a anotação oneToMany indica cliente (classe User) tem varios pedidos (classe Order)
	 * no parametro mappedBy informamos o nome do atributo da outra classe, classe User nesse caso,
	 * então sera feito um mapemento
	 *  */
	
	@JsonIgnore //usamos essa anotação para indicar ao json que ele tem uma relação com o client, se não tiver essa anotação vai gerar erro
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>(); //Relacionamento - um cliente tem varios pedidos
	
	public User() {
		
	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrders() {
		return orders;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}

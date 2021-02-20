package com.educandoweb.course.entities.pk; //nesse pacote criamos classes onde no relacionamento gera uma chave primaria composta

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Product;
/*Classe auxiliar para representar o par chave (chave primaria composta) PRODUCT-ORDER
 * Essa classe foi criada apenas como auxilio quando o relacionamento de muitos para muitos
 * gera uma relação associativa onde a mesma pois DADOS EXTRAS, no paradigma orientado a objeto
 * NÃO TEM COMO CRIAR NA MESMA CLASSE PK COMPOSTA, portanto a responsabilidade do par PRODUCT-ORDER
 * sera o que vai identificar o objeto ITEM PEDIDO
*/
@Embeddable //anotação para casos em que a classe auxiliar com chave-primaria composta
public class OrderItemPK implements Serializable 
{
	private static final long serialVersionUID = 1L;
	//essa classe não possui um construtor
	
	//nesse classe tanto o order como product tera relação manyToOne sendo lado muitos
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	//a comparação hashCode and equals sera com o Order e Product porque os dois é que 
	//identifica o item-pedido
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		OrderItemPK other = (OrderItemPK) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
	
}

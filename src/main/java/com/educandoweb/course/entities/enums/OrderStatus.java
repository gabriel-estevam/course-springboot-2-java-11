package com.educandoweb.course.entities.enums;
//enum responsavel por armazenar o status do pedido
public enum OrderStatus 
{
	/*Abaixo colocamos valores para cada enum, pois vai ficar explicito para outro dev quando
	 * necessario fazer manutenção e tambem porque se não atribuir nem um valor o java, atribui
	 * um valor para cada enum, sendo o primeiro em 0, segundo 1, terceiro 2 e assim por diante...
	 * Com isso pode gerar erro no banco de dados, pois caso seja acrescentado mais um enum no meio
	 * de outro enum vai mudar completamente a sequencia de codigo gerado, portanto por isso foi 
	 * necessario  colocar um codigo manualmente, o codigo pode ser qualquer valor, por padrão colocaremos
	 * em uma forma sequencial */
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private int code; //atributo que vai armazenar o codigo do enum
	
	private OrderStatus(int code) {
	//aqui é o construtor do enum, nesse caso ele é diferente de um construtor de classe
		this.code = code; 
	}
	
	public int getCode() {
	//metodo para conseguir acessar, em outra classe, o atributo code do enum 
		return code;
	}
	
	public static OrderStatus valueOf(int code) {
	/*Metodo para retornar um codigo do enum, tem
	 * como parametro um integer que vai receber o valor do codigo,
	 * recebeu o valor, entra no bloco abaixo*/
		for(OrderStatus value: OrderStatus.values()) {
		/*Esse bloco é responsavel por percorrer os codigos definidos*/
			if(value.getCode() == code) {
			/*Esse bloco valida se o codigo informado tem em algum enum
			 * dessa classe, caso tenha retorna o valor*/
				return value;
			}
		}
		//caso ele passe por todos os enums e não encotrar o codigo informado, lança a exceção abaixo
		throw new IllegalArgumentException("Invalid Orderstatus code");
	}
}

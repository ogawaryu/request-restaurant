package com.pedido.restaurante;

public class ObjetoCategoriaSimples {
	
	int _id, id_relacionado;
	String nome;

	public ObjetoCategoriaSimples(int _id, int id_relacionado,  String nome ) {
		this._id = _id;
		this.id_relacionado = id_relacionado;
		this.nome = nome;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getId_relacionado() {
		return id_relacionado;
	}

	public void setId_relacionado(int id_relacionado) {
		this.id_relacionado = id_relacionado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
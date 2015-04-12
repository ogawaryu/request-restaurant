package com.pedido.restaurante;

public class ObjetoProduto {

	private int _id;
	private int imagem, id_categoria;
	private String nome;
	private double preco;

	public ObjetoProduto( int _id , int id_categoria,  int imagem, String nome, double preco) {
		this._id = _id;
		this.id_categoria = id_categoria;
		this.imagem = imagem;
		this.nome = nome;
		this.preco = preco;
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getImagem() {
		return imagem;
	}

	public void setImagem(int imagem) {
		this.imagem = imagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}	
}
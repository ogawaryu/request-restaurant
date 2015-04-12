package com.pedido.sql;

import java.util.ArrayList;

import com.example.pedidorestaurante.R;
import com.pedido.restaurante.ObjetoProduto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Produto {
	
	private SQLiteDatabase database;
	private HelperSqlite dbHelper;

	public Produto(Context context) {
		dbHelper = new HelperSqlite(context, null);
	}
	
	public void recreate(){
		dbHelper.onUpgrade(database, 0, 0);
	}
	
	public void open() {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	
	public long insert( int _id,  int id_categoria, String nome, Double preco, int imagem) {
		ContentValues content = new ContentValues();
		content.put("_id", _id);
		content.put("id_categoria", id_categoria);
		content.put("nome", nome);
		content.put("preco", preco);
		content.put("imagem", imagem);
		return database.insert(dbHelper.PRODUTO_TABLE_NAME, null, content);
	}
	
	public ObjetoProduto fetchOne( int id_categoria ) {
		Cursor  cursor = database.rawQuery("select * from " + dbHelper.PRODUTO_TABLE_NAME + " WHERE _id = " + id_categoria + " LIMIT 1;" ,null);
		ObjetoProduto op = null;
		if (cursor .moveToFirst()) {
            while (cursor.isAfterLast() == false) {
            	int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            	int id_categoria1 = cursor.getInt(cursor.getColumnIndex("id_categoria"));
            	String nome = cursor.getString(cursor.getColumnIndex("nome"));
            	Double preco = cursor.getDouble(cursor.getColumnIndex("preco"));
            	int imagem = cursor.getInt(cursor.getColumnIndex("imagem"));
            	
            	op = new ObjetoProduto( _id, id_categoria1, imagem , nome, preco);
                cursor.moveToNext();
            }
        }
		return op;
	}
	
	public ArrayList<ObjetoProduto> fetchCategoria( int id_categoria ) {
		Cursor  cursor = database.rawQuery("select * from " + dbHelper.PRODUTO_TABLE_NAME + " WHERE id_categoria = " + id_categoria + ";" ,null);
		ArrayList<ObjetoProduto> lProduto = new ArrayList<ObjetoProduto> ();
		if (cursor .moveToFirst()) {
            while (cursor.isAfterLast() == false) {
            	int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            	int id_categoria1 = cursor.getInt(cursor.getColumnIndex("id_categoria"));
            	String nome = cursor.getString(cursor.getColumnIndex("nome"));
            	Double preco = cursor.getDouble(cursor.getColumnIndex("preco"));
            	int imagem = cursor.getInt(cursor.getColumnIndex("imagem"));
            	
            	lProduto.add( new ObjetoProduto( _id , id_categoria1 , imagem, nome, preco) );
            	
                cursor.moveToNext();
            }
        }
		return lProduto;
	}
	
	public void populateTemp() {
		
		dbHelper.onUpgrade(database, 1, 2);
		
		ArrayList<ObjetoProduto> lProduto = new ArrayList<ObjetoProduto> ();
	
		lProduto.add( new ObjetoProduto( 1 , 1 , R.drawable.food, "Combinado peixes 20 unidades", 20) );
		lProduto.add( new ObjetoProduto( 2 , 1 , R.drawable.sushi, "Combinado peixes 40 unidades", 40));
		lProduto.add( new ObjetoProduto( 3 , 1 , R.drawable.temaki, "Combinado peixes 60 unidades", 60));
		lProduto.add( new ObjetoProduto( 4 , 1 , R.drawable.food, "Combinado peixes 100 unidades", 100));
	
	
		lProduto.add( new ObjetoProduto( 5 , 2 , R.drawable.food,"Combinado Salmão 20 unidades", 20) );
		lProduto.add( new ObjetoProduto( 6 , 2 , R.drawable.sushi,"Combinado Salmão 40 unidades" , 45));
		lProduto.add( new ObjetoProduto( 7 , 2 , R.drawable.temaki,"Combinado Salmão 60 unidades", 65) );
	
		lProduto.add( new ObjetoProduto( 8 , 3 ,  R.drawable.food,"Combinado Salmão e Atum 20 unidades", 22) );
		lProduto.add( new ObjetoProduto( 9 , 3 , R.drawable.sushi,"Combinado Salmão e Atum 40 unidades", 45) );
		lProduto.add( new ObjetoProduto( 10 , 3 , R.drawable.sushi,"Combinado Salmão e Atum 60 unidades" , 62));
		lProduto.add( new ObjetoProduto( 11 , 3 , R.drawable.temaki,"Combinado Salmão e Atum 100 unidades", 82) );
		lProduto.add( new ObjetoProduto( 12 , 3 , R.drawable.sushi,"Combinado Salmão e Atum 120 unidades", 122) );
		lProduto.add( new ObjetoProduto( 13 , 3 , R.drawable.sushi,"Combinado Salmão e Atum 160 unidades", 152) );
		lProduto.add( new ObjetoProduto( 14 , 3 , R.drawable.temaki,"Combinado Salmão e Atum 180 unidades", 182) );
		lProduto.add( new ObjetoProduto( 15 , 3 , R.drawable.temaki,"Combinado Salmão e Atum 200 unidades", 202) );
		lProduto.add( new ObjetoProduto( 16 , 3 , R.drawable.temaki,"Combinado Salmão e Atum 220 unidades", 222) );
	
		lProduto.add( new ObjetoProduto( 17 , 4 , R.drawable.food,"Combinado Sushi Simples 20 unidades" , 18));
		lProduto.add( new ObjetoProduto( 18 , 4 , R.drawable.sushi,"Combinado Sushi Simples 40 unidades", 20) );
		lProduto.add( new ObjetoProduto( 19 , 4 , R.drawable.temaki,"Combinado Sushi Especial 100 unidades" , 16));
	
		lProduto.add( new ObjetoProduto( 20 , 5 , R.drawable.food,"Sushi Pantanal - 8 unidades" , 8));
		lProduto.add( new ObjetoProduto( 21 , 5 , R.drawable.sushi,"Sushi Hot Roll - 10 unidades" , 8));
		lProduto.add( new ObjetoProduto( 22 , 5 , R.drawable.temaki,"Sushi Vegetariano Holl - 8 unidades", 8) );
		lProduto.add( new ObjetoProduto( 23 , 5 , R.drawable.temaki,"Negui Shake Jhou - 10 unidades" , 8));
	
		lProduto.add( new ObjetoProduto( 24 , 6 , R.drawable.food,"Dupla Sushi Salmão" , 20));
		lProduto.add( new ObjetoProduto( 25 , 6 , R.drawable.temaki,"Dupla Sushi Atum" , 20));
		lProduto.add( new ObjetoProduto( 26 , 6 , R.drawable.sushi,"Dupla Sushi Polvo" , 20));
		lProduto.add( new ObjetoProduto( 27 , 6 , R.drawable.sushi,"Dupla Sushi Ovas" , 200));
	
		lProduto.add( new ObjetoProduto( 28 , 7 , R.drawable.drink,"Coca-cola" , 4));
		lProduto.add( new ObjetoProduto( 29 , 7 , R.drawable.drink,"Pepsi" , 4));
		lProduto.add( new ObjetoProduto( 30 , 7 , R.drawable.drink,"Água" , 3));
		lProduto.add( new ObjetoProduto( 31 , 7 , R.drawable.drink,"Suco" , 4));
		
		for(int i = 0; i < lProduto.size(); i++) {
			ObjetoProduto temp = lProduto.get(i);
			this.insert( temp.get_id(), temp.getId_categoria(), temp.getNome() , temp.getPreco(), temp.getImagem());
		}

	}
	
}

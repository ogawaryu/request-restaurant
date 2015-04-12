package com.pedido.sql;

import java.util.ArrayList;

import com.pedido.restaurante.ObjetoProduto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Carrinho {
	
	private SQLiteDatabase database;
	private HelperSqlite dbHelper;

	public Carrinho(Context context) {
		dbHelper = new HelperSqlite(context, null);
	}
	
	public void open() {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	public int count(){
		Cursor  cursor = database.rawQuery("select count(*) as count from " + dbHelper.CARRINHO_TABLE_NAME ,null);
		int count = 0;
		if (null != cursor && cursor.moveToFirst()) {
			count = cursor.getInt(cursor.getColumnIndex("count"));
		}
		return count;
	}
	
	public long insert(int id_produto, String nome, int quantidade) {
		ContentValues content = new ContentValues();
		content.put("id_produto", id_produto);
		content.put("nome", nome);
		content.put("quantidade", quantidade);
		return database.insert(dbHelper.CARRINHO_TABLE_NAME, null, content);
	}
	
	public ArrayList<ObjetoProduto> fetchAll() {
		Cursor  cursor = database.rawQuery("select * from " + dbHelper.CARRINHO_TABLE_NAME ,null);
		ArrayList<ObjetoProduto> lProduto = new ArrayList<ObjetoProduto> ();
		if (cursor .moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                String name = cursor.getString(cursor.getColumnIndex("nome"));
                cursor.moveToNext();
            }
        }
		return lProduto;
	}

}

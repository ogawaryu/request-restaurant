package com.pedido.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class HelperSqlite  extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "restaurante.db";
	
	public static final String PRODUTO_TABLE_NAME = "produtos";
	public static final String PRODUTO_ID = "_id";
	public static final String PRODUTO_ID_CATEGORIA = "id_categoria";
	public static final String PRODUTO_NOME = "nome";
	public static final String PRODUTO_PRECO = "preco";
	public static final String PRODUTO_IMAGEM = "imagem";
	
	public static final String CARRINHO_TABLE_NAME = "carrinho";
	public static final String CARRINHO_ID_PRODUTO = "id_produto";
	public static final String CARRINHO_NOME = "nome";
	public static final String CARRINHO_QUANTIDADE = "quantidade";
	
	public HelperSqlite(Context context,  CursorFactory factory) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String PRODUTO_CREATE_TABLE = "create table "
				+ PRODUTO_TABLE_NAME + "( " 
				+ PRODUTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ PRODUTO_ID_CATEGORIA + " INT NOT NULL, "
				+ PRODUTO_NOME + " CHAR(80), "
				+ PRODUTO_PRECO + " DOUBLE NOT NULL, "
				+ PRODUTO_IMAGEM + " INTEGER NOT NULL  );";
		
		String CARRINHO_CREATE_TABLE = "CREATE TABLE  "
				+ CARRINHO_TABLE_NAME + "( " 
				+ CARRINHO_ID_PRODUTO + " id_produto INTEGER NOT NULL, "
				+ CARRINHO_NOME + " CHAR(80), "
				+ CARRINHO_QUANTIDADE + " INTEGER NOT NULL);";
		
		
		db.execSQL(PRODUTO_CREATE_TABLE);
		db.execSQL(CARRINHO_CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + PRODUTO_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + CARRINHO_TABLE_NAME);
		this.onCreate(db);
	}

}

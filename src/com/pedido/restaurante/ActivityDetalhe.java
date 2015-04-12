package com.pedido.restaurante;

import com.example.pedidorestaurante.R;
import com.pedido.sql.Carrinho;
import com.pedido.sql.Produto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityDetalhe extends Activity implements OnClickListener{
	
	Button bMenos, bMais, bAddPedido, bAddObservacao;
	EditText etQtdProduto, etObservacao;
	TextView tvNomeDoPrato;
	ObjetoProduto oProduto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhe);
		
		bMenos = (Button)findViewById(R.id.bDetalheMenos);
		bMais = (Button)findViewById(R.id.bDetalheMais);
		bAddPedido = (Button)findViewById(R.id.bAddPedido);
		bAddObservacao = (Button)findViewById(R.id.bAddObservacao);
		
		etQtdProduto = (EditText) findViewById(R.id.etQtdProduto);
		etObservacao = (EditText) findViewById(R.id.etObservacao);
		
		tvNomeDoPrato = (TextView) findViewById(R.id.tvNomeDoPrato);
		
		bMenos.setOnClickListener(this);
		bMais.setOnClickListener(this);
		bAddObservacao.setOnClickListener(this);
		bAddPedido.setOnClickListener(this);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    int id_produto = extras.getInt("id_produto");
		    Produto produto = new Produto(this);
		    produto.open();
			oProduto = produto.fetchOne(id_produto);
			produto.close();
			
			Log.d("ID_",String.valueOf(oProduto.get_id()) );
			Log.d("NOME", oProduto.getNome());
			
			tvNomeDoPrato.setText(oProduto.getNome());
		}

	}
	
	@Override
	public void onClick(View v) {
		Editable str_display = etQtdProduto.getText();
		if (str_display.toString().trim().equals("")) {
			str_display.append("0");
		}
		switch (v.getId()) {
		case R.id.bDetalheMenos:
			int totalMenos = Integer.parseInt( str_display.toString() );
			if(totalMenos >= 1) {
				totalMenos = totalMenos - 1;
				etQtdProduto.setText( String.valueOf( totalMenos ) );
			}
		break;
		case R.id.bDetalheMais:
			int totalMais = Integer.parseInt( str_display.toString() );
			if(totalMais >= 0) {
				totalMais = totalMais + 1;
				etQtdProduto.setText( String.valueOf( totalMais ) );
			}
		break;
		case R.id.bAddObservacao:
			etObservacao.setVisibility(View.VISIBLE);
		break;
		case R.id.bAddPedido:
			Intent i = getIntent();
			String sQtd = etQtdProduto.getText().toString().trim();
			if(sQtd.length() > 0) {
				int qtd = Integer.parseInt(sQtd);
				if(qtd > 0 ) {
					Carrinho carrinho = new Carrinho(this);
					carrinho.open();
					carrinho.insert( oProduto.get_id() , oProduto.getNome(), qtd);
					carrinho.close();
					setResult(2, i);
					this.finish();
				} else {
					Toast.makeText(this, "Quantidade precisa ser maior que zero.", Toast.LENGTH_LONG).show();
				}
			}
		break;
		}
	}
}
package com.pedido.restaurante;

import java.util.ArrayList;
import java.util.List;

import com.example.pedidorestaurante.R;
import com.pedido.sql.Carrinho;
import com.pedido.sql.Produto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ActivityCatalogoSimples extends Activity implements OnItemSelectedListener, OnClickListener {
	
	ListView lvProduto;
	Spinner sCategoria;
	ObjetoCategoriaSimples ocs;
	Button bFinalizar;
	List<ObjetoCategoriaSimples> lCategoria;
	ArrayList<ObjetoProduto> lProduto;
	int requestCodeDetalhe = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catalogo_simples);
		
		sCategoria = (Spinner) findViewById(R.id.sCatagoria);
		lvProduto = (ListView)findViewById(R.id.lvProduto);
		bFinalizar = (Button) findViewById(R.id.bFinalizar);
		this.temp();
		this.atualizaCarrinho();
		
		bFinalizar.setOnClickListener(this);
		
		// CRIAR UMA LISTA TEMPORARIA
		lCategoria = new ArrayList<ObjetoCategoriaSimples>();
		lCategoria.add(new ObjetoCategoriaSimples(1, 0, "Combinados Sushi e Sashimi"));
		lCategoria.add(new ObjetoCategoriaSimples(2, 0, "Combinados de Salmão"));
		lCategoria.add(new ObjetoCategoriaSimples(3, 0, "Combinados Salmão e Atum"));
		lCategoria.add(new ObjetoCategoriaSimples(4, 0, "Combinados Sushi Variados"));
		lCategoria.add(new ObjetoCategoriaSimples(5, 0, "Sushis Especiais"));
		lCategoria.add(new ObjetoCategoriaSimples(6, 0, "Sushis Duplas"));
		lCategoria.add(new ObjetoCategoriaSimples(7, 0, "Bebidas"));
	
		SpinnerAdapter dataAdapter = new SpinnerAdapter(this, android.R.layout.simple_spinner_item, lCategoria);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sCategoria.setAdapter(dataAdapter);
		sCategoria.setOnItemSelectedListener(this);
				
		lvProduto.setOnItemClickListener( new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
				ObjetoProduto objProduto = lProduto.get(pos);
				Intent intent = new Intent( ActivityCatalogoSimples.this, ActivityDetalhe.class );
				intent.putExtra("id_produto",  objProduto.get_id());
				startActivityForResult(intent, requestCodeDetalhe);
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bFinalizar:
			Intent intentFinalizar = new Intent(this, ActivityFinalizarPedido.class);
			startActivity(intentFinalizar);
			break;
		}
	}
	
	public void temp() {
		Produto p = new Produto(this);
		p.open();
		p.recreate();
		p.populateTemp();
		p.close();
	}

	public void atualizaCarrinho() {
		Carrinho carrinho = new Carrinho(this);
		carrinho.open();
		int count = carrinho.count();
		if(count > 0) {
			String sCount = String.valueOf(count);
			bFinalizar.setEnabled(true);
			bFinalizar.setText("Finalizar Pedido ("+sCount+")");
		}
		carrinho.close();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			case 2:
				this.atualizaCarrinho();
			break;
		}
	}

	@Override
	public void onItemSelected( AdapterView<?> parent, View v, int pos, long id ) {
		ObjetoCategoriaSimples obj = lCategoria.get(pos);
		Produto produto = new Produto(this);
		Log.d("ATUALIZA PRODUTO", String.valueOf(obj.get_id()));
		produto.open();
		lProduto = produto.fetchCategoria(obj.get_id());
		produto.close();
		ListViewAdapter adp = new ListViewAdapter(this, R.layout.custom_listview_categoria_simples, lProduto);
		lvProduto.setAdapter(adp);
	}
	
	class ListViewAdapter extends ArrayAdapter<ObjetoProduto> {
		
		Context context;
		private int resource;
		private List<ObjetoProduto> objects;
		
		public ListViewAdapter(Context context, int resource, List<ObjetoProduto> objects) {
			super(context, resource, objects);
			this.context = context;
			this.resource = resource;
			this.objects = objects;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if(view == null) {
				LayoutInflater inflater = ((Activity) context).getLayoutInflater();
				view = inflater.inflate(resource, parent, false);
			}
			
			ObjetoProduto obj = objects.get(position);
			
			TextView tv = (TextView)view.findViewById(R.id.tvNome);
			ImageView iv = (ImageView)view.findViewById(R.id.ivProduto);
			iv.setImageResource(obj.getImagem());
			tv.setText(obj.getNome());
			
			return view;
		}
	}
	
	class SpinnerAdapter extends ArrayAdapter<ObjetoCategoriaSimples> {
		
		Context context;
		List<ObjetoCategoriaSimples> lCategoria;
		private int resource;

		public SpinnerAdapter(Context context, int resource, List<ObjetoCategoriaSimples> lCategoria) {
			super(context, resource, lCategoria);
			this.context = context;
			this.resource = resource;
			this.lCategoria = lCategoria;
		}
		
		@Override
		public View getDropDownView(int position, View convertView, ViewGroup parent) {
			View view = convertView;

			if(view == null) {
				LayoutInflater inflater = ((Activity) context).getLayoutInflater();
				view = inflater.inflate(resource, parent, false);	            
			}
			
			ObjetoCategoriaSimples objeto = lCategoria.get(position);
            TextView tv = (TextView) view.findViewById(android.R.id.text1);
            tv.setPadding(16, 26, 6, 26);
            tv.setTextSize(18);
            tv.setText(objeto.getNome());
		    
		    return view;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if(view == null) {
				LayoutInflater inflater = ((Activity) context).getLayoutInflater();
				view = inflater.inflate(resource, parent, false);	            
			}
			ObjetoCategoriaSimples objeto = lCategoria.get(position);
            TextView tv = (TextView) view.findViewById(android.R.id.text1);
            tv.setText(objeto.getNome());

			return view;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		
	}
}
package com.pedido.restaurante;

import com.example.pedidorestaurante.R;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class ActivityMesa extends Activity implements OnClickListener {

	EditText etMesa;
	Button bUm, bDois, bTres, bQuatro, bCinco, bSeis, bSete, bOito, bNove, bZero, bMesaRemover, bMesaSubmit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mesa);
		
		bUm = (Button) findViewById(R.id.bMesaUm);
		bDois = (Button) findViewById(R.id.bMesaDois);
		bTres = (Button) findViewById(R.id.bMesaTres);
		bQuatro = (Button) findViewById(R.id.bMesaQuatro);
		bCinco = (Button) findViewById(R.id.bMesaCinco);
		bSeis = (Button) findViewById(R.id.bMesaSeis);
		bSete = (Button) findViewById(R.id.bMesaSete);
		bOito = (Button) findViewById(R.id.bMesaOito);
		bNove = (Button) findViewById(R.id.bMesaNove);
		bZero = (Button) findViewById(R.id.bMesaZero);
		bMesaRemover = (Button) findViewById(R.id.bMesaRemover);
		bMesaSubmit = (Button) findViewById(R.id.bMesaSubmit);
		
		bUm.setOnClickListener(this);
		bDois.setOnClickListener(this);
		bTres.setOnClickListener(this);
		bQuatro.setOnClickListener(this);
		bCinco.setOnClickListener(this);
		bSeis.setOnClickListener(this);
		bSete.setOnClickListener(this);
		bOito.setOnClickListener(this);
		bNove.setOnClickListener(this);
		bZero.setOnClickListener(this);
		bMesaRemover.setOnClickListener(this);
		bMesaSubmit.setOnClickListener(this);
		
		etMesa = (EditText) findViewById(R.id.etMesaDisplay);
		
	}

	@Override
	public void onClick(View v) {
		Editable str_display = etMesa.getText();
		switch (v.getId()) {
		case R.id.bMesaUm:
			str_display = str_display.append(bUm.getText());
			etMesa.setText(str_display);
		break;
		case R.id.bMesaDois:
			str_display = str_display.append(bDois.getText());
			etMesa.setText(str_display);
		break;
		case R.id.bMesaTres:
			str_display = str_display.append( bTres.getText());
			etMesa.setText(str_display);
		break;
		case R.id.bMesaQuatro:
			str_display = str_display.append( bQuatro.getText());
			etMesa.setText(str_display);
		break;
		case R.id.bMesaCinco:
			str_display = str_display.append( bCinco.getText());
			etMesa.setText(str_display);
		break;
		case R.id.bMesaSeis:
			str_display = str_display.append( bSeis.getText());
			etMesa.setText(str_display);
		break;
		case R.id.bMesaSete:
			str_display = str_display.append( bSete.getText());
			etMesa.setText(str_display);
		break;
		case R.id.bMesaOito:
			str_display = str_display.append( bOito.getText());
			etMesa.setText(str_display);
		break;
		case R.id.bMesaNove:
			str_display = str_display.append( bNove.getText());
			etMesa.setText(str_display);
		break;
		case R.id.bMesaZero:
			str_display = str_display.append( bZero.getText());
			etMesa.setText(str_display);
		break;
		case R.id.bMesaRemover:
			String str = str_display.toString();
			if(str.length() > 0){
				str = str.substring(0, str_display.length()-1);
				etMesa.setText(str);
			}
		break;
		case R.id.bMesaSubmit:
			String str1 = str_display.toString();
			str1 = str1.replaceFirst("^0*", "");
			if(str1.length() > 0){
				Intent i = new Intent(this, ActivityCatalogoSimples.class);
				startActivity(i);
			} else {
				Toast.makeText(this, "Por Favor, digite o número da mesa.", Toast.LENGTH_SHORT ).show();
			}
		break;

		}
	}

}

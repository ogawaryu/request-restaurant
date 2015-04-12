package com.pedido.restaurante;

import com.example.pedidorestaurante.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ActivitySplash extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(2000);
				} catch (InterruptedException e) { 
					e.printStackTrace();
				} finally {
					Intent i = new Intent(ActivitySplash.this, ActivityMesa.class);
					startActivity(i);
				}
			}
		};
		timer.start();
	}

}

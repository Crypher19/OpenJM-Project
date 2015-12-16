package com.ismonnet.openjm;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class InfoScuolaActivity extends Activity {
	private TextView lbl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_scuola);
		
		lbl = (TextView) findViewById(R.id.textInfoScuola);
		String s = "L’Istituto d’Istruzione Superiore “Jean Monnet” "
				+ "di Mariano Comense è un importante complesso "
				+ "scolastico statale della Provincia di Como, "
				+ "che raccoglie studentesse e studenti da ben "
				+ "66 paesi delle provincie di Como, Lecco e Milano.";
		lbl.setText(s);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info_scuola, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

package com.ismonnet.openjm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class RegInvalid extends Activity {
	
	private Intent intentRegistrationActivity;
	private Button btnTornaAllaRegAct;
	private DatiCondivisi dc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg_invalid);
		btnTornaAllaRegAct = (Button) findViewById(R.id.btnTornaAllaRegAct);
		
		// ottengo dc dall'activity precedente
		Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        dc = (DatiCondivisi)bundle.getParcelable("DatiCondivisi");
		
		
		
		btnTornaAllaRegAct.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// invio dc all'altra activity
				intentRegistrationActivity = new Intent(RegInvalid.this, RegistrationActivity.class);
				Bundle bundle = new Bundle();
				bundle.putParcelable("DatiCondivisi", dc);
				intentRegistrationActivity.putExtras(bundle);
				
				startActivity(intentRegistrationActivity);
			} // fine metodo onClick()
		}); // fine listener btnTornaAlMenu
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reg_invalid, menu);
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

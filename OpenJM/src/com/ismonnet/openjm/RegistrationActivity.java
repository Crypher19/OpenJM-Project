package com.ismonnet.openjm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationActivity extends Activity {
	private EditText txtUsernameReg;
	private EditText txtPasswordReg;
	private EditText txtNomeReg;
	private EditText txtCognomeReg;
	private EditText txtEmailReg;
	private Button btnConfermaReg;
	private TextView lblResultReg;
	
	private DatiCondivisi dc;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
		txtUsernameReg = (EditText) findViewById(R.id.txtUsernameReg);
		txtPasswordReg = (EditText) findViewById(R.id.txtPasswordReg);
		txtNomeReg = (EditText) findViewById(R.id.txtNomeReg);
		txtCognomeReg = (EditText) findViewById(R.id.txtCognomeReg);
		txtEmailReg = (EditText) findViewById(R.id.txtEmailReg);
		
		btnConfermaReg = (Button) findViewById(R.id.btnConfermaReg);
		
		lblResultReg = (TextView) findViewById(R.id.lblResultReg);
		
		Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        dc = (DatiCondivisi)bundle.getParcelable("DatiCondivisi");
        
		btnConfermaReg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				btnConfermaReg.setEnabled(false);//per evitare registrazioni simultanee dello stesso utente
				
				if (!txtUsernameReg.getText().toString().isEmpty() 
						&& !txtPasswordReg.getText().toString().isEmpty()
						&& !txtNomeReg.getText().toString().isEmpty()
						&& !txtCognomeReg.getText().toString().isEmpty()
						&& !txtEmailReg.getText().toString().isEmpty()) {
							
					// creo un utente
					Utente u = new Utente();
					u.setUser(txtUsernameReg.getText().toString());
					u.setPass(txtPasswordReg.getText().toString());
					u.setName(txtNomeReg.getText().toString());
					u.setSurn(txtCognomeReg.getText().toString());	
					u.setMail(txtEmailReg.getText().toString());
					
					// lo salvo in dati condivisi
					dc.setUtente(u);
					dc.setScelta("R");
					
					ThreadSocket ts = new ThreadSocket(dc);
					ts.start();
					try {
						ts.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
					if (dc.isAutenticato()) {
						dc.setAutenticato(false); // resetto per fare il login
						//lblResultReg.setText(R.string.regsuccessful); // non registrato
						Intent intent = new Intent(RegistrationActivity.this, RegSuccess.class);
						Bundle bundle = new Bundle();
						bundle.putParcelable("DatiCondivisi", dc);
						intent.putExtras(bundle);
						// faccio partire la nuova activity
						startActivity(intent);
						//btnConfermaReg.setEnabled(true);
					}
					else {
						//lblResultReg.setText(R.string.connessionefallita); // non registrato	
						Intent intent = new Intent(RegistrationActivity.this, RegInvalid.class);
						Bundle bundle = new Bundle();
						bundle.putParcelable("DatiCondivisi", dc);
						intent.putExtras(bundle);
						// faccio partire la nuova activity
						startActivity(intent);
						//btnConfermaReg.setEnabled(true);
					}
					
					

					
				} // fine if input utente vuoto
				else if (!txtUsernameReg.getText().toString().isEmpty()) {
					lblResultReg.setText(R.string.inserireUsername);
					btnConfermaReg.setEnabled(true);
				}
				else if (!txtPasswordReg.getText().toString().isEmpty()) {
					lblResultReg.setText(R.string.inserirePassword);
					btnConfermaReg.setEnabled(true);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration, menu);
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

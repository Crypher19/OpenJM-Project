package com.ismonnet.openjm;

import com.dm.zbar.android.scanner.ZBarConstants;
import com.dm.zbar.android.scanner.ZBarScannerActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
//import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import net.sourceforge.zbar.Symbol;

public class SecretActivity extends Activity{

	public static final int ZBAR_SCANNER_REQUEST = 0;
	public static final int ZBAR_OR_SCANNER_REQUEST = 1;

	private Button scanBtn;
	private Button logoutBtn;
	private Button infoScuolaBtn;
	
	private Intent intentMainActivity;
	private Intent intentInfoScuolaActivity;
	
	private DatiCondivisi dc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secret);
		scanBtn = (Button)findViewById(R.id.button3);
		logoutBtn = (Button)findViewById(R.id.button4);
		infoScuolaBtn = (Button)findViewById(R.id.btnInfoScuola);

		//intent per i dati condivisi
		Intent i = getIntent();
		dc = i.getParcelableExtra("DatiCondivisi");

		intentMainActivity = new Intent(SecretActivity.this, MainActivity.class);
		intentInfoScuolaActivity = new Intent(SecretActivity.this, InfoScuolaActivity.class);

		// listener
		scanBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(dc.isAutenticato()){
					if(v.getId()==R.id.button3){
						//modified: only scan qr-codes
						scan();
					}
				} else{
					makeErrorToast();
				}

			} // fine metodo onClick()
		}); // fine listener scanBtn

		// listener
		logoutBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dc.setAutenticato(false);
				startActivity(intentMainActivity);
			} // fine metodo onClick()
		}); // fine listener logoutBtn

		infoScuolaBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(dc.isAutenticato()){
					startActivity(intentInfoScuolaActivity);
				} else{
					makeErrorToast();
				}
			} // fine metodo onClick()
		}); // fine listener infoScuolaActivity
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.secret, menu);
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
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{    
		if (resultCode == RESULT_OK) 
		{
			// Scan result is available by making a call to data.getStringExtra(ZBarConstants.SCAN_RESULT)
			// Type of the scan result is available by making a call to data.getStringExtra(ZBarConstants.SCAN_RESULT_TYPE)
			String scanResult = data.getStringExtra(ZBarConstants.SCAN_RESULT);
			Uri uriUrl;

			if(!scanResult.startsWith("http://") && !scanResult.startsWith("https://")){//non è un url oppure è un url incompleto
				if(!scanResult.contains("www.")){//non è un url
					//visualizzo il contenuto di scanResult in una nuova activity
					Intent IntentViewActivity = new Intent(SecretActivity.this, ViewActivity.class);

					Bundle bundle= new Bundle();
					bundle.putString("message", scanResult);

					IntentViewActivity.putExtras(bundle);
					startActivity(IntentViewActivity);
				}
				else{//è un url incompleto
					uriUrl = Uri.parse("http://" + scanResult);
					Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
					startActivity(launchBrowser);
				}
			} else {//url completo
				uriUrl = Uri.parse(scanResult);
				Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
				startActivity(launchBrowser);
			}
			// The value of type indicates one of the symbols listed in Advanced Options below.
		} else if(resultCode == RESULT_CANCELED) {
			Toast.makeText(this, "Camera unavailable", Toast.LENGTH_SHORT).show();
		}
	}

	private void scan(){
		Intent intent = new Intent(SecretActivity.this, ZBarScannerActivity.class);
		intent.putExtra(ZBarConstants.SCAN_MODES, new int[]{Symbol.QRCODE});
		startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
	}

	private void makeErrorToast(){
		Toast.makeText(this, "Error, you are not logged in", Toast.LENGTH_SHORT).show();
	}
}

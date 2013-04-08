package com.example.jungladroid;

import librerias.conecta_ws;
import librerias.validaciones;

import org.ksoap2.serialization.SoapObject;

import librerias.dialogos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {
	private TextView pass;
	private TextView user;
	private Button boton1;
	private Button boton2;
	public static String usuario;
	public static String clave;

	// private String codigo_personal;

	// private String apellido_m;
	private String nombre;
	private String apellido;
	private String perfil;
	Intent intent;
	SoapObject result;
	SoapObject pii;
	conecta_ws ws, resultado_ws;
	String METHOD_NAME = "login_user";
	String[] nom_variables;
	String[] datos_variables;
	ProgressDialog pd;
	Context context;
	dialogos dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.setTitle("La Jungla");
		setContentView(R.layout.login);
		user = (TextView) findViewById(R.id.txtusuario);
		pass = (TextView) findViewById(R.id.txtclave);
		boton1 = (Button) findViewById(R.id.btn_iniciar);
		boton2 = (Button) findViewById(R.id.btn_cancelar);
		// Se establece listener para nuestro boton
		boton1.setOnClickListener(this);
		boton2.setOnClickListener(this);
		context = this;
		user.setText("");
		pass.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    //Alternativa 1
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_login, menu);
	    return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		   switch (item.getItemId()) {
		        case R.id.menu_settings:
		           Toast.makeText(getApplicationContext(), "Has pulsado Config", Toast.LENGTH_SHORT).show();
		           return true;
		       /* case R.id.btInfo:
		           Toast.makeText(getApplicationContext(), "Has pulsado la opción Info", Toast.LENGTH_SHORT).show();
		           return true;*/
		        default:
		           return super.onOptionsItemSelected(item);
		    }
		}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_iniciar:
			usuario = user.getText().toString().trim();
			clave = pass.getText().toString().trim();
			if (usuario.equals("") || clave.equals("")) {
				dialog = new dialogos();
				dialog.Dialogo_Alerta(this, "Datos incorrectos");
				break;
			}
			resultado_ws = new conecta_ws();
			nom_variables = new String[] { "user", "clave" };// nombres de las
																// variables
			datos_variables = new String[] { usuario, clave };// datos de las
			pd = new ProgressDialog(context);
			if (validaciones.checkConex(context)) {
				AsyncTasksdowload tarea = new AsyncTasksdowload();
				tarea.execute();
				pd = ProgressDialog.show(context, "", "Verificando datos...",
						true, false);
			} else {
				dialog = new dialogos();
				dialog.Dialogo_Alerta(context,
						"nesecita estar conectado a internet");
				return;
			}


			break;
		case R.id.btn_cancelar:
			user.setText("");
			pass.setText("");
			break;
		}

	}
	private class AsyncTasksdowload extends AsyncTask {

		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
			/*ws = new conecta_ws();
			// comprobamos si tenemos conexion a internet
			result = (SoapObject) ws.get_ResultadoWS(getApplicationContext(),
					nom_variables, datos_variables, METHOD_NAME);*/
			return 1;
		}

		protected void onPreExecute() {
		}

		protected void onPostExecute(Object res) {
			try{
				for (int i = 0; i < result.getPropertyCount(); i++) {
				/*	pii = (SoapObject) result.getProperty(i);
					nombre = pii.getProperty(0).toString();
					apellido = pii.getProperty(1).toString();
					perfil = pii.getProperty(2).toString();*/
				}
			/*	Toast toast1 = Toast.makeText(getApplicationContext(),
						"Bienvenido " + nombre + " " + apellido,
						Toast.LENGTH_SHORT);
				toast1.show();*/

				intent = new Intent(Login.this, sistema.class);
				/*intent.putExtra("nombre", nombre);
				intent.putExtra("apellido", apellido);
				intent.putExtra("perfil", perfil);*/
				pd.dismiss();
				Login.this.finish();
				startActivity(intent);
			}catch(Exception e){
				Toast.makeText(Login.this, "Usuario o Contraseña Incorrectos", Toast.LENGTH_SHORT).show();
				user.setText("");
				pass.setText("");
				//focus a un TextView
				user.requestFocus();
				pd.dismiss();
			}
		}
	}
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return true;

	}
}
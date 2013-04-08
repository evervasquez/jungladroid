package com.example.jungladroid;

import adapters.ImageAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class sistema extends Activity {
    /** Called when the activity is first created. */
	public static String nombre,apellido,perfil;
	Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
		nombre = bundle.getString("nombre");
		apellido = bundle.getString("apellido");
		perfil=bundle.getString("perfil");
		this.setTitle(nombre+" "+apellido+" : "+perfil);
        setContentView(R.layout.sistema);
        
        GridView gv = (GridView)findViewById(R.id.gridview);
        gv.setAdapter(new ImageAdapter(this));
        
        gv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	
                switch (position) {
				case 0:
					intent = new Intent(sistema.this, a_habitaciones.class);
					startActivity(intent);
					break;
				case 1:
					intent = new Intent(sistema.this, estadia.class);
					startActivity(intent);
					break;
				case 2:
					Toast.makeText(sistema.this, "3", Toast.LENGTH_SHORT).show();
					break;
				case 3:
					Toast.makeText(sistema.this, "3", Toast.LENGTH_SHORT).show();
					break;
				case 4:
					Toast.makeText(sistema.this, "3", Toast.LENGTH_SHORT).show();
					break;
				case 5:
					Toast.makeText(sistema.this, "3", Toast.LENGTH_SHORT).show();
					break;
				case 6:
					Toast.makeText(sistema.this, "3", Toast.LENGTH_SHORT).show();
					break;
				case 7:
					Toast.makeText(sistema.this, "3", Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
            }
        });
    }
    
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent= new Intent(this.getApplicationContext(),Login.class);
			this.finish();
			startActivity(intent);
		}
		return true;

	}
}
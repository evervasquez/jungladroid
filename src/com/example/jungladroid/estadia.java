package com.example.jungladroid;

import java.util.ArrayList;

import side.menu.scroll.ScrollerLinearLayout;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import librerias.itemMenu;
import librerias.social;
import adapters.itemMenuAdapter;
import com.example.jungladroid.sistema;

public class estadia extends Activity {

	private LinearLayout rootLayout;
	private ScrollerLinearLayout sideSlideLayout;
	private Intent intent;
	private ListView listView;

	/*
	 * private final String[] menuTitles = {"Habitaciones", "Estadia", "Pagos",
	 * "Stock", "Caja", "Deudas"};
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setTitle(sistema.nombre + " " + sistema.apellido + " : "
				+ sistema.perfil);
		setContentView(R.layout.estadia);
		init();
		setMenuButton();
		setListView();
		// setContent(0);
	}

	private void init() {
		this.sideSlideLayout = (ScrollerLinearLayout) findViewById(R.id.menu_content_side_slide_layout);
		this.rootLayout = (LinearLayout) findViewById(R.id.menu_content_root);
	}

	private void setMenuButton() {
		Button menuButton = (Button) findViewById(R.id.main_tmp_button);
		menuButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				sideSlideLayout.scroll();
			}
		});
	}

	private void setListView() {

		/*
		 * ArrayList<String> items = new ArrayList<String>(); for (int i = 0; i
		 * < menuTitles.length; i++) { items.add(menuTitles[i]); }
		 */

		listView = (ListView) findViewById(R.id.menu_content_menulist);
		listView.setFadingEdgeLength(0);
		// ListView lv = (ListView)findViewById(R.id.listView);

		ArrayList<itemMenu> itemsCompra = obtenerItems();

		itemMenuAdapter adapter = new itemMenuAdapter(this, itemsCompra);

		listView.setAdapter(adapter);
		/*
		 * MenuAdapter menuAdapter = new MenuAdapter(this, items, this);
		 * listView.setAdapter(menuAdapter);
		 */
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				setContent(arg2);
				sideSlideLayout.scroll();
			}
		});

	}

	private ArrayList<itemMenu> obtenerItems() {
		ArrayList<itemMenu> items = new ArrayList<itemMenu>();

		items.add(new itemMenu(1, "Habitaciones", "roon",
				"drawable/habitaciones"));
		items.add(new itemMenu(2, "Estadia", "stay", "drawable/estadia"));
		items.add(new itemMenu(3, "Pagos", "payments", "drawable/pagos"));
		items.add(new itemMenu(4, "Stock", "stock", "drawable/stock"));
		items.add(new itemMenu(5, "Caja", "cash", "drawable/caja"));
		items.add(new itemMenu(6, "Deudas", "liabilities", "drawable/deudas"));
		items.add(new itemMenu(7, "Compartir", "share", "drawable/compartir"));
		items.add(new itemMenu(8, "Creditos", "credits", "drawable/flag"));
		items.add(new itemMenu(9, "Salir", "exit", "drawable/salir"));

		return items;
	}

	/*
	 * private void setContent(int position){ rootLayout.removeAllViews();
	 * TextView tmpText = new TextView(this);
	 * tmpText.setText(menuTitles[position]); rootLayout.addView(tmpText); }
	 */

	private void setContent(int position) {
		switch (position) {
		case 0:
			intent = new Intent(estadia.this, a_habitaciones.class);
			startActivity(intent);
			this.finish();
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			librerias.social
					.share(this, "jungla",
							"El único rincón de la selva en la ciudad - Albergue Turistico La jungla");
			break;
		case 7:
			break;
		case 8:
			intent = new Intent(estadia.this, Login.class);
			this.finish();
			new sistema().finish();
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}

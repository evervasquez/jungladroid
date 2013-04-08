package com.example.jungladroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.ksoap2.serialization.SoapObject;
import librerias.habitacion;
import librerias.conecta_ws;
import adapters.AdaptadorHabitacion;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

public class a_habitaciones extends ListActivity implements OnClickListener {
	String apelativo = "numero: ";
	conecta_ws list_habitaciones;
	SoapObject result, pi, element;
	String codigo;
	ListView li;
	Point p;
	String[] habitacion;
	String[] tipo_habitacion;
	String[] nom_variables;
	String[] datos_variables;
	List<habitacion> habitaciones;
	int cur;
	ProgressDialog dialog;
	Context contexto;
	String METHOD_NAME = "get_habitaciones";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setTitle(sistema.nombre + " " + sistema.apellido + " : "
				+ sistema.perfil);
		codigo = "ever";
		contexto = getApplicationContext();
		nom_variables = new String[] { "codigo" };// nombres de las variables
		datos_variables = new String[] { codigo };// datos de las variables

		AsyncTasks_habitacion tarea = new AsyncTasks_habitacion(
				a_habitaciones.this);
		tarea.execute();

		// *************************************************************
		// establecemos el evento LongClick

		// capturamos el objeto ListView y lo guardamos en lv
		li = getListView();

		// Then you can create a listener like so:
		li.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> av, View v,
					int position, long id) {
				onLongListItemClick(v, position, id);
				return false;
			}

			private void onLongListItemClick(View v, int position, long id) {
				// TODO Auto-generated method stub
				if (p != null)
					showPopup(a_habitaciones.this, p);
			}
		});
		// *******************************************************************
	}

	/*
	 * evento para el click en un ListView protected void
	 * onListItemClick(ListView l, View v, int position, long id) { // TODO
	 * Auto-generated method stub super.onListItemClick(l, v, position, id); if
	 * (p != null) showPopup(a_habitaciones.this, p); }
	 */

	private class AsyncTasks_habitacion extends AsyncTask {
		private ProgressDialog dialog;
		private ProgressBar mProgress;
		private ListActivity activity;

		// private Context context;
		public AsyncTasks_habitacion(ListActivity activity) {
			this.activity = activity;
			context = activity;
			dialog = new ProgressDialog(context);
		}

		private Context context;

		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stu
			list_habitaciones = new conecta_ws();
			result = (SoapObject) list_habitaciones.get_ResultadoWS(
					getApplicationContext(), nom_variables, datos_variables,
					METHOD_NAME);
			return 1;
		}

		protected void onPreExecute() {
			// li=getListView();
			// li.setBackgroundResource(R.drawable.loading);
			this.dialog.setMessage("Recuperando datos...");
			this.dialog.show();
		}

		protected void onPostExecute(Object res) {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
			Vector<?> transactions = (Vector<?>) result
					.getProperty("habitacion_list");
			habitaciones = new ArrayList<habitacion>();
			habitacion = new String[transactions.size()];
			tipo_habitacion = new String[transactions.size()];
			for (int i = 0; i < transactions.size(); i++) {
				SoapObject transaction0 = (SoapObject) transactions
						.elementAt(i);// recupero el primer array
				habitacion[i] = (String) transaction0.getProperty(
						"NRO_HABITACION").toString();
				tipo_habitacion[i] = (String) transaction0.getProperty(
						"DESCRIPCION").toString();
				// lleno al listveiw
				habitaciones.add(new habitacion(habitacion[i], apelativo
						+ tipo_habitacion[i]));
			}
			setListAdapter(new AdaptadorHabitacion(contexto, habitaciones));
		}

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {

		int[] location = new int[2];
		// Button button = (Button) findViewById(R.id.show_popup);

		// Get the x, y location and store it in the location[] array
		// location[0] = x, location[1] = y.
		// button.getLocationOnScreen(location);

		// Initialize the Point with x, and y positions
		p = new Point();
		p.x = location[0];
		p.y = location[1];
	}

	// The method that displays the popup.
	private void showPopup(final Activity context, Point p) {
		int popupWidth = 270;
		int popupHeight = 350;

		// Inflate the popup_layout.xml
		LinearLayout viewGroup = (LinearLayout) context
				.findViewById(R.id.popup);
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = layoutInflater.inflate(R.layout.habitacion_info,
				viewGroup);

		// Creating the PopupWindow
		final PopupWindow popup = new PopupWindow(context);
		popup.setContentView(layout);
		popup.setWidth(popupWidth);
		popup.setHeight(popupHeight);
		popup.setFocusable(true);

		// Some offset to align the popup a bit to the right, and a bit down,
		// relative to button's position.
		int OFFSET_X = 20;
		int OFFSET_Y = 60;

		// Clear the default translucent background
		popup.setBackgroundDrawable(new BitmapDrawable());

		// Displaying the popup at the specified location, + offsets.
		popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y
				+ OFFSET_Y);

		// Getting a reference to Close button, and close the popup when
		// clicked.
		Button close = (Button) layout.findViewById(R.id.close);
		close.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				popup.dismiss();
			}
		});

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}

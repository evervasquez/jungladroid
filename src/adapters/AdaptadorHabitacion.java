package adapters;

import java.util.ArrayList;
import java.util.List;
import librerias.habitacion;
import com.example.jungladroid.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdaptadorHabitacion extends BaseAdapter {
	Context context;
	/**
	 * 
	 */
	habitacion[] da;
	private LayoutInflater li;
	private List<habitacion> habitacion = new ArrayList<habitacion>();

	public AdaptadorHabitacion(Context context, List<habitacion> items) {
		this.context = context;
		li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		habitacion = items;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return habitacion.size();
	}

	public Object getItem(int location) {
		return habitacion.get(location);
	}

	public long getItemId(int location) {
		return location;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;
		final habitacion user = habitacion.get(position);
		if (v == null) {
			v = li.inflate(R.layout.habitacion_row, null);
		}
		final ImageView mLogo = (ImageView) v.findViewById(R.id.list_image);
		mLogo.setImageResource(R.drawable.hospedaje);

		final TextView nameTv = (TextView) v.findViewById(R.id.curso);
		nameTv.setText(user.gethabitacion());

		final TextView genderTv = (TextView) v.findViewById(R.id.docente);
		genderTv.setText(user.gettipohabitacion());

		return v;

	}
}

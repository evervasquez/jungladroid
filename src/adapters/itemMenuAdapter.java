package adapters;

import java.util.ArrayList;

import android.app.Activity;
import com.example.jungladroid.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import librerias.itemMenu;

public class itemMenuAdapter extends BaseAdapter {
	  protected Activity activity;
	  protected ArrayList<itemMenu> items;
	         
	  public itemMenuAdapter(Activity activity, ArrayList<itemMenu> items) {
	    this.activity = activity;
	    this.items = items;
	  }
	 
	  public int getCount() {
	    return items.size();
	  }
	 
	  public Object getItem(int position) {
	    return items.get(position);
	  }
	 
	  public long getItemId(int position) {
	    return items.get(position).getId();
	  }
	 
	  public View getView(int position, View convertView, ViewGroup parent) {
	    View vi=convertView;
	         
	    if(convertView == null) {
	      LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	      vi = inflater.inflate(R.layout.list_item_layout, null);
	    }
	             
	    itemMenu item = items.get(position);
	         
	    ImageView image = (ImageView) vi.findViewById(R.id.imagen);
	    int imageResource = activity.getResources().getIdentifier(item.getRutaImagen(), null, activity.getPackageName());
	    image.setImageDrawable(activity.getResources().getDrawable(imageResource));
	         
	    TextView nombre = (TextView) vi.findViewById(R.id.nombre);
	    nombre.setText(item.getNombre());
	         
	    TextView tipo = (TextView) vi.findViewById(R.id.tipo);
	    //tipo.setText(item.getTipo());
	 
	    return vi;
	  }

	}
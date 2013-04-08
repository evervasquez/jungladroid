package librerias;

import com.example.jungladroid.R;

import android.content.Context;
import android.content.Intent;

/**
 * Share a content using the user's installed apps
 *	CLASE PASA COMPARTIR EN LAS APLICACIONES INSTALADAS !
 * Thanks to : http://labs.emich.be/2010/01/23/how-to-send-to-twitter-or-facebook-from-your-android-application/
 *
 * @author http://francho.org/lab/
 *
 */
public class social {
	/**
	 * Open a contextual Menu with the available applications to share
	 *
	 * @param the Context (to open the menú and the new activity)
	 * @param the subject
	 * @param the text
	 */
	public static void share(Context ctx, String subject,String text) {
		 final Intent intent = new Intent(Intent.ACTION_SEND);

		 intent.setType("text/plain");
		 intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		 intent.putExtra(Intent.EXTRA_TEXT, text);

		 ctx.startActivity(Intent.createChooser(intent, ctx.getString(R.string.tit_share)));
		}
}

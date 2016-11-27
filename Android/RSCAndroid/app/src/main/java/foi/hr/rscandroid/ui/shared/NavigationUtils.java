package foi.hr.rscandroid.ui.shared;


import android.app.Activity;
import android.content.Intent;

import foi.hr.rscandroid.R;

public class NavigationUtils {

    public static void navigateWithSlideLeft(Activity activity, Intent intent, boolean finish) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

        if (finish) {
            activity.finish();
        }
    }

    public static void navigateWithSlideRight(Activity activity, Intent intent, boolean finish) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        if (finish) {
            activity.finish();
        }
    }
}

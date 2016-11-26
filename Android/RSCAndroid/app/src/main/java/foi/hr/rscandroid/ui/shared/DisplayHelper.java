package foi.hr.rscandroid.ui.shared;


import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import foi.hr.rscandroid.RSCApplication;

public class DisplayHelper {

    public DisplayHelper() {
    }

    public static void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) RSCApplication.getInstance()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager
                    .hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}

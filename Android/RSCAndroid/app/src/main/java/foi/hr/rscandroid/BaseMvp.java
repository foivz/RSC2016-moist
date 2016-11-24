package foi.hr.rscandroid;

import android.content.DialogInterface;
import android.support.annotation.StringRes;

/**
 * @author Igor Tepavac
 *         igor.tepavac@infinum.hr
 * @since 24/11/16
 */

public interface BaseMvp {

    interface View {

        void showProgress();

        void hideProgress();

        void showMessage(@StringRes int messageResId);

        void showMessage(String message);

        void showMessage(String message, DialogInterface.OnClickListener okListener);

        void showMessage(String message, @StringRes int positiveText, DialogInterface.OnClickListener positiveListener,
                         @StringRes int negativeText, DialogInterface.OnClickListener negativeListener);
    }

    // TODO: add base presenter and interactor only if really needed
}

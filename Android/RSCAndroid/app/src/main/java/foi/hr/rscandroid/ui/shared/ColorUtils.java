package foi.hr.rscandroid.ui.shared;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.annotation.ColorInt;

public class ColorUtils {

    @ColorInt
    public static int getDarkerColor(@ColorInt int color) {
        int r = (int) ((Color.red(color) * (1 + 0.1) / 255 - 0.1) * 255);
        int g = (int) ((Color.green(color) * (1 + 0.1) / 255 - 0.1) * 255);
        int b = (int) ((Color.blue(color) * (1 + 0.1) / 255 - 0.1) * 255);
        return Color.argb(Color.alpha(color), r, g, b);
    }

    public static ValueAnimator getColorChangeAnimator(@ColorInt int oldColor, @ColorInt int newColor,
                                                       int colorChangeDuration, ValueAnimator.AnimatorUpdateListener listener) {
        ValueAnimator animator = new ValueAnimator();
        animator.setIntValues(oldColor, newColor);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setDuration(colorChangeDuration);
        animator.addUpdateListener(listener);
        return animator;
    }
}

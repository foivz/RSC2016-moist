package foi.hr.rscandroid.ui.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.ui.BaseActivity;


public class BarCodeActivity extends BaseActivity {

    private static final int SIZE = 1000;

    @BindView(R.id.imageCode)
    ImageView imageCode;

    private int white;

    private int black;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);

        white = ResourcesCompat.getColor(getResources(), android.R.color.white, null);
        black = ResourcesCompat.getColor(getResources(), android.R.color.black, null);

        Bitmap bitmap = null;
        try {
            bitmap = encodeAsBitmap("jedan mali strdasjkdjkasdkjasjdasjkdbsajkdbsajkbdkjasbdjaksing");
        } catch (WriterException e) {
            e.printStackTrace();
        }
        imageCode.setImageBitmap(bitmap);
    }

    private Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, SIZE, SIZE, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }

        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? black : white;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, SIZE, 0, 0, w, h);
        return bitmap;
    }
}

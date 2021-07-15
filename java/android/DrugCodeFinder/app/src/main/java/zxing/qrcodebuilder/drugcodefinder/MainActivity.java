package zxing.qrcodebuilder.drugcodefinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    ImageView ivCode;
    EditText etContent;
    TextView codedisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.changeAppBrightness(this,1);
        ivCode = (ImageView) findViewById(R.id.ivCode);
        etContent = (EditText) findViewById(R.id.etContent);
        codedisplay = (TextView) findViewById(R.id.codedisplay);
        etContent.requestFocus();
    }

    public void genCode(View view) {

        String drugCode = etContent.getText().toString();
        String tmp = drugCode + "0019999999";
        BarcodeEncoder encoder = new BarcodeEncoder();
        try {
            Bitmap bit = encoder.encodeBitmap(tmp, BarcodeFormat.CODE_128,
                    1000, 250);
            ivCode.setImageBitmap(bit);
            codedisplay.setText(drugCode);
            etContent.setText("");
        } catch (WriterException e) {
            e.printStackTrace();
        }

    }

//    此方法為進入這個app後，螢幕亮度自動調到最亮，離開app後亮度自動恢復本來的亮度。
    public void changeAppBrightness(Context context, int brightness) {
        Window window = ((Activity) context).getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.screenBrightness = 1f;
        window.setAttributes(lp);
        }

}

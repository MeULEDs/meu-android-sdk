package com.ankitg.meusample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ankitguglani.MeU;

public class MeUActivity extends Activity {

	Button btn_connect;
	Button btn_send_text;
	ImageButton btn_send_image;
	EditText et_text;
	
	MeU MeU = new MeU();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu);
        
        btn_connect = (Button)findViewById(R.id.BTN_connect);
        btn_send_text = (Button)findViewById(R.id.BTN_sendText);
        btn_send_image = (ImageButton)findViewById(R.id.BTN_main_image1);
        et_text = (EditText)findViewById(R.id.ET_main);
    }
    
    public void connect(View v)
    {
    	MeU.setupBluetooth(getApplicationContext(), "00:06:66:6A:CB:9A"); // Change this to the SSID of your MeU
    }
    
    public void sendText(View v)
    {
    	MeU.sendText(et_text.getText().toString(), "FF0000");
    }
    
    public void sendImage(View v)
    {
    	Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    	MeU.sendImage(bmp);
    }

}

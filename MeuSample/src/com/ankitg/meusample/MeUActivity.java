package com.ankitg.meusample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.ankitguglani.MeU;
import com.larswerkman.holocolorpicker.ColorPicker;

public class MeUActivity extends Activity implements OnClickListener {

	Button btn_connect;
//	Button btn_disconnect;
	Button btn_refresh;
	Button btn_send_text;
	ImageButton btn_send_image1;
	ImageButton btn_send_image2;
	ImageButton btn_send_image3;
	ImageButton btn_send_image4;
	ImageButton btn_send_image5;
	ImageButton btn_send_image6;
	ImageButton btn_send_image7;
	ImageButton btn_send_image8;
	ImageButton btn_send_image9;
	ImageButton btn_send_image10;
	ImageButton btn_send_image11;
	ImageButton btn_send_image12;
	TextView tv_bt_status;
	EditText et_text;
	Spinner spn_bluetooth;
	ColorPicker picker;
	
	MeU MeU = new MeU();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu);
        
        init();
    }
    
    private void init() {
        btn_connect = (Button)findViewById(R.id.BTN_main_connect);
//        btn_disconnect = (Button)findViewById(R.id.BTN_main_disconnect);
        btn_refresh = (Button)findViewById(R.id.BTN_main_refresh);
        btn_send_text = (Button)findViewById(R.id.BTN_main_sendText);
        btn_send_image1 = (ImageButton)findViewById(R.id.BTN_main_image1);
        btn_send_image2 = (ImageButton)findViewById(R.id.BTN_main_image2);
        btn_send_image3 = (ImageButton)findViewById(R.id.BTN_main_image3);
        btn_send_image4 = (ImageButton)findViewById(R.id.BTN_main_image4);
        btn_send_image5 = (ImageButton)findViewById(R.id.BTN_main_image5);
        btn_send_image6 = (ImageButton)findViewById(R.id.BTN_main_image6);
        btn_send_image7 = (ImageButton)findViewById(R.id.BTN_main_image7);
        btn_send_image8 = (ImageButton)findViewById(R.id.BTN_main_image8);
        btn_send_image9 = (ImageButton)findViewById(R.id.BTN_main_image9);
        btn_send_image10 = (ImageButton)findViewById(R.id.BTN_main_image10);
        btn_send_image11 = (ImageButton)findViewById(R.id.BTN_main_image11);
        btn_send_image12 = (ImageButton)findViewById(R.id.BTN_main_image12);
        tv_bt_status = (TextView)findViewById(R.id.TV_main_bluetooth_status);
        et_text = (EditText)findViewById(R.id.ET_main_text);
        spn_bluetooth = (Spinner)findViewById(R.id.SPN_main_bluetooth_list);
        picker = (ColorPicker)findViewById(R.id.Picker_main_colorpicker);
        
        btn_connect.setOnClickListener(this);
//        btn_disconnect.setOnClickListener(this);
        btn_refresh.setOnClickListener(this);
        btn_send_image1.setOnClickListener(this);
        btn_send_image2.setOnClickListener(this);
        btn_send_image3.setOnClickListener(this);
        btn_send_image4.setOnClickListener(this);
        btn_send_image5.setOnClickListener(this);
        btn_send_image6.setOnClickListener(this);
        btn_send_image7.setOnClickListener(this);
        btn_send_image8.setOnClickListener(this);
        btn_send_image9.setOnClickListener(this);
        btn_send_image10.setOnClickListener(this);
        btn_send_image11.setOnClickListener(this);
        btn_send_image12.setOnClickListener(this);
        btn_send_text.setOnClickListener(this);
        
        picker.setShowOldCenterColor(false);
        populateBtDevices();
        updateStatus();
    }
    
    private void updateStatus()
    {
    	tv_bt_status.setText(MeU.btStatus());
    }
    
    private void populateBtDevices(){    	
    	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
        		android.R.layout.simple_spinner_item, MeU.listMeUs(getApplicationContext()));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_bluetooth.setAdapter(dataAdapter);
        updateStatus();
    }
    
    private void connect()
    {
    	if(spn_bluetooth.getSelectedItem() != null)
    	{
    		MeU.setupBluetooth(getApplicationContext(), spn_bluetooth.getSelectedItem().toString());
    	}
    	updateStatus();
    }
    
//	private void disconnnect() {
//		MeU.disconnectBt();
//		updateStatus();		
//	}
    
    private void sendText()
    {
    	MeU.sendText(et_text.getText().toString(), Integer.toHexString(picker.getColor()).substring(2));
    }
    
    private void sendImage(int resource)
    {
    	Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
    	MeU.sendImage(bmp);
    }

	@Override
	public void onClick(View v) {
		switch (v.getId())
		{
			case R.id.BTN_main_connect:
				connect();
				break;
								
			case R.id.BTN_main_sendText:
				sendText();
				break;
				
			case R.id.BTN_main_image1:
				sendImage(R.drawable.nascent);
				break;
				
			case R.id.BTN_main_image2:
				sendImage(R.drawable.wwto);
				break;
				
			case R.id.BTN_main_image3:
				sendImage(R.drawable.heart);
				break;

			case R.id.BTN_main_image4:
				sendImage(R.drawable.star);
				break;

			case R.id.BTN_main_image5:
				sendImage(R.drawable.skull);
				break;

			case R.id.BTN_main_image6:
				sendImage(R.drawable.megaman);
				break;

			case R.id.BTN_main_image7:
				sendImage(R.drawable.hearts);
				break;

			case R.id.BTN_main_image8:
				sendImage(R.drawable.mushroom_red);
				break;

			case R.id.BTN_main_image9:
				sendImage(R.drawable.mushroom_green);
				break;

			case R.id.BTN_main_image10:
				sendImage(R.drawable.goomba);
				break;
				
			case R.id.BTN_main_image11:
				sendImage(R.drawable.fireflower);
				break;

			case R.id.BTN_main_image12:
				sendImage(R.drawable.gremlin);
				break;
			
			case R.id.BTN_main_refresh:
				populateBtDevices();
				break;
				
//			case R.id.BTN_main_disconnect:
//				disconnnect();
//				break;
		}
	}
}

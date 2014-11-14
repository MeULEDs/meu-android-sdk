package com.ankitguglani.meu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import cc.arduino.btserial.BtSerial;

public class SDK {

	static BtSerial bt;
	static int i=0;
	
	public static void setupBluetooth (Context context, String remoteAddress)
	{
		if (bt == null) {
			bt = new BtSerial(context);
		}
		
		if(remoteAddress == null || remoteAddress.isEmpty() || remoteAddress.length() != 17)
		{
			Log.e("meusdk","Please ensure the SSID of the MeU is set correctly. " + remoteAddress.length());
		}
		else
		{
			if(bt.isConnected() && bt.getRemoteAddress().contains(remoteAddress))
			{
				Log.d("meusdk","Bluetooth connected to " + bt.getRemoteName());
			}
			else
			{
				if(i < 5)
				{
					bt.connect(remoteAddress);
					Log.e("meusdk","Attempting Bluetooth Connection: " + i);
					i++;
					setupBluetooth (context, remoteAddress);
				}
				else 
				{
					Log.e("meusdk","Failed to connect to Bluetooth after 5 retries. Giving up.");
					i=0;
				}
			}
		}
	}
	
	private static void sendMessage(String meuString) {
		if(bt != null && bt.isConnected())
		{
			bt.write(meuString);
			Log.d("meusdk","Data sent successfully.");
		}
		else
		{
			Log.e("meusdk","Bluetooth Connection failed.");
		}
	}
		
    private static String bitmapToHexString(Bitmap bmp) {
    	int w = 16; //bmp.getWidth();
    	int h = 16; //bmp.getHeight();
    	String hexString ="";
      
    	Bitmap bmpScaled = Bitmap.createScaledBitmap(bmp, 16, 16, false);
    	
    	for (int y = 0; y < w; y++) { // width
    		for (int x = 0; x < h; x++) { // height
	          	int color = bmpScaled.getPixel(x, y);
	          	
	          	int r = Color.red(color);
	          	int g = Color.green(color);
	          	int b = Color.blue(color);
	          	
	          	String hex = String.format("%02x%02x%02x", r, g, b).toUpperCase();
	          	hexString += hex;
    		}
    	}
    	return hexString;
    }

    private static String getMeuTextString(String text, String color) {
    	if(color.isEmpty() || color == null || color.length() != 6) {
    		color = "ffffff";
    	}
    	return "b"+color+text+"\r\n";
    }
    
    private static String getMeuTextString(String text) {
    	return getMeuTextString(text,"ffffff");
    }

    private static String getMeuImageString(Bitmap bmp) {
    	return "i"+bitmapToHexString(bmp);
    }

    public static void sendText(String text, String color) {
    	sendMessage(getMeuTextString(text, color));
    }

    public static void sendText(String text) {
    	sendMessage(getMeuTextString(text));
    }

    public static void sendImage(Bitmap bmp) {
    	sendMessage(getMeuImageString(bmp));
    }

}

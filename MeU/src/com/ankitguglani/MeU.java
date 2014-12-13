package com.ankitguglani;

import java.util.ArrayList;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
//import android.widget.Toast;
import cc.arduino.btserial.BtSerial;

/**
 * This acts a basic SDK for building android applications for the MeU, wearable LED platform.
 * @author Ankit Guglani
 * @version 0.0.2
 */
public class MeU {
	BtSerial bt;
	int i=0;
		
	/**
	 * This method enables the user to connect to the MeU using bluetooth.
	 * @author ankitg
	 * @param context
	 * @param remoteAddress
	 */
	public void setupBluetooth (Context context, String remoteAddress)
	{
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
		    // Device does not support Bluetooth
			Log.e("meusdk","Device does not support Bluetooth");
//			Toast.makeText(context, "This device does not support Bluetooth.", Toast.LENGTH_SHORT).show();
		} else {
		    if (mBluetoothAdapter.isEnabled()) {
		        // Bluetooth is enabled
		    	
				if (bt == null) {
					bt = new BtSerial(context);
				}
				
				if(remoteAddress == null || remoteAddress.isEmpty() || remoteAddress.length() != 17)
				{
					Log.e("meusdk","Please ensure the SSID of the MeU is set correctly. " + remoteAddress.length());
//					Toast.makeText(context, "Please ensure the SSID of the MeU is set correctly.", Toast.LENGTH_SHORT).show();
				}
				else
				{
					if(bt.isConnected() && bt.getRemoteAddress().contains(remoteAddress))
					{
						Log.d("meusdk","Bluetooth connected to " + bt.getRemoteName());
//				    	Toast.makeText(context, "Connected to " + bt.getRemoteName(), Toast.LENGTH_SHORT).show();
					}
					else
					{
						if(i < 5)
						{
							bt.connect(remoteAddress);
							Log.e("meusdk","Attempting Bluetooth Connection: " + i);
							i++;
//					    	Toast.makeText(context, "Failed to connect to Bluetooth "+i+" times. Retrying ...", Toast.LENGTH_SHORT).show();
							setupBluetooth (context, remoteAddress);
						}
						else 
						{
							Log.e("meusdk","Failed to connect to Bluetooth after 5 retries. Giving up.");
//							Toast.makeText(context, "Failed to connect to Bluetooth. Giving up.", Toast.LENGTH_LONG).show();
							i=0;
						}
					}
				}
		    	
		    } else {
		    	// Bluetooth is disabled
		    	Log.d("meusdk","Bluetooth is disabled. Please enable bluetooth.");
//		    	Toast.makeText(context, "Bluetooth is disabled. Please enable bluetooth.", Toast.LENGTH_SHORT).show();
		    }
		}
	}

	private void sendMessage(String meuString) {
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
		
    private String bitmapToHexString(Bitmap bmp) {
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

    private String getMeuTextString(String text, String color) {
    	if(color.isEmpty() || color == null || color.length() != 6) {
    		color = "ffffff";
    	}
    	return "b"+color+text+"\r\n";
    }
    
    private String getMeuTextString(String text) {
    	return getMeuTextString(text,"ffffff");
    }

    private String getMeuImageString(Bitmap bmp) {
    	return "i"+bitmapToHexString(Bitmap.createScaledBitmap(bmp, 16, 16, false));
    }

    /**
     * This method enables the user to send text and color (as a hex code string) to be marqueed on the MeU
     * @author ankitg
     * @param text
     * @param color
     */
    public void sendText(String text, String color) {
    	sendMessage(getMeuTextString(text, color));
    }

    /**
     * This method enables the user to send text to be marqueed on the MeU
     * @author ankitg
     * @param text
     */
    public void sendText(String text) {
    	sendMessage(getMeuTextString(text));
    }

    /**
     * This method enables the user to send a bitmap to be displayed on the MeU
     * @author ankitg
     * @param bmp
     */
    public void sendImage(Bitmap bmp) {
    	sendMessage(getMeuImageString(bmp));
    }

    /**
     * This method enables the user to send a raw string to be processed by the sketch currently loaded on the MeU
     * @author ankitg
     * @param text
     */
    public void sendRaw(String text) {
    	sendMessage(text);
    }
    
    /**
     * This method exposes the list of available MeUs in the vicinity.
     * @author ankitg
     */
     public String[] listMeUs(Context context)
     {
//    	String[] MeUList = new String[]{};
    	ArrayList<String> MeUList = new ArrayList<String>();
    	 
 		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
 		if (mBluetoothAdapter == null) {
 		    // Device does not support Bluetooth
 			Log.e("meusdk","Device does not support Bluetooth");
// 			Toast.makeText(context, "This device does not support Bluetooth.", Toast.LENGTH_SHORT).show();
 		} else {
 		    if (mBluetoothAdapter.isEnabled()) {
 		        // Bluetooth is enabled
 		    	
 				if (bt == null) {
 					bt = new BtSerial(context);
 				}
 				
 				String[] BtDeviceList = bt.list();
 				String filter = "00:06:66:6A:";
 				
 				for (String device : BtDeviceList)
 				{
 					if(device.startsWith(filter))
 					{
 						MeUList.add(device);
 					}
 				}
 				
 		    }  else {
		    	// Bluetooth is disabled
		    	Log.d("meusdk","Bluetooth is disabled. Please enable bluetooth.");
//		    	Toast.makeText(context, "Bluetooth is disabled. Please enable bluetooth.", Toast.LENGTH_SHORT).show();
		    }
 		}
 		
		return MeUList.toArray(new String[MeUList.size()]);
     }

//     /**
//      * This method exposes the list of available MeUs in the vicinity.
//      * @author ankitg
//      */
//      public void disconnectBt()
//      {
//     	 
//  		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//  		if (mBluetoothAdapter == null) {
//  		    // Device does not support Bluetooth
//  			Log.e("meusdk","Device does not support Bluetooth");
//  		} else {
//  		    if (mBluetoothAdapter.isEnabled()) {
//  		        // Bluetooth is enabled
//  		    	
//  				if (bt != null && bt.isConnected()) {
////					bt.disconnect(); // crashes the app :(
//  					bt = null;
//  				}
//  				
//  		    }  else {
// 		    	// Bluetooth is disabled
// 		    	Log.d("meusdk","Bluetooth is disabled. Please enable bluetooth.");
// 		    }
//  		}
//     }

      /**
       * This method exposes the current Bluetooth status.
       * @author ankitg
       */
       public String btStatus()
       {
      	 
   		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
   		if (mBluetoothAdapter == null) {
   		    // Device does not support Bluetooth
   			return ("Device does not support Bluetooth");
   		} else {
   		    if (mBluetoothAdapter.isEnabled()) {
   		        // Bluetooth is enabled
   				if (bt != null) {
   					if (bt.isConnected())
   					{
   						return ("Connected to "+bt.getName());
   					}
   					else if(mBluetoothAdapter.isDiscovering())
   					{
   						return ("Scanning");
   					}
   					else
   					{
   						return ("Disconnected");
   					}
   				}
   				return("Service not initialized. Please refresh.");
   				
   		    }  else {
  		    	// Bluetooth is disabled
  		    	return("Bluetooth is disabled.");
  		    }
		}
	}
}

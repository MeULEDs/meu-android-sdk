MeU Android SDK
===============

### About

**version: 0.0.1**

Simple implementation to connect to a MeU and send text, images or custom data over bluetooth.

What is the [MeU](http://www.themeu.net/)?
It's a flexible, wearable, programmable LED panel.

### Usage

The current implementation provides for 2 jar files which should be included into the projects build path. This may be achieved by adding these files to the *libs* folder in an eclipse based android project.

Make sure the your project has bluetooth permissions. You can obtain these by adding the following lines to your *AndroidManifest.xml*, before the \<application ...\> tag

`<uses-permission android:name="android.permission.BLUETOOTH" />`

Create and initialize an instance of the SDK like so:

`MeU MeU = new MeU();`

and initiate the bluetooth connection using the SSID of the MeU, like so:

`MeU.setupBluetooth(getApplicationContext(), "00:06:66:6A:CB:9A");`

Make sure to replace the SSID above with the one for your MeU.

Now you are read to send text, using the sendText method.

`MeU.sendText("This is awesome!");`

`MeU.sendText("I like RED!", "FF0000");`

Or bitmaps using

`MeU.sendImage(mBitmap);`

Or if you modify the code running on the MeU you can still use this library to connect and send custom strings using

`MeU.sendRaw("YOUR CUSTOM STRING HERE");`

That's pretty much it, enjoy!

### Sample

I've also include a (very poorly designed) sample app to help you along should you get stuck somewhere.

### Feedback

This was pretty much hacked together in a couple hours to make development easier for others. If there's enough interest, I'll make time to fix this up by adding proper interfaces for event handling AND a disconnect and clean up method.

Should you find any bugs please feel free to reach me @ ankitg[at]gmail[dot]com
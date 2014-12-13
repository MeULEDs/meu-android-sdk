MeU Android SDK
===============

### About

**version: 0.0.2**

Simple implementation to connect to a MeU and send text, images or custom data over bluetooth.

What is the [MeU](http://www.themeu.net/)?
It's a flexible, wearable, programmable LED panel.

### Repository Structure

This repository has 3 folder.

* **SDK-libs** This folder contains 2 jar files needed to incorporate the SDK into your project.
* **MeU** This folder contains the source code for the MeU SDK, and may be used a reference, or modified as desired.
* **MeuSample** This folder contains the sample project.

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

There are a few more methods available (such as listing currently available MeUs, getting the bluetooth status etc). The full source and a sample app is bundled in this repository for reference.

That's pretty much it, enjoy!

### Sample

I've also include a sample app to help you along should you get stuck somewhere. Here's what it looks like:

![Sample App](https://raw.github.com/ankitg/meu-android-sdk/master/MeU_Sample.png)

### Feedback

This was pretty much hacked together in a couple hours to make development easier for others. If there's enough interest, I'll make time to fix this up by adding proper interfaces for event handling AND a disconnect and clean up method.

Should you find any bugs please feel free to reach me @ ankitg[at]gmail[dot]com

### Credits

All images used in the sample are for educational purposes only and are properties of their respective copyright owners.

The SDK makes use of the [btserial](https://github.com/arduino/BtSerial) library (under the GPL / LGPL licenses).

The sample references the [Holo Color Picker](https://github.com/LarsWerkman/HoloColorPicker) which is under the [Apache License](https://github.com/LarsWerkman/HoloColorPicker/blob/master/LICENSE).

Special thanks to Robert Tu from [MeU](http://www.themeu.net/) for lending me the hardware to make this project possible.
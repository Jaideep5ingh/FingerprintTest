package com.component.independent.fingerprinttest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

/**
 * Created by jai00 on 14-03-2017.
 */

public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {
    private Context context;


    public FingerprintHandler(Context context){
        this.context= context;
    }

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject){
        CancellationSignal cancellationSignal = new CancellationSignal();

        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED){
            return;
        }

        manager.authenticate(cryptoObject,cancellationSignal,0,this,null);
    }


    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        this.update("Fingerprint authentication error\n" + errString, false);

    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update("Fingerprint authentication help\n" + helpString, false);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update("Fingerprint authentication failed.", false);
    }


    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("Fingerprint authentication succeeded!!!!!!", true);
    }



    private void update(String e, boolean success) {
        TextView textView =(TextView)((Activity)context).findViewById(R.id.errorText);
        textView.setText(e);

        if(success){
            textView.setTextColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));
        }

    }
}

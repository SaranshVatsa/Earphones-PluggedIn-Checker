package com.example.muj.earphoneschecker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static int seekValue;
    BroadcastReceiver bR=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void register(View view){
        bR=new EarphoneStateReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(bR, intentFilter);
        Toast.makeText(this, "Receiver Registered", Toast.LENGTH_SHORT).show();
        SeekBar seekBar=(SeekBar)findViewById(R.id.seekBar1);
        seekValue=seekBar.getProgress();
    }

    public void musicLaunch(View view){
        try {
            PackageManager packageManager = getPackageManager();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent = packageManager.getLaunchIntentForPackage("com.cyanogenmod.eleven");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(intent);
        }catch(Exception e){
            Toast.makeText(this, "Error in launching music player", Toast.LENGTH_SHORT).show();
        }
    }
}

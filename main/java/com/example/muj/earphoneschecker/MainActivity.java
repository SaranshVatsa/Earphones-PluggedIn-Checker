package com.example.muj.earphoneschecker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
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
        //To launch the specific package (for CyanogenMod Eleven Music Player)
//        try {
//            PackageManager packageManager = getPackageManager();
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent = packageManager.getLaunchIntentForPackage("com.cyanogenmod.eleven");
//            intent.addCategory(Intent.CATEGORY_LAUNCHER);
//            startActivity(intent);
//        }catch(Exception e){
//            Toast.makeText(this, "Error in launching music player", Toast.LENGTH_SHORT).show();
//        }

        //To launch the default music player app on any phone.

        if(Build.VERSION.SDK_INT>=15) {
            try {
                Intent intent = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_MUSIC);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "Error opening music app", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            try{
                Intent intent=new Intent("android.intent.action.MUSIC_PLAYER");
            } catch (Exception e){
                Toast.makeText(this, "Error opening music app", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

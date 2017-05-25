package com.example.muj.earphoneschecker;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.provider.MediaStore;
import android.widget.Toast;

/**
 * Created by MUJ on 4/18/2017.
 */

public class EarphoneStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Activity activity=(Activity)context;
        AudioManager audioManager=(AudioManager)activity.getSystemService(Context.AUDIO_SERVICE);
        final int max=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int y=MainActivity.seekValue;
        int x=intent.getIntExtra("state",-1);
        if(x==1){
            Toast.makeText(context, "Earphones are plugged in", Toast.LENGTH_SHORT).show();
            try {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, y, 0);
            }catch (Exception e){
                Toast.makeText(context, "Error changing volume", Toast.LENGTH_SHORT).show();
            }
        }else if(x==0){
            Toast.makeText(context, "Earphones are NOT plugged in", Toast.LENGTH_SHORT).show();
            try {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
            }catch (Exception e){
                Toast.makeText(context, "Error changing volume", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

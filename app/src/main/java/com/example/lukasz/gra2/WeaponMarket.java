package com.example.lukasz.gra2;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WeaponMarket extends AppCompatActivity
{
    MediaPlayer soundMarket;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_market);

        soundMarket = MediaPlayer.create(getApplicationContext(), R.raw.trade);
        soundMarket.start();

    }
    protected void onPause()
    {
        super.onPause();
        if (soundMarket != null)
        {
            soundMarket.release();
            soundMarket = null;
        }
    }
}

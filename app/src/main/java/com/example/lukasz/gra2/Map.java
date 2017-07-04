package com.example.lukasz.gra2;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Map extends AppCompatActivity
{
    Button b;
    ImageView iv1, iv2;
    MediaPlayer sound, mediaPlayer;
    int location;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        b = (Button)findViewById(R.id.buttonExit);
        sound = MediaPlayer.create(getApplicationContext(), R.raw.map);
        sound.start();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.map_track);
        mediaPlayer.start();
        iv1 = (ImageView)findViewById(R.id.map);
        iv2 = (ImageView)findViewById(R.id.pointer);
        iv1.setImageResource(R.drawable.mapa);
        iv2.setImageResource(R.drawable.znacznik);

        Bundle extras = getIntent().getExtras();
        location = extras.getInt("location");
        switch (location)
        {
            case 0:
                iv2.setX(335);
                iv2.setY(490);
                break;
            case 1:
                iv2.setX(335);
                iv2.setY(400);
                break;
            case 2:
                iv2.setX(190);
                iv2.setY(380);
                break;
            case 3:
                iv2.setX(170);
                iv2.setY(250);
                break;
            case 4:
                iv2.setX(180);
                iv2.setY(150);
                break;
            case 5:
                iv2.setX(110);
                iv2.setY(120);
                break;
            case 6:
                iv2.setX(40);
                iv2.setY(70);
                break;
            case 7:
                iv2.setX(10);
                iv2.setY(20);
                break;
            case 8:
                iv2.setX(10);
                iv2.setY(20);
                break;
            case 9:
                iv2.setX(245);
                iv2.setY(130);
                break;
            case 10:
                iv2.setX(320);
                iv2.setY(100);
                break;
            case 11:
                iv2.setX(180);
                iv2.setY(390);
                break;
            default:
                break;
        }
    }

    public void onClick1(View view)
    {
        super.finish();
        sound.start();
        mediaPlayer.stop();
    }

}

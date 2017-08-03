package com.example.lukasz.gra2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CampFire extends AppCompatActivity
{
    Handler myHandlerBurnt1, myHandlerOverall;
    MediaPlayer music, sound;
    Animation fireAnim, clockAnim, meatAnim;
    Button bExit, controlButton;
    int items[] = new int[7];
    int yourHp, meatAmount, cookingStep = 0, cookedMeatAmount, firstSide = 0, secondSide = 0;
    boolean isFishMeat = false, isCookedMeat, threadloop;
    TextView tv1, tv2;
    ImageView bg, fireImg, imageMeat, clock, clockPointer;

    long timeTemp1, timeTemp2, startTime1, startTime2, timeOverall, startTimeOverall;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_fire);

        tv1 = (TextView)findViewById(R.id.textCampFire1);
        tv2 = (TextView)findViewById(R.id.textBellowBg);
        bExit = (Button)findViewById(R.id.buttonExitFireCamp);
        controlButton = (Button)findViewById(R.id.controlButton);

        Bundle extra = getIntent().getExtras();
        yourHp = extra.getInt("yourHp");
        meatAmount = extra.getInt("meatAmount");
        cookedMeatAmount = extra.getInt("cookedMeatAmount");
        isFishMeat = extra.getBoolean("isFishMeat");
        isCookedMeat = extra.getBoolean("isCookedMeat");

        items[1] = extra.getInt("drop1");
        items[2] = extra.getInt("drop2");
        items[3] = extra.getInt("drop3");
        items[4] = extra.getInt("drop4");
        items[5] = extra.getInt("drop5");
        items[6] = extra.getInt("drop6");

        fireAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fire);
        clockAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.clock_rotation);
        meatAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.meat_taken);

        bg = (ImageView) findViewById(R.id.bg_firecamp);

        fireImg = (ImageView)findViewById(R.id.image_fire);
        fireImg.startAnimation(fireAnim);
        imageMeat = (ImageView)findViewById(R.id.imageMeat);
        clock = (ImageView)findViewById(R.id.clockImg);
        clockPointer = (ImageView)findViewById(R.id.clockPointerImg);

        imageMeat.setVisibility(View.INVISIBLE);
        clock.setVisibility(View.INVISIBLE);
        clockPointer.setVisibility(View.INVISIBLE);

        music = MediaPlayer.create(getApplicationContext(), R.raw.fire);
        music.setLooping(true);
        music.start();
        sound = MediaPlayer.create(getApplicationContext(),R.raw.meat_frie);

        startTimeOverall = System.currentTimeMillis();

        threadloop = true;
        timers();
        tv2.setText("HP: "+yourHp);

        if (isFishMeat == true)
        {
            tv1.setText("Posiadasz "+meatAmount+" kawałków surowego, rybiego mięsa. Wrzuć na ruszt, smażone mięso jest bardziej pożywne.");
            firstSide = 0;
            secondSide = 0;
            imageMeat.setVisibility(View.VISIBLE);
            imageMeat.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    switch (cookingStep)
                    {
                        case 0:
                            firstSide = 0;
                            clock.setVisibility(View.VISIBLE);
                            clockPointer.setVisibility(View.VISIBLE);
                            clockPointer.startAnimation(clockAnim);
                            startTime1 = System.currentTimeMillis();
                            tv1.setText("Wrzucono na ruszt "+meatAmount+" kawałków surowego mięsa. Poczekaj, aż mięso się upiecze i przewróć na drugą stronę.");
                            imageMeat.setRotation(30);
                            imageMeat.setX(420);
                            sound.start();
                            cookingStep = 1;
                            break;
                        case 1:
                            if (firstSide == 1)
                            {
                                clockPointer.clearAnimation();
                                clockPointer.startAnimation(clockAnim);
                                startTime2 = System.currentTimeMillis();
                                tv1.setText("Przewrócono na drugą stronę. Poczekaj, aż mięso się upiecze i zdemij z rusztu.");
                                imageMeat.setImageResource(R.drawable.smazone_rybie_mieso1);
                                sound.start();
                                cookingStep = 2;
                            }
                            break;
                        case 2:
                            if(secondSide == 1)
                            {
                                clockPointer.clearAnimation();
                                clock.setVisibility(View.INVISIBLE);
                                clockPointer.setVisibility(View.INVISIBLE);
                                sound.start();
                                tv1.setText("Udało Ci się usmażyć " + meatAmount + " kawałków rybiego mięsa. Smacznego!");
                                isFishMeat = false;
                                cookedMeatAmount = cookedMeatAmount + meatAmount;
                                cookingStep = 3;
                                meatAmount = 0;
                                imageMeat.startAnimation(meatAnim);
                                imageMeat.setOnClickListener(null);
                            }
                            break;

                    }
                }
            });

        }
        else tv1.setText("Niestety nie masz czego przyrządzać. Ogrzej się więc przy ognisku i odpocznij.");

    }
    protected void onPause()
    {
        super.onPause();
        threadloop = false;
        if (music != null && music.isPlaying())
        {
            music.stop();
            music.reset();
            music.release();
            music = null;
        }
        if (sound != null)
        {
            sound.release();
            sound = null;
        }
    }
    public void onExit(View view)
    {
        Intent campFireOver = new Intent();
        campFireOver.putExtra("yourHp", yourHp);
        campFireOver.putExtra("meatAmount", meatAmount);
        campFireOver.putExtra("isFishMeat", isFishMeat);
        campFireOver.putExtra("cookedMeatAmount", cookedMeatAmount);
        campFireOver.putExtra("isCookedMeat", isCookedMeat);
        setResult(RESULT_OK, campFireOver);
        finish();
    }
    public void onClick(View view) //temporary button
    {
        tv2.setText("Overall: "+String.valueOf(timeOverall)+" Time 1: "+String.valueOf(timeTemp1)+" Time 2:"+String.valueOf(timeTemp2));
    }


    public void timers()
    {
        myHandlerOverall = new Handler();
        myHandlerBurnt1 = new Handler();
        new Thread(new Runnable()
        {
            public void run()
            {
                while(threadloop)
                {
                    timeTemp1 = (System.currentTimeMillis() - startTime1)/1000;
                    timeTemp2 = (System.currentTimeMillis() - startTime2)/1000;
                    timeOverall = (System.currentTimeMillis() - startTimeOverall)/1000;
                    try
                    {
                        if (timeOverall >= 20)
                        {
                            startTimeOverall = System.currentTimeMillis();
                            myHandlerOverall.post(new Runnable() {
                                public void run()
                                {
                                    if (yourHp <= 99)
                                    {
                                        toast("+1 HP");
                                        yourHp = yourHp + 1;
                                        tv2.setText("HP: "+yourHp);
                                        if (meatAmount == 0)
                                        {
                                            tv1.setText("Niestety nie masz czego przyrządzać. Ogrzej się więc przy ognisku i odpocznij.");
                                        }
                                    }

                                }
                            });
                        }
                        if (timeTemp1 == 45 && cookingStep == 1)
                        {
                            firstSide = 1;
                        }
                        if (timeTemp1 == 60 && cookingStep == 1)
                        {
                            sound.start();
                            firstSide = 2;
                            myHandlerBurnt1.post(new Runnable() {
                                public void run()
                                {
                                        tv1.setText("Spalono mięso!");
                                        imageMeat.setImageResource(R.drawable.spalone_rybie_mieso);
                                        meatAmount = 0;
                                        isFishMeat = false;
                                        cookingStep =3;
                                }
                            });
                        }
                        if (timeTemp2 == 45 && cookingStep == 2)
                        {
                            secondSide = 1;
                        }
                        if (timeTemp2 == 60 && cookingStep == 2)
                        {
                            sound.start();
                            secondSide = 2;
                            myHandlerBurnt1.post(new Runnable() {
                                public void run()
                                {
                                    tv1.setText("Spalono mięso!");
                                    imageMeat.setImageResource(R.drawable.spalone_rybie_mieso);
                                    meatAmount = 0;
                                    isFishMeat = false;
                                    cookingStep = 3;
                                }
                            });
                        }
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

    private void toast(String s)
    {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

}

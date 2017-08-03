package com.example.lukasz.gra2;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

public class Fight extends AppCompatActivity
{
    Animation pulse, deadLeftAnim, deadRightAnim, appearing, swipeleft, swiperight;
    Button b1, b2, b3, b4, b5, b6, okButton, runButton;
    ProgressBar pb1, pb2;
    int moveNumber = 0;
    ImageView img1, img2, tlo2, icon1, icon2, icon3, icon4;
    MediaPlayer mediaPlayer;
    MediaPlayer sound1, sound2, sound3, sound4;
    TextView tv1, tv2, tv3, tv4;
    int yourLvl, yourHp, opponentLvl;
    int tempIndex, tempMap, tempDefense, tempAtack;
    String oppponentName, yourName;
    boolean bool;
    int opponentHp, opponentMaxHp, yourMaxHp, returnedHp = 0, returnedExp = 0, exp = 0, fightId;
    int goldAmount, addGold = 0;

    private class Atack
    {
        int field;
        int power;
    }

    private class Defense
    {
        int field;
        int power;
    }

    Atack yourAtack = new Atack();
    Defense yourDefense = new Defense();
    Atack opponentAtack = new Atack();
    Defense opponentDefense = new Defense();

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        Bundle extras = getIntent().getExtras();
        yourLvl = extras.getInt("yourLvl");
        opponentLvl = extras.getInt("opponentLvl");
        oppponentName = extras.getString("opponentName");
        yourName = extras.getString("yourName");
        tempIndex = extras.getInt("index");
        tempMap = extras.getInt("location");
        tempDefense = extras.getInt("yourDefense");
        tempAtack = extras.getInt("yourAtack");
        goldAmount = extras.getInt("goldAmount");
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.battle);
        sound2 = MediaPlayer.create(getApplicationContext(), R.raw.block);
        sound3 = MediaPlayer.create(getApplicationContext(), R.raw.hit1);
        sound4 = MediaPlayer.create(getApplicationContext(), R.raw.hit3);
        mediaPlayer.start();

        img1 = (ImageView) findViewById(R.id.image3);
        img2 = (ImageView) findViewById(R.id.image2);
        tlo2 = (ImageView) findViewById(R.id.bg2);
        icon1 = (ImageView) findViewById(R.id.defense1);
        icon4 = (ImageView) findViewById(R.id.atack1);
        icon3 = (ImageView) findViewById(R.id.defense2);
        icon2 = (ImageView) findViewById(R.id.atack2);
        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);

        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        b5 = (Button)findViewById(R.id.button5);
        b6 = (Button)findViewById(R.id.button6);
        okButton = (Button)findViewById(R.id.button);
        runButton = (Button)findViewById(R.id.button7);

        pb1 = (ProgressBar)findViewById(R.id.progressBar);
        pb2 = (ProgressBar)findViewById(R.id.progressBar2);
        pb1.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        pb2.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);

        pulse = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pulse);
        deadLeftAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.dead_left);
        deadRightAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.dead_right);
        appearing = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.appearing);
        swipeleft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.swipe_left);
        swiperight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.swipe_right);
        img2.startAnimation(appearing);



        switch (tempMap)
        {
            case 0:
                tlo2.setImageResource(R.drawable.bg1);
                break;
            case 1:
                tlo2.setImageResource(R.drawable.bg2);
                break;
            case 2:
                tlo2.setImageResource(R.drawable.bg_las1);
                break;
            case 3:
                tlo2.setImageResource(R.drawable.bg_las2);
                break;
            case 4:
                tlo2.setImageResource(R.drawable.bg_las3);
                break;
            case 5:
                tlo2.setImageResource(R.drawable.bg_cmentarz);
                break;
            case 6:
                tlo2.setImageResource(R.drawable.bg_cmentarz2);
                break;
            case 7:
                tlo2.setImageResource(R.drawable.bg_katakumby);
                break;
            case 9:
                tlo2.setImageResource(R.drawable.bg_jezioro1);
                break;
            case 11:
                tlo2.setImageResource(R.drawable.bg_droga_gory);
                break;
            case 12:
                tlo2.setImageResource(R.drawable.bg_droga_gory2);
                break;
            case 13: //temp
                tlo2.setImageResource(R.drawable.bg_droga_karczma);
                break;
            default:
                break;

        }
        switch (tempIndex)
        {
            case 1:
                img2.setImageResource(R.drawable.mucha);
                break;
            case 2:
                img2.setImageResource(R.drawable.pajak);
                break;
            case 4:
                img2.setImageResource(R.drawable.komar);
                break;
            case 6:
                img2.setImageResource(R.drawable.dzikipies);
                break;
            case 7:
                img2.setImageResource(R.drawable.dzikikogut);
                break;
            case 8:
                img2.setImageResource(R.drawable.malydzik);
                break;
            case 10:
                img2.setImageResource(R.drawable.duzydzik);
                break;
            case 16:
                img2.setImageResource(R.drawable.wkurzonymysliwy);
                break;
            case 18:
                img2.setImageResource(R.drawable.grzybiarz);
                break;
            case 19:
                img2.setImageResource(R.drawable.p_szkielet);
                break;
            case 20:
                img2.setImageResource(R.drawable.n_szkielet);
                break;
            case 22:
                img2.setImageResource(R.drawable.pi_szkielet);
                break;
            case 23:
                img2.setImageResource(R.drawable.w_szkielet);
                break;
            case 25:
                img2.setImageResource(R.drawable.c_zombie);
                break;
            case 26:
                img2.setImageResource(R.drawable.nekromanta);
                break;
            case 27:
                img2.setImageResource(R.drawable.w_zombie);
                break;
            case 28:
                img2.setImageResource(R.drawable.mumia);
                break;
            case 31:
                img2.setImageResource(R.drawable.zwariowana_gorska_koza);
                break;
            case 32:
                img2.setImageResource(R.drawable.niewielki_wilk);
                break;
            case 36:
                img2.setImageResource(R.drawable.pijany_goral);
                break;
            default:
                bool = true;
                break;
        }
        switch (opponentLvl)
        {
            case 0:
                if (bool == true)
                    img2.setImageResource(R.drawable.w1);
                opponentAtack.power = 10;
                opponentDefense.power = 10;
                exp = 10;
                opponentMaxHp = opponentHp = 100;
                addGold = 1;
                break;
            case 1:
                if (bool == true)
                    img2.setImageResource(R.drawable.w2);
                opponentAtack.power = 20;
                opponentDefense.power = 20;
                exp = 20;
                opponentMaxHp = opponentHp = 120;
                addGold = 2;
                break;
            case 2:
                if (bool == true)
                    img2.setImageResource(R.drawable.w3);
                opponentAtack.power = 30;
                opponentDefense.power = 30;
                exp = 30;
                opponentMaxHp = opponentHp = 140;
                addGold = 3;
                break;
            case 3:
                if (bool == true)
                    img2.setImageResource(R.drawable.w4);
                opponentAtack.power = 40;
                opponentDefense.power = 40;
                exp = 40;
                opponentMaxHp = opponentHp = 160;
                addGold = 4;
                break;
            case 4:
                if (bool == true)
                    img2.setImageResource(R.drawable.w5);
                opponentAtack.power = 50;
                opponentDefense.power = 50;
                exp = 50;
                opponentMaxHp = opponentHp = 180;
                addGold = 5;
                break;
            case 5:
                if (bool == true)
                    img2.setImageResource(R.drawable.w6);
                opponentAtack.power = 60;
                opponentDefense.power = 60;
                exp = 50;
                opponentMaxHp = opponentHp = 200;
                addGold = 6;
                break;
            case 6:
                if (bool == true)
                    img2.setImageResource(R.drawable.w6);
                opponentAtack.power = 70;
                opponentDefense.power = 70;
                exp = 60;
                opponentMaxHp = opponentHp = 220;
                addGold = 7;
                break;
            case 7:
                if (bool == true)
                    img2.setImageResource(R.drawable.w6);
                opponentAtack.power = 80;
                opponentDefense.power = 80;
                exp = 70;
                opponentMaxHp = opponentHp = 240;
                addGold = 8;
                break;
            case 8:
                if (bool == true)
                    img2.setImageResource(R.drawable.w6);
                opponentAtack.power = 90;
                opponentDefense.power = 90;
                exp = 80;
                opponentMaxHp = opponentHp = 260;
                addGold = 9;
                break;
            case 9:
                if (bool == true)
                    img2.setImageResource(R.drawable.w6);
                opponentAtack.power = 100;
                opponentDefense.power = 100;
                exp = 90;
                opponentMaxHp = opponentHp = 280;
                addGold = 10;
                break;
            default:
                break;
        }
        switch (yourLvl)
        {
            case 1:
                img1.setImageResource(R.drawable.p1);
                yourAtack.power = 20 + tempAtack;
                yourDefense.power = 20 + tempDefense;
                yourMaxHp = yourHp = 120;
                break;
            case 2:
                img1.setImageResource(R.drawable.p1);
                yourAtack.power = 30 + tempAtack;
                yourDefense.power = 30 + tempDefense;
                yourMaxHp = yourHp = 140;
                break;
            case 3:
                img1.setImageResource(R.drawable.p2);
                yourAtack.power = 40 + tempAtack;
                yourDefense.power = 40 + tempDefense;
                yourMaxHp = yourHp = 160;
                break;
            case 4:
                img1.setImageResource(R.drawable.p2);
                yourAtack.power = 50 + tempAtack;
                yourDefense.power = 50 + tempDefense;
                yourMaxHp = yourHp = 180;
                break;
            case 5:
                img1.setImageResource(R.drawable.p3);
                yourAtack.power = 60 + tempAtack;
                yourDefense.power = 60 + tempDefense;
                yourMaxHp = yourHp = 200;
                break;
            case 6:
                img1.setImageResource(R.drawable.p3);
                yourAtack.power = 70 + tempAtack;
                yourDefense.power = 70 + tempDefense;
                yourMaxHp = yourHp = 220;
                break;
            case 7:
                img1.setImageResource(R.drawable.p4);
                yourAtack.power = 80 + tempAtack;
                yourDefense.power = 80 + tempDefense;
                yourMaxHp = yourHp = 240;
                break;
            case 8:
                img1.setImageResource(R.drawable.p4);
                yourAtack.power = 90 + tempAtack;
                yourDefense.power = 90 + tempDefense;
                yourMaxHp = yourHp = 260;
                break;
            case 9:
                img1.setImageResource(R.drawable.p5);
                yourAtack.power = 100 + tempAtack;
                yourDefense.power = 100 + tempDefense;
                yourMaxHp = yourHp = 280;
                break;
            default:
                img1.setImageResource(R.drawable.p5);
                yourAtack.power = 110 + tempAtack;
                yourDefense.power = 110 + tempDefense;
                yourMaxHp = yourHp = 300;
                break;

        }
        pb1.setMax(yourMaxHp);
        pb2.setMax(opponentMaxHp);
        stats();
        iconFade();
        tv4.setText("Wybierz miejsce obrony");
        pulseLeftButtons();
    }

    public void onClick11(View view)
    {
        iconFade();
        switch (moveNumber)
        {
            case 0:
                yourDefense.field = 1;
                tv3.setText("Bronisz głowy.");
                icon1.setImageResource(R.drawable.obrona1);
                icon1.setY(180);
                icon1.setAlpha(128);
                tv4.setText("Wybierz miejsce ataku");
                pulseRightButtons();
                moveNumber = 1;
                break;

        }
    }

    public void onClick12(View view)
    {
        iconFade();
        switch (moveNumber)
        {
            case 0:
                yourDefense.field = 2;
                tv3.setText("Bronisz tułowia.");
                icon1.setImageResource(R.drawable.obrona1);
                icon1.setY(385);
                icon1.setAlpha(128);
                tv4.setText("Wybierz miejsce ataku");
                pulseRightButtons();
                moveNumber = 1;
                break;
        }
    }

    public void onClick13(View view)
    {
        iconFade();
        switch (moveNumber)
        {
            case 0:
                yourDefense.field = 3;
                tv3.setText("Bronisz nóg.");
                icon1.setImageResource(R.drawable.obrona1);
                icon1.setY(560);
                icon1.setAlpha(128);
                tv4.setText("Wybierz miejsce ataku");
                pulseRightButtons();
                moveNumber = 1;
                break;
        }
    }

    public void onClick21(View view)
    {
        switch (moveNumber)
        {
            case 1:
                yourAtack.field = 1;
                tv3.setText("Atakujesz głowe.");
                icon2.setImageResource(R.drawable.atak1);
                icon2.setY(180);
                icon2.setAlpha(128);
                tv4.setText("Wciśnij OK");
                pulseOK();
                moveNumber = 2;
                break;
        }
    }

    public void onClick22(View view)
    {
        switch (moveNumber)
        {
            case 1:
                yourAtack.field = 2;
                tv3.setText("Atakujesz tułów.");
                icon2.setImageResource(R.drawable.atak1);
                icon2.setY(385);
                icon2.setAlpha(128);
                tv4.setText("Wciśnij OK");
                pulseOK();
                moveNumber = 2;
                break;
        }
    }

    public void onClick23(View view)
    {
        switch (moveNumber)
        {
            case 1:
                yourAtack.field = 3;
                tv3.setText("Atakujesz nogi.");
                icon2.setImageResource(R.drawable.atak1);
                icon2.setY(560);
                icon2.setAlpha(128);
                tv4.setText("Wciśnij OK");
                pulseOK();
                moveNumber = 2;
                break;
        }
    }

    public void onClick(View view)
    {
        sound1 = MediaPlayer.create(getApplicationContext(), R.raw.run);
        sound1.start();
        super.finish();
    }

    public void onClickOk(View view)
    {
        switch (moveNumber)
        {
            case 2:
                opponentAtack.field = draw(3) + 1;
                switch (opponentAtack.field)
                {
                    case 1:
                        icon4.setImageResource(R.drawable.atak2);
                        icon4.setY(180);
                        break;
                    case 2:
                        icon4.setImageResource(R.drawable.atak2);
                        icon4.setY(385);
                        break;
                    case 3:
                        icon4.setImageResource(R.drawable.atak2);
                        icon4.setY(560);
                        break;
                }
                opponentDefense.field = draw(3) + 1;
                switch (opponentDefense.field)
                {
                    case 1:
                        icon3.setImageResource(R.drawable.obrona1);
                        icon3.setY(180);
                        break;
                    case 2:
                        icon3.setImageResource(R.drawable.obrona1);
                        icon3.setY(385);
                        break;
                    case 3:
                        icon3.setImageResource(R.drawable.obrona1);
                        icon3.setY(560);
                        break;
                }
                iconVisible();
                if (opponentAtack.field == yourDefense.field)
                {
                    if (opponentDefense.field == yourAtack.field)
                    {
                        sound2.start();
                        tv3.setText("Obroniles się, przeciwnik też.");
                        if (opponentAtack.power > yourDefense.power)
                            yourHp -= (opponentAtack.power - yourDefense.power);
                        if (yourAtack.power > opponentDefense.power)
                            opponentHp -= (yourAtack.power - opponentDefense.power);
                    } else
                    {
                        sound3.start();
                        tv3.setText("Obroniles się, a przeciwnik nie.");
                        if (opponentAtack.power > yourDefense.power)
                            yourHp -= opponentAtack.power - yourDefense.power;
                        opponentHp -= yourAtack.power;
                    }
                } else
                {
                    if (opponentDefense.field == yourAtack.field)
                    {
                        sound2.start();
                        tv3.setText("Nie obroniles się, a przeciwnik tak.");
                        if (yourAtack.power > opponentDefense.power)
                            opponentHp -= yourAtack.power - opponentDefense.power;
                        yourHp -= opponentAtack.power;
                    } else
                    {
                        sound4.start();
                        tv3.setText("Nie obroniles się, przeciwnik też nie.");
                        yourHp -= opponentAtack.power;
                        opponentHp -= yourAtack.power;
                    }

                }
                stats();
                if (yourHp > 0 && opponentHp > 0)
                {
                    moveNumber = 0;
                    tv4.setText("Ponowonie wybierz miejsce obrony");
                    pulseLeftButtons();

                } else if (opponentHp <= 0 && yourHp > 0)
                {
                    mediaPlayer.stop();
                    sound1 = MediaPlayer.create(getApplicationContext(), R.raw.win);
                    sound1.start();
                    tv4.setText("Wcisnij OK by kontunuować grę");
                    pulseOK();
                    if (yourHp < yourMaxHp /2)
                    {
                        returnedHp = 10;
                    }
                    returnedExp = exp;
                    moveNumber = 4;
                    tv3.setText("Zwyciestwo. EXP + " + returnedExp + " HP - " + returnedHp +" Złoto + "+addGold);
                    goldAmount = goldAmount + addGold;
                    img2.startAnimation(deadRightAnim);
                    fightId = 1;
                    runButton.setVisibility(View.INVISIBLE);
                } else if (yourHp <= 0 && opponentHp > 0)
                {
                    sound1 = MediaPlayer.create(getApplicationContext(), R.raw.dead);
                    mediaPlayer.stop();
                    sound1.start();
                    tv4.setText("Wcisnij OK by kontunuować grę");
                    pulseOK();
                    if (opponentHp < opponentMaxHp /2)
                    {
                        returnedExp = exp / 2;
                    }
                    returnedHp = 20;
                    moveNumber = 4;
                    tv3.setText("Przegrana. EXP + " + returnedExp + " HP - " + returnedHp);
                    fightId = 0;
                    runButton.setVisibility(View.INVISIBLE);
                    img1.startAnimation(deadLeftAnim);
                } else if (yourHp <= 0 && opponentHp <= 0)
                {
                    sound1 = MediaPlayer.create(getApplicationContext(), R.raw.dead);
                    mediaPlayer.stop();
                    sound1.start();
                    tv4.setText("Wcisnij OK by kontunuować grę");
                    pulseOK();
                    if (opponentHp < opponentMaxHp /2)
                    {
                        returnedExp = exp / 2;
                    }
                    returnedHp = 20;
                    moveNumber = 4;
                    tv3.setText("Obaj polegliście. EXP + " + returnedExp + " HP - " + returnedHp);
                    img1.startAnimation(deadLeftAnim);
                    img2.startAnimation(deadRightAnim);
                    fightId = 0;
                    runButton.setVisibility(View.INVISIBLE);
                }
                break;
            case 4:
                Intent fightOver = new Intent();
                fightOver.putExtra("returnedHp", returnedHp);
                fightOver.putExtra("returnedExp", returnedExp);
                fightOver.putExtra("returnedId", fightId);
                fightOver.putExtra("goldAmount", goldAmount);
                setResult(RESULT_OK, fightOver);
                finish();

        }
    }

    public int draw(int k)
    {
        Random lot = new Random();
        int randomNumber = lot.nextInt(k);
        return randomNumber;
    }

    public void stats()
    {
        tv1.setText(yourName + " lvl: " + String.valueOf(yourLvl) + " hp: " + (String.valueOf(yourHp))+" atak: "+String.valueOf(yourAtack.power)+" obrona: "+String.valueOf(yourDefense.power));
        tv2.setText(oppponentName + " lvl: " + String.valueOf(opponentLvl) + " hp: " + String.valueOf(opponentHp));
        pb1.setProgress(yourHp);
        pb2.setProgress(opponentHp);
    }

    private void iconFade()
    {
        if (icon1.getAlpha() != 128) icon1.setAlpha(128);
        if (icon2.getAlpha() != 128) icon2.setAlpha(128);
        if (icon3.getAlpha() != 128) icon3.setAlpha(128);
        if (icon4.getAlpha() != 128) icon4.setAlpha(128);
    }

    private void iconVisible()
    {
        icon4.startAnimation(swipeleft);
        icon2.startAnimation(swiperight);
        if (icon1.getAlpha() != 255) icon1.setAlpha(255);
        if (icon2.getAlpha() != 255) icon2.setAlpha(255);
        if (icon3.getAlpha() != 255) icon3.setAlpha(255);
        if (icon4.getAlpha() != 255) icon4.setAlpha(255);
    }

    protected void onPause()
    {
        super.onPause();
        disableSound();
    }

    private void pulseRightButtons()
    {
        b1.clearAnimation();
        b2.clearAnimation();
        b3.clearAnimation();
        b4.startAnimation(pulse);
        b5.startAnimation(pulse);
        b6.startAnimation(pulse);

    }

    private void pulseLeftButtons()
    {
        okButton.clearAnimation();
        b1.startAnimation(pulse);
        b2.startAnimation(pulse);
        b3.startAnimation(pulse);
    }
    private void pulseOK()
    {
        b4.clearAnimation();
        b5.clearAnimation();
        b6.clearAnimation();
        okButton.startAnimation(pulse);
    }

    private void disableSound()
    {
        //disable mediaPlayer, sound1, sound2, sound3, sound4;
            if (mediaPlayer != null && mediaPlayer.isPlaying())
            {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            if (sound2 != null)
            {
                sound2.release();
                sound2 = null;
            }
            if (sound3 != null)
            {
                sound3.release();
                sound3 = null;
            }
            if (sound4 != null)
            {
                sound4.release();
                sound4 = null;
            }
            if (sound1 != null)
            {
                sound1.release();
                sound1 = null;
            }


    }

}

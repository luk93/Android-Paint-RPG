package com.example.lukasz.gra2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static com.example.lukasz.gra2.R.drawable.bg_lowienie0;
import static com.example.lukasz.gra2.R.drawable.bg_lowienie1;
import static com.example.lukasz.gra2.R.drawable.bg_lowienie2;
import static com.example.lukasz.gra2.R.drawable.bg_lowienie3;
import static com.example.lukasz.gra2.R.drawable.bg_lowienie4;
import static com.example.lukasz.gra2.R.drawable.bg_lowienie5;
import static com.example.lukasz.gra2.R.drawable.bg_lowienie6;
import static com.example.lukasz.gra2.R.drawable.but;
import static com.example.lukasz.gra2.R.drawable.dziwny_wegorz;
import static com.example.lukasz.gra2.R.drawable.fala;
import static com.example.lukasz.gra2.R.drawable.jjazgacz;
import static com.example.lukasz.gra2.R.drawable.jjesiotr;
import static com.example.lukasz.gra2.R.drawable.kkarp;
import static com.example.lukasz.gra2.R.drawable.lleszcz;
import static com.example.lukasz.gra2.R.drawable.mmietus;
import static com.example.lukasz.gra2.R.drawable.ookon;
import static com.example.lukasz.gra2.R.drawable.pploc;
import static com.example.lukasz.gra2.R.drawable.ppstrag;
import static com.example.lukasz.gra2.R.drawable.rak;
import static com.example.lukasz.gra2.R.drawable.siec;
import static com.example.lukasz.gra2.R.drawable.ssandacz;
import static com.example.lukasz.gra2.R.drawable.sslimak;
import static com.example.lukasz.gra2.R.drawable.ssum;
import static com.example.lukasz.gra2.R.drawable.sszczupak;
import static com.example.lukasz.gra2.R.drawable.wodorosty;
import static com.example.lukasz.gra2.R.drawable.wwegorz;
import static com.example.lukasz.gra2.R.drawable.zaba;
import static com.example.lukasz.gra2.R.drawable.zlota_rybka;
import static com.example.lukasz.gra2.R.raw.away;
import static com.example.lukasz.gra2.R.raw.catched;
import static com.example.lukasz.gra2.R.raw.got;
import static com.example.lukasz.gra2.R.raw.swing1;

public class Fishing extends AppCompatActivity implements SensorEventListener
{
    Animation fishAnim;
    ArrayList<Character> fishList = new ArrayList<Character>();
    ImageView bg, image;
    TextView tv1, tv2;
    private long lastUpdate;
    long startTime;
    SensorManager sm;
    Sensor ac;
    float acc = 0;
    Sensor orient;
    float[] mGravity;
    float[] mGeomagnetic;
    int counter1 = 0, field;
    MediaPlayer sound1, media, sound2, sound3;
    int counter;
    Vibrator vib;
    int random, randomTime, tmpIndex, tempHp, tempExp, addHp, addExp, meatAmount;
    ImageView fishImage, instImage;
    String name;
    int tmpLvl;
    Button b1, b2,b3;
    boolean isCatched, isFishMeat;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fishing);
        isCatched = false;
        addHp = 0;
        meatAmount = 0;
        media = MediaPlayer.create(getApplicationContext(), R.raw.fishes);
        sound3 = MediaPlayer.create(getApplicationContext(), R.raw.map);
        media.setLooping(true);
        media.start();
        sound2 = MediaPlayer.create(getApplicationContext(), R.raw.swing3);
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.fieldB);
        bg = (ImageView)findViewById(R.id.imageFishing);
        image = (ImageView)findViewById(R.id.imageView);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        ac =  sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        orient = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        lastUpdate = System.currentTimeMillis();
        bg.setImageResource(bg_lowienie1);
        startTime = System.currentTimeMillis();
        image.setImageResource(fala);
        image.setVisibility(View.INVISIBLE);
        vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        fishImage = (ImageView)findViewById(R.id.imageFish);
        fishImage.setVisibility(View.INVISIBLE);
        instImage = (ImageView)findViewById(R.id.instruction);
        instImage.setImageResource(R.drawable.instrukcja);
        instImage.setVisibility(View.INVISIBLE);
        b1 = (Button)findViewById(R.id.button8);
        b2 = (Button)findViewById(R.id.button9);
        b3 = (Button)findViewById(R.id.button_instruction);

        fishAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.appearin_scale);

        //charList ryb: Character(String name, int lvl, int hp, int exp, int wrog)
        fishList.add(new Character("But", 0, 1, 0, 1));       //0-
        fishList.add(new Character("Wodorosty", 0, 1, 0, 1));       //1-
        fishList.add(new Character("Stara Sieć Rybacka", 0, 1, 0, 1)); //2-
        fishList.add(new Character("Rak", 1, 1, 0, 1)) ;       //3-
        fishList.add(new Character("Żaba", 1, 1, 0, 1));      //4
        fishList.add(new Character("Duży Slimak", 1, 1, 0, 1));//5-
        fishList.add(new Character("Okoń", 2, 1, 0, 1)); //6-
        fishList.add(new Character("Jazgacz", 2, 1, 0, 1)); //7-
        fishList.add(new Character("Leszcz", 2, 1, 0, 1)); //8-
        fishList.add(new Character("Sandacz", 2, 1, 0, 1));//9-
        fishList.add(new Character("Ploć", 2, 1, 0, 1)); //10-
        fishList.add(new Character("Sum", 3, 1, 0, 1));   //11-
        fishList.add(new Character("Jesiotr", 3, 1, 0, 1));   //12-
        fishList.add(new Character("Pstrąg Tęczowy", 3, 1, 0, 1));   //13-
        fishList.add(new Character("Miętus", 3, 1, 0, 1));    //14-
        fishList.add(new Character("Szczupak", 4, 1, 0, 1));       //15-
        fishList.add(new Character("Dorodny Karp", 4, 1, 0, 1));       //16-
        fishList.add(new Character("Węgorz", 4, 1, 0, 1));        //17-
        fishList.add(new Character("Dziwny węgorz", 5, 1, 0, 1));//18-
        fishList.add(new Character("Złota rybka", 6, 1, 0, 1)); //19-

        Bundle extras = getIntent().getExtras();
        tempExp = extras.getInt("tempExp");
        tempHp = extras.getInt("tempHp");
        meatAmount = extras.getInt("meatAmount");
        isFishMeat = extras.getBoolean("isFishMeat");

        tv2.setText("HP: "+ tempHp +" EXP: "+ tempExp);
    }

    public void onSensorChanged(SensorEvent event)
    {
        counter = (int) ((System.currentTimeMillis()- startTime)/1000);
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
        {
            mGeomagnetic = event.values;
        }
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            mGravity = event.values;
        }
        if (mGravity != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                if (counter1 ==0)
                {
                    acc = 0;
                    fishImage.setVisibility(View.INVISIBLE);
                    sound1 = MediaPlayer.create(getApplicationContext(),swing1);
                    bg.setImageResource(bg_lowienie1);
                    tv1.setText("");
                    image.setImageResource(fala);
                    if (orientation[2] > 3)
                    {
                        sound1.start();
                        counter1 =1;
                        randomTime = draw(10,5);
                        bg.setImageResource(bg_lowienie0);
                        startTime = System.currentTimeMillis();

                    }
                }
                if(counter1 ==1&& counter>=1)
                {
                    acc =0;
                    if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                    {
                        getAccelerometer(event);
                        if (acc>2 && acc<3)
                        {
                            sound2.start();
                            bg.setImageResource(bg_lowienie2);
                            startTime = System.currentTimeMillis();
                            tmpIndex = draw( 4,0);
                            counter1 =2;
                            acc=0;
                            field = 1;

                        }
                        if (acc>3 && acc<4)
                        {
                            sound2.start();
                            bg.setImageResource(bg_lowienie3);
                            startTime = System.currentTimeMillis();
                            tmpIndex = draw( 5,2);
                            counter1 =2;
                            acc=0;
                            field = 2;

                        }
                        if (acc>4 && acc<5)
                        {
                            sound2.start();
                            bg.setImageResource(bg_lowienie4);
                            startTime = System.currentTimeMillis();
                            tmpIndex = draw( 6,5);
                            counter1 =2;
                            acc=0;
                            field = 3;

                        }
                        if (acc>5 && acc<7)
                        {
                            sound2.start();
                            bg.setImageResource(bg_lowienie5);
                            startTime = System.currentTimeMillis();
                            tmpIndex = draw( 6,9);
                            counter1 =2;
                            acc=0;
                            field = 4;

                        }
                        if (acc>7)
                        {
                            sound2.start();
                            bg.setImageResource(bg_lowienie6);
                            startTime = System.currentTimeMillis();
                            tmpIndex = draw( 8,12);
                            counter1 =2;
                            acc=0;
                            field = 5;
                        }
                    }
                }
                if(counter1 ==2)
                {
                    acc=0;
                    if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                        getAccelerometer(event);
                    }
                    if (counter== randomTime)
                    {
                        sound1 = MediaPlayer.create(getApplicationContext(), catched);
                        sound1.start();
                        counter1 = 3;
                        startTime = System.currentTimeMillis();
                    }
                }
                if (counter1 ==3)
                {
                    acc = 0;
                    switch (field)
                    {
                        case 1:
                            image.setVisibility(View.VISIBLE);
                            image.setX(255);
                            image.setY(980);
                            break;
                        case 2:
                            image.setVisibility(View.VISIBLE);
                            image.setX(275);
                            image.setY(795);
                            break;
                        case 3:
                            image.setVisibility(View.VISIBLE);
                            image.setX(300);
                            image.setY(580);
                            break;
                        case 4:
                            image.setVisibility(View.VISIBLE);
                            image.setX(320);
                            image.setY(305);
                            break;
                        case 5:
                            image.setVisibility(View.VISIBLE);
                            image.setX(365);
                            image.setY(65);
                            break;
                        default:
                            break;
                    }
                    vib.vibrate(100);
                    if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                        getAccelerometer(event);
                    }
                    if (counter==2)
                    {
                        tv1.setText("Cokolwiek to było, uciekło...");
                        sound1 = MediaPlayer.create(getApplicationContext(),away);
                        sound1.start();
                        image.setVisibility(View.INVISIBLE);
                        counter1 = 5;
                    }
                }
                if (counter1 == 4)
                {
                    action(fishList.get(tmpIndex));
                    fishImage.setVisibility(View.VISIBLE);
                    switch(tmpIndex)
                    {
                        case 0:
                            fishImage.setImageResource(but);
                            break;
                        case 1:
                            fishImage.setImageResource(wodorosty);
                            break;
                        case 2:
                            fishImage.setImageResource(siec);
                            break;
                        case 3:
                            fishImage.setImageResource(rak);
                            break;
                        case 4:
                            fishImage.setImageResource(zaba);
                            break;
                        case 5:
                            fishImage.setImageResource(sslimak);
                            break;
                        case 6:
                            fishImage.setImageResource(ookon);
                            break;
                        case 7:
                            fishImage.setImageResource(jjazgacz);
                            break;
                        case 8:
                            fishImage.setImageResource(lleszcz);
                            break;
                        case 9:
                            fishImage.setImageResource(ssandacz);
                            break;
                        case 10:
                            fishImage.setImageResource(pploc);
                            break;
                        case 11:
                            fishImage.setImageResource(ssum);
                            break;
                        case 12:
                            fishImage.setImageResource(jjesiotr);
                            break;
                        case 13:
                            fishImage.setImageResource(ppstrag);
                            break;
                        case 14:
                            fishImage.setImageResource(mmietus);
                            break;
                        case 15:
                            fishImage.setImageResource(sszczupak);
                            break;
                        case 16:
                            fishImage.setImageResource(kkarp);
                            break;
                        case 17:
                            fishImage.setImageResource(wwegorz);
                            break;
                        case 18:
                            fishImage.setImageResource(dziwny_wegorz);
                            break;
                        case 19:
                            fishImage.setImageResource(zlota_rybka);
                            break;
                        default:
                            break;
                    }
                    switch (tmpLvl)
                    {
                        case 0:
                            addExp = 0;
                            addHp = 0;
                            break;
                        case 1:
                            addExp = 5;
                            addHp = 1;
                            break;
                        case 2:
                            addExp = 10;
                            addHp = 2;
                            break;
                        case 3:
                            addExp = 15;
                            addHp = 3;
                            break;
                        case 4:
                            addExp = 20;
                            addHp = 4;
                            break;
                        case 5:
                            addExp = 25;
                            addHp = 5;
                            break;
                        case 6:
                            addExp = 30;
                            addHp = 5;
                            break;
                    }
                    tv1.setText("Udało ci się złowić "+ name);
                    sound1 = MediaPlayer.create(getApplicationContext(),got);
                    sound1.start();
                    image.setVisibility(View.INVISIBLE);
                    fishImage.startAnimation(fishAnim);
                    counter1 = 5;
                    if (tmpIndex == 19)
                    {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                        alertDialogBuilder.setMessage("Wiem, trochę przewidywalne, ale złota rybka umie mówić. Co chcesz zrobić");
                        alertDialogBuilder.setPositiveButton("Wysłuchaj jej i wypuść wolno", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface arg0, int arg1)
                            {
                                tv1.setText("Dziękuję za oszczędzenie życia, w podzięce podpowiem Ci hasło do grobowca. Jest to imię i nazwisko tego, który w nim spoczywa. Niestety, ja znam tylko imię - Dominik");
                            }
                        });
                        alertDialogBuilder.setNegativeButton("To co z całą resztą", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                    meatAmount = meatAmount + addHp;
                                    tempExp = tempExp + addExp;
                                    tv1.setText("Bezlitośnie... Dodano: " + addHp + " sztuki miesa oraz " + addExp + " exp!");
                                    isCatched = true;
                            }
                        });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                    else
                    {
                            meatAmount = meatAmount + addHp;
                            tempExp = tempExp + addExp;
                            tv1.setText("Udało ci się złowić " + name + " Dodano " + addHp + " sztuk miesa oraz " + addExp + " exp!");
                            isCatched = true;
                    }
                    tv2.setText("HP: "+ tempHp +" EXP: "+ tempExp);
                }
            }
        }
    }

    private void getAccelerometer(SensorEvent event)
    {
        float[] a_val = event.values;
        float x = a_val[0];
        float y = a_val[1];
        float z = a_val[2];
        float accelationSquareRoot = (x * x + y * y + z * z) /(SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        long actualTime = event.timestamp;
        if (accelationSquareRoot >= 2)
        {
            if (actualTime - lastUpdate < 1000)
            {
                return;
            }
            lastUpdate = actualTime;
            acc = accelationSquareRoot;
            if (counter1 == 3)
            {
                if (counter > 0 && counter < 2)
                {
                    counter1 = 4;
                }
            }
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
    }

    protected void onResume() {
        super.onResume();
        sm.registerListener(this, ac, SensorManager.SENSOR_DELAY_UI);
        sm.registerListener(this, orient, SensorManager.SENSOR_DELAY_UI);
    }

    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
        media.stop();
    }

    public void onClick(View view)
    {
        counter1 = 0;
    }

    public void onClick2(View view)
    {
        Intent fishingOver = new Intent();
        fishingOver.putExtra("fishingHp", tempHp);
        fishingOver.putExtra("fishingExp", tempExp);
        fishingOver.putExtra("meatAmount", meatAmount);
        fishingOver.putExtra("isCatched", isCatched);
        fishingOver.putExtra("isFishMeat", isFishMeat);
        setResult(RESULT_OK, fishingOver);
        super.finish();
        media.stop();
    }

    public void onClick3(View view)
    {
        if(instImage.getVisibility()==View.INVISIBLE)
        {
            sound3.start();
            instImage.setVisibility(View.VISIBLE);
        }
        else if(instImage.getVisibility()==View.VISIBLE)
        {
            sound3.start();
            instImage.setVisibility(View.INVISIBLE);
        }
    }

    public int draw(int k)
    {
        Random lot = new Random();
        int randomNumber = lot.nextInt(k);
        return randomNumber;
    }

    public int draw(int i, int j)
    {
       return random = draw(i)+j;
    }

    public void action(Character fish)
    {
        name = fish.getName();
        tmpLvl = fish.getlvl();
    }

}

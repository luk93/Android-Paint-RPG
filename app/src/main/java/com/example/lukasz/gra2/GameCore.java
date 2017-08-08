package com.example.lukasz.gra2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static com.example.lukasz.gra2.R.raw.main;

public class GameCore extends AppCompatActivity
{
    public class Equipment
    {
        int defense;
        int atack;
    }

    MediaPlayer mediaPlayer;
    MediaPlayer sound;

    private static final int REQUEST_CODE = 1;

    Animation pulse_low, char_appearing;

    int counter = 0, counterPassword = 0, drop, random;
    EditText et;
    Button b1, b2, bFront, bLeft, bBack, bRight, bEQ, bDrop;
    TextView tv1, tv2, tv3;
    ImageView iv1, bg, imageTemp, imageTemp2;
    String nameHero, tempPassword;
    boolean newGame = true, isCatched = false, isFishMeat = false;
    int tempLvl, tempHp, tempExp;
    ArrayList<Character> charList = new ArrayList<Character>();
    ArrayList<String> locationList = new ArrayList<String>();
    ArrayList<String> itemNameList = new ArrayList<String>();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int tempMap = 0, fightId, cookedMeatAmount;
    int tempIndex = 1, meatAmount = 0, goldAmount = 0;
    int items[] = new int[12];
    boolean bagEmpty[] = new boolean[12], isEqFull, isClickedTemp = false,isClickedTemp2 = false, isCookedMeat;
    Equipment eq = new Equipment();

    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_core);
        //Character(String name, int lvl, int hp, int exp, int wrog)
        //Dom(1-4) Droga1(1-8) Las1(7-12) Las2(7-18) Las3(7-18) Cmentarz1(14-20)
        //Cmentarz2(19-24) Katakumby(21-28) Droga gory (7-12) Droga gory 2(30-36)
        charList.add(new Character("KOSTUCHA", 99, 100, 0, 99));//0
        charList.add(new Character("Mucha", 0, 1, 0, 1));         //1
        charList.add(new Character("Pajak", 0, 100, 0, 1));       //2
        charList.add(new Character("Kurz", 0, 100, 0, 0));           //3
        charList.add(new Character("Komar", 1, 100, 0, 1));        //4
        charList.add(new Character("Kobieta", 2, 100, 0, 0));     //5
        charList.add(new Character("Dziki Pies", 2, 100, 0, 1));  //6
        charList.add(new Character("Dziki Kogut", 1, 100, 0, 1)); //7
        charList.add(new Character("Mały Dzik", 2, 100, 0, 1));   //8
        charList.add(new Character("Chuderlawy Bandyta", 2, 100, 0, 1));     //9
        charList.add(new Character("Duży Dzik", 2, 100, 0, 1));   //10
        charList.add(new Character("Druid", 3, 100, 0, 3));       //11
        charList.add(new Character("Pies", 1, 100, 0, 0));        //12
        charList.add(new Character("Myśliwy", 1, 100, 0, 0));     //13
        charList.add(new Character("Niebieski Duch", 1, 100, 0, 3));//14
        charList.add(new Character("Bandyta Pospolity", 3, 100, 0, 1));//15
        charList.add(new Character("Wkurzony Myśliwy", 3, 100, 0, 1)); //16
        charList.add(new Character("Drwal Tomek", 1, 100, 0, 2)); //17
        charList.add(new Character("Zdenerwowany Grzybiarz", 3, 100, 0, 1)); //18
        charList.add(new Character("Szkielet Psa", 4, 100, 0, 1));//19
        charList.add(new Character("Niewyspany Szkielet", 4, 100, 0, 1));//20
        charList.add(new Character("Widmo Lekarza", 1, 100, 0, 3)); //21
        charList.add(new Character("Szkielet Pirat", 4, 100, 0, 1));       //22
        charList.add(new Character("Szkielet Wojownika", 5, 100, 0, 1));//23
        charList.add(new Character("Leniwy Zombie", 3, 100, 0, 0));//24
        charList.add(new Character("Chlop Zombie", 4, 100, 0, 1));//25
        charList.add(new Character("Nekromanta", 5, 100, 0, 1));//26
        charList.add(new Character("Wojownik Zombie", 5, 100, 0, 1));//27
        charList.add(new Character("Zwariowana Mumia", 6, 100, 0, 1));//28
        charList.add(new Character("Dominik Martwy", 8, 100, 0, 1));//29
        charList.add(new Character("Koza górska", 3, 100, 0, 0));//30
        charList.add(new Character("Zwariowana koza górska", 3, 100, 0, 1));//31
        charList.add(new Character("Niewielki wilk", 3, 100, 0, 1));//32
        charList.add(new Character("Bandyta Pospolity", 3, 100, 0, 1));//33
        charList.add(new Character("Myśliwy", 1, 100, 0, 0));     //34
        charList.add(new Character("Niebieski Duch", 1, 100, 0, 3));//35
        charList.add(new Character("Pijany góral", 3, 100, 0, 1));//36


        //spis lokacji
        locationList.add("Dom");    //0
        locationList.add("Droga1"); //1
        locationList.add("Las1");   //2
        locationList.add("Las2");   //3
        locationList.add("Las3");   //4
        locationList.add("Cmentarz");//5
        locationList.add("Cmentarz2");//6
        locationList.add("Katakumby");//7
        locationList.add("Grobowiec"); //8
        locationList.add("Jezioro1"); //9
        locationList.add("Jezioro2"); //10
        locationList.add("DrogaGory");//11
        locationList.add("DrogaGory2"); //12

        locationList.add("DrogaKarczma"); //temp 13
        locationList.add("DrogaKarczma2"); //temp14

        //lista nazw przedmiotow
        itemNameList.add("Nic"); //0
        itemNameList.add("Kij"); //1
        itemNameList.add("Laczki"); //2
        itemNameList.add("Wiaderko"); //3
        itemNameList.add("Koszulke"); //4
        itemNameList.add("Duzy kij"); //5
        itemNameList.add("Banana"); //6
        itemNameList.add("Czepek"); //7

        itemNameList.add("Drewniany miecz");  //8
        itemNameList.add("Kozaki");  //9
        itemNameList.add("Pokrywke od garnka");  //10
        itemNameList.add("Sweter");  //11
        itemNameList.add("łopate");  //12
        itemNameList.add("Dwa banany");  //13
        itemNameList.add("Peruke");  //14

        itemNameList.add("Mieso ryby"); //15

        itemNameList.add("Ostry sztylet");  //16
        itemNameList.add("Wzmacniane buty");  //17
        itemNameList.add("Drewniana tarcza");  //18
        itemNameList.add("Skorzana zbroja");  //19
        itemNameList.add("Miecz dwuręczny");  //20
        itemNameList.add("Szynka");  //21
        itemNameList.add("Drewniany hełm");  //22

        itemNameList.add("Szeroki miecz");  //23
        itemNameList.add("Żelazne buty");  //24
        itemNameList.add("Wzmacniany puklerz");  //25
        itemNameList.add("Kolczuga");  //26
        itemNameList.add("Topór obosieczny");  //27
        itemNameList.add("Potrawka");            //28
        itemNameList.add("Żelazny hełm");  //29

        itemNameList.add("Ostra katana");  //30
        itemNameList.add("Stalowe buty");  //31
        itemNameList.add("Stalowa tarcza");  //32
        itemNameList.add("Stalowa zbroja");  //33
        itemNameList.add("Stalowy Claymore");  //34
        itemNameList.add("Duża porcja potrawki"); //35
        itemNameList.add("Stalowy hełm");  //36


        pulse_low = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pulse_low);
        char_appearing = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.appearin_scale);

        et = (EditText) findViewById(R.id.editField);
        b1 = (Button) findViewById(R.id.buttonAction);
        b2 = (Button) findViewById(R.id.button2);
        bFront = (Button) findViewById(R.id.bFront);
        bBack = (Button) findViewById(R.id.bBack);
        bLeft = (Button) findViewById(R.id.bLeft);
        bRight = (Button) findViewById(R.id.bRight);
        bEQ = (Button) findViewById(R.id.bEQ);
        bDrop = (Button) findViewById(R.id.bEQ);
        tv1 = (TextView) findViewById(R.id.heroField);
        tv2 = (TextView) findViewById(R.id.opponentField);
        iv1 = (ImageView) findViewById(R.id.image);
        imageTemp = (ImageView)findViewById(R.id.imageTemp);
        imageTemp2 = (ImageView)findViewById(R.id.imageTemp2);
        bg = (ImageView) findViewById(R.id.bg1);
        tv3 = (TextView) findViewById(R.id.textView);

        bRight.setVisibility(View.INVISIBLE);
        bLeft.setVisibility(View.INVISIBLE);
        bBack.setVisibility(View.INVISIBLE);
        bFront.setVisibility(View.INVISIBLE);

        bg.setImageResource(R.drawable.bg_title);
        iv1.setVisibility(View.INVISIBLE);
        imageTemp.setVisibility(View.INVISIBLE);
        imageTemp2.setVisibility(View.INVISIBLE);

        sharedPreferences = getSharedPreferences("com.example.lukasz.gra2", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        tv1.setText("Poprzedni zapis: " + sharedPreferences.getString("name", "") + " lvl: " + sharedPreferences.getInt("level", 0) + " hp: " + sharedPreferences.getInt("hp", 0) + " exp: " + sharedPreferences.getInt("exp", 0));
        tv2.setText("");
        //allert nowa gra/ od zapisu
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Gra RPG");
        alertDialogBuilder.setPositiveButton("NOWA GRA", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface arg0, int arg1)
            {
                tv1.setText("Podaj nazwę bohatera, nieznajomy");
                newGame = true;
                counter = 1;
            }
        });
        alertDialogBuilder.setNegativeButton("KONTYNUUJ", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                tempHp = sharedPreferences.getInt("hp", 0);
                if (tempHp <= 0)
                {
                    tv1.setText("Nie możesz kontynuować poprzedniej gry, umarłeś. A więc nowa gra - Podaj nazwę bohatera");
                    newGame = true;
                    counter = 1;
                    tempHp = 100;
                } else
                {
                    et.setEnabled(false);
                    tempLvl = sharedPreferences.getInt("level", 0);
                    tempExp = sharedPreferences.getInt("exp", 0);
                    eq.defense = sharedPreferences.getInt("defense", 0);
                    eq.atack = sharedPreferences.getInt("atack", 0);
                    nameHero = sharedPreferences.getString("name", "");
                    items[1] = sharedPreferences.getInt("item1",0);
                    items[2] = sharedPreferences.getInt("item2",0);
                    items[3] = sharedPreferences.getInt("item3",0);
                    items[4] = sharedPreferences.getInt("item4",0);
                    items[5] = sharedPreferences.getInt("item5",0);
                    items[6] = sharedPreferences.getInt("item6",0);
                    items[7] = sharedPreferences.getInt("item7",0);
                    items[8] = sharedPreferences.getInt("item8",0);
                    items[9] = sharedPreferences.getInt("item9",0);
                    items[10] = sharedPreferences.getInt("item10",0);
                    items[11] = sharedPreferences.getInt("item11",0);
                    meatAmount = sharedPreferences.getInt("meatAmount", 0);
                    isFishMeat = sharedPreferences.getBoolean("isFishMeat", false);
                    goldAmount = sharedPreferences.getInt("goldAmount", 0);
                    cookedMeatAmount = sharedPreferences.getInt("cookedMeatAmount",0);
                    isCookedMeat = sharedPreferences.getBoolean("isCookedMeat", false);
                    Character hero = new Character(nameHero, tempLvl, tempHp, tempExp, 5, eq.atack, eq.defense);
                    newGame = false;
                    et.setText("Wcisnij OK, aby kontunować grę od poprzedniego zapisu");
                    counter = 1;
                }
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.title);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        tempMap = locationList.indexOf("Dom");//lokacja poczatkowa

    }

    public void onClick2(View view)
    {
        Intent intentMap = new Intent(this, Map.class);
        intentMap.putExtra("location", tempMap);
        startActivity(intentMap);
    }

    public void onClick1(View view) throws InterruptedException
    {
        if (counter == 3)
        {
            Character hero = new Character(nameHero, tempLvl, tempHp, tempExp, 5, eq.atack, eq.defense);
            tv1.setText("Wynik gry: Osiągnieto poziom: " + hero.getlvl());
        }
        if (counter == 2)
        {
            switch (tempMap)
            {
                case 0:
                    action1(4, 1);
                    break;
                case 1:
                    action1(8, 1);
                    break;
                case 2:
                    action1(6, 7);
                    break;
                case 3:
                    action1(12, 7);
                    break;
                case 4:
                    action1(12, 7);
                    break;
                case 5:
                    action1(7, 14);
                    break;
                case 6:
                    action1(6, 19);
                    break;
                case 7:
                    action1(8, 21);
                    break;
                case 8:
                    String haslo = "Dominik Martwy";
                    tempPassword = et.getText().toString();
                    if (haslo.equals(tempPassword))
                    {
                        tv1.setText("zgadłeś");
                        Character hero = new Character(nameHero, tempLvl, tempHp, tempExp, 5, eq.atack, eq.defense);
                        meeting(hero, charList.get(29));
                    } else
                    {
                        switch (counterPassword)
                        {
                            case 0:
                                tv1.setText("Podałeś złe hasło, jeśli nie znasz hasła czmychaj stąd");
                                break;
                            case 1:
                                tv1.setText("Ha! Znowu podałeś złe hasło.");
                                break;
                            case 2:
                                tv1.setText("Kolejny raz podałeś złe hasło, nie masz jeszcze dość?.");
                                break;
                            case 3:
                                tv1.setText("Jesteś upartym graczem, graczu");
                                break;
                            case 4:
                                tv1.setText("Nie dajesz za wygraną, lecz nie łudź się. Nie zgadniesz");
                                break;
                            case 5:
                                tv1.setText("Dobre hasło! Żartowałem, kolejny raz złe...");
                                break;
                            default:
                                tv1.setText("Złe hasło! Odpuść sobie. Idź połowić, czy coś...");
                                break;
                        }
                        counterPassword++;
                    }
                    break;
                case 9:
                    action1(12, 7);
                    break;
                case 10:
                    fishing();
                    break;
                case 11:
                    if (isClickedTemp)
                    {
                        fireCampAction();
                    }
                    else action1(6, 7);
                    break;
                case 12:
                    action1(7, 30);
                    break;
                case 13: //temp
                    action1(6,7);
                    break;
                case 14:
                    if (isClickedTemp2)
                    {
                        weaponMarketAction();
                    }
                default:
                    break;
            }

        }

        if (counter == 1)
        {
            iv1.setVisibility(View.INVISIBLE);
            if (newGame == true)
            {
                goldAmount = 0;
                nameHero = et.getText().toString();
                Character hero = new Character(nameHero, 1, 100, 0, 5, 0, 0);
                tempLvl = hero.getlvl();
                tempHp = hero.gethp();
                tempExp = hero.getexp();
                eq.atack = hero.getAttackpoint();
                eq.defense = hero.getAttackpoint();
                et.setText("Niech rozpocznie się twa przygoda. Powodzenia " + hero.getName() + "!");
                et.setEnabled(false);
                bg.setImageResource(R.drawable.bg1);
                for (int i = 0; i <= 11; i++)
                {
                    items[i] = 0;
                }
                for (int j = 0; j <= 11; j++)
                {
                    bagEmpty[j] = true;
                }
            } else
            {
                et.setText("Ha! Powróciłeś do gry " + nameHero + ". Kolejny raz - powodzenia");
                et.setEnabled(false);
                bg.setImageResource(R.drawable.bg1);
            }
            Character hero = new Character(nameHero, tempLvl, tempHp, tempExp, 5, eq.atack, eq.defense);
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), main);
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
            tv1.setText(hero.toString());
            b1.setText("Losuj przeciwnika");

            counter = 2;
            bFront.setVisibility(View.VISIBLE);

        }
    }

    public void action1(int number1, int number2) throws InterruptedException
    {
        int l1 = number1;
        int l2 = number2;
        Character hero = new Character(nameHero, tempLvl, tempHp, tempExp, 5, eq.atack, eq.defense);
        if (hero.gethp() <= 0)
        {
            meeting(hero, charList.get(0));
        } else
        {
            tempIndex = draw(number1) + number2;
            tv1.setText(String.valueOf(tempIndex));
            meeting(hero, charList.get(tempIndex));
            tempLvl = hero.getlvl();
            tempHp = hero.gethp();
            tempExp = hero.getexp();
            eq.defense = hero.getDefensepoint();
            eq.atack = hero.getAttackpoint();
            tv1.setText(hero.toString());
        }
    }


    public void onClickFront(View view)
    {
        switch (tempMap)
        {
            case 0:
                bg.setImageResource(R.drawable.bg2);
                tempMap = locationList.indexOf("Droga1");
                et.setText("Jestes na drodze ");
                iv1.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.VISIBLE);
                bRight.setVisibility(View.VISIBLE);
                bFront.setVisibility(View.INVISIBLE);
                break;
            case 2:
                bg.setImageResource(R.drawable.bg_las2);
                tempMap = locationList.indexOf("Las2");
                et.setText("Jestes w lesie");
                iv1.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.INVISIBLE);
                bFront.setVisibility(View.VISIBLE);
                break;
            case 3:
                bg.setImageResource(R.drawable.bg_las3);
                tempMap = locationList.indexOf("Las3");
                et.setText("Jestes w lesie");
                iv1.setVisibility(View.INVISIBLE);
                bRight.setVisibility(View.VISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.VISIBLE);
                bFront.setVisibility(View.INVISIBLE);
                break;
            case 5:
                bg.setImageResource(R.drawable.bg_cmentarz2);
                tempMap = locationList.indexOf("Cmentarz2");
                et.setText("Jestes na cmentarzu");
                iv1.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.INVISIBLE);
                bFront.setVisibility(View.VISIBLE);
                break;
            case 6:
                bg.setImageResource(R.drawable.bg_katakumby);
                tempMap = locationList.indexOf("Katakumby");
                et.setText("Jestes w katakumbach");
                iv1.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.INVISIBLE);
                bFront.setVisibility(View.VISIBLE);
                break;
            case 7:
                counterPassword = 0;
                tempMap = locationList.indexOf("Grobowiec");
                bg.setImageResource(R.drawable.bg_drzwi);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.INVISIBLE);
                bFront.setVisibility(View.INVISIBLE);
                bRight.setVisibility(View.INVISIBLE);
                tv1.setText("Podaj hasło, albo odejdź z niesmakiem");
                et.setText("");
                et.setEnabled(true);
                b1.setText("Podaj hasło");
                break;
            case 9:
                bg.setImageResource(R.drawable.bg_jezioro2);
                tempMap = locationList.indexOf("Jezioro2");
                et.setText("Jestes nad jeziorem");
                iv1.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.INVISIBLE);
                bFront.setVisibility(View.INVISIBLE);
                bRight.setVisibility(View.INVISIBLE);
                b1.setText("LOWIENIE");
                break;
            case 11:
                bg.setImageResource(R.drawable.bg_droga_gory2);
                tempMap = locationList.indexOf("DrogaGory2");
                et.setText("Jestes w drodze w gory");
                iv1.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.VISIBLE);
                bFront.setVisibility(View.VISIBLE);
                bRight.setVisibility(View.INVISIBLE);
                imageTemp.clearAnimation();
                imageTemp.setVisibility(View.INVISIBLE);
                b1.setText("Losuj przeciwnika");
                isClickedTemp = false;
                break;
            case 13:
                bg.setImageResource(R.drawable.bg_droga_karczma2);
                tempMap = locationList.indexOf("DrogaKarczma2");
                et.setText("Jestes przed karczmą");
                iv1.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.VISIBLE);
                bFront.setVisibility(View.VISIBLE);
                bRight.setVisibility(View.INVISIBLE);
                market();
                isClickedTemp = false;
                break;
            default:
                break;

        }
    }

    public void onClickBack(View view)
    {
        switch (tempMap)
        {
            case 0:
                break;
            case 1:
                et.setText("Jestes w domu ");
                bg.setImageResource(R.drawable.bg1);
                iv1.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.INVISIBLE);
                bLeft.setVisibility(View.INVISIBLE);
                bFront.setVisibility(View.VISIBLE);
                bRight.setVisibility(View.INVISIBLE);
                tempMap = locationList.indexOf("Dom");
                break;
            case 2:
                et.setText("Jestes na drodze ");
                bg.setImageResource(R.drawable.bg2);
                iv1.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.VISIBLE);
                bRight.setVisibility(View.VISIBLE);
                bFront.setVisibility(View.INVISIBLE);
                tempMap = locationList.indexOf("Droga1");
                break;
            case 3:
                bg.setImageResource(R.drawable.bg_las1);
                tempMap = locationList.indexOf("Las1");
                et.setText("Jestes przed lasem");
                iv1.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.VISIBLE);
                bFront.setVisibility(View.VISIBLE);
                break;
            case 4:
                bg.setImageResource(R.drawable.bg_las2);
                tempMap = locationList.indexOf("Las2");
                et.setText("Jestes w lesie");
                iv1.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.INVISIBLE);
                bFront.setVisibility(View.VISIBLE);
                bRight.setVisibility(View.INVISIBLE);
                break;
            case 5:
                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(getApplicationContext(), main);
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
                bg.setImageResource(R.drawable.bg_las3);
                tempMap = locationList.indexOf("Las3");
                et.setText("Jestes w lesie");
                iv1.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.VISIBLE);
                bFront.setVisibility(View.INVISIBLE);
                bRight.setVisibility(View.VISIBLE);
                break;
            case 6:
                bg.setImageResource(R.drawable.bg_cmentarz);
                tempMap = locationList.indexOf("Cmentarz");
                et.setText("Jestes prawie na cmentarzu");
                iv1.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.INVISIBLE);
                bFront.setVisibility(View.VISIBLE);
                break;
            case 7:
                bg.setImageResource(R.drawable.bg_cmentarz2);
                tempMap = locationList.indexOf("Cmentarz2");
                et.setText("Jestes na cmentarzu");
                iv1.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.INVISIBLE);
                bFront.setVisibility(View.VISIBLE);
                break;
            case 8:
                b1.setText("Losuj przeciwnika");
                bg.setImageResource(R.drawable.bg_katakumby);
                tempMap = locationList.indexOf("Katakumby");
                et.setText("Jestes w katakumbach");
                et.setEnabled(false);
                iv1.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.INVISIBLE);
                bFront.setVisibility(View.VISIBLE);
                break;
            case 9:
                bg.setImageResource(R.drawable.bg_las3);
                tempMap = locationList.indexOf("Las3");
                et.setText("Jestes w lesie");
                iv1.setVisibility(View.INVISIBLE);
                bRight.setVisibility(View.VISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.VISIBLE);
                bFront.setVisibility(View.INVISIBLE);
                break;
            case 10:
                bg.setImageResource(R.drawable.bg_jezioro1);
                tempMap = locationList.indexOf("Jezioro1");
                et.setText("Jestes w drodze nad jezioro");
                iv1.setVisibility(View.INVISIBLE);
                bRight.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.INVISIBLE);
                bFront.setVisibility(View.VISIBLE);
                b1.setText("LOSUJ PRZECIWNIKA");
                break;
            case 11:
                bg.setImageResource(R.drawable.bg_las1);
                tempMap = locationList.indexOf("Las1");
                et.setText("Jestes przed lasem");
                iv1.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.VISIBLE);
                bFront.setVisibility(View.VISIBLE);
                imageTemp.clearAnimation();
                imageTemp.setVisibility(View.INVISIBLE);
                b1.setText("Losuj przeciwnika");
                isClickedTemp = false;
                break;
            case 12:
                bg.setImageResource(R.drawable.bg_droga_gory);
                tempMap = locationList.indexOf("DrogaGory");
                et.setText("Jestes na drodze do gór");
                iv1.setVisibility(View.INVISIBLE);
                bRight.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.INVISIBLE);
                bFront.setVisibility(View.VISIBLE);
                fireCamp();
                break;
            case 13: //temp
                et.setText("Jestes na drodze ");
                bg.setImageResource(R.drawable.bg2);
                iv1.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.VISIBLE);
                bFront.setVisibility(View.INVISIBLE);
                tempMap = locationList.indexOf("Droga1");
                break;
            case 14: //temp
                et.setText("Jestes w drodze do karczmy");
                bg.setImageResource(R.drawable.bg_droga_karczma);
                iv1.setVisibility(View.INVISIBLE);
                bLeft.setVisibility(View.INVISIBLE);
                bRight.setVisibility(View.VISIBLE);
                bFront.setVisibility(View.VISIBLE);
                bBack.setVisibility(View.VISIBLE);
                tempMap = locationList.indexOf("DrogaKarczma");
                imageTemp2.setVisibility(View.INVISIBLE);
                b1.setText("Losuj przeciwnika");
                break;
            default:
                break;

        }
    }

    public void onClickLeft(View view)
    {
        switch (tempMap)
        {
            case 1:
                et.setText("Jestes przed lasem ");
                bg.setImageResource(R.drawable.bg_las1);
                iv1.setVisibility(View.INVISIBLE);
                bLeft.setVisibility(View.VISIBLE);
                bRight.setVisibility(View.INVISIBLE);
                bFront.setVisibility(View.VISIBLE);
                bBack.setVisibility(View.VISIBLE);
                tempMap = locationList.indexOf("Las1");
                break;
            case 2:
                bg.setImageResource(R.drawable.bg_droga_gory);
                tempMap = locationList.indexOf("DrogaGory");
                et.setText("Jestes na drodze do gór");
                iv1.setVisibility(View.INVISIBLE);
                bRight.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.INVISIBLE);
                bFront.setVisibility(View.VISIBLE);
                fireCamp();
                break;
            case 4:
                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.grave);
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
                bg.setImageResource(R.drawable.bg_cmentarz);
                tempMap = locationList.indexOf("Cmentarz");
                et.setText("Jestes prawie na cmentarzu");
                iv1.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.INVISIBLE);
                bFront.setVisibility(View.VISIBLE);
                bRight.setVisibility(View.INVISIBLE);
                break;
            default:
                break;

        }
    }

    public void onClickRight(View view)
    {
        switch (tempMap)
        {
            case 1:
                et.setText("Jestes w drodze do karczmy");
                bg.setImageResource(R.drawable.bg_droga_karczma);
                iv1.setVisibility(View.INVISIBLE);
                bLeft.setVisibility(View.INVISIBLE);
                bRight.setVisibility(View.VISIBLE);
                bFront.setVisibility(View.VISIBLE);
                bBack.setVisibility(View.VISIBLE);
                tempMap = locationList.indexOf("DrogaKarczma");
                break;
            case 4:
                bg.setImageResource(R.drawable.bg_jezioro1);
                tempMap = locationList.indexOf("Jezioro1");
                et.setText("Jestes w drodze nad jezioro");
                iv1.setVisibility(View.INVISIBLE);
                bRight.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);
                bLeft.setVisibility(View.INVISIBLE);
                bFront.setVisibility(View.VISIBLE);
                break;
            default:
                break;

        }
    }

    public void onDrop(View view) //temporary button for testing EQ
    {
        rollEq(0, 22, 40);
    }

    public void onClickEQ(View view)
    {
        Intent intentEq = new Intent(this, com.example.lukasz.gra2.Equipment.class);
        intentEq.putExtra("yourLvl", tempLvl);
        intentEq.putExtra("yourHp", tempHp);
        intentEq.putExtra("yourName", nameHero);
        intentEq.putExtra("yourDefense", eq.defense);
        intentEq.putExtra("yourAtack", eq.atack);
        intentEq.putExtra("yourExp", tempExp);

        intentEq.putExtra("drop1", items[1]);
        intentEq.putExtra("drop2", items[2]);
        intentEq.putExtra("drop3", items[3]);
        intentEq.putExtra("drop4", items[4]);
        intentEq.putExtra("drop5", items[5]);
        intentEq.putExtra("drop6", items[6]);
        intentEq.putExtra("head", items[7]);
        intentEq.putExtra("right", items[8]);
        intentEq.putExtra("left", items[9]);
        intentEq.putExtra("body", items[10]);
        intentEq.putExtra("boots", items[11]);

        intentEq.putExtra("meatAmount", meatAmount);
        intentEq.putExtra("isFishMeat", isFishMeat);
        intentEq.putExtra("goldAmount", goldAmount);
        intentEq.putExtra("cookedMeatAmount", cookedMeatAmount);
        intentEq.putExtra("isCookedMeat", isCookedMeat);


        sound = MediaPlayer.create(getApplicationContext(), R.raw.eq_open_close);
        sound.start();
        startActivityForResult(intentEq, REQUEST_CODE);
    }

    public int draw(int k)
    {
        Random lot = new Random();
        int randomNumber = lot.nextInt(k);
        return randomNumber;

    }

    public int draw(int i, int j)
    {
        return random = draw(i) + j;
    }

    public void meeting(Character you, Character opponent) throws InterruptedException
    {

        et.setText("NAPOTKANO: " + opponent);
        tv2.setText(opponent.toString());
        if (opponent.getOpponentId() == 1)
        {
            iv1.setVisibility(View.INVISIBLE);
            Intent intentOpponent = new Intent(this, Fight.class);
            intentOpponent.putExtra("opponentLvl", opponent.getlvl());
            intentOpponent.putExtra("opponentName", opponent.getName());
            intentOpponent.putExtra("yourLvl", you.getlvl());
            intentOpponent.putExtra("yourHp", you.gethp());
            intentOpponent.putExtra("yourName", you.getName());
            intentOpponent.putExtra("yourAtack", eq.atack);
            intentOpponent.putExtra("yourDefense", eq.defense);
            intentOpponent.putExtra("index", tempIndex);
            intentOpponent.putExtra("location", tempMap);
            intentOpponent.putExtra("goldAmount", goldAmount);
            startActivityForResult(intentOpponent, REQUEST_CODE);
        }
        if (opponent.getOpponentId() == 0)
        {
            iv1.setVisibility(View.VISIBLE);
            et.setText("To jakis neutralny gosc...\nPowitał Cię i szybko odszedł.");
            switch (tempIndex)
            {
                case 3:
                    iv1.setImageResource(R.drawable.kurz);
                    break;
                case 5:
                    iv1.setImageResource(R.drawable.kobieta);
                    break;
                case 12:
                    iv1.setImageResource(R.drawable.pies);
                    break;
                case 13:
                    iv1.setImageResource(R.drawable.mysliwy);
                    break;
                case 24:
                    iv1.setImageResource(R.drawable.zombie);
                    break;
                case 30:
                    iv1.setImageResource(R.drawable.gorskakoza);
                    break;
                case 34:
                    iv1.setImageResource(R.drawable.mysliwy);
                    break;
                default:
                    iv1.setImageResource(R.drawable.neutral);
                    break;
            }
            iv1.startAnimation(char_appearing);
        }
        if (opponent.getOpponentId() == 2)
        {
            iv1.setVisibility(View.VISIBLE);
            switch (tempIndex)
            {
                case 17:
                    iv1.setImageResource(R.drawable.drwal);
                    break;
                case 35:
                    iv1.setImageResource(R.drawable.niebieskiduch);
                default:
                    iv1.setImageResource(R.drawable.przyjaciel);
                    break;
            }
            iv1.startAnimation(char_appearing);
            if (you.gethp() <= 90)
            {
                you.plushp(10);
                et.setText("To Twój przyjaciel!\nPo rozmowie odzyskałeś 10 zdrowia.");
                Thread.sleep(100);
            } else
            {
                et.setText("To Twój przyjaciel!\nPo rozmowie jestes w dobrym humorze, gotowy na przygody!");
                Thread.sleep(100);
            }
        }
        if (opponent.getOpponentId() == 99)
        {
            iv1.setVisibility(View.VISIBLE);
            iv1.setImageResource(R.drawable.kostucha);
            iv1.startAnimation(char_appearing);
            sound = MediaPlayer.create(getApplicationContext(), R.raw.game_over);
            sound.start();
            mediaPlayer.stop();
            et.setText("TO KONIEC!");
            you.dead();
            allert("UMARłES!");
            b1.setText("Wyświetl wynik");
            counter = 3;
        }
        if (opponent.getOpponentId() == 3)
        {
            iv1.setVisibility(View.VISIBLE);
            switch (tempIndex)
            {
                case 11:
                    iv1.setImageResource(R.drawable.druid);
                    break;
                case 14:
                    iv1.setImageResource(R.drawable.niebieskiduch);
                    break;
                default:
                    iv1.setImageResource(R.drawable.medyk);
                    break;
            }
            iv1.startAnimation(char_appearing);
            if (you.gethp() <= 80)
            {
                you.plushp(20);
                et.setText("Masz spore szczęscie, to Kaplan. Uleczył Cię.");
            } else if (you.gethp() <= 90)
            {
                you.plushp(10);
                et.setText("Masz spore szczęscie, to Kaplan. Uleczył Cię.");
            } else et.setText("Nic ci to nie dalo, ze spotkales Kaplana. Byles w pelni sil.");
        }
    }

    public void newLvl(Character you)
    {
        if (you.getlvl() == 1)
        {
            if (you.getexp() >= 200)
            {
                you.setLvl(2);
                Toast.makeText(this, "AWANS NA LEVEL 2!!!", Toast.LENGTH_LONG).show();
                sound = MediaPlayer.create(getApplicationContext(), R.raw.lvl_up);
                sound.start();
            }
        }
        if (you.getlvl() == 2)
        {
            if (you.getexp() >= 400)
            {
                you.setLvl(3);
                Toast.makeText(this, "AWANS NA LEVEL 3!!!", Toast.LENGTH_LONG).show();
                sound = MediaPlayer.create(getApplicationContext(), R.raw.lvl_up);
                sound.start();
            }
        }
        if (you.getlvl() == 3)
        {
            if (you.getexp() >= 700)
            {
                you.setLvl(4);
                Toast.makeText(this, "AWANS NA LEVEL 4!!!", Toast.LENGTH_LONG).show();
                sound = MediaPlayer.create(getApplicationContext(), R.raw.lvl_up);
                sound.start();
            }
        }
        if (you.getlvl() == 4)
        {
            if (you.getexp() >= 1150)
            {
                you.setLvl(5);
                Toast.makeText(this, "AWANS NA LEVEL 5!!!", Toast.LENGTH_LONG).show();
                sound = MediaPlayer.create(getApplicationContext(), R.raw.lvl_up);
                sound.start();
            }

        }
        if (you.getlvl() == 5)
        {
            if (you.getexp() >= 1900)
            {
                you.setLvl(6);
                Toast.makeText(this, "AWANS NA LEVEL 6!!!", Toast.LENGTH_LONG).show();
                sound = MediaPlayer.create(getApplicationContext(), R.raw.lvl_up);
                sound.start();
            }
        }
        if (you.getlvl() == 6)
        {
            if (you.getexp() >= 3100)
            {
                you.setLvl(7);
                Toast.makeText(this, "AWANS NA LEVEL 7!!!", Toast.LENGTH_LONG).show();
                sound = MediaPlayer.create(getApplicationContext(), R.raw.lvl_up);
                sound.start();
            }
        }
        if (you.getlvl() == 7)
        {
            if (you.getexp() >= 4900)
            {
                you.setLvl(8);
                Toast.makeText(this, "AWANS NA LEVEL 8!!!", Toast.LENGTH_LONG).show();
                sound = MediaPlayer.create(getApplicationContext(), R.raw.lvl_up);
                sound.start();
            }
        }
        if (you.getlvl() == 8)
        {
            if (you.getexp() >= 7500)
            {
                you.setLvl(9);
                Toast.makeText(this, "AWANS NA LEVEL 9!!!", Toast.LENGTH_LONG).show();
                sound = MediaPlayer.create(getApplicationContext(), R.raw.lvl_up);
                sound.start();
            }
        }
        if (you.getlvl() == 9)
        {
            if (you.getexp() >= 11500)
            {
                you.setLvl(10);
                Toast.makeText(this, "AWANS NA LEVEL 10!!! Poki co to maksymalny level", Toast.LENGTH_LONG).show();
                sound = MediaPlayer.create(getApplicationContext(), R.raw.lvl_up);
                sound.start();
            }
        }
        tempLvl = you.getlvl();
    }

    public void allert(String allert)
    {
        String allert1 = allert;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(allert1);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    protected void onPause()
    {
        super.onPause();
        mediaPlayer.pause();
        disableSound();
        editor.putInt("atack", eq.atack);
        editor.putInt("defense", eq.defense);
        editor.putInt("level", tempLvl);
        editor.putInt("exp", tempExp);
        editor.putInt("hp", tempHp);
        editor.putInt("item1", items[1]);
        editor.putInt("item2", items[2]);
        editor.putInt("item3", items[3]);
        editor.putInt("item4", items[4]);
        editor.putInt("item5", items[5]);
        editor.putInt("item6", items[6]);
        editor.putInt("item7", items[7]);
        editor.putInt("item8", items[8]);
        editor.putInt("item9", items[9]);
        editor.putInt("item10", items[10]);
        editor.putInt("item11", items[11]);
        editor.putInt("meatAmount", meatAmount);
        editor.putInt("goldAmount", goldAmount);
        editor.putInt("cookedMeatAmount", cookedMeatAmount);
        editor.putBoolean("isFishMeat", isFishMeat);
        editor.putBoolean("isCookedMeat",isCookedMeat);
        editor.putString("name", nameHero);
        editor.commit();
    }

    protected void onResume()
    {
        super.onResume();
        if (counter != 0)
        {
            mediaPlayer.start();
            Character bohater = new Character(nameHero, tempLvl, tempHp, tempExp, 5, eq.atack, eq.defense);
            newLvl(bohater);
            tv1.setText(bohater.toString());
            if (tempHp <= 0)
            {
                b1.setText("SPOTKANIE Z KOSTUCHĄ");
                bRight.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.INVISIBLE);
                bFront.setVisibility(View.INVISIBLE);
                bLeft.setVisibility(View.INVISIBLE);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent returnedData)
    {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE)
        {
            if (returnedData.hasExtra("returnedExp") && returnedData.hasExtra("returnedHp") && returnedData.hasExtra("returnedId"))
            {
                Character hero = new Character(nameHero, tempLvl, tempHp, tempExp, 5, eq.atack, eq.defense);
                tempHp -= returnedData.getExtras().getInt("returnedHp");
                tempExp += returnedData.getExtras().getInt("returnedExp");
                fightId = returnedData.getExtras().getInt("returnedId");
                goldAmount = returnedData.getExtras().getInt("goldAmount");
                if (fightId == 1 && charList.get(tempIndex).getlvl() == 0)
                {
                    rollEq(0, 7, 15); //numbers 1-7 are being rolled from 15 which is 6/15 * 100% = 40 drop rate
                }
                if (fightId == 1 && charList.get(tempIndex).getlvl() == 1)
                {
                    rollEq(0, 7, 15);
                }
                if (fightId == 1 && charList.get(tempIndex).getlvl() == 2)
                {
                    rollEq(0, 14, 32); // 13/32 = 0,4
                }
                if(fightId == 1 && charList.get(tempIndex).getlvl() == 3)
                {
                    rollEq(7, 22, 40); // (22-7)/47 = 0,4
                }
                if(fightId == 1 && charList.get(tempIndex).getlvl() == 4)
                {
                    rollEq(15, 22, 19); // 7/17 = 0,41
                }
                if(fightId == 1 && charList.get(tempIndex).getlvl() == 5)
                {
                    rollEq(23, 29, 15); // 6/15 = 0,4
                }
                if(fightId == 1 && charList.get(tempIndex).getlvl() == 6)
                {
                    rollEq(23, 36, 32); // 13/32 = 0,4
                }

            }  if (returnedData.hasExtra("fishingExp") && returnedData.hasExtra("fishingHp") &&
                returnedData.hasExtra("meatAmount") && returnedData.hasExtra("isCatched")
                && returnedData.hasExtra("isFishMeat"))
            {
                Character bohater = new Character(nameHero, tempLvl, tempHp, tempExp, 5, eq.atack, eq.defense);
                tempHp = returnedData.getExtras().getInt("fishingHp");
                tempExp = returnedData.getExtras().getInt("fishingExp");
                meatAmount = returnedData.getExtras().getInt("meatAmount");
                isCatched = returnedData.getExtras().getBoolean("isCatched");
                isFishMeat = returnedData.getExtras().getBoolean("isFishMeat");
                addFishMeat();
            } if (returnedData.hasExtra("yourAtack") && returnedData.hasExtra("yourDefense") && returnedData.hasExtra("yourHp")
                    && returnedData.hasExtra("yourExp") && returnedData.hasExtra("drop1") && returnedData.hasExtra("drop2") &&
                    returnedData.hasExtra("drop3") && returnedData.hasExtra("drop4") && returnedData.hasExtra("drop5") &&
                    returnedData.hasExtra("drop6") && returnedData.hasExtra("head") && returnedData.hasExtra("right") &&
                    returnedData.hasExtra("left") && returnedData.hasExtra("body") && returnedData.hasExtra("boots")
                    && returnedData.hasExtra("isFishMeat"))
            {
                Character bohater = new Character(nameHero, tempLvl, tempHp, tempExp, 5, eq.atack, eq.defense);
                eq.atack = returnedData.getExtras().getInt("yourAtack");
                eq.defense = returnedData.getExtras().getInt("yourDefense");
                tempHp = returnedData.getExtras().getInt("yourHp");
                tempExp = returnedData.getExtras().getInt("yourExp");
                items[1] = returnedData.getExtras().getInt("drop1");
                items[2] = returnedData.getExtras().getInt("drop2");
                items[3] = returnedData.getExtras().getInt("drop3");
                items[4] = returnedData.getExtras().getInt("drop4");
                items[5] = returnedData.getExtras().getInt("drop5");
                items[6] = returnedData.getExtras().getInt("drop6");
                items[7] = returnedData.getExtras().getInt("head");
                items[8] = returnedData.getExtras().getInt("right");
                items[9] = returnedData.getExtras().getInt("left");
                items[10] = returnedData.getExtras().getInt("body");
                items[11] = returnedData.getExtras().getInt("boots");
                meatAmount = returnedData.getExtras().getInt("meatAmount");
                isFishMeat = returnedData.getExtras().getBoolean("isFishMeat");
                goldAmount = returnedData.getExtras().getInt("goldAmount");
                cookedMeatAmount = returnedData.getExtras().getInt("cookedMeatAmount");
                isCookedMeat = returnedData.getExtras().getBoolean("isCookedMeat");
                checkBags();
            }
            if (returnedData.hasExtra("yourHp") && returnedData.hasExtra("isFishMeat") && returnedData.hasExtra("cookedMeatAmount")
                && returnedData.hasExtra("meatAmount"))
            {
                Character bohater = new Character(nameHero, tempLvl, tempHp, tempExp, 5, eq.atack, eq.defense);
                tempHp = returnedData.getExtras().getInt("yourHp");
                meatAmount = returnedData.getExtras().getInt("meatAmount");
                isFishMeat = returnedData.getExtras().getBoolean("isFishMeat");
                cookedMeatAmount = returnedData.getExtras().getInt("cookedMeatAmount");
                isCookedMeat = returnedData.getExtras().getBoolean("isCookedMeat");
            }
        }
    }

    protected void fishing()
    {
        Character hero = new Character(nameHero, tempLvl, tempHp, tempExp, 5, eq.atack, eq.defense);
        Intent intentFishing = new Intent(this, Fishing.class);
        intentFishing.putExtra("tempExp", tempExp);
        intentFishing.putExtra("tempHp", tempHp);
        intentFishing.putExtra("meatAmount", meatAmount);
        intentFishing.putExtra("isFishMeat", isFishMeat);
        startActivityForResult(intentFishing, REQUEST_CODE);
    }

    public void checkBags()
    {
        for (int i = 1; i <= 11; i++)
        {
            if (items[i] == 0)
            {
                bagEmpty[i] = true;
            }
        }
    }

    public void rollEq(int bottomNumber, int ceilingNumber, int prawdopodobienstwo)
    {
        isEqFull = true;
        drop = draw(prawdopodobienstwo, 0);
        if (drop > bottomNumber && drop <= ceilingNumber)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Po wygranym pojedynku przeciwnik zostawił " + itemNameList.get(drop) + ". Co chcesz uczynić?");
            alert.setPositiveButton("Podnieś", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface arg0, int arg1)
                {
                    for (int i = 1; i <= 6; i++)
                    {
                        if (bagEmpty[i])
                        {
                            items[i] = drop;
                            bagEmpty[i] = false;
                            isEqFull = false;
                            break;
                        }
                    }
                    if (isEqFull)
                    {
                        Toast.makeText(getApplicationContext(), "Pełny ekwipunek!", Toast.LENGTH_LONG).show();
                    }
                }
            });
            alert.setNegativeButton("Nie dotykaj!", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                }
            });
            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        }
    }
    public void addFishMeat()
    {
        isEqFull = true;
        if (isCatched == true && meatAmount != 0)
        {
            if (isFishMeat == false)
            {
                drop = 15;
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage("Po łowieniu uzyskałeś kilka kawałków surowego rybiego mięsiwa. Co chcesz uczynić?");
                alert.setPositiveButton("Weź ze sobą", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        for (int i = 1; i <= 6; i++)
                        {
                            if (bagEmpty[i])
                            {
                                items[i] = drop;
                                bagEmpty[i] = false;
                                isFishMeat = true;
                                isEqFull = false;
                                break;
                            }
                        }
                        if (isEqFull)
                        {
                            Toast.makeText(getApplicationContext(), "Pełny ekwipunek!", Toast.LENGTH_LONG).show();
                        }


                    }
                });
                alert.setNegativeButton("Zostaw", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        meatAmount = 0;
                    }
                });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        }
        isCatched = false;
    }
    private void fireCamp()
    {
        imageTemp.setImageResource(R.drawable.ognisko);
        imageTemp.startAnimation(pulse_low);
        imageTemp.setVisibility(View.VISIBLE);
        imageTemp.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (isClickedTemp == false)
                {
                    imageTemp.clearAnimation();
                    imageTemp.setImageResource(R.drawable.ognisko_active);
                    b1.setText("Rozpal ognisko");
                    isClickedTemp = true;
                }
                else
                {
                    isClickedTemp = false;
                    imageTemp.setImageResource(R.drawable.ognisko);
                    imageTemp.startAnimation(pulse_low);
                    b1.setText("Losuj przeciwnika");
                }
            }
        });
    }
    private void market()
    {
        imageTemp2.setImageResource(R.drawable.targ);
        imageTemp2.setVisibility(View.VISIBLE);
        imageTemp2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (isClickedTemp2 == false)
                {
                    imageTemp2.setImageResource(R.drawable.targ_active);
                    b1.setText("Handluj");
                    isClickedTemp2 = true;
                }
                else
                {
                    isClickedTemp2 = false;
                    imageTemp2.setImageResource(R.drawable.targ);
                    b1.setText("Losuj przeciwnika");
                }
            }
        });
    }
    private void fireCampAction()
    {
        Intent intentEq = new Intent(this, CampFire.class);
        intentEq.putExtra("yourHp", tempHp);
        intentEq.putExtra("drop1", items[1]);
        intentEq.putExtra("drop2", items[2]);
        intentEq.putExtra("drop3", items[3]);
        intentEq.putExtra("drop4", items[4]);
        intentEq.putExtra("drop5", items[5]);
        intentEq.putExtra("drop6", items[6]);
        intentEq.putExtra("meatAmount", meatAmount);
        intentEq.putExtra("isFishMeat", isFishMeat);
        intentEq.putExtra("cookedMeatAmount", cookedMeatAmount);
        intentEq.putExtra("isCookedMeat", isCookedMeat);

        sound = MediaPlayer.create(getApplicationContext(), R.raw.eq_open_close);
        sound.start();
        startActivityForResult(intentEq, REQUEST_CODE);
    }
    private void weaponMarketAction()
    {
        Intent intentWeaponMarket = new Intent(this, WeaponMarket.class);
        intentWeaponMarket.putExtra("drop1", items[1]);
        intentWeaponMarket.putExtra("drop2", items[2]);
        intentWeaponMarket.putExtra("drop3", items[3]);
        intentWeaponMarket.putExtra("drop4", items[4]);
        intentWeaponMarket.putExtra("drop5", items[5]);
        intentWeaponMarket.putExtra("drop6", items[6]);

        intentWeaponMarket.putExtra("meatAmount", meatAmount);
        intentWeaponMarket.putExtra("isFishMeat", isFishMeat);
        intentWeaponMarket.putExtra("cookedMeatAmount", cookedMeatAmount);
        intentWeaponMarket.putExtra("isCookedMeat", isCookedMeat);
        intentWeaponMarket.putExtra("goldAmount", goldAmount);

        startActivityForResult(intentWeaponMarket, REQUEST_CODE);
    }
    private void disableSound()
    {
            if (sound != null && sound.isPlaying())
            {
                sound.stop();
                sound.reset();
                sound.release();
                sound = null;
            }

    }

}

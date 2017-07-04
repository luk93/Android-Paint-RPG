package com.example.lukasz.gra2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Equipment extends AppCompatActivity
{
    //fishList przedmiotów
    //name, typ(1- glowa, 2- prawa reka, 3- lewa reka, 4 - korpus, 5 - buty, 6 - oburącz 7 - pozywienie), typ klasy(0 - wszyscy, 1 - woj, 2 - mag)
    //wymagany level, punkty obrony, punkty ataku
    ArrayList<Item> itemList = new ArrayList<>();
    ArrayList<Integer> iconList = new ArrayList<>();
    Button bhead, bright, bbody, bleft, bboots, b1, b2, b3, b4, b5, b6, bExit, bUse, bThrow, bAddFish;
    TextView tv1, tv2, tv3, tv4, tv5, goldText;
    int yourLvl, yourHp, yourDefense, yourAtack, yourExp, meatAmount, cookedMeatAmount;
    boolean dead = false, zero = true, isFishMeat, isCookedMeat = false;
    boolean isSelected[] = new boolean[12];
    int selected = 0;
    boolean empty[] = new boolean[12];
    String yourName;
    MediaPlayer mediaPlayer, sound, sound2;
    int items[] = new int[12];
    int item1, item2, item3, item4, item5, item6, itemhead, itemright, itemleft, itembody, itemboots, goldAmount;
    int tempX = 1;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);
        //0,1
        itemList.add(new Item("czepek", 1, 0, 0, 3, 0));    //0
        itemList.add(new Item("kij", 2, 0, 0, 0, 3));       //1
        itemList.add(new Item("laczki", 5, 0, 0, 3, 0));    //2
        itemList.add(new Item("wiaderko", 3, 0, 0, 0, 0));    //3
        itemList.add(new Item("koszulka", 4, 0, 0, 3, 0));    //4
        itemList.add(new Item("duzy kij", 6, 0, 0, 0, 6));   //5
        itemList.add(new Item("banan", 7, 0, 0, 0, 0, 5));      //6
        itemList.add(new Item("czepek", 1, 0, 0, 3, 0));      //7
        //1,2
        itemList.add(new Item("drewniany miecz", 2, 0, 0, 0, 5));       //8
        itemList.add(new Item("kozaki", 5, 0, 0, 5, 0));             //9
        itemList.add(new Item("pokrywka od garnka", 3, 0, 0, 5, 0));    //10
        itemList.add(new Item("sweter", 4, 0, 0, 5, 0));             //11
        itemList.add(new Item("łopata", 6, 0, 0, 0, 10));             //12
        itemList.add(new Item("dwa banany", 7, 0, 0, 0, 0, 8));        //13
        itemList.add(new Item("peruka", 1, 0, 0, 6, 0));             //14

        itemList.add(new Item("surowe rybie mięso", 7, 0, 0, 0, 0, 1));// 15
        //2,3,4
        itemList.add(new Item("ostry sztylet", 2, 0, 0, 0, 8));       //16
        itemList.add(new Item("wzmacniane buty", 5, 0, 0, 8, 0));             //17
        itemList.add(new Item("drewniana tarcza", 3, 0, 0, 8, 0));    //18
        itemList.add(new Item("skórzana zbroja", 4, 0, 0, 8, 0));             //19
        itemList.add(new Item("miecz dwuręczny", 6, 0, 0, 0, 15));             //20
        itemList.add(new Item("szynka", 7, 0, 0, 0, 0, 12));        //21
        itemList.add(new Item("drewniany hełm", 1, 0, 0, 8, 0));             //22
        //5,6
        itemList.add(new Item("szeroki miecz", 2, 0, 0, 0, 8));       //23
        itemList.add(new Item("żelazne buty", 5, 0, 0, 8, 0));             //24
        itemList.add(new Item("wzmacniany puklerz", 3, 0, 0, 8, 0));    //25
        itemList.add(new Item("kolczuga", 4, 0, 0, 8, 0));             //26
        itemList.add(new Item("topór obosieczny", 6, 0, 0, 0, 15));             //27
        itemList.add(new Item("potrawka", 7, 0, 0, 0, 0, 12));        //28
        itemList.add(new Item("żelazny hełm", 1, 0, 0, 8, 0));             //29
        //6
        itemList.add(new Item("ostra katana", 2, 0, 0, 0, 8));       //30
        itemList.add(new Item("stalowe buty", 5, 0, 0, 8, 0));             //31
        itemList.add(new Item("stalowa tarcza", 3, 0, 0, 8, 0));    //32
        itemList.add(new Item("stalowa zbroja", 4, 0, 0, 8, 0));             //33
        itemList.add(new Item("stalowy claymore", 6, 0, 0, 0, 15));             //34
        itemList.add(new Item("duża porcja potrawki", 7, 0, 0, 0, 0, 12));        //35
        itemList.add(new Item("stalowy hełm", 1, 0, 0, 8, 0));             //36
        itemList.add(new Item("smażone rybie mięso",7, 0, 0, 0, 0, 1));          //37





        //fishList ikon
        iconList.add(R.drawable.eq_okno);  //0
        iconList.add(R.drawable.kij);     //1
        iconList.add(R.drawable.laczki);  //2
        iconList.add(R.drawable.wiaderko);//3
        iconList.add(R.drawable.koszulka);//4
        iconList.add(R.drawable.duzy_kij);//5
        iconList.add(R.drawable.banan);   //6
        iconList.add(R.drawable.czepek);  //7

        iconList.add(R.drawable.drewniany_miecz); //8
        iconList.add(R.drawable.kozaki);  //9
        iconList.add(R.drawable.pokrywa); //10
        iconList.add(R.drawable.sweter); //11
        iconList.add(R.drawable.lopata); //12
        iconList.add(R.drawable.dwa_banany);//13
        iconList.add(R.drawable.peruka);  //14

        iconList.add(R.drawable.surowe_rybie_mieso); //15 poki co

        iconList.add(R.drawable.ostry_sztylet); //16
        iconList.add(R.drawable.wzmacniane_buty);//17
        iconList.add(R.drawable.drewniana_tarcza);//18
        iconList.add(R.drawable.skorzana_zbroja);//19
        iconList.add(R.drawable.miecz_dwureczny);//20
        iconList.add(R.drawable.szynka);          //21
        iconList.add(R.drawable.drewniany_helm);    //22

        iconList.add(R.drawable.szeroki_miecz);//23
        iconList.add(R.drawable.zelaze_buty);   //24
        iconList.add(R.drawable.wzmacniany_puklerz);//25
        iconList.add(R.drawable.kolczuga);          //26
        iconList.add(R.drawable.topor_obosieczny);  //27
        iconList.add(R.drawable.potrawka);          //28
        iconList.add(R.drawable.zelazny_helm);      //29

        iconList.add(R.drawable.katana);        //30
        iconList.add(R.drawable.stalowe_buty);     //31
        iconList.add(R.drawable.stalowa_tarcza);    //32
        iconList.add(R.drawable.stalowa_zbroja);    //33
        iconList.add(R.drawable.stalowy_claymore);  //34
        iconList.add(R.drawable.duza_porcja_potrawki);//35
        iconList.add(R.drawable.stalowy_helm);          //36
        iconList.add(R.drawable.smazone_rybie_mieso);   //37



        bhead = (Button) findViewById(R.id.head);
        bright = (Button) findViewById(R.id.right);
        bleft = (Button) findViewById(R.id.left);
        bbody = (Button) findViewById(R.id.body);
        bboots = (Button) findViewById(R.id.boots);
        b1 = (Button) findViewById(R.id.slot1);
        b2 = (Button) findViewById(R.id.slot2);
        b3 = (Button) findViewById(R.id.slot3);
        b4 = (Button) findViewById(R.id.slot4);
        b5 = (Button) findViewById(R.id.slot5);
        b6 = (Button) findViewById(R.id.slot6);
        bExit = (Button) findViewById(R.id.exit);
        bUse = (Button) findViewById(R.id.use);
        bThrow = (Button) findViewById(R.id.throwAway);
        tv1 = (TextView) findViewById(R.id.heroEqField);
        tv2 = (TextView) findViewById(R.id.defenseEq);
        tv3 = (TextView) findViewById(R.id.atackEq);
        tv4 = (TextView) findViewById(R.id.itemDescription);
        tv5 = (TextView) findViewById(R.id.textView);
        goldText = (TextView) findViewById(R.id.goldText);

        bAddFish = (Button) findViewById(R.id.addFish);

        sound2 = MediaPlayer.create(getApplicationContext(), R.raw.throw_away);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.eq);
        mediaPlayer.start();
        sound = MediaPlayer.create(getApplicationContext(), R.raw.icon);
        if (zero)
        {
            emptyAll();
            zero = false;
        }

        Bundle extra = getIntent().getExtras();
        yourAtack = extra.getInt("yourAtack");
        yourDefense = extra.getInt("yourDefense");
        yourName = extra.getString("yourName");
        yourLvl = extra.getInt("yourLvl");
        yourHp = extra.getInt("yourHp");
        yourExp = extra.getInt("yourExp");

        items[1] = extra.getInt("drop1");
        items[2] = extra.getInt("drop2");
        items[3] = extra.getInt("drop3");
        items[4] = extra.getInt("drop4");
        items[5] = extra.getInt("drop5");
        items[6] = extra.getInt("drop6");
        items[7] = extra.getInt("head");
        items[8] = extra.getInt("right");
        items[9] = extra.getInt("left");
        items[10] = extra.getInt("body");
        items[11] = extra.getInt("boots");

        meatAmount = extra.getInt("meatAmount");
        isFishMeat = extra.getBoolean("isFishMeat");
        goldAmount = extra.getInt("goldAmount");
        cookedMeatAmount = extra.getInt("cookedMeatAmount");
        isCookedMeat = extra.getBoolean("isCookedMeat");
        tv5.setText(String.valueOf(cookedMeatAmount)+" "+String.valueOf(isCookedMeat));

        goldText.setText(String.valueOf(goldAmount));

        resetSelection();

        if (yourHp <= 0)
        {
            tv1.setText("Uuuuu idż straszyć ludzi, jesteś duchem! A no tak, nigdzie nie pójdziesz, bo nie żyjesz...");
            dead = true;
        } else
        {
            Character bohater = new Character(yourName, yourLvl, yourHp, yourExp, 0, yourAtack, yourDefense);
            tv1.setText(bohater.toString());
            tv2.setText("Dodatkowe punkty obrony: " + String.valueOf(bohater.getDefensepoint()));
            tv3.setText("Dodatkowe punkty ataku: " + String.valueOf(bohater.getAttackpoint()));
            if (meatAmount != 0)
            {
                itemList.get(15).setHp(meatAmount);
            }
            if (meatAmount == 0)
            {
                deleteItem(15);
            }
            if (cookedMeatAmount != 0)
            {
                tempX = cookedMeatAmount * 2;
                itemList.get(37).setHp(tempX);
            }
            if (isCookedMeat == false && cookedMeatAmount != 0)
            {
                addItem(37);
                isCookedMeat = true;
            }
            fillEq();
            fillBags();
        }
    }

    protected void onPause()
    {
        mediaPlayer.stop();
        sound = MediaPlayer.create(getApplicationContext(), R.raw.eq_open_close);
        sound.start();
        super.onPause();
    }

    public void onHead(View view)
    {
        sound.start();
        resetSelection();
        isSelected[7] = true;
        resetIcons();
        bhead.setScaleY(2);
        bhead.setScaleX(2);
        if (empty[7])
        {
            tv4.setText("Nie masz nic na głowie");
        } else
        {
            tv4.setText(itemList.get(itemhead).toString());
            selected = itemhead;
            bUse.setText("ZDEJMIJ");
        }
    }

    public void onRight(View view)
    {
        sound.start();
        resetSelection();
        isSelected[8] = true;
        resetIcons();
        bright.setScaleY(2);
        bright.setScaleX(2);
        if (empty[8])
        {
            tv4.setText("Nie masz nic w prawej ręce");
        } else
        {
            tv4.setText(itemList.get(itemright).toString());
            selected = itemright;
            bUse.setText("ZDEJMIJ");
        }
    }

    public void onLeft(View view)
    {
        sound.start();
        resetSelection();
        isSelected[9] = true;
        resetIcons();
        bleft.setScaleY(2);
        bleft.setScaleX(2);
        if (empty[9])
        {
            tv4.setText("Nie masz nic w lewej ręce");
        } else
        {
            tv4.setText(itemList.get(itemleft).toString());
            selected = itemleft;
            bUse.setText("ZDEJMIJ");
        }
    }

    public void onBody(View view)
    {
        sound.start();
        resetSelection();
        isSelected[10] = true;
        resetIcons();
        bbody.setScaleY(2);
        bbody.setScaleX(2);
        if (empty[10])
        {
            tv4.setText("Nie masz na korpusie");
        } else
        {
            tv4.setText(itemList.get(itembody).toString());
            selected = itembody;
            bUse.setText("ZDEJMIJ");
        }
    }

    public void onBoots(View view)
    {
        sound.start();
        resetSelection();
        isSelected[11] = true;
        resetIcons();
        bboots.setScaleY(2);
        bboots.setScaleX(2);
        if (empty[11])
        {
            tv4.setText("Nie masz nic na nogach");
        } else
        {
            tv4.setText(itemList.get(itemboots).toString());
            selected = itemboots;
            bUse.setText("ZDEJMIJ");
        }
    }

    public void onClick1(View view)
    {
        bUse.setText("UŻYJ");
        sound.start();
        resetSelection();
        isSelected[1] = true;
        resetIcons();
        b1.setScaleY(2);
        b1.setScaleX(2);
        if (empty[1])
        {
            tv4.setText("Slot 1 jest pusty");
        } else
        {
            tv4.setText(itemList.get(item1).toString());
            selected = item1;
        }
    }

    public void onClick2(View view)
    {
        bUse.setText("UŻYJ");
        sound.start();
        resetSelection();
        isSelected[2] = true;
        resetIcons();
        b2.setScaleY(2);
        b2.setScaleX(2);
        if (empty[2])
        {
            tv4.setText("Slot 2 jest pusty");
        } else
        {
            tv4.setText(itemList.get(item2).toString());
            selected = item2;
        }
    }

    public void onClick3(View view)
    {
        bUse.setText("UŻYJ");
        sound.start();
        resetSelection();
        isSelected[3] = true;
        resetIcons();
        b3.setScaleY(2);
        b3.setScaleX(2);
        if (empty[3])
        {
            tv4.setText("Slot 3 jest pusty");
        } else
        {
            tv4.setText(itemList.get(item3).toString());
            selected = item3;
        }
    }

    public void onClick4(View view)
    {
        bUse.setText("UŻYJ");
        sound.start();
        resetSelection();
        isSelected[4] = true;
        resetIcons();
        b4.setScaleY(2);
        b4.setScaleX(2);
        if (empty[4])
        {
            tv4.setText("Slot 4 jest pusty");
        } else
        {
            tv4.setText(itemList.get(item4).toString());
            selected = item4;
        }
    }

    public void onClick5(View view)
    {
        bUse.setText("UŻYJ");
        sound.start();
        resetSelection();
        isSelected[5] = true;
        resetIcons();
        b5.setScaleY(2);
        b5.setScaleX(2);
        if (empty[5])
        {
            tv4.setText("Slot 5 jest pusty");
        } else
        {
            tv4.setText(itemList.get(item5).toString());
            selected = item5;
        }
    }

    public void onClick6(View view)
    {
        bUse.setText("UŻYJ");
        sound.start();
        resetSelection();
        isSelected[6] = true;
        resetIcons();
        b6.setScaleY(2);
        b6.setScaleX(2);
        if (empty[6])
        {
            tv4.setText("Slot 6 jest pusty");
        } else
        {
            tv4.setText(itemList.get(item6).toString());
            selected = item6;
        }
    }

    public void onClickExit(View view)
    {
        Intent eqOver = new Intent();
        eqOver.putExtra("yourAtack", yourAtack);
        eqOver.putExtra("yourDefense", yourDefense);
        eqOver.putExtra("yourHp", yourHp);
        eqOver.putExtra("yourExp", yourExp);
        eqOver.putExtra("drop1", items[1]);
        eqOver.putExtra("drop2", items[2]);
        eqOver.putExtra("drop3", items[3]);
        eqOver.putExtra("drop4", items[4]);
        eqOver.putExtra("drop5", items[5]);
        eqOver.putExtra("drop6", items[6]);
        eqOver.putExtra("head", items[7]);
        eqOver.putExtra("right", items[8]);
        eqOver.putExtra("left", items[9]);
        eqOver.putExtra("body", items[10]);
        eqOver.putExtra("boots", items[11]);
        eqOver.putExtra("meatAmount", meatAmount);
        eqOver.putExtra("isFishMeat", isFishMeat);
        eqOver.putExtra("goldAmount", goldAmount);
        eqOver.putExtra("cookedMeatAmount", cookedMeatAmount);
        eqOver.putExtra("isCookedMeat", isCookedMeat);

        setResult(RESULT_OK, eqOver);
        finish();
    }

    public void onClickThrow(View view)
    {
        if (empty[checkSelection()] == false)
        {
            sound2.start();
            empty[checkSelection()] = true;
            items[checkSelection()] = 0;
            resetButtons();
            if (isSelected[7] == true || isSelected[8] == true || isSelected[9] == true || isSelected[10] == true || isSelected[11] == true)
            {
                setZeros();
                if (itemList.get(selected).getItem_type() == 6)
                {
                    if (isSelected[8])
                    {
                        itemleft = 0;
                        empty[9] = true;
                        items[9] = 0;
                        bleft.setBackgroundResource(iconList.get(0));
                    } else
                    {
                        itemright = 0;
                        empty[8] = true;
                        items[8] = 0;
                        bright.setBackgroundResource(iconList.get(0));
                    }
                }
                update_throw();
            } else
            {
                Character bohater = new Character(yourName, yourLvl, yourHp, yourExp, 0, yourAtack, yourDefense);
                tv1.setText(bohater.toString());
                tv2.setText("Dodatkowe punkty obrony: " + String.valueOf(bohater.getDefensepoint()));
                tv3.setText("Dodatkowe punkty ataku: " + String.valueOf(bohater.getAttackpoint()));
            }
            if (selected == 15)
            {
                meatAmount = 0;
                isFishMeat = false;
            }
            if (selected == 37)
            {
                cookedMeatAmount = 0;
                isCookedMeat = false;
            }
        }
    }

    public void onClickUse(View view)
    {
        if (empty[checkSelection()] == false)
        {
            sound.start();
            use();
        }
    }

    public void resetIcons()
    {
        b1.setScaleX(1);
        b1.setScaleY(1);
        b2.setScaleX(1);
        b2.setScaleY(1);
        b3.setScaleX(1);
        b3.setScaleY(1);
        b4.setScaleX(1);
        b4.setScaleY(1);
        b5.setScaleY(1);
        b5.setScaleX(1);
        b6.setScaleX(1);
        b6.setScaleY(1);
        bbody.setScaleX(1);
        bbody.setScaleY(1);
        bboots.setScaleX(1);
        bboots.setScaleY(1);
        bright.setScaleX(1);
        bright.setScaleY(1);
        bleft.setScaleY(1);
        bleft.setScaleX(1);
        bhead.setScaleY(1);
        bhead.setScaleX(1);
        tv5.setText(" ");
    }

    public void resetSelection()
    {
        for (int i = 0; i <= 11; i++)
        {
            isSelected[i] = false;
        }
    }

    public void emptyAll()
    {
        for (int i = 0; i <= 11; i++)
        {
            empty[i] = true;
        }
    }

    public void use()
    {   //(1- glowa, 2- prawa reka, 3- lewa reka, 4 - korpus, 5 - buty, 6 - oburącz 7 - pozywienie)
        switch (itemList.get(selected).getItem_type())
        {
            case 1:
                if (empty[7])
                {
                    tv1.setText("uzyto rzeczy na glowe");
                    bhead.setBackgroundResource(iconList.get(selected));
                    empty[7] = false;
                    empty[checkSelection()] = true;
                    resetButtons();
                    itemhead = selected;
                    items[7] = itemhead;
                    items[checkSelection()] = 0;
                    updateUse();
                } else if (isSelected[7] == true || isSelected[8] == true || isSelected[9] == true || isSelected[10] == true || isSelected[11] == true)
                {
                    takeOff();
                } else tv5.setText("Masz juz cos na glowie");
                break;
            case 2:
                if (empty[8])
                {
                    tv5.setText("Uzyto rzeczy przeznaczonej do prawej reki");
                    bright.setBackgroundResource(iconList.get(selected));
                    empty[8] = false;
                    empty[checkSelection()] = true;
                    resetButtons();
                    itemright = selected;
                    items[8] = itemright;
                    items[checkSelection()] = 0;
                    updateUse();
                } else if (isSelected[7] == true || isSelected[8] == true || isSelected[9] == true || isSelected[10] == true || isSelected[11] == true)
                {
                    takeOff();
                } else tv5.setText("Masz juz cos w prawej rece");
                break;
            case 3:
                if (empty[9])
                {
                    tv5.setText("Uzyto rzeczy przeznaczonej do lewej reki");
                    bleft.setBackgroundResource(iconList.get(selected));
                    empty[9] = false;
                    empty[checkSelection()] = true;
                    resetButtons();
                    itemleft = selected;
                    items[9] = itemleft;
                    items[checkSelection()] = 0;
                    updateUse();
                } else if (isSelected[7] == true || isSelected[8] == true || isSelected[9] == true || isSelected[10] == true || isSelected[11] == true)
                {
                    takeOff();
                } else tv5.setText("Masz juz cos w lewej rece");
                break;
            case 4:
                if (empty[10])
                {
                    tv5.setText("Uzyto rzeczy na korpus");
                    bbody.setBackgroundResource(iconList.get(selected));
                    empty[10] = false;
                    empty[checkSelection()] = true;
                    resetButtons();
                    itembody = selected;
                    items[10] = itembody;
                    items[checkSelection()] = 0;
                    updateUse();
                } else if (isSelected[7] == true || isSelected[8] == true || isSelected[9] == true || isSelected[10] == true || isSelected[11] == true)
                {
                    takeOff();
                } else tv5.setText("Masz juz cos na korpusie");
                break;
            case 5:
                if (empty[11])
                {
                    tv5.setText("Uzyto rzeczy na stopy");
                    bboots.setBackgroundResource(iconList.get(selected));
                    empty[11] = false;
                    empty[checkSelection()] = true;
                    resetButtons();
                    itemboots = selected;
                    items[11] = itemboots;
                    items[checkSelection()] = 0;
                    updateUse();
                } else if (isSelected[7] == true || isSelected[8] == true || isSelected[9] == true || isSelected[10] == true || isSelected[11] == true)
                {
                    takeOff();
                } else tv5.setText("Juz masz cos na stopach");
                break;
            case 6:
                if (empty[8] && empty[9])
                {
                    tv5.setText("Uzyto rzeczy dwurecznej");
                    bright.setBackgroundResource(iconList.get(selected));
                    bleft.setBackgroundResource(iconList.get(selected));
                    empty[8] = false;
                    empty[9] = false;
                    empty[checkSelection()] = true;
                    resetButtons();
                    itemright = selected;
                    itemleft = selected;
                    items[8] = itemright;
                    items[9] = itemleft;
                    items[checkSelection()] = 0;
                    updateUse();
                } else if (isSelected[7] == true || isSelected[8] == true || isSelected[9] == true || isSelected[10] == true || isSelected[11] == true)
                {
                    takeOff();
                } else tv5.setText("Masz juz cos w lewej lub prawej rece");
                break;
            case 7:
                if (selected == 15)
                {
                    meatAmount = 0;
                    isFishMeat = false;
                }
                if (selected == 37)
                {
                    cookedMeatAmount = 0;
                    isCookedMeat = false;
                }
                tv5.setText("Uzyto pozywienia");
                empty[checkSelection()] = true;
                items[checkSelection()] = 0;
                resetButtons();
                updateUse();
                break;
            default:
                break;

        }

    }

    public int checkSelection()
    {
        for (int i = 0; i <= 11; i++)
        {
            if (isSelected[i] == true) return i;
        }
        return 0;
    }

    public void resetButtons()
    {
        switch (checkSelection())
        {
            case 1:
                b1.setBackgroundResource(iconList.get(0));
                break;
            case 2:
                b2.setBackgroundResource(iconList.get(0));
                break;
            case 3:
                b3.setBackgroundResource(iconList.get(0));
                break;
            case 4:
                b4.setBackgroundResource(iconList.get(0));
                break;
            case 5:
                b5.setBackgroundResource(iconList.get(0));
                break;
            case 6:
                b6.setBackgroundResource(iconList.get(0));
                break;
            case 7:
                bhead.setBackgroundResource(iconList.get(0));
                break;
            case 8:
                bright.setBackgroundResource(iconList.get(0));
                break;
            case 9:
                bleft.setBackgroundResource(iconList.get(0));
                break;
            case 10:
                bbody.setBackgroundResource(iconList.get(0));
                break;
            case 11:
                bboots.setBackgroundResource(iconList.get(0));
                break;
        }
    }

    public void updateUse()
    {   //(1- glowa, 2- prawa reka, 3- lewa reka, 4 - korpus, 5 - buty, 6 - oburącz 7 - pozywienie)
        switch (itemList.get(selected).getItem_type())
        {
            case 1:
                yourAtack = itemList.get(selected).getAttack_point() + yourAtack;
                yourDefense = itemList.get(selected).getDefense_point() + yourDefense;
                break;
            case 2:
                yourAtack = itemList.get(selected).getAttack_point() + yourAtack;
                yourDefense = itemList.get(selected).getDefense_point() + yourDefense;
                break;
            case 3:
                yourAtack = itemList.get(selected).getAttack_point() + yourAtack;
                yourDefense = itemList.get(selected).getDefense_point() + yourDefense;
                break;
            case 4:
                yourAtack = itemList.get(selected).getAttack_point() + yourAtack;
                yourDefense = itemList.get(selected).getDefense_point() + yourDefense;
                break;
            case 5:
                yourAtack = itemList.get(selected).getAttack_point() + yourAtack;
                yourDefense = itemList.get(selected).getDefense_point() + yourDefense;
                break;
            case 6:
                yourAtack = itemList.get(selected).getAttack_point() + yourAtack;
                yourDefense = itemList.get(selected).getDefense_point() + yourDefense;
                break;
            case 7:
                yourAtack = itemList.get(selected).getAttack_point() + yourAtack;
                yourDefense = itemList.get(selected).getDefense_point() + yourDefense;
                if (yourHp + itemList.get(selected).getHp() <= 100)
                {
                    yourHp = itemList.get(selected).getHp() + yourHp;
                }
                else yourHp = 100;
                break;
        }
        Character hero = new Character(yourName, yourLvl, yourHp, yourExp, 0, yourAtack, yourDefense);
        tv1.setText(hero.toString());
        tv2.setText("Dodatkowe punkty obrony: " + String.valueOf(hero.getDefensepoint()));
        tv3.setText("Dodatkowe punkty ataku: " + String.valueOf(hero.getAttackpoint()));
    }

    public void update_throw()
    {   //(1- glowa, 2- prawa reka, 3- lewa reka, 4 - korpus, 5 - buty, 6 - oburącz 7 - pozywienie)
        switch (itemList.get(selected).getItem_type())
        {
            case 1:
                yourAtack = yourAtack - itemList.get(selected).getAttack_point();
                yourDefense = yourDefense - itemList.get(selected).getDefense_point();
                break;
            case 2:
                yourAtack = yourAtack - itemList.get(selected).getAttack_point();
                yourDefense = yourDefense - itemList.get(selected).getDefense_point();
                break;
            case 3:
                yourAtack = yourAtack - itemList.get(selected).getAttack_point();
                yourDefense = yourDefense - itemList.get(selected).getDefense_point();
                break;
            case 4:
                yourAtack = yourAtack - itemList.get(selected).getAttack_point();
                yourDefense = yourDefense - itemList.get(selected).getDefense_point();
                break;
            case 5:
                yourAtack = yourAtack - itemList.get(selected).getAttack_point();
                yourDefense = yourDefense - itemList.get(selected).getDefense_point();
                break;
            case 6:
                yourAtack = yourAtack - itemList.get(selected).getAttack_point();
                yourDefense = yourDefense - itemList.get(selected).getDefense_point();
                break;
        }
        Character hero = new Character(yourName, yourLvl, yourHp, yourExp, 0, yourAtack, yourDefense);
        tv1.setText(hero.toString());
        tv2.setText("Dodatkowe punkty obrony: " + String.valueOf(hero.getDefensepoint()));
        tv3.setText("Dodatkowe punkty ataku: " + String.valueOf(hero.getAttackpoint()));
    }

    public void fillBags()
    {
        if (empty[1] && items[1] != 0)
        {
            b1.setBackgroundResource(iconList.get(items[1]));
            item1 = items[1];
            empty[1] = false;
        }
        if (empty[2] && items[2] != 0)
        {
            b2.setBackgroundResource(iconList.get(items[2]));
            item2 = items[2];
            empty[2] = false;
        }
        if (empty[3] && items[3] != 0)
        {
            b3.setBackgroundResource(iconList.get(items[3]));
            item3 = items[3];
            empty[3] = false;
        }
        if (empty[4] && items[4] != 0)
        {
            b4.setBackgroundResource(iconList.get(items[4]));
            item4 = items[4];
            empty[4] = false;
        }
        if (empty[5] && items[5] != 0)
        {
            b5.setBackgroundResource(iconList.get(items[5]));
            item5 = items[5];
            empty[5] = false;
        }
        if (empty[6] && items[6] != 0)
        {
            b6.setBackgroundResource(iconList.get(items[6]));
            item6 = items[6];
            empty[6] = false;
        }
    }

    public void takeOff()
    {
        for (int i = 1; i <= 6; i++)
        {
            if (empty[i])
            {
                tv1.setText("Zdjeto rzecz");
                empty[i] = false;
                empty[checkSelection()] = true;
                items[checkSelection()] = 0;
                resetButtons();
                switch (i)
                {
                    case 1:
                        items[1] = selected;
                        item1 = selected;
                        b1.setBackgroundResource(iconList.get(selected));
                        break;
                    case 2:
                        items[2] = selected;
                        item2 = selected;
                        b2.setBackgroundResource(iconList.get(selected));
                        break;
                    case 3:
                        items[3] = selected;
                        item3 = selected;
                        b3.setBackgroundResource(iconList.get(selected));
                        break;
                    case 4:
                        items[4] = selected;
                        item4 = selected;
                        b4.setBackgroundResource(iconList.get(selected));
                        break;
                    case 5:
                        items[5] = selected;
                        item5 = selected;
                        b5.setBackgroundResource(iconList.get(selected));
                        break;
                    case 6:
                        items[6] = selected;
                        item6 = selected;
                        b6.setBackgroundResource(iconList.get(selected));
                        break;
                }
                if (itemList.get(selected).getItem_type() == 6)
                {
                    if (isSelected[8])
                    {
                        items[9] = 0;
                        empty[9] = true;
                        bleft.setBackgroundResource(iconList.get(0));
                    } else
                    {
                        items[8] = 0;
                        empty[8] = true;
                        bright.setBackgroundResource(iconList.get(0));
                    }
                }
                update_throw();
                break;
            } else tv1.setText("Pelny ekwipunek");
        }
    }

    public void setZeros()
    {
        if (isSelected[7])
        {
            itemhead = 0;
        }
        if (isSelected[8])
        {
            itemright = 0;
        }
        if (isSelected[9])
        {
            itemleft = 0;
        }
        if (isSelected[10])
        {
            itembody = 0;
        }
        if (isSelected[11])
        {
            itemboots = 0;
        }
        if (itemList.get(selected).getItem_type() == 6)
        {
            if (isSelected[8])
            {
                itemright = 0;
            } else
            {
                itemleft = 0;
            }
        }
    }

    public void fillEq()
    {
        if (items[7] != 0)
        {
            empty[7] = false;
            itemhead = items[7];
            bhead.setBackgroundResource(iconList.get(itemhead));
        }
        if (items[8] != 0)
        {
            empty[8] = false;
            itemright = items[8];
            bright.setBackgroundResource(iconList.get(itemright));
            if (itemList.get(itemright).getItem_type() == 6)
            {
                empty[9] = false;
                itemleft = items[9];
                bleft.setBackgroundResource(iconList.get(itemleft));
            }
        }
        if (items[9] != 0)
        {
            empty[9] = false;
            itemleft = items[9];
            bleft.setBackgroundResource(iconList.get(itemleft));
            if (itemList.get(itemleft).getItem_type() == 6)
            {
                empty[8] = false;
                itemright = items[8];
                bright.setBackgroundResource(iconList.get(itemright));
            }
        }
        if (items[10] != 0)
        {
            empty[10] = false;
            itembody = items[10];
            bbody.setBackgroundResource(iconList.get(itembody));
        }
        if (items[11] != 0)
        {
            empty[11] = false;
            itemboots = items[11];
            bboots.setBackgroundResource(iconList.get(itemboots));
        }
    }

    private void deleteItem(int indexOfItem)
    {
        for (int i=1; i<=6; i++)
        {
            if(items[i] == indexOfItem)
            {
                items[i] = 0;
                empty[i] = true;
                switch (i)
                {
                    case 1:
                        b1.setBackgroundResource(iconList.get(0));
                        break;
                    case 2:
                        b2.setBackgroundResource(iconList.get(0));
                        break;
                    case 3:
                        b3.setBackgroundResource(iconList.get(0));
                        break;
                    case 4:
                        b4.setBackgroundResource(iconList.get(0));
                        break;
                    case 5:
                        b5.setBackgroundResource(iconList.get(0));
                        break;
                    case 6:
                        b6.setBackgroundResource(iconList.get(0));
                        break;
                }
                break;
            }
        }
    }

    private void addItem(int indexOfItem)
    {
        for (int i=1; i<=6; i++)
        {
            if (empty[i] == true)
            {
                switch (i)
                {
                    case 1:
                        b1.setBackgroundResource(iconList.get(indexOfItem));
                        break;
                    case 2:
                        b2.setBackgroundResource(iconList.get(indexOfItem));
                        break;
                    case 3:
                        b3.setBackgroundResource(iconList.get(indexOfItem));
                        break;
                    case 4:
                        b4.setBackgroundResource(iconList.get(indexOfItem));
                        break;
                    case 5:
                        b5.setBackgroundResource(iconList.get(indexOfItem));
                        break;
                    case 6:
                        b6.setBackgroundResource(iconList.get(indexOfItem));
                        break;
                }
                items[i] = indexOfItem;
                empty[i] = false;
                break;
            }
        }
    }

    public void onClickAddFish(View view) //temporary button for tests
    {
        meatAmount = 10;
        isFishMeat = true;
        addItem(15);
    }

}

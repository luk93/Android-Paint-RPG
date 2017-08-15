package com.example.lukasz.gra2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class WeaponMarket extends AppCompatActivity
{
    ScrollView sv;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    ArrayList<Item> itemList = new ArrayList<>();
    ArrayList<Integer>iconList = new ArrayList<>();
    Button bAction, bExit, b1, b2, b3, b4, b5, b6;
    MediaPlayer soundMarket, musicMarket, soundNegative, soundPositive;
    int items[] = new int[7];
    boolean isSelected[] = new boolean[13];
    boolean empty[] = new boolean[7];
    boolean isSelectedRB[] = new boolean[7];
    boolean isFishMeat, isCookedMeat = false;
    int meatAmount, cookedMeatAmount, goldAmount, item1, item2, item3, item4, item5, item6, selectedItemId = 0;
    int itemToBuy[] = new int[6];
    int sellingPrize, buyingPrize;
    TextView goldText, tvMarket;
    ImageView imageOneHand, imageTwoHands, imageShield, imageArmor, imageBoots, bg, imageHead;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_market);

        emptyAll();
        //lista przedmiotów
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
        itemList.add(new Item("żelazne buty", 5, 0, 0, 12, 0));             //24
        itemList.add(new Item("wzmacniany puklerz", 3, 0, 0, 12, 0));    //25
        itemList.add(new Item("kolczuga", 4, 0, 0, 12, 0));             //26
        itemList.add(new Item("topór obosieczny", 6, 0, 0, 0, 20));             //27
        itemList.add(new Item("potrawka", 7, 0, 0, 0, 0, 15));        //28
        itemList.add(new Item("żelazny hełm", 1, 0, 0, 8, 0));             //29
        //6
        itemList.add(new Item("ostra katana", 2, 0, 0, 0, 15));       //30
        itemList.add(new Item("stalowe buty", 5, 0, 0, 15, 0));             //31
        itemList.add(new Item("stalowa tarcza", 3, 0, 0, 15, 0));    //32
        itemList.add(new Item("stalowa zbroja", 4, 0, 0, 15, 0));             //33
        itemList.add(new Item("stalowy claymore", 6, 0, 0, 0, 23));             //34
        itemList.add(new Item("duża porcja potrawki", 7, 0, 0, 0, 0, 18));        //35
        itemList.add(new Item("stalowy hełm", 1, 0, 0, 15, 0));             //36
        itemList.add(new Item("smażone rybie mięso",7, 0, 0, 0, 0, 1));          //37

        //lista ikon
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

        goldText = (TextView)findViewById(R.id.goldText);
        tvMarket = (TextView)findViewById(R.id.textViewMarket);

        rg = (RadioGroup)findViewById(R.id.itemListRG);
        sv = (ScrollView)findViewById(R.id.scroll);
        rb1 = (RadioButton)findViewById(R.id.radioButton1);
        rb2 = (RadioButton)findViewById(R.id.radioButton2);
        rb3 = (RadioButton)findViewById(R.id.radioButton3);
        rb4 = (RadioButton)findViewById(R.id.radioButton4);
        rb5 = (RadioButton)findViewById(R.id.radioButton5);

        imageOneHand = (ImageView)findViewById(R.id.imageOneHand);
        imageTwoHands = (ImageView)findViewById(R.id.imageTwoHands);
        imageShield = (ImageView)findViewById(R.id.imageShield);
        imageArmor = (ImageView)findViewById(R.id.imageArmor);
        imageBoots = (ImageView)findViewById(R.id.imageBoots);
        imageHead = (ImageView)findViewById(R.id.imageHead);
        bg = (ImageView)findViewById(R.id.bg_market);

        bAction = (Button)findViewById(R.id.buttonAction);
        bExit = (Button)findViewById(R.id.buttonExit);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        b5 = (Button)findViewById(R.id.button5);
        b6 = (Button)findViewById(R.id.button6);

        soundMarket = MediaPlayer.create(getApplicationContext(), R.raw.trade);
        soundNegative = MediaPlayer.create(getApplicationContext(), R.raw.trade2);
        soundPositive = MediaPlayer.create(getApplicationContext(), R.raw.gold_sound);
        musicMarket = MediaPlayer.create(getApplicationContext(),R.raw.weapon_market);
        musicMarket.setLooping(true);
        musicMarket.start();
        soundMarket.start();

        sv.setVisibility(View.INVISIBLE);

        Bundle extra = getIntent().getExtras();
        items[1] = extra.getInt("drop1");
        items[2] = extra.getInt("drop2");
        items[3] = extra.getInt("drop3");
        items[4] = extra.getInt("drop4");
        items[5] = extra.getInt("drop5");
        items[6] = extra.getInt("drop6");

        meatAmount = extra.getInt("meatAmount");
        isFishMeat = extra.getBoolean("isFishMeat");
        goldAmount = extra.getInt("goldAmount");
        cookedMeatAmount = extra.getInt("cookedMeatAmount");
        isCookedMeat = extra.getBoolean("isCookedMeat");

        goldText.setText(String.valueOf(goldAmount));
        bAction.setText("AKCJA");
        setNoSelection();
        setNoSelectionRB();
        fillBags();

        imageOneHand.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (isSelected[1] == false)
                {
                    bAction.setText("AKCJA");
                    rg.clearCheck();
                    tvMarket.setText("Wybierz broń jednoreczną");
                    resetSelectionAndImages();
                    imageOneHand.setImageResource(R.drawable.one_h_market_active);
                    soundMarket.start();
                    isSelected [1] = true;
                    fillListOneHand();
                }
            }
        });
        imageTwoHands.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (isSelected[2] == false)
                {
                    bAction.setText("AKCJA");
                    rg.clearCheck();
                    tvMarket.setText("Wybierz broń dwureczną");
                    resetSelectionAndImages();
                    imageTwoHands.setImageResource(R.drawable.two_h_market_active);
                    soundMarket.start();
                    isSelected [2] = true;
                    fillListTwoHands();
                }
            }
        });
        imageArmor.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (isSelected[3] == false)
                {
                    bAction.setText("AKCJA");
                    rg.clearCheck();
                    tvMarket.setText("Wybierz pancerz");
                    resetSelectionAndImages();
                    imageArmor.setImageResource(R.drawable.armor_market_active);
                    soundMarket.start();
                    isSelected[3] = true;
                    fillListArmor();
                }
            }
        });
        imageBoots.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (isSelected[4] == false)
                {
                    bAction.setText("AKCJA");
                    rg.clearCheck();
                    tvMarket.setText("Wybierz obuwie");
                    resetSelectionAndImages();
                    imageBoots.setImageResource(R.drawable.boots_market_active);
                    soundMarket.start();
                    isSelected[4] = true;
                    fillListBoots();
                }
            }
        });
        imageShield.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (isSelected[5] == false)
                {
                    bAction.setText("AKCJA");
                    rg.clearCheck();
                    tvMarket.setText("Wybierz rzecz do drugiej ręki");
                    resetSelectionAndImages();
                    imageShield.setImageResource(R.drawable.shield_market_active);
                    soundMarket.start();
                    isSelected[5] = true;
                    fillListShield();
                }
            }
        });
        imageHead.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (isSelected[6] == false)
                {
                    bAction.setText("AKCJA");
                    rg.clearCheck();
                    tvMarket.setText("Wybierz nakrycie głowy");
                    resetSelectionAndImages();
                    imageHead.setImageResource(R.drawable.head_market_active);
                    soundMarket.start();
                    isSelected [6] = true;
                    fillListHead();
                }
            }
        });
        rb1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bAction.setText("KUP");
                isSelectedRB[1]= true;
                selectedItemId = itemToBuy[1];
                buyingPrize = countBuyingPrize();
                tvMarket.setText(itemList.get(itemToBuy[1]).toString()+"\nCena: "+buyingPrize+" zlotych monet");
            }
        });
        rb2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bAction.setText("KUP");
                isSelectedRB[2] = true;
                selectedItemId = itemToBuy[2];
                buyingPrize = countBuyingPrize();
                tvMarket.setText(itemList.get(itemToBuy[2]).toString()+"\nCena: "+buyingPrize+" zlotych monet");
            }
        });
        rb3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bAction.setText("KUP");
                isSelectedRB[3] = true;
                selectedItemId = itemToBuy[3];
                buyingPrize = countBuyingPrize();
                tvMarket.setText(itemList.get(itemToBuy[3]).toString()+"\nCena: "+buyingPrize+" zlotych monet");
            }
        });
        rb4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bAction.setText("KUP");
                isSelectedRB[4] = true;
                selectedItemId = itemToBuy[4];
                buyingPrize = countBuyingPrize();
                tvMarket.setText(itemList.get(itemToBuy[4]).toString()+"\nCena: "+buyingPrize+" zlotych monet");
            }
        });
        rb5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bAction.setText("KUP");
                isSelectedRB [5] = true;
                selectedItemId = itemToBuy[5];
                buyingPrize = countBuyingPrize();
                tvMarket.setText(itemList.get(itemToBuy[5]).toString()+"\nCena: "+buyingPrize+" zlotych monet");
            }
        });
    }
    public void onClick1(View view)
    {
        bAction.setText("SPRZEDAJ");
        resetSelectionAndImages();
        sv.setVisibility(View.INVISIBLE);
        b1.setScaleX((float) 1.5);
        b1.setScaleY((float) 1.5);
        isSelected[7] = true;
        if (empty[1])
        {
            tvMarket.setText("Slot 1 pusty");
            selectedItemId = 0;
        }
        else
        {
            tvMarket.setText(itemList.get(item1).toString());
            selectedItemId = item1;
        }
    }
    public void onClick2(View view)
    {
        bAction.setText("SPRZEDAJ");
        resetSelectionAndImages();
        sv.setVisibility(View.INVISIBLE);
        b2.setScaleX((float) 1.5);
        b2.setScaleY((float) 1.5);
        isSelected[8] = true;
        if (empty[2])
        {
            tvMarket.setText("Slot 2 pusty");
            selectedItemId = 0;
        }
        else
        {
            tvMarket.setText(itemList.get(item2).toString());
            selectedItemId = item2;
        }
    }
    public void onClick3(View view)
    {
        bAction.setText("SPRZEDAJ");
        resetSelectionAndImages();
        sv.setVisibility(View.INVISIBLE);
        b3.setScaleX((float) 1.5);
        b3.setScaleY((float) 1.5);
        isSelected[9] = true;
        if (empty[3])
        {
            tvMarket.setText("Slot 3 pusty");
            selectedItemId = 0;
        }
        else
        {
            tvMarket.setText(itemList.get(item3).toString());
            selectedItemId = item3;
        }
    }
    public void onClick4(View view)
    {
        bAction.setText("SPRZEDAJ");
        resetSelectionAndImages();
        sv.setVisibility(View.INVISIBLE);
        b4.setScaleX((float) 1.5);
        b4.setScaleY((float) 1.5);
        isSelected[10] = true;
        if (empty[4])
        {
            tvMarket.setText("Slot 4 pusty");
            selectedItemId = 0;
        }
        else
        {
            tvMarket.setText(itemList.get(item4).toString());
            selectedItemId = item4;
        }
    }
    public void onClick5(View view)
    {
        bAction.setText("SPRZEDAJ");
        resetSelectionAndImages();
        sv.setVisibility(View.INVISIBLE);
        b5.setScaleX((float) 1.5);
        b5.setScaleY((float) 1.5);
        isSelected[11] = true;
        if (empty[5])
        {
            tvMarket.setText("Slot 5 pusty");
            selectedItemId = 0;
        }
        else
        {
            tvMarket.setText(itemList.get(item5).toString());
            selectedItemId = item5;
        }
    }
    public void onClick6(View view)
    {
        bAction.setText("SPRZEDAJ");
        resetSelectionAndImages();
        sv.setVisibility(View.INVISIBLE);
        b6.setScaleX((float) 1.5);
        b6.setScaleY((float) 1.5);
        isSelected[12] = true;
        if (empty[6])
        {
            tvMarket.setText("Slot 6 pusty");
            selectedItemId = 0;
        }
        else
        {
            tvMarket.setText(itemList.get(item6).toString());
            selectedItemId = item6;
        }
    }
    protected void onPause()
    {
        super.onPause();
        if (soundMarket != null)
        {
            soundMarket.release();
            soundMarket = null;
        }
        if (soundNegative != null)
        {
            soundNegative.release();
            soundNegative = null;
        }
        if (soundPositive != null)
        {
            soundPositive.release();
            soundPositive = null;
        }
        if (musicMarket != null && musicMarket.isPlaying())
        {
            musicMarket.stop();
            musicMarket.reset();
            musicMarket.release();
            musicMarket = null;
        }
    }
    private void setNoSelection()
    {
        for (int i = 1; i<=6; i++)
        {
            isSelected[i] = false;
        }
    }
    private void setNoSelectionRB()
    {
        for (int i = 1; i<=6; i++)
        {
            isSelectedRB[i] = false;
        }
    }
    private void emptyAll()
    {
        for (int i=1; i<=6; i++)
        {
            empty[i] = true;
        }
    }
    private void resetSelectionAndImages()
    {
        sv.setVisibility(View.VISIBLE);
        for (int i = 1; i<=12; i++)
        {
            if (isSelected[i])
            {
                isSelected[i] = false;
                switch (i)
                {
                    case 1:
                        imageOneHand.setImageResource(R.drawable.one_h_market);
                        break;
                    case 2:
                        imageTwoHands.setImageResource(R.drawable.two_h_market);
                        break;
                    case 3:
                        imageArmor.setImageResource(R.drawable.armor_market);
                        break;
                    case 4:
                        imageBoots.setImageResource(R.drawable.boots_market);
                        break;
                    case 5:
                        imageShield.setImageResource(R.drawable.shield_market);
                        break;
                    case 6:
                        imageHead.setImageResource(R.drawable.head_market);
                        break;
                    default:
                        resetIcons();
                        break;
                }
            }
        }
        for (int j = 1; j<=5; j++)
        {
            if (isSelectedRB[j])
            {
                isSelectedRB[j] = false;
            }
        }
    }
    public void onExit(View view)
    {
        Intent marketOver = new Intent();

        marketOver.putExtra("drop1", items[1]);
        marketOver.putExtra("drop2", items[2]);
        marketOver.putExtra("drop3", items[3]);
        marketOver.putExtra("drop4", items[4]);
        marketOver.putExtra("drop5", items[5]);
        marketOver.putExtra("drop6", items[6]);

        marketOver.putExtra("meatAmount", meatAmount);
        marketOver.putExtra("isFishMeat", isFishMeat);
        marketOver.putExtra("goldAmount", goldAmount);
        marketOver.putExtra("cookedMeatAmount", cookedMeatAmount);
        marketOver.putExtra("isCookedMeat", isCookedMeat);

        setResult(RESULT_OK, marketOver);
        finish();
    }
    public void onAction(View view)
    {
        if(isSelectedRB[1]||isSelectedRB[2]||isSelectedRB[3]||isSelectedRB[4]||isSelectedRB[5])
        {
            buyingPrize = countBuyingPrize();
            buyItem();
        }
        else if(isSelected[7]||isSelected[8]||isSelected[9]||isSelected[10]||isSelected[11]||isSelected[12])
        {
            if (selectedItemId != 6 && selectedItemId != 13 && selectedItemId != 15 && selectedItemId != 21 && selectedItemId != 28 && selectedItemId != 35 && selectedItemId != 37 ) //buyer doesnt want any food
            {
                sellItem();
            }
            else
            {
                tvMarket.setText("Nie handluję pożywieniem, a w tej chwili nie jestem głodny");
                soundNegative.start();
            }
        }
    }
    private void fillListHead()
    {
        itemToBuy[1] = 7;
        itemToBuy[2] = 14;
        itemToBuy[3] = 22;
        itemToBuy[4] = 29;
        itemToBuy[5] = 36;
        fillList();
    }
    private void fillListArmor()
    {
        itemToBuy[1] = 4;
        itemToBuy[2] = 11;
        itemToBuy[3] = 19;
        itemToBuy[4] = 26;
        itemToBuy[5] = 33;
        fillList();
    }
    private void fillListOneHand()
    {
        itemToBuy[1] = 1;
        itemToBuy[2] = 8;
        itemToBuy[3] = 16;
        itemToBuy[4] = 23;
        itemToBuy[5] = 30;
        fillList();
    }
    private void fillListTwoHands()
    {
        itemToBuy[1] = 5;
        itemToBuy[2] = 12;
        itemToBuy[3] = 20;
        itemToBuy[4] = 27;
        itemToBuy[5] = 34;
        fillList();
    }
    private void fillListShield()
    {
        itemToBuy[1] = 3;
        itemToBuy[2] = 10;
        itemToBuy[3] = 18;
        itemToBuy[4] = 25;
        itemToBuy[5] = 32;
        fillList();
    }
    private void fillListBoots()
    {
        itemToBuy[1] = 2;
        itemToBuy[2] = 9;
        itemToBuy[3] = 17;
        itemToBuy[4] = 24;
        itemToBuy[5] = 31;
        fillList();
    }
    private void resetIcons()
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
    private void fillList()
    {
        if (isSelected[1]||isSelected[2])
        {
            rb1.setText(itemList.get(itemToBuy[1]).getName() + ", atak: " + itemList.get(itemToBuy[1]).getAttack_point());
            rb2.setText(itemList.get(itemToBuy[2]).getName() + ", atak: " + itemList.get(itemToBuy[2]).getAttack_point());
            rb3.setText(itemList.get(itemToBuy[3]).getName() + ", atak: " + itemList.get(itemToBuy[3]).getAttack_point());
            rb4.setText(itemList.get(itemToBuy[4]).getName() + ", atak: " + itemList.get(itemToBuy[4]).getAttack_point());
            rb5.setText(itemList.get(itemToBuy[5]).getName() + ", atak: " + itemList.get(itemToBuy[5]).getAttack_point());
        }
        else
        {
            rb1.setText(itemList.get(itemToBuy[1]).getName() + ", obrona: " + itemList.get(itemToBuy[1]).getDefense_point());
            rb2.setText(itemList.get(itemToBuy[2]).getName() + ", obrona: " + itemList.get(itemToBuy[2]).getDefense_point());
            rb3.setText(itemList.get(itemToBuy[3]).getName() + ", obrona: " + itemList.get(itemToBuy[3]).getDefense_point());
            rb4.setText(itemList.get(itemToBuy[4]).getName() + ", obrona: " + itemList.get(itemToBuy[4]).getDefense_point());
            rb5.setText(itemList.get(itemToBuy[5]).getName() + ", obrona: " + itemList.get(itemToBuy[5]).getDefense_point());
        }
    }
    private int countBuyingPrize()
    {
        if(selectedItemId >= 1&& selectedItemId <= 7)
        {
            return 5;
        }
        if(selectedItemId >= 8 && selectedItemId <= 14)
        {
            return 10;
        }
        if(selectedItemId >= 16 && selectedItemId <= 22)
        {
            return 15;
        }
        if(selectedItemId >= 23 && selectedItemId <= 29)
        {
            return 20;
        }
        if(selectedItemId >= 30 && selectedItemId <= 37)
        {
            return 25;
        }
        else return 0;
    }
    private int countSellingPrize()
    {
        if(selectedItemId >= 1&& selectedItemId <= 7)
        {
            return 1;
        }
        if(selectedItemId >= 8 && selectedItemId <= 14)
        {
            return 6;
        }
        if(selectedItemId >= 16 && selectedItemId <= 22)
        {
            return 11;
        }
        if(selectedItemId >= 23 && selectedItemId <= 29)
        {
            return 16;
        }
        if(selectedItemId >= 30 && selectedItemId <= 37)
        {
            return 21;
        }
        else return 0;
    }
    private void sellItem()
    {
        int selectedSlot = 0;
        if (selectedItemId != 0)
        {
            sellingPrize = countSellingPrize();
            goldAmount = goldAmount + sellingPrize;
            tvMarket.setText("Sprzedano " + itemList.get(selectedItemId).getName() + " za " + sellingPrize+" zlotych monet");
            goldText.setText(String.valueOf(goldAmount));
            soundPositive.start();
            selectedItemId = 0;
            for (int i = 7; i <= 12; i++) //set index of selected button
            {
                if (isSelected[i] == true)
                {
                    selectedSlot = i;
                }
            }
            switch (selectedSlot)
            {
                case 7:
                    b1.setBackgroundResource(iconList.get(0));
                    item1 = 0;
                    empty[1] = true;
                    items[1] = 0;
                    break;
                case 8:
                    b2.setBackgroundResource(iconList.get(0));
                    item2 = 0;
                    empty[2] = true;
                    items[2] = 0;
                    break;
                case 9:
                    b3.setBackgroundResource(iconList.get(0));
                    item3 = 0;
                    empty[3] = true;
                    items[3] = 0;
                    break;
                case 10:
                    b4.setBackgroundResource(iconList.get(0));
                    item4 = 0;
                    empty[4] = true;
                    items[4] = 0;
                    break;
                case 11:
                    b5.setBackgroundResource(iconList.get(0));
                    item5 = 0;
                    empty[5] = true;
                    items[5] = 0;
                    break;
                case 12:
                    b6.setBackgroundResource(iconList.get(0));
                    item6 = 0;
                    empty[6] = true;
                    items[6] = 0;
                    break;
            }
        }
    }
    private void buyItem()
    {
        if (buyingPrize <= goldAmount)
        {
            for (int i = 1; i <= 6; i++)
            {
                if (empty[i] && items[i] == 0)
                {
                    switch (i)
                    {
                        case 1:
                            b1.setBackgroundResource(iconList.get(selectedItemId));
                            item1 = selectedItemId;
                            empty[1] = false;
                            break;
                        case 2:
                            b2.setBackgroundResource(iconList.get(selectedItemId));
                            item2 = selectedItemId;
                            empty[2] = false;
                            break;
                        case 3:
                            b3.setBackgroundResource(iconList.get(selectedItemId));
                            item3 = selectedItemId;
                            empty[3] = false;
                            break;
                        case 4:
                            b4.setBackgroundResource(iconList.get(selectedItemId));
                            item4 = selectedItemId;
                            empty[4] = false;
                            break;
                        case 5:
                            b5.setBackgroundResource(iconList.get(selectedItemId));
                            item5 = selectedItemId;
                            empty[5] = false;
                            break;
                        case 6:
                            b6.setBackgroundResource(iconList.get(selectedItemId));
                            item6 = selectedItemId;
                            empty[6] = false;
                            break;
                    }
                    items[i] = selectedItemId;
                    goldAmount = goldAmount - buyingPrize;
                    tvMarket.setText("Kupiono " + itemList.get(selectedItemId).getName() + " za " + buyingPrize+" zlotych monet.");
                    goldText.setText(String.valueOf(goldAmount));
                    soundPositive.start();
                    break;
                }
                else
                {
                    tvMarket.setText("Pełny ekwipunek.");
                    soundNegative.start();
                }
            }
        }
        else
        {
            tvMarket.setText("Nie masz wystarczającej ilości złota.");
            soundNegative.start();
        }
    }
}

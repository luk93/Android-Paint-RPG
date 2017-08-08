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
    Button bAction, bExit;
    MediaPlayer soundMarket, musicMarket, soundMarket2;
    int items[] = new int[7];
    boolean isSelected[] = new boolean[7];
    boolean isFishMeat, isCookedMeat = false;
    int meatAmount, cookedMeatAmount, goldAmount;
    TextView goldText, tvMarket;
    ImageView imageOneHand, imageTwoHands, imageShield, imageArmor, imageBoots;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_market);
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

        bAction = (Button)findViewById(R.id.buttonAction);
        bExit = (Button)findViewById(R.id.buttonExit);

        soundMarket = MediaPlayer.create(getApplicationContext(), R.raw.trade);
        soundMarket2 = MediaPlayer.create(getApplicationContext(), R.raw.trade2);
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
        setNoSelection();

        imageOneHand.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                if (isSelected[1] == false)
                {
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
                    resetSelectionAndImages();
                    imageShield.setImageResource(R.drawable.shield_market_active);
                    soundMarket.start();
                    isSelected[5] = true;
                    fillListShield();
                }
            }
        });

    }
    protected void onPause()
    {
        super.onPause();
        if (soundMarket != null)
        {
            soundMarket.release();
            soundMarket = null;
        }
        if (soundMarket2 != null)
        {
            soundMarket2.release();
            soundMarket2 = null;
        }
        if (musicMarket != null && musicMarket.isPlaying())
        {
            musicMarket.stop();
            musicMarket.reset();
            musicMarket.release();
            musicMarket = null;
        }
    }
    public void onClickExit(View view)
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
    private void setNoSelection()
    {
        for (int i = 1; i<=6; i++)
        {
            isSelected[i] = false;
        }
    }
    private void resetSelectionAndImages()
    {
        sv.setVisibility(View.VISIBLE);
        for (int i = 1; i<=6; i++)
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
                }
            }
        }
    }
    public void onExit(View view)
    {
        finish();
    }
    public void onAction(View view)
    {

    }
    private void fillListHead()
    {
        rb1.setText(itemList.get(7).getName()+" obrona: "+itemList.get(7).getDefense_point());
        rb2.setText(itemList.get(14).getName()+" obrona: "+itemList.get(14).getDefense_point());
        rb3.setText(itemList.get(22).getName()+" obrona: "+itemList.get(22).getDefense_point());
        rb4.setText(itemList.get(29).getName()+" obrona: "+itemList.get(29).getDefense_point());
        rb5.setText(itemList.get(36).getName()+" obrona: "+itemList.get(36).getDefense_point());
    }
    private void fillListArmor()
    {
        rb1.setText(itemList.get(4).getName()+" obrona: "+itemList.get(4).getDefense_point());
        rb2.setText(itemList.get(11).getName()+" obrona: "+itemList.get(11).getDefense_point());
        rb3.setText(itemList.get(19).getName()+" obrona: "+itemList.get(19).getDefense_point());
        rb4.setText(itemList.get(26).getName()+" obrona: "+itemList.get(26).getDefense_point());
        rb5.setText(itemList.get(33).getName()+" obrona: "+itemList.get(33).getDefense_point());
    }
    private void fillListOneHand()
    {
        rb1.setText(itemList.get(1).getName()+" atak: "+itemList.get(1).getAttack_point());
        rb2.setText(itemList.get(8).getName()+" atak: "+itemList.get(8).getAttack_point());
        rb3.setText(itemList.get(16).getName()+" atak: "+itemList.get(16).getAttack_point());
        rb4.setText(itemList.get(23).getName()+" atak: "+itemList.get(23).getAttack_point());
        rb5.setText(itemList.get(30).getName()+" atak: "+itemList.get(30).getAttack_point());
    }
    private void fillListTwoHands()
    {
        rb1.setText(itemList.get(5).getName()+" obrona: "+itemList.get(5).getAttack_point());
        rb2.setText(itemList.get(12).getName()+" obrona: "+itemList.get(12).getAttack_point());
        rb3.setText(itemList.get(20).getName()+" obrona: "+itemList.get(20).getAttack_point());
        rb4.setText(itemList.get(27).getName()+" obrona: "+itemList.get(27).getAttack_point());
        rb5.setText(itemList.get(34).getName()+" obrona: "+itemList.get(34).getAttack_point());
    }
    private void fillListShield()
    {
        rb1.setText(itemList.get(3).getName()+" obrona: "+itemList.get(3).getDefense_point());
        rb2.setText(itemList.get(10).getName()+" obrona: "+itemList.get(10).getDefense_point());
        rb3.setText(itemList.get(18).getName()+" obrona: "+itemList.get(18).getDefense_point());
        rb4.setText(itemList.get(25).getName()+" obrona: "+itemList.get(25).getDefense_point());
        rb5.setText(itemList.get(32).getName()+" obrona: "+itemList.get(32).getDefense_point());
    }
    private void fillListBoots()
    {
        rb1.setText(itemList.get(2).getName()+" obrona: "+itemList.get(2).getDefense_point());
        rb2.setText(itemList.get(9).getName()+" obrona: "+itemList.get(9).getDefense_point());
        rb3.setText(itemList.get(17).getName()+" obrona: "+itemList.get(17).getDefense_point());
        rb4.setText(itemList.get(24).getName()+" obrona: "+itemList.get(24).getDefense_point());
        rb5.setText(itemList.get(31).getName()+" obrona: "+itemList.get(31).getDefense_point());
    }
}

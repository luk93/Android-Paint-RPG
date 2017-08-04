package com.example.lukasz.gra2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WeaponMarket extends AppCompatActivity
{
    MediaPlayer soundMarket, musicMarket;
    int items[] = new int[7];
    boolean isSelected[] = new boolean[7];
    boolean isFishMeat, isCookedMeat = false;
    int meatAmount, cookedMeatAmount, goldAmount;
    TextView goldText;
    ImageView imageOneHand, imageTwoHands, imageShield, imageArmor, imageBoots;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_market);

        goldText = (TextView)findViewById(R.id.goldText);
        imageOneHand = (ImageView)findViewById(R.id.imageOneHand);
        imageTwoHands = (ImageView)findViewById(R.id.imageTwoHands);
        imageShield = (ImageView)findViewById(R.id.imageShield);
        imageArmor = (ImageView)findViewById(R.id.imageArmor);
        imageBoots = (ImageView)findViewById(R.id.imageBoots);

        soundMarket = MediaPlayer.create(getApplicationContext(), R.raw.trade);
        musicMarket = MediaPlayer.create(getApplicationContext(),R.raw.weapon_market);
        musicMarket.setLooping(true);
        musicMarket.start();
        soundMarket.start();

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
                    isSelected [1] = true;
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
                    isSelected [2] = true;
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
                    isSelected[3] = true;
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
                    isSelected[4] = true;
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
                    isSelected[5] = true;
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


}

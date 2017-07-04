package com.example.lukasz.gra2;

/**
 * Created by Lukasz on 2017-05-20.
 */

public class Item
{
    private int defense_point, attack_point, class_type, lvl_req, item_type, hp;
    private String name;
    String klasa, typ;

    public Item()
    {
        name = "Item";
        defense_point = 0;
        attack_point = 0;
        class_type = 0;
        lvl_req = 0;
    }

    public int getHp()
    {
        return hp;
    }

    public void setHp(int hp)
    {
        this.hp = hp;
    }

    public Item(String name, int item_type, int class_type, int lvl_req, int defense_point, int attack_point)
    {
        this.item_type = item_type;
        this.name = name;
        this.class_type = class_type;
        this.lvl_req = lvl_req;
        this.defense_point = defense_point;
        this.attack_point = attack_point;
        hp = 0;

    }

    public int getItem_type()
    {
        return item_type;
    }

    public Item(String name, int item_type, int class_type, int lvl_req, int defense_point, int attack_point, int hp)
    {
        this.item_type = item_type;
        this.name = name;
        this.class_type = class_type;
        this.lvl_req = lvl_req;
        this.defense_point = defense_point;
        this.attack_point = attack_point;
        this.hp = hp;
    }

    public int getClass_type()
    {
        return class_type;
    }

    public void setClass_type(int class_type)
    {
        this.class_type = class_type;
    }

    public int getLvl_req()
    {
        return lvl_req;
    }

    public void setLvl_req(int lvl_req)
    {
        this.lvl_req = lvl_req;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAttack_point()
    {

        return attack_point;
    }

    public void setAttack_point(int attack_point)
    {
        this.attack_point = attack_point;
    }

    public int getDefense_point()
    {

        return defense_point;
    }

    public void setDefense_point(int defense_point)
    {
        this.defense_point = defense_point;
    }

    public String toString()
    {
        switch (class_type)
        {
            case 0:
                klasa = "Brak";
                break;
            case 1:
                klasa = "Wojownik";
                break;
            case 2:
                klasa = "Mag";
                break;
        }
        switch (item_type)
        {
            case 1:
                typ = "na głowę";
                break;
            case 2:
                typ = "do prawej ręki";
                break;
            case 3:
                typ = "do lewej ręki";
                break;
            case 4:
                typ = "na korpus";
                break;
            case 5:
                typ = "na nogi";
                break;
            case 6:
                typ = "broń oburęczna";
                break;
            case 7:
                typ = "pożywienie";
                break;
        }
        if (item_type == 7)
        {
            return (name + ", wymagany lvl: " + lvl_req + ", wymagana klasa: " + klasa + ", rodzaj rzeczy: " + typ+ ", zwraca: "+hp+ " hp");
        }
        else return (name + ", wymagany lvl: " + lvl_req + ", wymagana klasa: " + klasa + ", rodzaj rzeczy: " + typ +", obrażenia: "+attack_point +", obrona: "+defense_point);
    }
}

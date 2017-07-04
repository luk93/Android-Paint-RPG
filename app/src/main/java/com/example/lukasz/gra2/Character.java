package com.example.lukasz.gra2;
public class Character
{

    private int lvl,hp, opponentId, exp, attack_point, defense_point;
    private String name;
    public Character()
    {
        name = "domyslna";
        lvl = 0;
        hp = 0;
        exp = 0;
        opponentId = 5;
        attack_point = 0;
        defense_point = 0;
    }
    public Character(String name, int lvl, int hp, int exp, int opponentId, int attack_point, int defense_point)
    {
        this.name = name;
        this.lvl=lvl;
        this.hp=hp;
        this.exp=exp;
        this.opponentId = opponentId;
        this.defense_point = defense_point;
        this.attack_point = attack_point;
    }

    public int getAttackpoint()
    {
        return attack_point;
    }

    public void setAttackpoint(int attack_point)
    {
        this.attack_point = attack_point;
    }

    public int getDefensepoint()
    {
        return defense_point;
    }

    public void setDefensepoint(int defense_point)
    {
        this.defense_point = defense_point;
    }

    public Character(String name, int lvl, int hp, int exp, int opponentId)
    {
        this.name = name;
        this.lvl=lvl;
        this.hp=hp;
        this.exp=exp;
        this.opponentId = opponentId;
        defense_point = 0;
        attack_point = 0;
    }


    public void setName(String name)
    {
        this.name =name;
    }
    public void setLvl(int lvl)
    {
        this.lvl=lvl;
    }
    public void minusHp(int hp)
    {
        this.hp-=hp;
    }
    public void plushp(int hp)
    {
        this.hp+=hp;
    }
    public void addexp(int exp)
    {
        this.exp+=exp;
    }
    public void setOpponentId(int opponentId)
    {
        this.opponentId =opponentId;
    }
    public int getlvl()
    {
        return lvl;
    }
    public int gethp()
    {
        return hp;
    }
    public int getexp()
    {
        return exp;
    }
    public int getOpponentId()
    {
        return opponentId;
    }
    public String getName()
    {
        return name;
    }
    public void dead()
    {
        hp=0;
    }
    public String toString()
    {
        return (name + " lvl: "+lvl+" HP: "+hp+ " EXP: "+exp);
    }



}
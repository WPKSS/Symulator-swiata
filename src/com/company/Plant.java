package com.company;

import javafx.util.Pair;

public class Plant extends Organism{
    public Plant(char letter, int strength, int x, int y, World world)
    {
        super(letter,strength,0,x,y,world);
    }
    @Override
    public void action()
    {
        breed();
    }
    @Override
    protected void breed()
    {
        if((int)(Math.random() * 100) == 2)
        {
            Pair<Integer, Integer> coordinates = world.findFreeArea(this);
            if(coordinates.getKey() == -1 && coordinates.getValue() == -1)
            {
                return;
            }
            Organism child = this.child();
            child.setPosition(coordinates.getKey(),coordinates.getValue());
            world.addOrganism(child);
            world.addLog(this.print() + " has breed");
        }
    }
    public void addBuff(Organism attacker){}
    @Override
    public void collision(Organism attacker)
    {
        attacker.setPosition(this.getX(),this.getY());
        this.addBuff(attacker);
        kill(this);
        world.addLog(this.print() + " has been stepped on by " + attacker.print());
    }
    @Override
    public String print()
    {
        String toPrint = "Plant [" + this.getX() + "," + this.getY() + "]";
        return toPrint;
    }
}

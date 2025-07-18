package com.company;

public class Turtle extends Animal{
    public Turtle(int x, int y, World world)
    {
        super('T',2,1,x,y,world);
    }
    @Override
    public Turtle child()
    {
        return new Turtle(this.getX(),this.getY(),world);
    }
    @Override
    public String print()
    {
        String toPrint = "Turtle [" + this.getX() + "," + this.getY() + "]";
        return toPrint;
    }
    @Override
    public boolean hasDefendedAttack(Organism organism)
    {
        if(organism.getStrength() < 5)
        {
            world.addLog(this.print() + " has defended attack against " + organism.print());
            return true;
        }
        else
        {
            return false;
        }
    }
    @Override
    public void action()
    {
        if((int)(Math.random()*4) == 0)
        {
            moveRandom(1);
        }
    }

}

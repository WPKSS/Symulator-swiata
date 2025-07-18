package com.company;

public class Antelope extends Animal{
    public Antelope(int x, int y, World world)
    {
        super('A',4,4,x,y,world);
    }

    @Override
    public Antelope child()
    {
        return new Antelope(this.getX(),this.getY(),world);
    }
    @Override
    public void action()
    {
        moveRandom(2);
    }
    @Override
    public String print()
    {
        String toPrint = "Antelope [" + this.getX() + "," + this.getY() + "]";
        return toPrint;
    }
    @Override
    public boolean hasEscapedAttack()
    {
        if((int)(Math.random() * 2) == 0)
        {
            world.addLog(this.print() + " has escaped an attack");
            action();
            return true;
        }
        else
        {
            return false;
        }
    }
}

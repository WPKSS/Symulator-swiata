package com.company;

public class Sheep extends Animal{
    public Sheep(int x, int y, World world)
    {
        super('S',4,4,x,y,world);
    }
    @Override
    public Sheep child()
    {
        return new Sheep(this.getX(),this.getY(),world);
    }
    @Override
    public String print()
    {
        String toPrint = "Sheep [" + this.getX() + "," + this.getY() + "]";
        return toPrint;
    }
}

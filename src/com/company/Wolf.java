package com.company;

public class Wolf extends Animal{
    public Wolf(int x, int y, World world)
    {
        super('W',9,5,x,y,world);
    }
    @Override
    public Wolf child()
    {
        return new Wolf(this.getX(),this.getY(),world);
    }
    @Override
    public String print()
    {
        String toPrint = "Wolf [" + this.getX() + "," + this.getY() + "]";
        return toPrint;
    }
}

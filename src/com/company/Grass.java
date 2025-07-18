package com.company;

public class Grass extends Plant{
    public Grass(int x, int y, World world)
    {
        super('G',0, x,y,world);
    }
    @Override
    public Grass child()
    {
        return new Grass(this.getX(),this.getY(),world);
    }
    @Override
    public String print()
    {
        String toPrint = "Grass [" + this.getX() + "," + this.getY() + "]";
        return toPrint;
    }
}

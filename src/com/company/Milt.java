package com.company;

public class Milt extends Plant{
    public Milt(int x, int y, World world)
    {
        super('M',0, x,y,world);
    }
    @Override
    public void action()
    {
        for(int i = 0; i<3; i++)
        {
            breed();
        }
    }
    @Override
    public Milt child()
    {
        return new Milt(this.getX(),this.getY(),world);
    }
    @Override
    public String print()
    {
        String toPrint = "Milt [" + this.getX() + "," + this.getY() + "]";
        return toPrint;
    }
}

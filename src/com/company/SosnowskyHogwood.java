package com.company;

public class SosnowskyHogwood extends Plant{
    public SosnowskyHogwood(int x, int y, World world)
    {
        super('H',10, x,y,world);
    }
    @Override
    public void action()
    {
        for(Organism x : world.getNeighbours(this))
        {
           kill(x);
           world.addLog(this.print() + " has killed " + x.print());
        }
        breed();
    }
    @Override
    public void addBuff(Organism attacker)
    {
        kill(attacker);
    }
    @Override
    public SosnowskyHogwood child()
    {
        return new SosnowskyHogwood(this.getX(),this.getY(),world);
    }
    @Override
    public String print()
    {
        String toPrint = "SosnowskyHogwood [" + this.getX() + "," + this.getY() + "]";
        return toPrint;
    }
}

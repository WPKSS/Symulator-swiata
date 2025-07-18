package com.company;

public class KeenBerry extends Plant{
    public KeenBerry(int x, int y, World world)
    {
        super('K',99, x,y,world);
    }
    @Override
    public KeenBerry child()
    {
        return new KeenBerry(this.getX(),this.getY(),world);
    }
    @Override
    public String print()
    {
        String toPrint = "KeenBerry [" + this.getX() + "," + this.getY() + "]";
        return toPrint;
    }
    @Override
    public void addBuff(Organism attacker)
    {
        world.addLog(this.print() + " has killed " + attacker.print());
        kill(attacker);
    }
}

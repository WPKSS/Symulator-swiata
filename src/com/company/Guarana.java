package com.company;

public class Guarana extends Plant{
    public Guarana(int x, int y, World world)
    {
        super('U',0, x,y,world);
    }
    @Override
    public Guarana child()
    {
        return new Guarana(this.getX(),this.getY(),world);
    }
    @Override
    public String print()
    {
        String toPrint = "Guarana [" + this.getX() + "," + this.getY() + "]";
        return toPrint;
    }
    @Override
    public void addBuff(Organism attacker)
    {
        attacker.addStrength(3);
        world.addLog(this.print() + " has increased strength to " + attacker.print());
    }
}

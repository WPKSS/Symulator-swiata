package com.company;

import javafx.util.Pair;

public class Animal extends Organism{
    public Animal(char letter, int strength, int initiative, int x, int y, World world)
    {
        super(letter,strength,initiative,x,y,world);
    }

    protected void moveRandom(int range)
    {
        if(world.freeSidesOrganism(this) != 0)
        {
            Pair<Integer, Integer> coordinates = null;
            int k = 0;
            do {
                if(k>50)
                {
                    return;
                }
                coordinates = world.findPosition(this.getX(),this.getY(),range);
                k++;
            }while(coordinates.getKey() == -1 && coordinates.getValue() == -1);
            move(coordinates.getKey(), coordinates.getValue());
        }
    }

    protected void move(int x, int y)
    {
        if(!this.world.isEmpty(x,y))
        {
            this.world.getOrganism(x,y).collision(this);
        }
        else
        {
            String info = this.print() + " to [" + x +  "," + y +"]";
            world.addLog(info);
            setPosition(x,y);
        }
    }

    @Override
    public void collision(Organism attacker)
    {
        if(this.getLetter() == attacker.getLetter())
        {
            breed();
        }
        else
        {
            fight(attacker);
        }
    }

    @Override
    protected void breed()
    {
        Pair<Integer, Integer> coordinates = world.findFreeArea(this);
        if((coordinates.getKey() == -1 && coordinates.getValue() == -1) || world.getOrganismsAmount() >= world.getWidth() * world.getHeight())
        {
            world.addLog(this.print() + " does not have space to breed");
        }
        Organism child = this.child();
        child.setPosition(coordinates.getKey(),coordinates.getValue());
        world.addOrganism(child);
        world.addLog(this.print() + " has breed");
    }

    private void fight(Organism attacker)
    {
        if(this.hasDefendedAttack(attacker) == false && this.hasEscapedAttack() == false)
        {
            if (attacker.getStrength() < this.getStrength())
            {
                kill(attacker);
            }
		    else if (attacker.getStrength() >= this.getStrength())
            {
                world.addLog(this.print() + " has lost fight against " + attacker.print());
                attacker.setPosition(this.getX(), this.getY());
                kill(this);
            }
        }
    }

    @Override
    public void action()
    {
        moveRandom(1);
    }

    @Override
    public String print()
    {
        String toPrint = "Animal [" + this.getX() + "," + this.getY() + "]";
        return toPrint;
    }
}

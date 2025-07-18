package com.company;

public class Human extends Animal{
    private Direction direction;
    private int destX, destY;
    private int specialAbilityTurns;
    private int specialAbilityCooldown;
    private boolean isSpecialAbilityActive;
    private int specialAbilityMultiplier;
    public Human(int x, int y, World world)
    {
        super('X',5,4,x,y,world);
        destX = 0; destY = 0;
        this.specialAbilityTurns = 0;
        this.isSpecialAbilityActive = false;
        this.specialAbilityCooldown = 0;
        this.specialAbilityMultiplier = 1;
        this.direction = Direction.NONE;
    }

    final public int getDestX() {
        return destX;
    }

    public void setDestX(int destX) {
        this.destX = destX;
    }

    final public int getDestY() {
        return destY;
    }

    public void setDestY(int destY) {
        this.destY = destY;
    }

    final public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    final public int getSpecialAbilityTurns() {
        return specialAbilityTurns;
    }

    public void setSpecialAbilityTurns(int specialAbilityTurns) {
        this.specialAbilityTurns = specialAbilityTurns;
    }

    final public int getSpecialAbilityCooldown() {
        return specialAbilityCooldown;
    }

    public void setSpecialAbilityCooldown(int specialAbilityCooldown) {
        this.specialAbilityCooldown = specialAbilityCooldown;
    }

    final  public boolean isSpecialAbilityActive() {
        return isSpecialAbilityActive;
    }

    public void setSpecialAbilityActive(boolean specialAbilityActive) {
        isSpecialAbilityActive = specialAbilityActive;
    }

    final  public int getSpecialAbilityMultiplier() {
        return specialAbilityMultiplier;
    }

    public void setSpecialAbilityMultiplier(int specialAbilityMultiplier) {
        this.specialAbilityMultiplier = specialAbilityMultiplier;
    }
    @Override
    public Human child()
    {
        return null;
    }
    @Override
    public String print()
    {
        String toPrint = "Human [" + this.getX() + "," + this.getY() + "]";
        return toPrint;
    }

    @Override
    public void action()
    {
        skillHandler();
        keyHandler();
    }
    @Override
    protected void move(int x, int y)
    {
        if (!this.world.isEmpty(x, y))
        {
            this.collision(this.world.getOrganism(x, y));
            this.destX = 0; this.destY = 0;
        }
	else
        {
            setPosition(x, y);
            this.destX = 0; this.destY = 0;
        }
    }

    public boolean setHumanSpecialAbility()
    {
        if (!isSpecialAbilityActive && specialAbilityCooldown == 0)
        {
            this.specialAbilityTurns = 5;
            this.specialAbilityMultiplier = 2;
            this.isSpecialAbilityActive = true;
            return true;
        }
        return false;
    }
    @Override
    final public boolean isHuman()
    {
        return true;
    }

    private void skillHandler()
    {
        if (specialAbilityCooldown > 0)
        {
            this.specialAbilityCooldown--;
        }
        if (isSpecialAbilityActive && specialAbilityTurns > 0)
        {
            if (specialAbilityTurns < 2)
            {
                if ((int)(Math.random() * 2) == 1)
                {
                    this.specialAbilityMultiplier = 2;
                }
                else
                {
                    this.specialAbilityMultiplier = 1;
                }
            }
            this.specialAbilityTurns--;
        }
        if (specialAbilityTurns == 0 && isSpecialAbilityActive)
        {
            this.isSpecialAbilityActive = false;
            this.specialAbilityCooldown = 5;
            this.specialAbilityMultiplier = 1;
        }
    }
    private void keyHandler()
    {
        if((destX != 0 || destY != 0) && !world.checkBoundaries(this.getX() + destX * this.specialAbilityMultiplier, this.getY() + destY * this.specialAbilityMultiplier) )
        {
            move(this.getX() + destX*this.specialAbilityMultiplier, this.getY() + destY*this.specialAbilityMultiplier);
        }
    }
   public boolean setHumanDirection(int x, int y) {
       if (world.checkBoundaries(this.getX() + x * this.specialAbilityMultiplier, this.getY() + y * this.specialAbilityMultiplier) == true) {
           return false;
       } else {
           this.destX = x;
           this.destY = y;
           return true;
       }
   }
}

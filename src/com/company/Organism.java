package com.company;

public class Organism {
    private int strength, initiative, age, x, y;
    private char letter;
    private boolean isAlive;
    protected World world;

    public Organism(char letter, int strength, int initiative, int x, int y, World world) {
        this.letter = letter;
        this.strength = strength;
        this.initiative = initiative;
        this.x = x;
        this.y = y;
        this.world = world;
        isAlive = true;
        age = 0;
    }

    final public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    final public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    final public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    final public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    final public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    final public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    final public boolean getAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void incrementAge()
    {
        age++;
    }
    public void addStrength(int value)
    {
        strength = strength + value;
    }

    public boolean hasDefendedAttack(Organism organism)
    {
        return false;
    }

    public boolean hasEscapedAttack()
    {
        return false;
    }

    public boolean isHuman()
    {
        return false;
    }

    public void kill(Organism organism)
    {
        if(organism.isHuman() == true)
        {
            world.setHumanState(false);
        }
        organism.setAlive(false);
    }

    public void action(){}
    public Organism child(){ return null; }
    public void collision(Organism attacker) {}
    public String print(){return null;}
    protected void breed(){}
}

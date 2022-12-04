package Player;


public abstract class Player {
    protected String name;
    public String getName()
    {
        return name;
    }
    public abstract void win();
    public abstract void lose();

    public void  SetName(String n)
    {
        name=n;
    }

    public boolean equals(Player p)
    {
        return p.getName().equals(this.name);
    }

}

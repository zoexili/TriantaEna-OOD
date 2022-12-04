package Player;
import Player.Player;

import java.util.ArrayList;

public abstract class Team {
    protected ArrayList<Player> team;
    protected String name;

    public Team() {
        team=new ArrayList<Player>();
    }

    public String getName() {
        return name;
    }

    public abstract void win();
    public abstract void lose();

    public abstract void addPlayer(Player p);
    public abstract void removePlayer(Player p);

    public void setName(String s) {
        this.name = s;
    }

    public boolean equals(Team t) {
        return t.getName().equals(this.name);
    }

    public int size() {return team.size();}

    public Player get(int index) {return team.get(index);}


}


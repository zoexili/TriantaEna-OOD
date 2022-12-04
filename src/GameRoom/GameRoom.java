package GameRoom;

import Game.Game;
import Game.GameSet;
import Player.Player;

import java.io.IOException;
import java.util.List;

public class GameRoom
{
    protected GameSet games;
    protected String name;

    protected List<Player> players;

    public GameRoom(String name)
    {
        this.name=name;
        games=new GameSet();
    }

    public void addGame(Game g)
    {
        games.addGame(g);
    }

    public void showGame() {games.showlist();}

    public void runGame(int index) throws IOException, InterruptedException {games.run(index);}

}


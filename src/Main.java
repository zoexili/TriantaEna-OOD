import Game.TEGame;
import GameRoom.GameRoom;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException
    {
        GameRoom room=new GameRoom("tegame room");
        room.addGame(new TEGame());
        room.runGame(0);
    }
}
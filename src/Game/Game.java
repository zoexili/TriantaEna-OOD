package Game;

import Game.Log.LogList;
import GameUtil.Gameutil;
import Player.Player;
import Player.Team;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Random;

abstract public class Game {
   protected List<Player> players;

   protected int gameState;
   protected String name;
   protected LogList logs;
   protected List<Team> teams;

   public abstract void addPlayer() throws IOException;
   public abstract void run() throws IOException, InterruptedException;
   public abstract boolean IsGameEnd();
   public abstract  void end();


   protected int getPlayerN() throws IOException {
      InputStreamReader in;
      in = new InputStreamReader(System.in);
      BufferedReader buf;
      buf=new BufferedReader(in);
      String s;
      while (true)
      {
         System.out.print("Please input the number of players:");
         s=buf.readLine();
         if (Gameutil.isStringDigit(s)) break;
         System.out.println("Invalid input!");
      }
      int n=Integer.parseInt(s);
      return n;
   }

   public boolean restart() throws IOException
   {
      InputStreamReader in;
      in = new InputStreamReader(System.in);
      BufferedReader buf;
      buf=new BufferedReader(in);
      System.out.println("Do you want to play another round? Y/N");
      String s=buf.readLine();
      while (!s.equals("Y") && !s.equals("y") && !s.equals("N") && !s.equals("n"))
      {
         System.out.println("Invalid Input!");
         System.out.println("Do you want to play another round? Y/N");
         s=buf.readLine();
      }
      if (s.equals("y") || s.equals("Y"))
      {
         return true;
      }
      System.out.println("Thanks for playing!");
      return false;
   }

   public String getName()
   {
      return name;
   }

}

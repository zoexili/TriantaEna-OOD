package Game;

import Game.GameItem.Card.Card;
import Game.GameItem.Card.RecycleCardsPool;
import Player.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static GameUtil.Gameutil.isStringDigit;
import static java.lang.Math.min;

public class TEGame extends Game {
    RecycleCardsPool cardsPool;
    int roundIndex;
    int InitialMoney;

    public TEGame() {
        teams=new ArrayList<Team>();
        teams.add(new teTeam("Banker"));
        teams.add(new teTeam("Player"));
        roundIndex = 0;
        name = "TriantaEna";
        InitialMoney = 10000;
        cardsPool=new RecycleCardsPool();
        players = new ArrayList<Player>();
    }

    @Override
    public void addPlayer() throws IOException {
        InputStreamReader in;
        in = new InputStreamReader(System.in);
        BufferedReader buf;
        buf = new BufferedReader(in);
        String s;
        int n = getPlayerN();
        while (n < 2) {
            System.out.println("this game needs at least two players!please reinput the number!");
            n = getPlayerN();
        }
        for (int i = 0; i < n; i++) {
            System.out.print("Please input Player " + i + "'s name:");
            s = buf.readLine();
            players.add(new tePlayer(s, this.InitialMoney));
        }
        for (int i = 0; i < players.size(); i++) {
            teams.get(1).addPlayer(players.get(i));
        }
    }

    private void pickFirstBanker() throws IOException, InterruptedException {
        InputStreamReader in;
        in = new InputStreamReader(System.in);
        BufferedReader buf;
        buf = new BufferedReader(in);
        String s;
        tePlayer Banker=null;
        System.out.println("The TriantaEna Game need a first banker.");
        for (int i = 0; i < players.size(); i++) {
            System.out.println("Hello," +players.get(i).getName() + "!Do you want to be the Banker?(Y/N)");
            s = buf.readLine();
            while (!s.equals("y") && !s.equals("Y") && !s.equals("n") && !s.equals("N")) {
                System.out.println("Invalid Input, please reinput:");
                s = buf.readLine();
            }
            if (s.equals("y") || s.equals("Y")) {
                Banker = (tePlayer) players.get(i);
                break;
            }
        }

        if (Banker == null) {
            System.out.println("Since no one want to be the Banker, Game will randomly choose one lucky guy to be the banker:");
            int index = new Random().nextInt(players.size());
            Banker = (tePlayer) players.get(index);
        }
        System.out.println("The First Banker is:" + Banker.getName());
        Banker.beFirstBanker();
        teams.get(0).addPlayer(Banker);
        teams.get(1).removePlayer(Banker);
        Thread.sleep(1000);
    }

    private void prepareCards() {
        String suits[] = new String[]{"♣", "♥", "♠", "♦"};
        String values[] = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        for (int k = 0; k < 2; k++) {
            for (int i = 1; i <= 13; i++) {
                for (int j = 0; j < 4; j++)
                    cardsPool.addCard(new Card(suits[j] + values[i - 1], min(i, 10)));
            }
        }
        cardsPool.reshuffle();
    }

    private void deal1stCard() throws InterruptedException
    {
        for (int t=0;t<2;t++)
        {
            for (int i = 0; i < teams.get(t).size(); i++) {
                tePlayer tmpPlayer = ((teTeam)teams.get(t)).get(i);
                Card c = cardsPool.getOneCard();
                if (tmpPlayer.isBanker())
                {
                    c.faceUp();
                    System.out.println("First Card dealt to Banker:" + tmpPlayer.getName());
                    System.out.print("Since this is Banker's first Card, it's known to all of players, the card is:");
                    System.out.println(c.showToSelf());
                }
                else
                {
                    c.faceDown();
                    System.out.println("Hello," + tmpPlayer.getName() + "!");
                    System.out.println("This is your Card" + c.showToSelf());
                }
                tmpPlayer.rsvCard(c);
                Thread.sleep(1000);
                System.out.println("-----------------------------------------");
            }
        }
    }

    private void PerspectiveInfo(tePlayer p) throws InterruptedException {
        for (int t=0;t<2;t++)
        {
            for (int i = 0; i < teams.get(t).size(); i++) {
                tePlayer tmpPlayer = ((teTeam)teams.get(t)).get(i);
                if (!tmpPlayer.equals(p)) {
                    System.out.print(tmpPlayer.toString() + ":");
                    tmpPlayer.showCards();
                }
            }
        }
        System.out.print(p.toString() + ":");
        p.showHands();
        System.out.println("-----------------------------------------");
        Thread.sleep(1000);
    }

    private void placeBet() throws IOException, InterruptedException {
        System.out.println("Hello everyone! Now It's time to choose whether to place a bet");
        InputStreamReader in;
        in = new InputStreamReader(System.in);
        BufferedReader buf;
        buf = new BufferedReader(in);
        String s;
        for (int i = 0; i < teams.get(1).size(); i++) {
            tePlayer tmpPlayer = ((teTeam)teams.get(1)).get(i);
            System.out.println("Hello," + tmpPlayer.getName() + "!");
            System.out.println("This is overall information:");
            PerspectiveInfo(tmpPlayer);
            System.out.println("Do you want to place a bet? (Y/N) (Y means you choose to take a bet and N means you choose to fold)");
            s = buf.readLine();
            while (!s.equals("y") && !s.equals("Y") && !s.equals("n") && !s.equals("N")) {
                System.out.println("Invalid Input, please reinput:");
                s = buf.readLine();
            }
            if (s.equals("y") || s.equals("Y")) {
                System.out.println("Great!How much do you want to bet?(Your rest money is:+" + tmpPlayer.getMoney() + ")");
                while (true) {
                    s = buf.readLine();
                    while (!isStringDigit(s)) {
                        System.out.println("Invalid Input, please reinput:");
                        s = buf.readLine();
                    }
                    int bet = Integer.parseInt(s);
                    if (bet == 0) {
                        System.out.println("Bet must greater than 0 :(");
                        continue;
                    }
                    if (bet > tmpPlayer.getMoney()) {
                        System.out.println("You don't have enough money :(");
                        continue;
                    }
                    tmpPlayer.bet(bet);
                    System.out.println("Your bet is:" + bet + ",and your rest money is:" + tmpPlayer.getMoney());
                    break;
                }
            } else {
                tmpPlayer.bet(0);
                System.out.println("you choose to fold,Wise choice!");
            }
            System.out.println("-----------------------------------------");
            Thread.sleep(1000);
        }
    }

    public void deal2Cards() throws InterruptedException {
        System.out.println("Hello everyone! Here are your rest 2 cards!");
        Thread.sleep(1000);
        for (int i = 0; i < teams.get(1).size(); i++) {
            tePlayer tmpPlayer = ((teTeam)teams.get(1)).get(i);
            if (tmpPlayer.getCurbet() == 0) {
                System.out.println(tmpPlayer.getName() + "chose to fold this turn");
                continue;
            }
            Card c1 = cardsPool.getOneCard();
            Card c2 = cardsPool.getOneCard();
            c1.faceUp();
            c2.faceUp();
            System.out.println("Hello," + tmpPlayer.getName() + "!");
            System.out.println("These are your Cards" + c1.showToSelf() + "," + c2.showToSelf());
            tmpPlayer.rsvCard(c1);
            tmpPlayer.rsvCard(c2);
            System.out.println("-----------------------------------------");
            Thread.sleep(1000);
        }
    }

    public void hitOrStand() throws IOException, InterruptedException {
        InputStreamReader in;
        in = new InputStreamReader(System.in);
        BufferedReader buf;
        buf = new BufferedReader(in);
        String s;
        for (int i = 0; i < teams.get(1).size(); i++) {
            tePlayer tmpPlayer = ((teTeam)teams.get(1)).get(i);
            if (tmpPlayer.getCurbet() == 0) continue;
            System.out.println("Hello," + tmpPlayer.getName() + "!This is overall information:");
            PerspectiveInfo(tmpPlayer);
            System.out.println("Do you want to hit?(Y/N)");
            while (true) {
                s = buf.readLine();
                while (!s.equals("y") && !s.equals("Y") && !s.equals("n") && !s.equals("N")) {
                    System.out.println("Invalid Input, please reinput:");
                    s = buf.readLine();
                }
                if (s.equals("n") || s.equals("N")) break;
                Card c = cardsPool.getOneCard();
                c.faceDown();
                tmpPlayer.rsvCard(c);
                System.out.println("This is your Card" + c.showToSelf());
                if (tmpPlayer.goBust()) {
                    System.out.println("AND BOOM! YOU ARE BUST WITH HANDS:");
                    tmpPlayer.showHands();
                    break;
                }
                System.out.println("Now the cards in your hand are:");
                tmpPlayer.showHands();
                System.out.println("Do you want to hit once more?");
            }
        }
    }

    public void RevealCards() throws InterruptedException {
        System.out.println("It's time to reveal your cards to BANKER!");
        Thread.sleep(1000);
        for (int i = 0; i < teams.get(1).size(); i++) {
            tePlayer tmpPlayer = ((teTeam)teams.get(1)).get(i);
            System.out.println(tmpPlayer + ":");
            tmpPlayer.showHands();
            Thread.sleep(1000);
        }
    }

    public void BankerHit() throws IOException, InterruptedException {
        InputStreamReader in;
        in = new InputStreamReader(System.in);
        BufferedReader buf;
        buf = new BufferedReader(in);
        String s;
        tePlayer tmpPlayer =((teTeam)teams.get(0)).get(0);

        System.out.println("Banker hit time!");
        System.out.println("Hello," + tmpPlayer.getName() + "!Do you want to hit? (Y/N)");
        while (tmpPlayer.canHitAsBanker()) {
            s = buf.readLine();
            while (!s.equals("y") && !s.equals("Y") && !s.equals("n") && !s.equals("N")) {
                System.out.println("Invalid Input, please reinput:");
                s = buf.readLine();
            }
            if (s.equals("n") || s.equals("N")) break;
            Card c = cardsPool.getOneCard();
            c.faceDown();
            tmpPlayer.rsvCard(c);
            System.out.println("This is your Card" + c.showToSelf());
            if (tmpPlayer.goBust()) {
                System.out.println("AND BOOM! YOU ARE BUST WITH HANDS:");
                tmpPlayer.showHands();
                Thread.sleep(1000);
                break;
            }
            if (!tmpPlayer.canHitAsBanker()) {
                System.out.println("And your hands value is equals or exceeds 27 with hands:");
                tmpPlayer.showHands();
                System.out.println("So you are not allowed to hit");
                Thread.sleep(1000);
                return;
            }
            Thread.sleep(1000);
            System.out.println("Now the cards in your hand are:");
            tmpPlayer.showHands();
            System.out.println("Do you want to hit once more?(Y/N)");
        }
    }

    public void RoundSettle() throws InterruptedException, IOException {
        InputStreamReader in;
        in = new InputStreamReader(System.in);
        BufferedReader buf;
        buf = new BufferedReader(in);
        String s;
        tePlayer Banker = ((teTeam)teams.get(0)).get(0);
        int bankvalue = Banker.HandsVal();
        System.out.print(Banker.toString());
        Banker.showHands();
        if (bankvalue < 0)
            System.out.println("Banker " + Banker.getName() + " is bust");
        else
            System.out.println("Banker's hands value is:" + bankvalue);
        //who wins
        boolean Bankerwin = true;
        for (int i=0;i<teams.get(1).size();i++)
        {
            tePlayer tmpPlayer = ((teTeam)teams.get(1)).get(i);
            int v = tmpPlayer.HandsVal();
            if (v>bankvalue)
            {
                Bankerwin=false;
                break;
            }
        }
        //playerwin
        if (!Bankerwin)
        {
            for (int i = 0; i < teams.get(1).size(); i++) {
                tePlayer tmpPlayer = ((teTeam)teams.get(1)).get(i);
                if (tmpPlayer.isBanker()) continue;
                System.out.print(tmpPlayer.toString());
                tmpPlayer.showHands();
                int v = tmpPlayer.HandsVal();
                if (v < 0)
                    System.out.println(tmpPlayer.getName() + " is bust");
                else
                    System.out.println(tmpPlayer.getName() + "'s hands value is:" + v);
                if (v > bankvalue) {
                    System.out.println("Congratulation," + tmpPlayer.getName() + "! You win! and you can get an extra " + tmpPlayer.getCurbet() + " from the Banker!");
                    tmpPlayer.getBetBack();
                    Banker.giveMoney(tmpPlayer);
                }
                else{
                    System.out.println("Although not a winner,but you can take your bet back!");
                    tmpPlayer.getBetBack();
                }
            }
        }
        else
        {
            System.out.println("Banker win this round! and he/she will take all the other players' bet!");
            for (int i = 0; i < teams.get(1).size(); i++) {
                tePlayer tmpPlayer = ((teTeam)teams.get(1)).get(i);
                if (tmpPlayer.isBanker()) continue;
                Banker.bankRsvMoney(tmpPlayer);
            }
        }
        Thread.sleep(1000);

        List<tePlayer> tmplist=new ArrayList<tePlayer>();
        for (int i=0;i<teams.get(1).size();i++)
        {
            tmplist.add( ((teTeam)teams.get(1)).get(i));
        }
        Collections.sort(tmplist);

        for (int i = 0; i < tmplist.size(); i++) {
            tePlayer p = tmplist.get(i);
            System.out.println(p.getName() + "'s money:" + p.getMoney());
            if (p.getMoney() > Banker.getMoney()) {
                System.out.println("Do you want to be the new Banker?");
                s = buf.readLine();
                while (!s.equals("y") && !s.equals("Y") && !s.equals("n") && !s.equals("N")) {
                    System.out.println("Invalid Input, please reinput:");
                    s = buf.readLine();
                }
                if (s.equals("Y") || s.equals("y"))
                {
                    Banker.notBanker();
                    teams.get(0).removePlayer(Banker);
                    p.beBanker();
                    teams.get(0).addPlayer(Banker);
                    teams.get(1).removePlayer(Banker);
                    System.out.println("The new Banker is:"+p.getName()+"!");
                }

            }
        }
        if (Banker.getMoney()<=0)
        {
            System.out.println("Current Banker has no money! so we'll choose a new Banker randomly!");
            int index = new Random().nextInt(teams.get(1).size());
            Banker.notBanker();
            teams.get(0).removePlayer(Banker);
            ((teTeam)teams.get(1)).get(index).beBanker();
            Banker = ((teTeam)teams.get(1)).get(index);
            teams.get(0).addPlayer(Banker);
            teams.get(1).removePlayer(Banker);
            System.out.println("The new Banker is:"+Banker.getName()+"!");
        }

        List<tePlayer> removeList=new ArrayList<tePlayer>();
        Banker.DropCardTo(cardsPool);
        Banker.bet(0);
        for (int i = 0; i < teams.get(1).size(); i++) {
            tePlayer p =((teTeam)teams.get(1)).get(i);
            if (p.getMoney() <= 0) {
                    System.out.println(p.getName() + " got no money! so he/she has to leave the game!");
                    removeList.add(p);
                } else {
                    System.out.print("Hello," + p.getName() + "!");
                    if (!restart()) {
                        System.out.println(p.getName() + " choose to cash out");
                        removeList.add(p);
                    }
                }
            p.DropCardTo(cardsPool);
            p.bet(0);
            }

        for (int i=0;i<removeList.size();i++)
            ((teTeam)teams.get(1)).removePlayer(removeList.get(i));
        //=================

    }

    @Override
    public void run() throws IOException, InterruptedException {
        System.out.println("Welcome to the " + name + "!");
        addPlayer();
        pickFirstBanker();
        prepareCards();
        while (true) {
            roundIndex++;
            System.out.println("Round " + roundIndex + " start!");
            deal1stCard();
            placeBet();
            deal2Cards();
            hitOrStand();
            RevealCards();
            BankerHit();
            RoundSettle();
            Thread.sleep(1000);
            if (IsGameEnd())
            {
                end();
                break;
            }
        }
    }


    @Override
    public boolean IsGameEnd() {
        if (teams.get(1).size()<1) return true;
        return false;
    }

    @Override
    public void end() {
        System.out.println("Players number is less than 2,the game end!");
        System.out.println("This is the score of every player:");
        for (int i=0;i<players.size();i++)
        {
            tePlayer p=(tePlayer) players.get(i);
            System.out.println(p.getName()+":"+p.getMoney());
        }
    }
}

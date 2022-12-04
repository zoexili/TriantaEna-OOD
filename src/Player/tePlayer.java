package Player;

import Game.GameItem.Card.Card;
import Game.GameItem.Card.Hands;
import Game.GameItem.Card.RecycleCardsPool;

public class tePlayer extends  Player implements Comparable<tePlayer>{
    private int Score;
    Hands hand;
    protected int money;
    protected int state;
    protected boolean isBust;
    protected boolean isBanker;
    protected int curbet;
    public tePlayer(String name,int money)
    {
        this.name=name;
        this.Score=0;
        this.money=money;
        isBanker=false;
        isBust=false;
        hand=new Hands();
    }

    public void rsvCard(Card card)
    {
        hand.addCard(card);
    }

    public void beFirstBanker()
    {
        beBanker();
        money*=3;
    }
    public void beBanker()
    {
        isBanker=true;
    }
    public void notBanker() {isBanker=false;}
    public boolean isBanker()
    {
        return  isBanker;
    }

    public boolean canHitAsBanker()
    {
        if (hand.getMinVal()<27) return true;
        return false;
    }
    public void showHands() {
        for (int i=0;i<hand.size();i++)
            System.out.print(hand.get(i).showToSelf()+" ");
        System.out.println();
    }

    public void DropCardTo(RecycleCardsPool cardsPool) {
        while (!hand.isEmpty())
            cardsPool.dropCard(hand.getOneCard());
        isBust=false;
    }

    public void showCards()
    {
        for (int i=0;i<hand.size();i++)
            System.out.print(hand.get(i).showToOthers()+" ");
        System.out.println();
    }

    public String toString()
    {
        if (isBanker) return "★"+name+"(banker rest money:"+money+")";
        return name+"(rest money:"+money+", current bet:"+curbet+")";
    }

    public void bet(int bet)
    {
        money-=bet;
        curbet=bet;
    }

    public boolean goBust()
    {
        if (isBust) return isBust;
        if (hand.getMinVal()>31)
        {
            isBust=true;
            for (int i=0;i<hand.size();i++)
                hand.get(i).faceUp();
            return true;
        }
        return false;
    }
    public void giveMoney(tePlayer p)
    {
        if (isBanker)
            money-=p.getCurbet();
        p.rsvMoney();
    }

    public void bankRsvMoney(tePlayer p)
    {
        if (isBanker)
            money+=p.getCurbet();
    }
    public void rsvMoney()
    {
        if (!isBanker)
            money+=this.curbet;
    }
    public void getBetBack()
    {
        this.money+=curbet;
    }
    public int HandsVal()
    {
        if (isBust)
        {
            if (isBanker) return -1;
            else return -2;
        }
        return hand.getFinalVal();
    }

    public int getCurbet() {
        return curbet;
    }

    public int getMoney()
    {
        return money;
    }

    public int getScore() {return getMoney();}

    @Override
    public void win() {
    }

    @Override
    public void lose() {
    }

    public int compareTo(tePlayer p1) {
        return p1.money-this.money;
    }
}

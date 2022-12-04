package Game.GameItem.Card;

import Game.GameItem.GameItem;

public class Card extends GameItem
{
    final static int FACE_UP=1;
    final static int FACE_DOWN=0;
    final static int FACE_UNKNOWN=-1;
    protected int face;
    protected int value;
    public Card(String name,int v)
    {
        super(name);
        value=v;
        face=FACE_UNKNOWN;
    }

    public Card(String name,int v,String facestate)
    {
        this(name,v);
        if (facestate.equals("UP")) face=FACE_UP;
        if (facestate.equals("DOWN")) face=FACE_DOWN;
    }

    public int getFace() {
        return face;
    }

    public void faceUp()
    {
        face=FACE_UP;
    }

    public void faceDown()
    {
        face=FACE_DOWN;
    }

    public int getValue() {
        return value;
    }

    public String showToOthers()
    {
        if (face==FACE_UP) return this.showToSelf();
        return "*";
    }
    public String showToSelf()
    {
        return this.toString();
    }
}

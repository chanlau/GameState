package edu.up.gamestate;

public class Card {
    //create variables for the current player playing the card, the target player of the card
    //if there is one, and the cardType as an int
    private int cardType;

    public Card(int cardType) {
        this.cardType = cardType;
    }

    public Card(Card orig) {
        this(orig.getCardType());
    }

    public int getCardType() {
        return cardType;
    }
}

/*Card Index:
0: Exploding Kitten
1:Tacocat
2:BeardCat
3:Hairy Potato Cat
4:Rainbow Cat
5:Cattermelon
6:Attack
7:Shuffle
8:Favor
9:Skip
10:See the Future
11: Nope
12:Defuse
 */
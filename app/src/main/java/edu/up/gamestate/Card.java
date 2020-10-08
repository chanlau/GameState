package edu.up.gamestate;

public class Card {
    //create variables for the current player playing the card, the target player of the card
    //if there is one, and the cardType as an int
    Player currPlayer;
    Player targPlayer;
    int cardType;

    public Card(int cardType, Player currPlayer, Player targPlayer) {
        this.cardType = cardType;
        this.currPlayer = currPlayer;
        this.targPlayer = targPlayer;
    }
    public Card(Card orig){
        this(orig.getCardType(),orig.getCurrPlayer(), orig.getTargPlayer());
    }

    public Player getCurrPlayer(){
        return currPlayer;
    }

    public Player getTargPlayer(){
        return targPlayer;
    }

    public int getCardType(){
        return cardType;
    }


    //method to call when a card is played
    public void playCard(int card) {
        //check which card is passed in and call the corresponding method
        switch (cardType) {
            case 0:
                Attack();
                break;
            case 1:
                BeardCat();
                break;
            case 2:
                Cattermelon();
                break;
            case 3:
                Favor();
                break;
            case 4:
                HairyPotatoCat();
                break;
            case 5:
                Nope();
                break;
            case 6:
                RainbowRalphingCat();
                break;
            case 7:
                SeeTheFuture();
                break;
            case 8:
                Shuffle();
                break;
            case 9:
                Skip();
                break;
            case 10:
                TacoCat();
                break;
            case 11:
                ExplodingKitten();
                break;
            default:
                break;

        }

    }

    //attack card
    void Attack() {
        //increment whose turn it is
        whoseTurn++;
        //determine what player is forced to draw 2 cards
        switch (whoseTurn) {
            case 1:
                Player(1);
                break;
        }
    }

    //Beard Cat card
    void BeardCat() {

    }

    //Cattermelon card
    void Cattermelon() {

    }

    //Favor card
    void Favor() {

    }

    //Hairy Potato Cat card
    void HairyPotatoCat() {

    }

    //Nope card
    void Nope() {

    }

    //Rainbow-Ralphing Cat card
    void RainbowRalphingCat() {

    }

    //See the Future card
    void SeeTheFuture() {

    }

    //Shuffle card
    void Shuffle() {

    }

    //Skip card
    void Skip() {

    }

    //Tacocat card
    void TacoCat () {

    }

    //Exploding Kitten card
    void ExplodingKitten() {

    }

}

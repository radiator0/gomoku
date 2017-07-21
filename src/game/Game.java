package game;

/**
 * Author: Krzysztof Pilarczyk
 * Date: 2017-07-11
 */
public class Game {
    private int maxRound=Settings.DEFAULT_MAX_ROUND;
    private int round = 1;

    private int playerOneScore = 0;
    private int playerTwoScore = 0;

    private int movesCount = 0;

    private String playerOne = Settings.DEFAULT_NICK;
    private String playerTwo = Settings.DEFAULT_NICK;

    private Board board;

    private boolean isEnd = false;

    public Game(int boardSize){
        board = new Board(boardSize);
    }

    public Board getBoard(){
        return board;
    }

    public int getMovesCount(){ return movesCount;}

    public void incrementMovesCount(){
        movesCount++;
    }

    public void setMaxRound(int maxRound){
        this.maxRound = maxRound;
    }

    public void setPlayerOne(String playerOne){
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(String playerTwo){
        this.playerTwo = playerTwo;
    }

    public void incrementOneScore(){
        playerOneScore++;
    }

    public void incrementTwoScore(){
        playerTwoScore++;
    }

    public void setEnd(){
        isEnd = true;
    }

    public boolean isEnd() {return isEnd;}

    public boolean isLastRound(){
        if(round == maxRound) return true;
        return false;
    }


    public void nextRound(){
        round++;
    }

    public int getPlayerOneScore(){
        return playerOneScore;
    }

    public int getPlayerTwoScore(){
        return playerTwoScore;
    }

    public String getPlayerOne(){
        return playerOne;
    }

    public String getPlayerTwo(){
        return playerTwo;
    }

    public int getMaxRound(){
        return maxRound;
    }

    public int getRound(){
        return round;
    }
}

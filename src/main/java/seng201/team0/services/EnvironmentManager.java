package seng201.team0.services;
import java.util.function.Consumer;

/**
 * A class that manage the interaction between all the launchScreen (GUI)
 */
public class EnvironmentManager {
    private String playerName;
    private String gameDifficulty;
    private String roundDifficulty;
    private int numberOfRounds;
    private int currentRoundNumber = 1;
    private int score = 0;
    private double trackDistance = 6450;
    private final Consumer<EnvironmentManager> setupScreenLauncher;
    private final Consumer<EnvironmentManager> inventoryScreenLauncher;
    private final Consumer<EnvironmentManager> roundDifficultySelectScreenLauncher;
    private final Consumer<EnvironmentManager> easyGameScreenLauncher;
    private final Consumer<EnvironmentManager> winnerScreenLauncher;
    private final Consumer<EnvironmentManager> loserScreenLauncher;
    private final Consumer<EnvironmentManager> shopScreenLauncher;
    private final Consumer<EnvironmentManager> moderateGameScreenLauncher;
    private final Consumer<EnvironmentManager> challengingGameScreenLauncher;
    private final Consumer<EnvironmentManager> winnerGameScreenLauncher;
    private final Runnable clearScreen;
    /**
     * Initialize the default towers on page setup and parse the interface Consumer of all related controllers
     */
    public EnvironmentManager(Consumer<EnvironmentManager> setupScreenLauncher, Consumer<EnvironmentManager> inventoryScreenLauncher, Consumer<EnvironmentManager> roundDifficultySelectScreenLauncher, Consumer<EnvironmentManager> easyGameScreenLauncher, Consumer<EnvironmentManager> winnerScreenLauncher, Consumer<EnvironmentManager> loserScreenLauncher, Consumer<EnvironmentManager> shopScreenLauncher, Consumer<EnvironmentManager> moderateGameScreenLauncher, Consumer<EnvironmentManager> challengingGameScreenLauncher, Consumer<EnvironmentManager> winnerGameScreenLauncher, Runnable clearScreen) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.roundDifficultySelectScreenLauncher = roundDifficultySelectScreenLauncher;
        this.easyGameScreenLauncher = easyGameScreenLauncher;
        this.winnerScreenLauncher = winnerScreenLauncher;
        this.loserScreenLauncher = loserScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.moderateGameScreenLauncher = moderateGameScreenLauncher;
        this.challengingGameScreenLauncher = challengingGameScreenLauncher;
        this.winnerGameScreenLauncher = winnerGameScreenLauncher;
        this.clearScreen = clearScreen;
        launchSetupScreen();
    }

    /**
     * Check if the name text field does not contain special character
     * @param input
     */
    public boolean isNotSpecialChar(String input){ return input.matches("[a-zA-Z0-9]+");}

    /**
     * Get the track distance of current round
     */
    public double getTrackDistance(){ return this.trackDistance;}

    /**
     * Get the current score
     * @return score
     */
    public int getScore() {return this.score;}

    /**
     * Increment the score by the given amount
     * @param amount amount to add to the score
     */
    public void incrementScore(int amount) {
        this.score += amount;
    }

    /**
     * Clear player score and reset score to zero when start a new game
     */
    public void resetScore() {
        this.score = 0;
    }

    /**
     * Get current round difficulty
     */
    public String getRoundDifficulty() {return this.roundDifficulty;}

    /**
     * Set the round difficulty level inside the chosen game difficulty
     * @param roundDifficulty in String
     */
    public void setRoundDifficulty(String roundDifficulty) {this.roundDifficulty = roundDifficulty;}

    /**
     * Set the difficult level which will be chosen by user on setup page and keep through the whole game
     * @param difficulty in String
     */
    public void setGameDifficulty(String difficulty) {this.gameDifficulty = difficulty;}

    /**
     * Get the difficult level
     * @return difficulty in String
     */
    public String getGameDifficulty(){return this.gameDifficulty;}

    /**
     * set the number of rounds which will be chosen by user on setup page and keep through the whole game
     * @param numberOfRounds in Integer
     */
    public void setNumberOfRounds(int numberOfRounds){this.numberOfRounds = numberOfRounds;}

    /**
     * Get numberOfRounds
     * @return numberOfRounds in int
     */
    public int getNumberOfRounds(){return this.numberOfRounds;}

    /**
     * Get the currentRounds which player is at
     * @return currentRounds
     */
    public int getCurrentRoundNumber(){return this.currentRoundNumber;}

    /**
     * Reset the current round number back to 1
     */
    public void resetCurrentRoundNumber(){this.currentRoundNumber = 1;}

    /**
     * Increment the current round when click to play
     */
    public void incrementCurrentRoundNumber(){
        if (this.currentRoundNumber < numberOfRounds)
            this.currentRoundNumber += 1;
    }

    /**
     * Get the player name
     * @return playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * set player name which will be input by user from keyboard
     * @param playerName in String
     */
    public void setName(String playerName) {
        this.playerName = playerName;
    }

    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }

    public void returnedSetupScreen() {
        clearScreen.run();
    }

    public void launchInventoryScreen() {
        inventoryScreenLauncher.accept(this);
    }

    public void launchRoundDifficultySelectScreen() {roundDifficultySelectScreenLauncher.accept(this);}

    public void launchWinnerNextRoundScreen() { winnerScreenLauncher.accept(this);}

    public void launchLoserScreen() { loserScreenLauncher.accept(this);}

    public void closeCurrentScreen() { clearScreen.run();}

    public void launchShopScreen() { shopScreenLauncher.accept(this);}

    public void launchEasyGameScreen() {easyGameScreenLauncher.accept(this);}

    public void launchModerateGameScreen() {moderateGameScreenLauncher.accept(this);}

    public void launchChallengingGameScreen() {challengingGameScreenLauncher.accept(this);}

    public void launchWinnerGameScreen() {winnerGameScreenLauncher.accept(this);}
}

package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.services.EnvironmentManager;
import seng201.team0.services.InventoryService;
import seng201.team0.services.RandomEventService;
import seng201.team0.models.Tower;


public class WinnerNextRoundController {
    private EnvironmentManager environmentManager;
    private InventoryService inventoryService;
    private RandomEventService randomEventService;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label coinsLabel;

    @FXML
    private Label playerNameLabel;

    @FXML
    private Label roundCompletedLabel;

    @FXML
    private Label roundRemainingLabel;

    @FXML
    private Label brokenTowerAlertLabel;

    @FXML
    private void onExitButtonClicked() {
        System.exit(0);
    }

    public WinnerNextRoundController(EnvironmentManager environmentManager, RandomEventService randomEventService, InventoryService inventoryService) {
        this.environmentManager = environmentManager;
        this.randomEventService = randomEventService;
        this.inventoryService = inventoryService;

    }

    /**
     * Initialize the round winning screen which have player name, total points and coins earned
     * current round completed and round remaining. It may have alert message if tower breaks
     */
    public void initialize(){
        scoreLabel.setText(environmentManager.getScore() + " points");
        coinsLabel.setText(inventoryService.getPlayerCoins() + " coins");
        playerNameLabel.setText(environmentManager.getPlayerName() + " player");
        roundCompletedLabel.setText(environmentManager.getCurrentRoundNumber() + " round completed");
        roundRemainingLabel.setText(environmentManager.getNumberOfRounds() - environmentManager.getCurrentRoundNumber()+ " round remaining");

        environmentManager.incrementCurrentRoundNumber();
        System.out.println("Current rounds: " + environmentManager.getNumberOfRounds());
        this.randomEventService.dicePossibilityToHaveEvent();
        if (this.randomEventService.isHasRandomEvent()) {
            Tower removedTower = randomEventService.getAndRemoveTheRandomTowerBeChosen();
            brokenTowerAlertLabel.setText("OH NO! Your " + removedTower.getName() + " Tower has broken! You can no longer use it");
            randomEventService.setHasRandomEventToFalse();
        }
    }
    @FXML
    private void onNextRoundButtonClicked() {
        environmentManager.closeCurrentScreen();
        environmentManager.launchShopScreen();
    }
}

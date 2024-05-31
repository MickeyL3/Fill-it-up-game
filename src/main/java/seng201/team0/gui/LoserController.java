package seng201.team0.gui;

import javafx.fxml.FXML;
import seng201.team0.services.EnvironmentManager;
import javafx.scene.control.Label;
import seng201.team0.services.InventoryService;

public class LoserController {
    private EnvironmentManager environmentManager;
    private InventoryService inventoryService;
    public LoserController(EnvironmentManager environmentManager, InventoryService inventoryService) {
        this.environmentManager = environmentManager;
        this.inventoryService = inventoryService;
    }

    @FXML
    private Label totalScoreLabel;

    @FXML
    private Label totalCoinLabel;

    @FXML
    private Label playerNameLabel;

    @FXML
    private Label roundCompletedLabel;


    @FXML
    private void onExitGameButtonClicked() {
        System.exit(0);
    }

    @FXML
    private void onPlayAgainButtonClicked() {
        environmentManager.resetScore();
        inventoryService.resetPlayerCoins();
        inventoryService.getReservedTowerList().clear();
        inventoryService.getCurrentUsedTowerList().clear();
        inventoryService.getListUpgradeItemsInInventory().clear();
        environmentManager.closeCurrentScreen();
        environmentManager.launchSetupScreen();
    }
    /**
     * Initialize the losing screen with player's name, total points and coins amnd round completed
     */
    public void initialize(){
        totalScoreLabel.setText(environmentManager.getScore() + " points");
        totalCoinLabel.setText(inventoryService.getPlayerCoins() + " coins");
        playerNameLabel.setText(environmentManager.getPlayerName() + " player");
        roundCompletedLabel.setText((environmentManager.getCurrentRoundNumber() - 1) + " round completed");

    }
}

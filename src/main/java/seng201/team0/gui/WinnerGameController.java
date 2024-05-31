package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.services.EnvironmentManager;
import seng201.team0.services.InventoryService;

public class WinnerGameController {
    private EnvironmentManager environmentManager;
    private InventoryService inventoryService;
    @FXML
    private Label totalScoreLabel;

    @FXML
    private Label playerNameLabel;

    @FXML
    private Label roundCompletedLabel;

    @FXML
    private Label totalCoinLabel;

    @FXML
    private void onExitButtonClicked() {
        System.exit(0);
    }

    @FXML
    private void onNewGameButtonClicked() {
        environmentManager.resetScore();
        inventoryService.resetPlayerCoins();
        inventoryService.getReservedTowerList().clear();
        inventoryService.getCurrentUsedTowerList().clear();
        inventoryService.getListUpgradeItemsInInventory().clear();
        environmentManager.closeCurrentScreen();
        environmentManager.launchSetupScreen();

    }

    public WinnerGameController(EnvironmentManager environmentManager, InventoryService inventoryService) {
        this.environmentManager = environmentManager;
        this.inventoryService = inventoryService;
    }
    /**
     * Initialize the game winning screen, contains player's name, total points and coins and round completed
     */
    public void initialize(){
        totalScoreLabel.setText(environmentManager.getScore() + " points");
        totalCoinLabel.setText(inventoryService.getPlayerCoins() + " coins");
        playerNameLabel.setText(environmentManager.getPlayerName() + " player");
        roundCompletedLabel.setText(environmentManager.getCurrentRoundNumber() + " round completed" );

    }
}

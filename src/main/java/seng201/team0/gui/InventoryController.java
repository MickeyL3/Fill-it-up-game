package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.models.Tower;
import seng201.team0.models.UpgradeItems;
import seng201.team0.services.EnvironmentManager;
import seng201.team0.services.InventoryService;
import seng201.team0.services.ShopService;

import java.util.ArrayList;
import java.util.List;

public class InventoryController {
    @FXML
    private Label playerCoins;
    @FXML
    private Label outputMessage;
    @FXML
    private Label playerName;
    @FXML
    private Label tower1Time;
    @FXML
    private Label tower2Time;
    @FXML
    private Label tower3Time;
    @FXML
    private Label tower4Time;
    @FXML
    private Label tower5Time;
    @FXML
    private Label tower1Resource;
    @FXML
    private Label tower2Resource;
    @FXML
    private Label tower3Resource;
    @FXML
    private Label tower4Resource;
    @FXML
    private Label tower5Resource;
    @FXML
    private Label tower1Level;
    @FXML
    private Label tower2Level;
    @FXML
    private Label tower3Level;
    @FXML
    private Label tower4Level;
    @FXML
    private Label tower5Level;
    @FXML
    private Button tower1;
    @FXML
    private Button tower2;
    @FXML
    private Button tower3;
    @FXML
    private Button tower4;
    @FXML
    private Button tower5;
    @FXML
    private Button reservedTower1;
    @FXML
    private Button reservedTower2;
    @FXML
    private Button reservedTower3;
    @FXML
    private Button reservedTower4;
    @FXML
    private Button reservedTower5;
    @FXML
    private Button upgradeCard1;
    @FXML
    private Button upgradeCard2;
    @FXML
    private Button upgradeCard3;
    @FXML
    private Button upgradeCard4;
    @FXML
    private Button upgradeCard5;

    private int selectedCurrentTowerIndex = -1;
    private Tower selectedCurrentUsedTowers = null;
    private Button selectedCurrentTowerButton;
    private Tower selectedReservedTowers = null;
    private Button selectedReservedTowersButton;
    private UpgradeItems selectedUpgradeItem = null;
    private Button selectedUpgradeCardButton;
    private final EnvironmentManager environmentManager;
    private ShopService shopService;
    private final InventoryService inventoryService;
    private List<Tower> currentTowersFromInventory;
    private List<Tower> reservedTowerFromInventory;
    private List<UpgradeItems> upgradeItemsFromInventory;
    private List<Label> towerTimeList;
    private List<Label> towerResourceList;
    private List<Label> towerLevelList;
    private List<Button> towerButtons = new ArrayList<>();
    private List<Button> reservedTowersButton = new ArrayList<>();
    private List<Button> upgradeItemsButton = new ArrayList<>();

    /**
     * A constructors that pass the parameters from other classes to use in this Inventory Screen
     * @param environmentManager EnvironmentManager
     * @param shopService ShopService
     * @param inventoryService InventoryService
     */
    public InventoryController(EnvironmentManager environmentManager, ShopService shopService, InventoryService inventoryService) {
        this.environmentManager = environmentManager;
        this.shopService = shopService;
        this.inventoryService = inventoryService;
    }

    /**
     * Initialize the screen
     */
    @FXML
    public void initialize() {
        this.playerCoins.setText(String.valueOf(inventoryService.getPlayerCoins()));
        this.upgradeItemsFromInventory = inventoryService.getListUpgradeItemsInInventory();
        this.currentTowersFromInventory = inventoryService.getCurrentUsedTowerList();
        this.reservedTowerFromInventory = inventoryService.getReservedTowerList();
        this.playerName.setText(this.environmentManager.getPlayerName());
        this.towerTimeList = List.of(this.tower1Time, this.tower2Time, this.tower3Time, this.tower4Time, this.tower5Time);
        this.towerResourceList = List.of(this.tower1Resource, this.tower2Resource, this.tower3Resource, this.tower4Resource, this.tower5Resource);
        this.towerLevelList = List.of(this.tower1Level, this.tower2Level, this.tower3Level, this.tower4Level, this.tower5Level);
        this.towerButtons = List.of(this.tower1, this.tower2, this.tower3, this.tower4, this.tower5);
        this.reservedTowersButton = List.of(this.reservedTower1, this.reservedTower2, this.reservedTower3, this.reservedTower4, this.reservedTower5);
        this.upgradeItemsButton = List.of(this.upgradeCard1, this.upgradeCard2, this.upgradeCard3, this.upgradeCard4, this.upgradeCard5);

        this.showAllCurrentTower(this.towerButtons, this.currentTowersFromInventory);
        this.showAllReservedTower(this.reservedTowersButton, this.reservedTowerFromInventory);
        this.showAllUpgradeItems(this.upgradeItemsButton, this.upgradeItemsFromInventory);
    }

    /**
     * Show all the current used towers and its statistics, which is reach from inventory, on the screen
     * @param towerButtons List<Button>
     * @param currentTowersFromInventory List<Tower>
     */
    private void showAllCurrentTower(List<Button> towerButtons, List<Tower> currentTowersFromInventory){
        for(int i = 0; i < towerButtons.size(); ++i) {
            int finalI = i;
            if(finalI < currentTowersFromInventory.size()) {
                this.updateStats(currentTowersFromInventory.get(finalI), this.towerTimeList.get(finalI), this.towerResourceList.get(finalI), this.towerLevelList.get(finalI));
                (towerButtons.get(finalI)).setText(String.valueOf((currentTowersFromInventory.get(finalI)).getName()));
                (towerButtons.get(finalI)).setOnAction((event) -> {
                    towerButtons.forEach((button) -> {
                        if (button == towerButtons.get(finalI)) {
                            selectedCurrentTowerIndex = finalI;
                            selectedCurrentTowerButton = button;
                            if(finalI < currentTowersFromInventory.size()) {
                                selectedCurrentUsedTowers = currentTowersFromInventory.get(finalI);
                                System.out.println("Selected current tower: " + selectedCurrentUsedTowers.getName());
                            }
                            button.setStyle("-fx-background-color: pink; -fx-text-fill: black; -fx-font-size: 15px; -fx-font-family: Verdana; -fx-font-weight: bold; -fx-background-radius: 5;");
                        } else {
                            button.setStyle("");
                        }
                    });
                });
            }else{
                this.towerButtons.get(finalI).setText("Empty");
                this.towerTimeList.get(finalI).setText("");
                this.towerResourceList.get(finalI).setText("");
                this.towerLevelList.get(finalI).setText("");
            }
        }
    }

    /**
     * Show all the reserved tower, which is reach from inventory, on the screen
     * @param reservedTowerButton List<Button>
     * @param reservedTowerFromInventory List<Tower>
     */
    private void showAllReservedTower(List<Button> reservedTowerButton, List<Tower> reservedTowerFromInventory){
        for(int j = 0; j < reservedTowerButton.size(); ++j){
            int finalJ = j;
            if(finalJ < reservedTowerFromInventory.size())
                reservedTowerButton.get(finalJ).setText(reservedTowerFromInventory.get(finalJ).getName());
            else{
                reservedTowerButton.get(finalJ).setText("Empty");
            }
            reservedTowerButton.get(finalJ).setOnAction((event) -> {
                reservedTowerButton.forEach((button) -> {
                    if (button == reservedTowerButton.get(finalJ)) {
                        if(this.selectedUpgradeCardButton != null) {
                            this.selectedCurrentUsedTowers = null;
                            this.selectedUpgradeCardButton.setStyle("");
                            this.selectedCurrentTowerButton.setStyle("");
                        }
                        this.selectedReservedTowersButton = button;
                        if(finalJ < reservedTowerFromInventory.size()) {
                            selectedReservedTowers = reservedTowerFromInventory.get(finalJ);
                            System.out.println("Selected reserved tower: " + selectedReservedTowers.getName());
                        }
                        button.setStyle("-fx-background-color: pink; -fx-text-fill: black; -fx-font-size: 15px; -fx-font-family: Verdana; -fx-font-weight: bold; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }
    }

    /**
     * Show all the upgrade items, which is reach from inventory, on the screen
     * @param upgradeItemsButton List<Button>
     * @param upgradeItemsFromInventory List<UpgradeItems>
     */
    private void showAllUpgradeItems(List<Button> upgradeItemsButton, List<UpgradeItems> upgradeItemsFromInventory){
        for(int k = 0; k < upgradeItemsButton.size(); ++k){
            int finalK = k;
            if(finalK < upgradeItemsFromInventory.size())
                upgradeItemsButton.get(finalK).setText(upgradeItemsFromInventory.get(finalK).getName());
            else{
                upgradeItemsButton.get(finalK).setText("Empty");
            }
            upgradeItemsButton.get(finalK).setOnAction((event) -> {
                upgradeItemsButton.forEach((button) -> {
                    if (button == upgradeItemsButton.get(finalK)) {
                        if(selectedReservedTowersButton != null)
                            selectedReservedTowersButton.setStyle("");
                        this.selectedUpgradeCardButton = button;
                        if(finalK < upgradeItemsFromInventory.size()) {
                            selectedUpgradeItem = upgradeItemsFromInventory.get(finalK);
                            System.out.println("Selected item tower: " + selectedUpgradeItem.getName());
                        }
                        button.setStyle("-fx-background-color: pink; -fx-text-fill: black; -fx-font-size: 15px; -fx-font-family: Verdana; -fx-font-weight: bold; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }
    }

    /**
     * Show statistics of the input Tower on the labels on screen
     * @param tower Tower
     * @param towerTime Label
     * @param towerResource Label
     * @param towerLevel Label
     */
    private void updateStats(Tower tower, Label towerTime, Label towerResource, Label towerLevel) {
        towerTime.setText(String.valueOf(tower.getRecoveryTime()/1000)+" s");
        towerResource.setText(String.valueOf(tower.getResourceAmount()));
        towerLevel.setText(String.valueOf(tower.getLevel()));
    }

    /**
     * Button when clicked will put the selected tower in current used list back to reserved list.
     * When the size is out of list capacity it will throw an Exception with message.
     * @throws Exception
     */
    @FXML
    private void onPutBackReservedClicked() throws Exception{
        System.out.println("Click on put back");
        try {
            if (selectedCurrentUsedTowers != null) {
                int lastIndex = this.reservedTowersButton.size() - 1;
                this.outputMessage.setText("");
                inventoryService.putTowerBackToReserved(selectedCurrentUsedTowers);
                this.reservedTowersButton.get(lastIndex).setText("Empty");
                this.showAllCurrentTower(this.towerButtons, inventoryService.getCurrentUsedTowerList());
                this.showAllReservedTower(this.reservedTowersButton, inventoryService.getReservedTowerList());
                selectedCurrentTowerButton.setStyle("");
            } else
                this.outputMessage.setText("Please choose 1 Tower in current list to put back!");
        }catch (Exception e) {
            selectedCurrentTowerButton.setStyle("");
            this.outputMessage.setText(e.getMessage());
        }
        selectedCurrentUsedTowers = null;
    }

    /**
     * Button when clicked will add the selected tower in reserved list to current used list.
     * When the size is out of list capacity it will throw an Exception with message.
     * @throws Exception
     */
    @FXML
    private void onAddToCurrentClicked() throws Exception{
        System.out.println("Click on add to current");
        try {
            if (selectedReservedTowers != null) {
                int lastIndex = this.towerButtons.size() - 1;
                this.outputMessage.setText("");
                inventoryService.addTowerToCurrent(selectedReservedTowers);
                this.towerButtons.get(lastIndex).setText("Empty");
                this.showAllCurrentTower(this.towerButtons, inventoryService.getCurrentUsedTowerList());
                this.showAllReservedTower(this.reservedTowersButton, inventoryService.getReservedTowerList());
                selectedReservedTowersButton.setStyle("");
            } else
                this.outputMessage.setText("Please choose 1 Tower in reserved list to add on current!");
        }catch (Exception e){
            selectedReservedTowersButton.setStyle("");
            this.outputMessage.setText(e.getMessage());
        }
        selectedReservedTowers = null;
    }

    /**
     * Button when clicked will call upgradeTower method in InventoryService
     * If the recovery time (in ms) of the tower will be lower than 500ms, throw an Exception with message.
     * @throws Exception
     */
    @FXML
    private void onUpgradedClicked() throws Exception {
        System.out.println("onUpgradedClicked");
        try {
            if (selectedUpgradeItem != null && selectedCurrentUsedTowers != null) {
                this.outputMessage.setText("");
                inventoryService.upgradeTower(selectedUpgradeItem, selectedCurrentUsedTowers);
                this.updateStats(selectedCurrentUsedTowers, this.towerTimeList.get(selectedCurrentTowerIndex), this.towerResourceList.get(selectedCurrentTowerIndex), this.towerLevelList.get(selectedCurrentTowerIndex));
                selectedCurrentTowerButton.setText(String.valueOf(selectedCurrentUsedTowers.getName()));
                selectedUpgradeCardButton.setStyle("");
                selectedCurrentTowerButton.setStyle("");
                this.showAllUpgradeItems(this.upgradeItemsButton, this.upgradeItemsFromInventory);
            } else if (selectedUpgradeItem != null) {
                this.outputMessage.setText("Please choose the current tower to upgrade");
                selectedUpgradeCardButton.setStyle("");
                System.out.println("Please choose the current tower to upgrade");
            } else if (selectedCurrentUsedTowers != null) {
                this.outputMessage.setText("Please choose the card to upgrade for tower");
                selectedCurrentTowerButton.setStyle("");
                System.out.println("Please choose the card to upgrade for tower");
            } else
                this.outputMessage.setText("Please choose 1 upgrade item and 1 tower in current used to do the action");
                System.out.println("Please choose 1 upgrade item and 1 tower in current used to do the action");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            this.outputMessage.setText(e.getMessage());
        }
        this.showAllCurrentTower(this.towerButtons, inventoryService.getCurrentUsedTowerList());
        selectedCurrentUsedTowers = null;
        selectedUpgradeItem = null;
    }

    /**
     * Sell the selected tower in current used list
     */
    @FXML
    public void onSellClicked() throws Exception{
        System.out.println("Sell clicked");
        try {
            if (selectedCurrentUsedTowers != null) {
                this.outputMessage.setText("");
                inventoryService.sellTower(selectedCurrentUsedTowers);
                this.playerCoins.setText(String.valueOf(inventoryService.getPlayerCoins()));
                this.showAllCurrentTower(this.towerButtons, inventoryService.getCurrentUsedTowerList());
                selectedCurrentTowerButton.setStyle("");
            } else {
                this.outputMessage.setText("Please choose 1 tower from current towers to sell");
                System.out.println("Please choose 1 tower from current towers to sell");
            }
        }catch (Exception e){
            this.outputMessage.setText(e.getMessage());
        }
        selectedCurrentUsedTowers = null;

    }

    /**
     * When clicked button, close this Inventory Screen and launch the next screen
     */
    @FXML
    private void onReturnedClicked(){
        environmentManager.returnedSetupScreen();
        environmentManager.launchRoundDifficultySelectScreen();

    }
}

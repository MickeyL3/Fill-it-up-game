package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.models.PurchasableItem;
import seng201.team0.services.InventoryService;
import seng201.team0.services.ShopService;
import seng201.team0.models.UpgradeItems;
import seng201.team0.models.Tower;
import seng201.team0.services.EnvironmentManager;

import java.util.*;


public class ShopController {
    @FXML
    private Label messageAlertLabel;
    @FXML
    private Label playerCoins;
    @FXML
    private Button upgradeCard1;
    @FXML
    private Button upgradeCard2;
    @FXML
    private Button upgradeCard3;
    @FXML
    private Button tower1;
    @FXML
    private Button tower2;
    @FXML
    private Button tower3;
    private EnvironmentManager environmentManager;
    private ShopService shopService;
    private InventoryService inventoryService;
    private Tower selectedTowerInShop = null;
    private UpgradeItems selectedUpgradeItemInShop = null;
    private Button itemIsBought;
    private int selectedItemIndex = -1;
    private List<Tower> listTowersInShop = new ArrayList<>();
    private List<UpgradeItems> listUpgradeCardsInShop = new ArrayList<>();
    private final String[] TYPE_RESOURCES = {"Water", "Fire", "Gold", "Coal", "Ruby"};
    private final long[] TIME_DECREMENT = {1000, 1000, 1000};
    private final Integer[] COST = {15, 20, 25};
    private final Integer[] RESOURCE_ENHANCEMENT = {10, 12, 15};
    public ShopController(EnvironmentManager environmentManager, ShopService shopService, InventoryService inventoryService) {
        this.environmentManager = environmentManager;
        this.shopService = shopService;
        this.inventoryService = inventoryService;
    }

    /**
     * Initialize 3 random upgrade cards and 3 random towers which player can buy from shop to upgrade tower's statistics
     * or have new tower. The price is different depending on how good the upgrade card can do to the tower.
     */
    public void initialize(){
        playerCoins.setText(String.valueOf(inventoryService.getPlayerCoins()));
        List<Button> listItemsButton = List.of(upgradeCard1, upgradeCard2, upgradeCard3, tower1, tower2, tower3);
        Random randomResource = new Random();
        Random randomTimeUpgrade = new Random();
        Random randomTower = new Random();
        int enhancedResourceIndex=  randomResource.nextInt(RESOURCE_ENHANCEMENT.length);
        int timeIndex = randomTimeUpgrade.nextInt(TIME_DECREMENT.length);
        int typeResourceIndex = randomResource.nextInt(RESOURCE_ENHANCEMENT.length);
        List<Tower> randomTowers = inventoryService.getDefaultTowers();

        for(int i=0; i < 3; ++i){
            int randomTowerIndex = randomTower.nextInt(randomTowers.size());
            Tower tower = randomTowers.get(randomTowerIndex);
            listTowersInShop.add(new Tower(tower.getName(), tower.getCost(), tower.getResourceAmount(), tower.getRecoveryTime()));
        }
        shopService.setListTowersInShop(listTowersInShop);

        listUpgradeCardsInShop.addAll(List.of(
                new UpgradeItems("Upgrade Time", 0, TIME_DECREMENT[timeIndex], COST[timeIndex]),
                new UpgradeItems("Upgrade\nAmount", RESOURCE_ENHANCEMENT[enhancedResourceIndex], 0, COST[enhancedResourceIndex]),
                new UpgradeItems("Changing Type\nTower to " + TYPE_RESOURCES[typeResourceIndex], TYPE_RESOURCES[typeResourceIndex] , 20)
        ));
        shopService.setListUpgradeItemsInShop(listUpgradeCardsInShop);

        ArrayList<PurchasableItem> allItemsInShop = new ArrayList<>();
        for(UpgradeItems card : listUpgradeCardsInShop){
            allItemsInShop.add(card);
        }
        for(Tower tower : listTowersInShop){
            allItemsInShop.add(tower);
        }

        for (int i = 0; i < listItemsButton.size(); i++) {
            int finalI = i;
            PurchasableItem item = allItemsInShop.get(finalI);
            listItemsButton.get(finalI).setText(item.getName() + "\n$ " + item.getCost());
            listItemsButton.get(finalI).setOnAction(event -> {
                selectedItemIndex = finalI;
                itemIsBought = listItemsButton.get(finalI);
                if(item instanceof Tower){
                    selectedTowerInShop = shopService.getListTowersInShop().get(finalI - 3);
                    if(selectedUpgradeItemInShop != null)
                        selectedUpgradeItemInShop = null;
                }else {
                    selectedUpgradeItemInShop = shopService.getListUpgradeItemsInShop().get(finalI);
                    if (selectedTowerInShop != null)
                        selectedTowerInShop = null;
                }
                listItemsButton.forEach(button -> {
                    if (button == listItemsButton.get(finalI)) {
                        button.setStyle("-fx-background-color: pink; -fx-text-fill: black; -fx-font-size: 10px; -fx-font-family: Verdana; -fx-font-weight: bold; -fx-background-radius: 5;");
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }
    }
    public void onBuyClicked() throws Exception{
        playerCoins.setText(String.valueOf(inventoryService.getPlayerCoins()));
        int sizeBeforeBuyTower = inventoryService.getReservedTowerList().size();
        int sizeBeforeBuyItem = inventoryService.getListUpgradeItemsInInventory().size();
            if (selectedTowerInShop != null) {
                shopService.buy(selectedTowerInShop);
                if (inventoryService.getReservedTowerList().size() > sizeBeforeBuyTower)
                    itemIsBought.setDisable(true);
            } else if (selectedUpgradeItemInShop != null) {
                shopService.buy(selectedUpgradeItemInShop);
                if (inventoryService.getListUpgradeItemsInInventory().size() > sizeBeforeBuyItem)
                    itemIsBought.setDisable(true);
            }
            else
                messageAlertLabel.setText("Please choose item to buy");
        playerCoins.setText(String.valueOf(inventoryService.getPlayerCoins()));
        selectedTowerInShop = null;
        selectedUpgradeItemInShop = null;
    }

    public void onNextClicked(){
        System.out.println("Clicked on Next button");
        environmentManager.closeCurrentScreen();
        environmentManager.launchInventoryScreen();
    }
}

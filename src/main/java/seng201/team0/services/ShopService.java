package seng201.team0.services;

import seng201.team0.models.Tower;
import seng201.team0.models.UpgradeItems;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that interact with InventoryService, ShopController
 */
public class ShopService {
    private InventoryService inventoryService;
    private List<Tower> listTowersInShop = new ArrayList<Tower>();
    private List<UpgradeItems> listUpgradeItemsInShop = new ArrayList<UpgradeItems>();
    private int remainingCoins;

    /**
     * A constructor that has the Inventory Instance is parameter and initialize playerCoin
     * @param inventoryService InventoryService
     */
    public ShopService(InventoryService inventoryService){
        this.inventoryService= inventoryService;
    }

    /**
     * Update the playerCoin and listUpgradeItemsInInventory if remaining coins is enough and
     * the size listUpgradeItemsInInventory is less than 5, otherwise won't update anything if not enough coins or throw an Exception.
     * @param item UpgradeItems
     * @throws Exception
     */
    public void buy(UpgradeItems item) throws Exception{
        List<UpgradeItems> currentUpgradeItemsList = new ArrayList<UpgradeItems>(inventoryService.getListUpgradeItemsInInventory());
        if (inventoryService.getListUpgradeItemsInInventory().size() < 5) {
            if (inventoryService.getPlayerCoins() >= item.getCost()) {
                remainingCoins = inventoryService.getPlayerCoins() - item.getCost();
                inventoryService.setPlayerCoins(remainingCoins);
                currentUpgradeItemsList.add(item);
                inventoryService.setListUpgradeItemsInInventory(currentUpgradeItemsList);
            }
        }else {
            throw new Exception("Can't have more than 5 items");
        }
    }

    /**
     * Update the playerCoin and reservedTowerList if remaining coins is enough and
     * the size reservedTowerList is less than 5, otherwise won't update anything if not enough coins or throw an Exception.
     * @param tower Tower
     * @throws Exception
     */
    public void buy(Tower tower) throws Exception{
        List<Tower> reservedTowerList = new ArrayList<Tower>(inventoryService.getReservedTowerList());
        if (inventoryService.getReservedTowerList().size() < 5) {
            if (inventoryService.getPlayerCoins() >= tower.getCost()) {
                remainingCoins = inventoryService.getPlayerCoins() - tower.getCost();
                inventoryService.setPlayerCoins(remainingCoins);
                reservedTowerList.add(tower);
                inventoryService.setReservedTowerList(reservedTowerList);
            }
        }
        else {
            throw new Exception("Can't have more than 5 tower in reserved");
        }
    }

    /**
     * Set the listTowerInShop to be the input listTowersInShop
     * @param listTowersInShop List<Tower>
     */
    public void setListTowersInShop(List<Tower> listTowersInShop){
        this.listTowersInShop = listTowersInShop;
    }

    /**
     * Set the listUpgradeItemsInShop to be the input listUpgradeCardsInShop
     * @param listUpgradeCardsInShop List<UpgradeItems>
     */
    public void setListUpgradeItemsInShop(List<UpgradeItems> listUpgradeCardsInShop){
        this.listUpgradeItemsInShop = listUpgradeCardsInShop;
    }

    /**
     * Get the listUpgradeItemsInShop
     * @return listUpgradeCardsInShop
     */
    public List<UpgradeItems> getListUpgradeItemsInShop(){return this.listUpgradeItemsInShop;}

    /**
     * Get the listTowersInShop
     * @return listTowersInShop
     */
    public List<Tower> getListTowersInShop(){return this.listTowersInShop;}
}

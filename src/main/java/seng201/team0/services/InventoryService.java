package seng201.team0.services;

import seng201.team0.models.Tower;
import seng201.team0.models.UpgradeItems;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that interact with ShopService, and Controller during the game
 */
public class InventoryService {
    private int playerCoins;
    private List<Tower> reservedTowerList = new ArrayList<Tower>(5);
    private List<UpgradeItems> listUpgradeItemsInInventory = new ArrayList<UpgradeItems>(5);
    private List<Tower> currentTowerList = new ArrayList<>(5);
    private List<Tower> defaultTowers = new ArrayList<>();
    private TowerService towerService;

    /**
     * Initialize the Inventory and its properties
     */
    public InventoryService(TowerService towerService){
        this.towerService = towerService;
    }

    /**
     * Get the playerCoins
     * @return playerCoins
     */
    public int getPlayerCoins(){return playerCoins;}

    /**
     * Set the playerCoins
     * @param playerCoins int
     */
    public void setPlayerCoins(int playerCoins){this.playerCoins = playerCoins;}

    /**
     * Reset the player coin to 0 when new game start
     */
    public void resetPlayerCoins(){ this.playerCoins = 0;}

    /**
     * Sell the selected tower in current used list
     * @param tower Tower
     */
    public void sellTower(Tower tower) throws Exception{
        int sizeCurrentTower = this.currentTowerList.size();
        if(sizeCurrentTower >= 3) {
            this.setPlayerCoins(this.playerCoins + tower.getCost());
            this.currentTowerList.remove(tower);
        }
        else
            throw new Exception("You can't have less than 2 towers to play next round");
    }

    /**
     * Move the selected tower in current used list to reserved list
     * @param tower Tower
     * @throws Exception when the size reserved list exceed 5
     */
    public void putTowerBackToReserved(Tower tower) throws Exception{
        if(this.reservedTowerList.size() < 5 && this.currentTowerList.size() > 2) {

            this.reservedTowerList.add(tower);
            this.currentTowerList.remove(tower);
        }else
            throw new Exception("Can't add more towers back to reserved");
    }

    /**
     * Move the selected tower in reserved list to current used list
     * @param tower Tower
     * @throws Exception
     */
    public void addTowerToCurrent(Tower tower) throws Exception{
        if(this.currentTowerList.size() < 5) {
            this.currentTowerList.add(tower);
            this.reservedTowerList.remove(tower);
        }else
            throw new Exception("Can't add more towers into current");
    }

    /**
     * Upgrade the selected Tower's statistics and consume the selected upgrade item
     * @param item UpgradeItems
     * @param tower Tower
     * @throws Exception
     */
    public void upgradeTower(UpgradeItems item, Tower tower) throws Exception {
        try {
            if (item.getName().contains("Changing Type")) {
                towerService.changeTypeResource(tower, item.getNewTypeTower());
                System.out.println("Card type:" + item.getNewTypeTower());
                System.out.println("New type: " + tower.getName());
            } else {
                towerService.upgradeTime(tower, item.getImprovedTime());
                towerService.upgradeResourceAmount(tower, item.getImprovedAmountResource());
                towerService.levelIncrement(tower);
            }
            this.getListUpgradeItemsInInventory().remove(item);
        }
        catch (Exception e) {
            throw new Exception("You can't upgrade time lower than 0 second");
        }
    }

    /**
     * Get the default towers which can be selected by player on the setup page
     * @return defaultTowers
     */
    public List<Tower> getDefaultTowers() {
        return defaultTowers;
    }

    /**
     * set the default towers which can be selected by player on the setup page
     * @param towers List<Tower>
     */
    public void setDefaultTowers(List<Tower> towers) {
        defaultTowers = towers;
    }

    /**
     * Get list of the current towers are used in round
     * @return currentTowerList
     */
    public List<Tower> getCurrentUsedTowerList() {
        return this.currentTowerList;
    }

    /**
     * set the current towers are used
     * @param currentTowerList ArrayList<Tower>
     */
    public void setCurrentTowerList(List<Tower> currentTowerList) {
        this.currentTowerList = new ArrayList<Tower>(currentTowerList);
    }

    /**
     * Get list of the upgrade card in Inventory
     * @return listUpgradeCardsInInventory
     */
    public List<UpgradeItems> getListUpgradeItemsInInventory(){return this.listUpgradeItemsInInventory;}

    /**
     * Get list of the upgrade card in Inventory
     * @param listUpgradeCardsInInventory ArrayList<UpgradeItems>
     */
    public void setListUpgradeItemsInInventory(List<UpgradeItems> listUpgradeCardsInInventory){
        this.listUpgradeItemsInInventory = new ArrayList<>(listUpgradeCardsInInventory);
    }

    /**
     * Get list of the reserved tower in Inventory
     * @return reservedTowerList
     */
    public List<Tower> getReservedTowerList(){return this.reservedTowerList;}

    /**
     * set the current towers are used
     * @param reservedTowerList ArrayList<Tower>
     */
    public void setReservedTowerList(List<Tower> reservedTowerList) {
        this.reservedTowerList = new ArrayList<>(reservedTowerList);
    }
}

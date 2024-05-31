package seng201.team0.unittests.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Tower;
import seng201.team0.models.UpgradeItems;
import seng201.team0.services.EnvironmentManager;
import seng201.team0.services.InventoryService;
import seng201.team0.services.ShopService;
import seng201.team0.services.TowerService;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShopServiceTest {
    private ShopService shopService;
    private InventoryService inventoryService;
    private TowerService towerService;
    private Tower testTower;
    private UpgradeItems testUpgradeItem;

    /**
     * Set up before each test set playerCoin, create mocked tower, upgrade item,
     * Inventory instance, ShopService instance
     */
    @BeforeEach
    public void setUpTest(){
        towerService = new TowerService();
        inventoryService = new InventoryService(towerService);
        shopService = new ShopService(inventoryService);
        inventoryService.setPlayerCoins(30);
        Tower tower1 = new Tower("Water", 30, 20, 2000);
        Tower tower2 = new Tower("Ruby", 40, 40, 2000);
        testTower = new Tower("Fire", 40, 40, 2000);
        UpgradeItems item1 = new UpgradeItems("Upgrade Time", 0, 1000, 20);
        UpgradeItems item2 = new UpgradeItems("Upgrade Resource", 10, 0, 20);
        UpgradeItems item3 = new UpgradeItems("Change Type", "Ruby", 50);
        testUpgradeItem = new UpgradeItems("Change Type", "Coal", 10);
        List<Tower> towersInShop = List.of(tower1, tower2);
        List<UpgradeItems> itemsInShop = List.of(item1, item2, item3);
        shopService.setListTowersInShop(towersInShop);
        shopService.setListUpgradeItemsInShop(itemsInShop);
    }

    /**
     * Test the buy button functionality which added the upgrade items type item if
     * player have current coins enough or more than item's cost
     */
    @Test
    public void testBuyUpgradedItemWhenHasEnoughCoin() throws Exception{
        System.out.println("coin " +inventoryService.getPlayerCoins());
        UpgradeItems upgradeItemsToBuy = shopService.getListUpgradeItemsInShop().get(0); // Choose the first upgrade item in shop to  which cost 20 coins
        shopService.buy(upgradeItemsToBuy);
        assertEquals(1, inventoryService.getListUpgradeItemsInInventory().size()); // Test if the upgrade card is added in inventory or not
        assertEquals(10, inventoryService.getPlayerCoins()); // Test the remaining coins after success purchase
    }

    /**
     * Test the buy button functionality which added the tower in reserved list in inventory if
     * player have current coins enough or more than item's cost
     */
    @Test
    public void testBuyTowerWhenHasEnoughCoin() throws Exception{
        Tower towerToBuy = shopService.getListTowersInShop().get(0); //Choose the 1st tower in shop which cost 30
        shopService.buy(towerToBuy);
        int sizeOfReservedTowers = inventoryService.getReservedTowerList().size();
        assertEquals(1, sizeOfReservedTowers); // Test if the tower is added in reserved list or not
        assertEquals(0, inventoryService.getPlayerCoins()); // Test the remaining coins after success purchase
    }

    /**
     * Test the buy button functionality which won't added the upgrade item if
     * player have current coins enough or more than item's cost
     */
    @Test
    public void testBuyUpgradedItemWhenHasNotEnoughCoin() throws Exception{
        UpgradeItems upgradeItemsToBuy = shopService.getListUpgradeItemsInShop().get(2); // Choose the 3rd upgrade item in shop to  which cost 50 coins
        shopService.buy(upgradeItemsToBuy);
        assertEquals(0, inventoryService.getListUpgradeItemsInInventory().size()); // Test if the upgrade card is added in inventory or not
        assertEquals(30, inventoryService.getPlayerCoins()); // Test the remaining coins after failure purchase
    }

    /**
     * Test the buy button functionality which won't add tower in reserved list if
     * player have current coins less than item's cost
     */
    @Test
    public void testBuyTowerWhenHasNotEnoughCoin() throws Exception{
        Tower towerToBuy = shopService.getListTowersInShop().get(1); // Choose the second tower in shop to buy which cost 40
        shopService.buy(towerToBuy);
        int sizeOfReservedTowers = inventoryService.getReservedTowerList().size();
        assertEquals(0, sizeOfReservedTowers);
        assertEquals(30, inventoryService.getPlayerCoins()); // Test the remaining coins after failure purchase
    }

    /**
     * Test the buy button functionality which won't add the tower in to reserved list
     * and throw an Exception with message if the size of reserved towers list exceed 5
     */
    @Test
    public void testBuyTowerFailedWhenExceedTheListSize() throws Exception{
        List<Tower> listReservedTower = inventoryService.getReservedTowerList();
        listReservedTower.add(testTower);
        listReservedTower.add(testTower);
        listReservedTower.add(testTower);
        listReservedTower.add(testTower);
        listReservedTower.add(testTower);
        int sizeOfReservedTowers = inventoryService.getReservedTowerList().size();
        assertEquals(5, sizeOfReservedTowers);
        Exception exception = assertThrows(Exception.class, () -> shopService.buy(testTower));
        assertEquals("Can't have more than 5 tower in reserved", exception.getMessage());
    }

    /**
     * Test the buy button functionality which won't add the upgrade item in to inventory
     * and throw an Exception with message if the size of upgrade items list exceed 5
     */
    @Test
    public void testBuyUpgradeItemFailedWhenExceedTheListSize() throws Exception {
        List<UpgradeItems> listUpgradeItem = inventoryService.getListUpgradeItemsInInventory();
        listUpgradeItem.add(testUpgradeItem);
        listUpgradeItem.add(testUpgradeItem);
        listUpgradeItem.add(testUpgradeItem);
        listUpgradeItem.add(testUpgradeItem);
        listUpgradeItem.add(testUpgradeItem);
        int sizeOfUpgradeItems = inventoryService.getListUpgradeItemsInInventory().size();
        assertEquals(5, sizeOfUpgradeItems);
        Exception exception = assertThrows(Exception.class, () -> shopService.buy(testUpgradeItem));
        assertEquals("Can't have more than 5 items", exception.getMessage());
    }
}

package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Tower;
import seng201.team0.models.UpgradeItems;
import seng201.team0.services.InventoryService;
import seng201.team0.services.TowerService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class InventoryServiceTest {
    private InventoryService inventoryService;
    private TowerService towerService;
    private List<Tower> currentUsedTowers = new ArrayList<Tower>();
    private Tower mockedReservedTower;
    private Tower mockedCurrentUsedTower;
    private Tower mockedCurrentUsedTower2;
    private Tower mockedCurrentUsedTower3;
    private UpgradeItems mockedChangeTypeItem;
    private UpgradeItems mockedUpgradeTimeItem;
    private UpgradeItems mockedUpgradeResourceItem;

    /**
     * Set up before each test set playerCoin, create mockedReservedTower, mockedCurrentUsedTower, Inventory instance
     */
    @BeforeEach
    public void setUpTest(){
        towerService = new TowerService();
        inventoryService = new InventoryService(towerService);
        mockedReservedTower = new Tower("Water", 30, 20, 3000);
        mockedCurrentUsedTower = new Tower("Fire", 20, 20, 3000);
        mockedCurrentUsedTower2 = new Tower("Fire", 20, 20, 1000);
        mockedCurrentUsedTower3 = new Tower("Fire", 20, 20, 1000);
        inventoryService.setPlayerCoins(30);
        mockedChangeTypeItem = new UpgradeItems("Changing Type", "Gold", 20);
        mockedUpgradeTimeItem = new UpgradeItems("Upgrade Time", 0, 2000, 20);
        mockedUpgradeResourceItem = new UpgradeItems("Upgrade Resource", 10, 0, 20);
        List<UpgradeItems> listUpgradeItems = List.of(mockedUpgradeResourceItem, mockedUpgradeTimeItem, mockedChangeTypeItem);
        inventoryService.setListUpgradeItemsInInventory(listUpgradeItems);
        List<Tower> listReservedTowers = List.of(mockedReservedTower);
        currentUsedTowers.add(mockedCurrentUsedTower);
        currentUsedTowers.add(mockedCurrentUsedTower2);
        inventoryService.setReservedTowerList(listReservedTowers);
        inventoryService.setCurrentTowerList(currentUsedTowers);
    }

    /**
     * Test sellTower method when called will remove the selected tower from the current used tower list
     * and increase the coin to player coins
     */
    @Test
    void testSellTowerWhenCurrentTowerHasMoreThanTwo() throws Exception{
        currentUsedTowers.add(mockedCurrentUsedTower3);
        inventoryService.setCurrentTowerList(currentUsedTowers);
        inventoryService.sellTower(mockedCurrentUsedTower);
        int sizeCurrentTowerList = inventoryService.getCurrentUsedTowerList().size();
        assertEquals(50, inventoryService.getPlayerCoins());
        assertEquals(2, sizeCurrentTowerList);
    }

    /**
     * Test sellTower method when called will throw an Exception
     * when the number of current used towers in list is less than 2
     */
    @Test
    void testSellTowerWhenCurrentTowerHasLessThanThree() throws Exception{
        Exception exception = assertThrows(Exception.class, () -> inventoryService.sellTower(mockedCurrentUsedTower2));
        assertEquals("You can't have less than 2 towers to play next round", exception.getMessage());
    }

    /**
     * Test moving the selected current Tower to the reserved list when
     * the size of reserved Towers list is smaller than 5 and the towers in current are bigger than 2
     */
    @Test
    void testPutTowerBackToReservedWhenSizeOfReservedListSmallerThanFiveSizeCurrentBiggerThanTwo() throws Exception{
        currentUsedTowers.add(mockedCurrentUsedTower3);
        inventoryService.setCurrentTowerList(currentUsedTowers);
        assertEquals(3, inventoryService.getCurrentUsedTowerList().size());
        inventoryService.putTowerBackToReserved(mockedCurrentUsedTower);
        assertEquals(2, inventoryService.getCurrentUsedTowerList().size());
        assertEquals(2, inventoryService.getReservedTowerList().size());
    }

    /**
     * Test moving the selected current Tower to the reserved list when
     * the size of reserved Towers list is smaller than 5 and the towers in current are smaller than 2
     * throw Exception
     */
    @Test
    void testPutTowerBackToReservedWhenSizeOfReservedListSmallerThanFiveAndSizeCurrentSmallerThanThree() throws Exception{
        Exception exception = assertThrows(Exception.class, () -> inventoryService.putTowerBackToReserved(mockedCurrentUsedTower));
        assertEquals("Can't add more towers back to reserved", exception.getMessage());
    }

    /**
     * Test moving the selected current Tower to the reserved list when
     * the size of reserved towers list is equal or bigger than 5 will throw an Exception
     * @throws Exception
     */
    @Test
    void testPutTowerBackToReservedWhenSizeOfReservedListNotSmallerThanFive() throws Exception{
        List<Tower> reservedTowers = inventoryService.getReservedTowerList();
        reservedTowers.add(mockedReservedTower);
        reservedTowers.add(mockedReservedTower);
        reservedTowers.add(mockedReservedTower);
        reservedTowers.add(mockedReservedTower);
        assertEquals(5, inventoryService.getReservedTowerList().size());
        Exception exception = assertThrows(Exception.class, () -> inventoryService.putTowerBackToReserved(mockedCurrentUsedTower));
        assertEquals("Can't add more towers back to reserved", exception.getMessage());
    }

    /**
     * Test moving the selected Tower in current used towers to the reserved list when
     * the size of current used towers list is smaller than 5
     */
    @Test
    void testAddTowerToCurrentWhenSizeOfCurrentListSmallerThanFive() throws Exception{
        inventoryService.addTowerToCurrent(mockedReservedTower);
        assertEquals(0, inventoryService.getReservedTowerList().size());
        assertEquals(3, inventoryService.getCurrentUsedTowerList().size());
    }

    /**
     * Test moving the selected Tower in current used towers to the reserved list when
     * the size of current used towers list is equal or bigger than 5 will throw an Exception
     * @throws Exception
     */
    @Test
    void testAddTowerToCurrentWhenSizeOfCurrentListNotSmallerThanFive() throws Exception{
        List<Tower> currentUsedTowers = inventoryService.getCurrentUsedTowerList();
        currentUsedTowers.add(mockedCurrentUsedTower);
        currentUsedTowers.add(mockedCurrentUsedTower);
        currentUsedTowers.add(mockedCurrentUsedTower);
        Exception exception = assertThrows(Exception.class, () -> inventoryService.addTowerToCurrent(mockedReservedTower));
        assertEquals("Can't add more towers into current", exception.getMessage());
    }

    /**
     * Test when the Card is Change Type item which change the selected Tower's type to the type in item
     * and remove that upgrade item from upgrade items list
     */
    @Test
    void testUpgradeTowerWhenItemIsChangeTypeTower() throws Exception {
        inventoryService.upgradeTower(mockedChangeTypeItem, mockedCurrentUsedTower);
        String newType = mockedChangeTypeItem.getNewTypeTower();
        assertEquals(newType, mockedCurrentUsedTower.getName());
        assertEquals(2, inventoryService.getListUpgradeItemsInInventory().size());
    }

    /**
     * Test when the Card is not the change Type item which upgrade amount resource of the selected Tower's
     * and remove that upgrade item from upgrade items list
     */
    @Test
    void testUpgradeTowerWhenItemIsUpgradeResource() throws Exception {
        int amountBeforeUpgrade = mockedCurrentUsedTower.getResourceAmount();
        int expectedAmount = amountBeforeUpgrade + mockedUpgradeResourceItem.getImprovedAmountResource();
        inventoryService.upgradeTower(mockedUpgradeResourceItem, mockedCurrentUsedTower);
        assertEquals(expectedAmount, mockedCurrentUsedTower.getResourceAmount());
        assertEquals(2, inventoryService.getListUpgradeItemsInInventory().size());
    }

    /**
     * Test when the Card is not the change Type item which upgrade the time (not smaller than 500ms)
     * of the selected Tower's and remove that upgrade item from upgrade items list
     */
    @Test
    void testUpgradeTowerWhenItemIsUpgradeTimeAndNotSmallerThanLimitTime() throws Exception {
        long timeBeforeUpgrade = mockedCurrentUsedTower.getRecoveryTime();
        long expectedTime = (long) (timeBeforeUpgrade - mockedUpgradeTimeItem.getImprovedTime());
        inventoryService.upgradeTower(mockedUpgradeTimeItem, mockedCurrentUsedTower);
        long actualTime = mockedCurrentUsedTower.getRecoveryTime();
        assertEquals(expectedTime, actualTime);
        assertEquals(2, inventoryService.getListUpgradeItemsInInventory().size());
    }

    /**
     * Test when the Card is not the change Type item which upgrade the time (smaller than 500ms)
     * will throw an Exception and won't remove from upgrade items list
     * @throws Exception
     */
    @Test
    void testUpgradeTowerWhenItemIsUpgradeTimeAndSmallerThanLimitTime() throws Exception {
        int sizeUpgradeItemsBefore = inventoryService.getListUpgradeItemsInInventory().size();
        Exception exception = assertThrows(Exception.class, () -> inventoryService.upgradeTower(mockedUpgradeTimeItem, mockedCurrentUsedTower2));
        assertEquals("You can't upgrade time lower than 0 second", exception.getMessage());
        assertEquals(sizeUpgradeItemsBefore, inventoryService.getListUpgradeItemsInInventory().size());
    }
}



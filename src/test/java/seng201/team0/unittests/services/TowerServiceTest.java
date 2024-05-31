package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Tower;
import seng201.team0.services.TowerService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TowerServiceTest {
    private TowerService towerService;
    private Tower mockedTower;

    /**
     * Setup before each test, creating a mocked Tower object
     */
    @BeforeEach
    public void setupTest() {
        towerService = new TowerService();
        mockedTower = new Tower("Water", 40, 15, 3000);
    }

    /**
     * Test incrementing the amount of resource of tower after update by incrementAmount
     */
    @Test
    void testUpgradeResourceAmount(){
        towerService.upgradeResourceAmount(mockedTower,5);
        assertEquals(20, mockedTower.getResourceAmount());
    }

    /**
     * Test type of Tower after using the changeTypeResource
     */
    @Test
    void testChangeTypeResource(){
        towerService.changeTypeResource(mockedTower, "Fire");
        assertEquals("Fire", mockedTower.getName());
    }

    /**
     * Test incrementing the tower level by one
     */
    @Test
    void testLevelIncrement(){
        towerService.levelIncrement(mockedTower);
        assertEquals(2, mockedTower.getLevel());
    }

    /**
     * Pass if recoveryTime of Tower after upgraded is smaller than 0 s
     */
    @Test
    void testUpgradeTimeWithNoException() throws Exception{
        towerService.upgradeTime(mockedTower,2000);
        assertEquals(1000, mockedTower.getRecoveryTime());
    }

    /**
     * Pass if upgradeTime() throw Exception when the recovery time of Tower be smaller than 0 s after upgrade
     */
    @Test
    void testUpgradeTimeWithException(){
        Exception exception = assertThrows(Exception.class, () -> towerService.upgradeTime(mockedTower, 4000));
        assertEquals("You can't upgrade time lower than 0 second", exception.getMessage());
    }
}

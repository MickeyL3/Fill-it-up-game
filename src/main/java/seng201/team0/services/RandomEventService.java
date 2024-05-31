package seng201.team0.services;

import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class will do a random event
 */
public class RandomEventService {
    private InventoryService inventoryService;
    private final List<Integer> indexesTowerWillBeChosen = new ArrayList<Integer>();
    private Random randomEvent = new Random();
    private Random randomEventHappened = new Random();
    private boolean hasRandomEvent = false;

    /**
     * Constructor to create RandomEvent Instance
     * @param inventoryService InventoryService
     */
    public RandomEventService(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    /**
     * Get true if the random event happens
     * @return hasRandomEvent boolean
     */
    public boolean isHasRandomEvent(){return this.hasRandomEvent;}

    /**
     * Random a number if equal to the fixed number is set, the event will happen
     */
    public void dicePossibilityToHaveEvent(){
        int sizeCurrentUsedTowers = inventoryService.getCurrentUsedTowerList().size();
        if(inventoryService.getCurrentUsedTowerList().size() > 2){
        if(sizeCurrentUsedTowers > 2) {
            int randomEventHappenedNumber = randomEventHappened.nextInt(3);
            System.out.println("Random num: " + randomEventHappenedNumber);
            if (randomEventHappenedNumber == 1) {
                this.hasRandomEvent = true;
            }
            }
        }
    }

    /**
     * Set hasRandomEvent to false after it run an event
     */
    public void setHasRandomEventToFalse(){this.hasRandomEvent = false;}

    /**
     * Event occurs will remove a tower from current used towers list in inventory
     * if towers is used in previous round, the more chance it will be chosen to removed
     * @return removedTower
     */
    public Tower getAndRemoveTheRandomTowerBeChosen(){
        List<Tower> currentTowers = inventoryService.getCurrentUsedTowerList();
        indexesTowerWillBeChosen.clear();
        for(int i=0; i < currentTowers.size(); i++){
            Tower tower = currentTowers.get(i);
            if(tower.isInUse()) {
                indexesTowerWillBeChosen.add(i);
                tower.setInUseState(false);
            }
            indexesTowerWillBeChosen.add(i);
        }
        int randomEventIndex = randomEvent.nextInt(indexesTowerWillBeChosen.size() - 1);
        int randomTowerIndex = indexesTowerWillBeChosen.get(randomEventIndex);
        Tower removedTower = currentTowers.get(randomTowerIndex);
        currentTowers.remove(removedTower);
        inventoryService.setCurrentTowerList(currentTowers);
        return removedTower;
    }

}

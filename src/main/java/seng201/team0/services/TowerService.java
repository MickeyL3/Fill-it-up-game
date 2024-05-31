package seng201.team0.services;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import seng201.team0.models.Cart;
import seng201.team0.models.Tower;

public class TowerService{
    /**
     * @param incrementAmount
     * Increment the resource amount of the tower by expand
     */
    public void upgradeResourceAmount(Tower tower, int incrementAmount){
        int newAmount = tower.getResourceAmount() + incrementAmount;
        tower.setResourceAmount(newAmount);
    }

    /**
     * @param newType String
     * @param tower Tower
     * Change the resource type of the tower to newType
     */
    public void changeTypeResource(Tower tower, String newType){
        tower.setTypeResource(newType);
    }

    /**
     * Increment the level tower whenever it is upgraded by one
     */
    public void levelIncrement(Tower tower){
        int newLevel = tower.getLevel() + 1;
        tower.setLevel(newLevel);
    }

    /**
     * @param decrementTime
     * Decrement the recovery time of the tower by decrementTime
     */
    public void upgradeTime(Tower tower, long decrementTime) throws Exception{
        long timeAfterUpgrade;
        long timeBeforeUpgrade = tower.getRecoveryTime();
        if (timeBeforeUpgrade - decrementTime >= 0) {
            timeAfterUpgrade = timeBeforeUpgrade - decrementTime;
            tower.setRecoveryTime(timeAfterUpgrade);
        }
        else
            throw new Exception("You can't upgrade time lower than 0 second");
    }
}

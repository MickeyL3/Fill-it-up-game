package seng201.team0.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Purchasable class to keep track all the items can be bought from shop
 */
public class UpgradeItems extends PurchasableItem {
    private String nameItem;
    private long improvedTime = 0;
    private int improvedAmountResource = 0;
    private int cost;
    private String newTypeTower = null;

    /**
     * This constructor to initialize an upgrade card which can be used for tower
     * @param name String
     * @param improvedAmountResource int
     * @param improvedTime float
     * @param cost int
     */
    public UpgradeItems(String name, int improvedAmountResource, long improvedTime, int cost) {
        this.nameItem = name;
        this.improvedAmountResource = improvedAmountResource;
        this.improvedTime = improvedTime;
        this.cost = cost;
    }

    /**
     * This constructor to initialize the changing card which can be used to change the type of tower
     * @param name String
     * @param newTypeTower String
     * @param cost int
     */
    public UpgradeItems(String name, String newTypeTower, int cost){
        this.nameItem = name;
        this.newTypeTower = newTypeTower;
        this.cost = cost;
    }

    /**
     * @return name of the Item
     */
    public String getName(){
        return nameItem;
    };

    /**
     * @return the price to buy the item in shop
     */
    public int getCost(){
        return cost;
    }

    /**
     * @return the amount resource which can be added to upgrade the tower
     */
    public int getImprovedAmountResource(){
        return improvedAmountResource;
    }

    /**
     * Get the amount of time which can decrement the reload time of Tower
     * @return improvedTime
     */
    public long getImprovedTime(){ return improvedTime;}

    /**
     * Get the newTypeTower if the item is a changing card
     * @return newTypeTower
     */
    public String getNewTypeTower(){return this.newTypeTower;}
}

package seng201.team0.models;
/**
 * Tower class which will be interacted by user in game
 */
public class Tower extends PurchasableItem {
    private String resourceName;
    private int level;
    private int cost;
    private int resourceAmount;
    private long recoveryTime;
    private boolean inUse;
    /**
     * This constructor will create tower by the different input paras(resourceType, level, price, resourceAmount)
     */
    public Tower(String resourceName, int cost, int resourceAmount, long recoveryTime){
        this.resourceName = resourceName;
        this.level = 1;
        this.cost = cost;
        this.resourceAmount = resourceAmount;
        this.recoveryTime = recoveryTime;
        this.inUse = false;
    }

    /**
     * Get the current amount resource of the tower
     * @return resourceAmount int
     */
    public int getResourceAmount(){ return resourceAmount;}

    /**
     * Set the current amount resource of the tower
     * @param resourceAmount int
     */
    public void setResourceAmount(int resourceAmount){ this.resourceAmount = resourceAmount;}

    /**
     * Get the current type of the tower
     * @return resourceName String
     */
    public String getName(){ return resourceName;}

    /**
     * @param newName String
     * Change the resource type of the tower into newType
     */
    public void setTypeResource(String newName){ this.resourceName = newName;}

    /**
     * Get the current level of the tower
     * @return level int
     */
    public int getLevel(){ return level;}

    /**
     * Set level tower
     * @param level int
     */
    public void setLevel(int level){
        this.level = level;
    }

    /**
     * Get the price of the tower
     * @return cost int
     */
    public int getCost(){ return cost;}

    /**
     * Get the current place (in used OR in reserved) of the tower
     * @return true if the tower is using in round game OR false if the tower is in reserved
     */
    public boolean isInUse(){ return inUse;}

    /**
     * Set the property this.inUse of tower to the isUsed input
     * @param isUsed boolean
     */
    public void setInUseState(boolean isUsed){ this.inUse = isUsed;}

    /**
     * Get the current recovery time after each action
     * @return recoveryTime long
     */
    public long getRecoveryTime(){ return recoveryTime;}

    /**
     * Set the recovery time of tower
     * @param recoveryTime long
     */
    public void setRecoveryTime(long recoveryTime){this.recoveryTime = recoveryTime;}
}

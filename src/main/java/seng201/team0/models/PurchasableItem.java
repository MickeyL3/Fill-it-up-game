package seng201.team0.models;

public class PurchasableItem {
    private String nameItem;
    private int itemCost;
    /**
     * @return name of the Item
     */
    public String getName(){
        return nameItem;
    };

    /**
     * Get the cost of item
     * @return itemCost
     */
    public int getCost(){return itemCost;}
}

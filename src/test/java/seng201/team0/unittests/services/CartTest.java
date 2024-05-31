package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Cart;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    private Cart cart;
    private Tower towerSameTypeCart;
    private Tower towerDiffTypeCart;

    /**
     * Initialize a cart, and 1 tower with the same type cart's resource and 1 tower which is not
     */
    @BeforeEach
    public void setUpTest(){
        cart = new Cart("Water", 115, 40);
        towerSameTypeCart = new Tower("Water", 20, 20, 2000);
        towerDiffTypeCart = new Tower("Fire", 20, 20, 2000);
    }

    /**
     * Test the incrementAmountResourceIntoCart which the input tower is the same type resource
     */
    @Test
    public void testIncrementAmountResourceIntoCartWithInputIsSameType(){
        cart.incrementAmountResourceIntoCart(towerSameTypeCart);
        assertEquals(20, cart.getCurrentAmountOfCart());
        cart.incrementAmountResourceIntoCart(towerSameTypeCart);
        assertEquals(40, cart.getCurrentAmountOfCart());
    }

    /**
     * Test the incrementAmountResourceIntoCart which the input tower is different type resource
     */
    @Test
    public void testIncrementAmountResourceIntoCartWithInputIsNotSameType(){
        cart.incrementAmountResourceIntoCart(towerDiffTypeCart);
        assertEquals(0, cart.getCurrentAmountOfCart());
    }

    /**
     * Test when the cart is full, it won't add more
     */
    @Test
    public void testIncrementAmountResourceIntoCartWhenCartFull(){
        cart.incrementAmountResourceIntoCart(towerSameTypeCart);
        assertEquals(20, cart.getCurrentAmountOfCart());
        cart.incrementAmountResourceIntoCart(towerSameTypeCart);
        assertEquals(40, cart.getCurrentAmountOfCart());
        cart.incrementAmountResourceIntoCart(towerSameTypeCart);
        assertEquals(40, cart.getCurrentAmountOfCart());
    }

    /**
     * Test the state if cart has been filled up or not
     */
    @Test
    public void testIsCartFilledUp(){
        cart.incrementAmountResourceIntoCart(towerSameTypeCart);
        assertFalse(cart.isCartFilledUp());
        cart.incrementAmountResourceIntoCart(towerSameTypeCart);
        assertTrue(cart.isCartFilledUp());
        cart.incrementAmountResourceIntoCart(towerSameTypeCart);
        assertTrue(cart.isCartFilledUp());
    }

}

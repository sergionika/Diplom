package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient firstIngredient;

    @Mock
    private Ingredient secondIngredient;

    @Mock
    private Ingredient thirdIngredient;

    private Burger burger;

    @Before
    public void createBurgerObject(){
        burger = new Burger();
    }

    @Test
    public void checkSetBun(){
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void checkAddIngredient(){
        burger.addIngredient(firstIngredient);
        int size = burger.ingredients.size();
        Ingredient currentIngredient = burger.ingredients.get(0);
        assertEquals(1, size);
        assertEquals(firstIngredient, currentIngredient);
    }

    @Test
    public void checkRemoveIngredientToIndex() {
        burger.ingredients.add(firstIngredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void checkMoveIngredientToNewIndex(){
        burger.ingredients.add(firstIngredient);
        burger.ingredients.add(secondIngredient);
        burger.ingredients.add(thirdIngredient);
        burger.moveIngredient(0, 2);
        assertEquals(secondIngredient, burger.ingredients.get(0));
        assertEquals(thirdIngredient, burger.ingredients.get(1));
        assertEquals(firstIngredient, burger.ingredients.get(2));
    }

    @Test
    public void checkGetPrice() {
        burger.setBuns(bun);
        burger.ingredients.add(firstIngredient);
        burger.ingredients.add(secondIngredient);

        float bunPrice = 100;
        float firstIngredientPrice = 20;
        float secondIngredientPrice = 30;
        float expectedPrice = 250;

        when(bun.getPrice()).thenReturn(bunPrice);
        when(firstIngredient.getPrice()).thenReturn(firstIngredientPrice);
        when(secondIngredient.getPrice()).thenReturn(secondIngredientPrice);

        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.001);
    }

    @Test
    public void checkGetReceiptWithIngredients() {
        burger.setBuns(bun);
        burger.ingredients.add(firstIngredient);
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(firstIngredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(firstIngredient.getName()).thenReturn("chipotle");
        Mockito.when(firstIngredient.getPrice()).thenReturn(50f);
        String exReceipt = String.format(
                "(==== black bun ====)%n" + "= sauce chipotle =%n" +
                        "(==== black bun ====)%n" + "%nPrice: %f%n", 250f);
        String realReceipt = burger.getReceipt();
        assertEquals(exReceipt, realReceipt);
    }
}

package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerRemoveIngredientTest {

    private final int index;

    private final Ingredient firstIngredient = Mockito.mock(Ingredient.class);
    private final Ingredient secondIngredient = Mockito.mock(Ingredient.class);
    private final Ingredient thirdIngredient = Mockito.mock(Ingredient.class);

    private Burger burger;

    public BurgerRemoveIngredientTest(int index) {
        this.index = index;
    }

    @Parameterized.Parameters(name = "Удаление ингредиента с индексом {0}")
    public static Object[][] getData() {
        return new Object[][]{
                {0},
                {1},
                {2}
        };
    }

    @Before
    public void setUp() {
        burger = new Burger();
        burger.ingredients.add(firstIngredient);
        burger.ingredients.add(secondIngredient);
        burger.ingredients.add(thirdIngredient);
    }

    @Test
    public void checkRemoveIngredientByIndex() {
        List<Ingredient> expectedIngredients = new ArrayList<>(burger.ingredients);
        expectedIngredients.remove(index);
        burger.removeIngredient(index);
        assertEquals(expectedIngredients, burger.ingredients);
    }
}

package com.zummore.effectivejava.ryudung.ch02.item02.pizza;

import ch02.item02.domains.pizza.Calzone;
import ch02.item02.domains.pizza.NyPizza;
import ch02.item02.domains.pizza.Pizza;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class PizzaTest {

    @Test
    public void 피자만들기() {
        //given
        NyPizza nyPizza = (NyPizza) NyPizza.NYBuilder(NyPizza.Size.LARGE)
                .addTopping(Pizza.Topping.HAM)
                .addTopping(Pizza.Topping.ONION)
                .addTopping(Pizza.Topping.MUSHROOM)
                .build();
        //given
        Calzone calzonePizza = (Calzone) Calzone.CalzoneBuilder()
                .sauceInside()
                .build();

        //then
        Assert.assertThat(nyPizza.getSize(), is(NyPizza.Size.LARGE));
        Assert.assertThat(calzonePizza.isSauceInside(), is(true));
    }
}

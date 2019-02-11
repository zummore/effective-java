package com.zummore.effectivejava.joonghyun;


import com.zummore.effectivejava.joonghyun.equals.EqualsFailClazz;
import com.zummore.effectivejava.joonghyun.equals.EqualsSuccessClazz;
import com.zummore.effectivejava.joonghyun.generic.Collections;
import com.zummore.effectivejava.joonghyun.generic.SubA;
import com.zummore.effectivejava.joonghyun.generic.SubB;
import com.zummore.effectivejava.joonghyun.generic.Super;
import com.zummore.effectivejava.joonghyun.pizza.Calzone;
import com.zummore.effectivejava.joonghyun.pizza.NyPizza;
import com.zummore.effectivejava.joonghyun.pizza.Pizza;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Joonghyun {

    @Test
    public void 피자_만들기() {
        Pizza pizza = new NyPizza.Builder(NyPizza.Size.SMALL)
                .addTopping(Pizza.Topping.SAUSAGE)
                .addTopping(Pizza.Topping.ONION)
                .builder();

        Pizza calzone = new Calzone.Builder()
                .addTopping(Pizza.Topping.HAM)
                .sauceInside()
                .builder();
    }

    @Test
    public void 타임스템프_데이트_비교() {
        Date date = new Date(); //Date (상위객체)
        Timestamp stamp = new Timestamp(date.getTime());    //Timestamp (하위객체)

        assertTrue(date.equals(stamp));  //date 는 stamp 와 같다.
        assertFalse(stamp.equals(date)); //인스턴스가 stamp 가 아니면 같은객체로 보지 않는다. 맞는말이네

        assertTrue(stamp.compareTo(date) == 0);     //stamp compareTo = 0 으로 date 와 stamp 가 같다.
        assertTrue(date.compareTo(stamp) == 1);     //date compareTo = 1 으로 date 가 stamp 보다 크다.
    }

    @Test
    public void equals_비교조심() {
        EqualsFailClazz a = new EqualsFailClazz("joong", 99L);
        EqualsFailClazz b = new EqualsFailClazz("joong", 990L);
        assertFalse(a.equals(b));

        EqualsSuccessClazz c = new EqualsSuccessClazz("joong", 99L);
        EqualsSuccessClazz d = new EqualsSuccessClazz("joong", 990L);
        assertTrue(c.equals(d));
    }

    @Test
    public void 제네릭_super_extends() {
        List<SubA> subAList = new ArrayList<>();
        subAList.add(new SubA("a"));
        subAList.add(new SubA("b"));

        List<SubB> subBList = new ArrayList<>();
        subBList.add(new SubB("1"));
        subBList.add(new SubB("2"));

        List<Super> supers = new ArrayList<>();

        //supers 에 sub a 를 추가.
        Collections.copy(supers, subAList);
        assertEquals(2, supers.size());

        //supers 에 sub b 를 추가.
        Collections.copy2(supers, subBList);
        assertEquals(4, supers.size());
    }
}

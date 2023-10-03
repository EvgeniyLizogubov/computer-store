package com.github.evgenylizogubov.computerstore.web;

import com.github.evgenylizogubov.computerstore.model.FormFactor;
import com.github.evgenylizogubov.computerstore.model.PersonalComputer;

public class PersonalComputerTestData {
    public static final MatcherFactory.Matcher<PersonalComputer> PERSONAL_COMPUTER_MATCHER = MatcherFactory.usingEqualsComparator(PersonalComputer.class);
    
    public static final int HP_ID = 1;
    public static final int ASUS_ID = 2;
    public static final int APPLE_ID = 3;
    
    public static final PersonalComputer hp = PersonalComputer.builder().id(HP_ID).seriesNumber(HP_ID * 1000)
            .manufacturer("HP").price(HP_ID * 100).amountInStock(HP_ID * 10).formFactor(FormFactor.DESKTOP).build();
    public static final PersonalComputer asus = PersonalComputer.builder().id(ASUS_ID).seriesNumber(ASUS_ID * 1000)
            .manufacturer("ASUS").price(ASUS_ID * 100).amountInStock(ASUS_ID * 10).formFactor(FormFactor.NETTOP).build();
    public static final PersonalComputer apple = PersonalComputer.builder().id(APPLE_ID).seriesNumber(APPLE_ID * 1000)
            .manufacturer("APPLE").price(APPLE_ID * 100).amountInStock(APPLE_ID * 10).formFactor(FormFactor.MONOBLOCK).build();
    
    public static PersonalComputer getNew() {
        return PersonalComputer.builder().id(null).seriesNumber(123).manufacturer("MSI").price(50).amountInStock(2)
                .formFactor(FormFactor.NETTOP).build();
    }
    
    public static PersonalComputer getUpdated() {
        return PersonalComputer.builder().id(APPLE_ID).seriesNumber(APPLE_ID * 1000)
                .manufacturer("APPLE").price(APPLE_ID * 100 * 10).amountInStock(APPLE_ID * 10 / 2).formFactor(FormFactor.MONOBLOCK).build();
    }
}

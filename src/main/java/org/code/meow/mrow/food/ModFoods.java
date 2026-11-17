package org.code.meow.mrow.food;


import net.minecraft.component.type.FoodComponent;

public class ModFoods {
    public static final FoodComponent CATNIP = new FoodComponent.Builder().nutrition(3).saturationModifier(0.5f).alwaysEdible().build();
}

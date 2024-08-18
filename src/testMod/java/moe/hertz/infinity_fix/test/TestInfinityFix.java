package moe.hertz.infinity_fix.test;
import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.test.*;
import net.minecraft.item.Items;
import net.minecraft.test.TestContext;
import net.minecraft.util.Identifier;
import net.minecraft.registry.DynamicRegistryManager;


public class TestInfinityFix {

    private static final String TEMPLATE = "infinity_fix-testmod:1x2x1_chamber";
    @GameTest(templateName = TEMPLATE,tickLimit =  200)
    public void testInfinityWithMendingBow(TestContext context)
    {
        var world = context.getWorld();
        DynamicRegistryManager dynamicRegistryManager = world.getRegistryManager();
        RegistryEntry<Enchantment> infinity = dynamicRegistryManager.get(RegistryKeys.ENCHANTMENT).getEntry(Identifier.ofVanilla("infinity")).get();
        RegistryEntry<Enchantment> mending = dynamicRegistryManager.get(RegistryKeys.ENCHANTMENT).getEntry(Identifier.ofVanilla("mending")).get();

        var bow = Items.BOW.getDefaultStack();
        bow.addEnchantment(infinity,1);
        var IsMendingCompat = bow.canBeEnchantedWith(mending, EnchantingContext.ACCEPTABLE);
        System.out.println("IsMendingCompat"+ IsMendingCompat);
        var bow2 = Items.BOW.getDefaultStack();
        bow2.addEnchantment(mending,1);
        context.assertTrue(IsMendingCompat,"mending is able to be added after Infinity");

        var IsInfinityCompat = bow2.canBeEnchantedWith(infinity,EnchantingContext.ACCEPTABLE);
        System.out.println("IsMendingCompat"+ IsInfinityCompat);
        context.assertTrue(IsInfinityCompat,"Infinity is able to be added after Mending");

        context.complete();

    }
    @GameTest(templateName = TEMPLATE,tickLimit =  200)
    public void testInfinityBowWithPower(TestContext context)
    {
        var world = context.getWorld();
        DynamicRegistryManager dynamicRegistryManager = world.getRegistryManager();
        RegistryEntry<Enchantment> infinity = dynamicRegistryManager.get(RegistryKeys.ENCHANTMENT).getEntry(Identifier.ofVanilla("infinity")).get();
        RegistryEntry<Enchantment> power = dynamicRegistryManager.get(RegistryKeys.ENCHANTMENT).getEntry(Identifier.ofVanilla("power")).get();

        var bow = Items.BOW.getDefaultStack();
        bow.addEnchantment(infinity,1);
        var IsPowerCompat = bow.canBeEnchantedWith(power, EnchantingContext.ACCEPTABLE);
        context.assertTrue(IsPowerCompat,"Power is compatible with infinity");


        context.complete();

    }


    @GameTest(templateName = TEMPLATE,tickLimit =  200)
    public void testInfinityWithMendingCrossbow(TestContext context)
    {
        var world = context.getWorld();
        DynamicRegistryManager dynamicRegistryManager = world.getRegistryManager();
        RegistryEntry<Enchantment> infinity = dynamicRegistryManager.get(RegistryKeys.ENCHANTMENT).getEntry(Identifier.ofVanilla("infinity")).get();
        RegistryEntry<Enchantment> mending = dynamicRegistryManager.get(RegistryKeys.ENCHANTMENT).getEntry(Identifier.ofVanilla("mending")).get();

        var bow = Items.CROSSBOW.getDefaultStack();
        bow.addEnchantment(infinity,1);
        var IsMendingCompat = bow.canBeEnchantedWith(mending, EnchantingContext.ACCEPTABLE);
        System.out.println("IsMendingCompat"+ IsMendingCompat);
        var bow2 = Items.CROSSBOW.getDefaultStack();
        bow2.addEnchantment(mending,1);
        context.assertTrue(IsMendingCompat,"mending is able to be added after Infinity");

        var IsInfinityCompat = bow2.canBeEnchantedWith(infinity,EnchantingContext.ACCEPTABLE);
        System.out.println("IsMendingCompat"+ IsInfinityCompat);
        context.assertTrue(IsInfinityCompat,"Infinity is able to be added after Mending");

        context.complete();

    }
    @GameTest(templateName = TEMPLATE,tickLimit =  200)
    public void testInfinityWithMendingCrossbowFalse(TestContext context)
    {
        TestUtil.runCommand(context,"gamerule infinity-fix:CrossbowMending false");
        var world = context.getWorld();
        DynamicRegistryManager dynamicRegistryManager = world.getRegistryManager();
        RegistryEntry<Enchantment> infinity = dynamicRegistryManager.get(RegistryKeys.ENCHANTMENT).getEntry(Identifier.ofVanilla("infinity")).get();
        RegistryEntry<Enchantment> mending = dynamicRegistryManager.get(RegistryKeys.ENCHANTMENT).getEntry(Identifier.ofVanilla("mending")).get();

        var bow = Items.CROSSBOW.getDefaultStack();
        bow.addEnchantment(infinity,1);
        var IsMendingCompat = bow.canBeEnchantedWith(mending, EnchantingContext.ACCEPTABLE);
        System.out.println("IsMendingCompat"+ IsMendingCompat);
        var bow2 = Items.CROSSBOW.getDefaultStack();
        bow2.addEnchantment(mending,1);
        context.assertFalse(IsMendingCompat,"Gamerule prevents mending from being added");

        var IsInfinityCompat = bow2.canBeEnchantedWith(infinity,EnchantingContext.ACCEPTABLE);
        System.out.println("IsMendingCompat"+ IsInfinityCompat);
        context.assertFalse(IsInfinityCompat,"Infinity is unable to be added...");

        context.complete();

    }

}


package moe.hertz.infinity_fix;


import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.fabricmc.fabric.api.item.v1.EnchantmentEvents;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import java.util.EventListener;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;

public class InfinityFix implements net.fabricmc.api.ModInitializer {

    public static final GameRules.Key<BooleanRule> ALLOW_CROSSBOW_INFINITY_MENDING = GameRuleRegistry.register("infinity-fix:CrossbowMending", GameRules.Category.PLAYER,GameRuleFactory.createBooleanRule(true));
    public static ServerWorld currentWorld;
    @Override
    public void onInitialize() {

        EnchantmentEvents.ALLOW_ENCHANTING.register(this::canEnchantTogether);
        ServerWorldEvents.LOAD.register(this::setCurrentWorld);
    }



    private void setCurrentWorld(MinecraftServer minecraftServer, ServerWorld serverWorld) {
        currentWorld = serverWorld;
    }




    private TriState canEnchantTogether(RegistryEntry<Enchantment> enchantmentRegistryEntry, ItemStack stack, EnchantingContext enchantingContext) {

        if(stack.getItem() == Items.CROSSBOW) {
                enchantmentRegistryEntry.getKeyOrValue();

                if (enchantmentRegistryEntry.matchesKey(Enchantments.INFINITY) || enchantmentRegistryEntry.matchesKey(Enchantments.MENDING) || enchantmentRegistryEntry.matchesKey(Enchantments.POWER)) {
                    return TriState.TRUE;

                }


            }
        System.out.println("returning default");
        return TriState.DEFAULT;

    }


}

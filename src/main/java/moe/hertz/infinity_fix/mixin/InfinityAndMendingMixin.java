package moe.hertz.infinity_fix.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Enchantment.class)
public class InfinityAndMendingMixin {
    @ModifyReturnValue(method = "canBeCombined", at = @At("RETURN"))
    private static boolean modifyCanBeCombinedReturnValue(boolean original, RegistryEntry<Enchantment> first, RegistryEntry<Enchantment> second) {
        if (first.matchesKey(Enchantments.INFINITY) && second.matchesKey(Enchantments.MENDING) ||
                first.matchesKey(Enchantments.MENDING) && second.matchesKey(Enchantments.INFINITY)) {
            return true;
        }
        return original;
    }
}
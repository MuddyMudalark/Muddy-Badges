package muddy.badges.items.custom;

import muddy.badges.utilities.ModComponents;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class SignableItem extends Item {
    private PlayerEntity owner;
    public ComponentType<UUID> OWNER;

    public SignableItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            // Only sign once; remove this guard if you want to allow overwriting
            if (stack.get(ModComponents.OWNER) == null) {
                stack.set(ModComponents.OWNER, user.getUuid());
                stack.set(ModComponents.OWNER_NAME, user.getName().getString());
            }
        }

        // Return the actual stack you modified
        if(stack.get(ModComponents.OWNER) != null) {
            return TypedActionResult.pass(stack);
        }
        return TypedActionResult.success(stack, stack.get(ModComponents.OWNER) == null);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        UUID ownerId = stack.get(ModComponents.OWNER);
        if (ownerId != null) {
            String name = stack.get(ModComponents.OWNER_NAME);

            if (name == null || name.isEmpty()) name = ownerId.toString();

            tooltip.add(Text.translatable("item.muddys-badges.tooltip-0")
                            .append(Text.literal(name).formatted(Formatting.BLUE))
            );
        }
        else {
            tooltip.add(Text.translatable("item.muddys-badges.tooltip-1"));
        }
    }


}

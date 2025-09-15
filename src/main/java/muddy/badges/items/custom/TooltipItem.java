package muddy.badges.items.custom;

import muddy.badges.items.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class TooltipItem extends Item {
    public TooltipItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if(stack.isOf(ModItems.STRENGTH_SCALE)) {
            tooltip.set(0, Text.of("Wait aren't they supposed to be blue?"));
        } else {
            //Some other Tooltip
        }


        super.appendTooltip(stack, context, tooltip, type);
    }
}

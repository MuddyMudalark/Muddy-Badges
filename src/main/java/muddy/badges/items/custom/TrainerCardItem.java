package muddy.badges.items.custom;

import muddy.badges.utilities.ClientPlayerOpenScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TrainerCardItem extends Item {
    public TrainerCardItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient && user instanceof ClientPlayerOpenScreen duck) {
            duck.muddybadges$openCardScreen(Text.of(""));
            return TypedActionResult.success(this.getDefaultStack(), true);
        }

        return TypedActionResult.success(this.getDefaultStack(), true);
    }
}

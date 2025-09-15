package muddy.badges.items;

import muddy.badges.MuddysBadges;
import muddy.badges.items.custom.SignableItem;
import muddy.badges.items.custom.TooltipItem;
import muddy.badges.items.custom.TrainerCardItem;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.List;

public class ModItems {
    public static List<Text> textList = List.of(Text.of("Wait aren't they supposed to be blue?"));

    public static Item register(Item item, String id) {
        // Create the identifier for the item.
        Identifier itemID = Identifier.of(MuddysBadges.MOD_ID, id);

        // Register the item.
        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);

        // Return the registered item!
        return registeredItem;
    }

    public static final Item GIMMICK_BADGE = register(
            new SignableItem(new Item.Settings().rarity(Rarity.RARE).maxCount(1)),
            "gimmick_badge"
    );

    public static final Item ARMOURED_BADGE = register(
            new SignableItem(new Item.Settings().rarity(Rarity.RARE).maxCount(1)),
            "armoured_badge"
    );

    public static final Item TRAINER_CARD = register(
            new TrainerCardItem(new Item.Settings().maxCount(1)),
            "trainer_card"
    );

    public static final Item STRENGTH_SCALE = register(
            new TooltipItem(new Item.Settings()),
            "strength_scale"
    );

    public static void initialize() {

    }

}

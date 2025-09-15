package muddy.badges;

import muddy.badges.items.ModItems;
import muddy.badges.utilities.ModComponents;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MuddysBadges implements ModInitializer {
	public static final String MOD_ID = "muddys-badges";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModComponents.initialize();

		LOGGER.info("- P.S. Stealing Your IP!");
	}
}
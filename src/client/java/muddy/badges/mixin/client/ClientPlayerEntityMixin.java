package muddy.badges.mixin.client;

import muddy.badges.screens.TrainerCardScreen;
import muddy.badges.utilities.ClientPlayerOpenScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin implements ClientPlayerOpenScreen {
    @Unique
    @Override
    public void muddybadges$openCardScreen(Text title) {
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.setScreen(new TrainerCardScreen(Text.empty()));
    }
}

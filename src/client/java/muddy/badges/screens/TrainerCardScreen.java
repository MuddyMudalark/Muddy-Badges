package muddy.badges.screens;

import com.cobblemon.mod.common.Cobblemon;
import muddy.badges.MuddysBadges;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.network.packet.c2s.play.ClientStatusC2SPacket;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class TrainerCardScreen extends Screen {
    public static final Identifier CARD_TEXTURE = Identifier.of(MuddysBadges.MOD_ID,"textures/gui/trainer_card.png");
    protected int x;
    protected int y;
    protected int backgroundWidth = 254;
    protected int backgroundHeight = 254;

    static DecimalFormat DECIMAL_FORMAT = (DecimalFormat) Util.make(new DecimalFormat("########0.00"), (decimalFormat) ->
            decimalFormat.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT)));
    static StatFormatter TIME;

    public TrainerCardScreen(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        this.x = (this.width - this.backgroundWidth) / 2;
        this.y = (this.height - this.backgroundHeight) / 2;

        this.client.getNetworkHandler().sendPacket(new ClientStatusC2SPacket(ClientStatusC2SPacket.Mode.REQUEST_STATS));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);

        super.render(context, mouseX, mouseY, delta);

        assert client != null;
        assert client.player != null;

        int bgWidth = 254;

        int bgX = (this.width - bgWidth) / 2;
        int bgY = (this.textRenderer.fontHeight + 41);

        String trainerText = "Trainer: " + this.client.player.getName().getString();
        String uuidText = "ID: " + this.client.player.getUuid().toString().substring(0,8) + "...";
        String seenText = "Pokémon Seen: " + Cobblemon.playerDataManager.getPokedexData(client.player.getUuid()).toClientData().getSpeciesRecords().size();
        String timeText = "Time Played: " + timeCalc(this.client.player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.PLAY_TIME, StatFormatter.TIME)));

        int trainerX = bgX + 23;
        int trainerY = bgY + 20;

        int seenX = bgX + 23;
        int seenY = trainerY + 25;

        int timeX = bgX + 23;
        int timeY = seenY + 25;

        context.drawText(this.textRenderer, trainerText, trainerX, trainerY, 0xFFFFFFFF, false);
        context.drawText(this.textRenderer, uuidText, trainerX + 129, trainerY, 0xFFFFFFFF, false);

        context.drawText(this.textRenderer, seenText, seenX, seenY, 0xFFFFFFFF, false);

        context.drawText(this.textRenderer, timeText, timeX, timeY, 0xFFFFFFFF, false);
    }

    public String timeCalc(float time) {
        double d = (double)time / (double)20.0F;
        double e = d / (double)60.0F;
        double f = e / (double)60.0F;
        double g = f / (double)24.0F;
        double h = g / (double)365.0F;
        if (h > (double)0.5F) {
            return DECIMAL_FORMAT.format(h) + " y";
        } else if (g > (double)0.5F) {
            return DECIMAL_FORMAT.format(g) + " d";
        } else if (f > (double)0.5F) {
            return DECIMAL_FORMAT.format(f) + " h";
        } else {
            return e > (double)0.5F ? DECIMAL_FORMAT.format(e) + " m" : d + " s";
        }
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderInGameBackground(context);
        context.drawTexture(CARD_TEXTURE, (this.width - 254) / 2, -10, 1, 1, 254, 254);
    }
}

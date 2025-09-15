package muddy.badges.utilities;

import com.mojang.serialization.Codec;
import muddy.badges.MuddysBadges;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.UUID;

public class ModComponents {
    public static final ComponentType<UUID> OWNER = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(MuddysBadges.MOD_ID, "owner"),
            ComponentType.<UUID>builder()
                    .codec(Uuids.CODEC)               // how it saves to disk
                    .packetCodec(Uuids.PACKET_CODEC)  // how it syncs over network
                    .build()
    );

    public static final ComponentType<String> OWNER_NAME = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(MuddysBadges.MOD_ID, "owner_name"),
            ComponentType.<String>builder()
                    .codec(Codec.STRING)
                    .packetCodec(net.minecraft.network.codec.PacketCodecs.STRING)
                    .build()
    );

    public static void initialize() {}
}

package org.code.meow.mrow.networking;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import org.code.meow.Meow;

public record DoubleJumpC2SPayload() implements CustomPayload {
	public static final PacketCodec<RegistryByteBuf, DoubleJumpC2SPayload> PC = PacketCodec.unit(new DoubleJumpC2SPayload());
	public static final Identifier ID = Meow.id("double_jump");
	public static final Id<DoubleJumpC2SPayload> TYPE = new Id<>(ID);

	@Override
	public Id<? extends CustomPayload> getId() {
		return TYPE;
	}
}

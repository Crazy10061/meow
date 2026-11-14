package org.code.meow.mrow.networking;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.code.meow.Meow;

public record DoubleJumpC2SPayload() implements CustomPacketPayload {
	public static final StreamCodec<RegistryFriendlyByteBuf, DoubleJumpC2SPayload> PC = StreamCodec.unit(new DoubleJumpC2SPayload());
	public static final Identifier ID = Meow.id("double_jump");
	public static final Type<DoubleJumpC2SPayload> TYPE = new Type<>(ID);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}

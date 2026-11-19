package meow.transfems.delight.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

public record EstrogenInfo(boolean startedHRT, int injections, long lastInjection) {
	public static final Codec<EstrogenInfo> C = RecordCodecBuilder.create(inst -> inst.group(
		Codec.BOOL.fieldOf("startedHRT").forGetter(EstrogenInfo::startedHRT),
		Codec.INT.fieldOf("injections").forGetter(EstrogenInfo::injections),
		Codec.LONG.fieldOf("lastInjection").forGetter(EstrogenInfo::lastInjection)
	).apply(inst, EstrogenInfo::new));

	public static final PacketCodec<RegistryByteBuf, EstrogenInfo> PC = PacketCodec.tuple(
		PacketCodecs.BOOLEAN,
		EstrogenInfo::startedHRT,
		PacketCodecs.INTEGER,
		EstrogenInfo::injections,
		PacketCodecs.LONG,
		EstrogenInfo::lastInjection,
		EstrogenInfo::new
	);

	public static EstrogenInfo fromDefault() {
		return new EstrogenInfo(false, 0, 0);
	}
}

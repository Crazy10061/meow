package meow.transfems.delight.config;

import folk.sisby.kaleido.api.WrappedConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.SerializedNameConvention;
import folk.sisby.kaleido.lib.quiltconfig.api.metadata.NamingSchemes;

@SerializedNameConvention(NamingSchemes.SNAKE_CASE)
public class MeowConfig extends WrappedConfig {
	@Comment("The time (in ticks) that needs to pass between estrogen injections to not overdose")
	public long estrogenDecay = 28800L;
}

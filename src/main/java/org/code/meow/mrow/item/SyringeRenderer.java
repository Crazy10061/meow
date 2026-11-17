package org.code.meow.mrow.item;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.block.model.BlockModelPart;
import net.minecraft.client.render.block.model.BlockStateModel;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.command.RenderCommandQueue;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import org.code.meow.Meow;
import org.code.meow.mixin.SpriteContentsAccessor;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class SyringeRenderer implements SpecialModelRenderer<RegistryKey<Block>> {
	public static final Identifier TEX = Meow.id("textures/item/syringe.png");
	public static HashMap<RegistryKey<Block>, Identifier> CACHE = new HashMap<>();
	public NativeImage syringeImageDefault;
	public NativeImage syringeImage;

	public SyringeRenderer() {
		try (InputStream stream = MinecraftClient.getInstance()
			.getResourceManager().getInputStreamOrThrow(TEX)) {
			byte[] bytes = stream.readAllBytes();

			syringeImageDefault = NativeImage.read(bytes);
			syringeImage = NativeImage.read(bytes);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void render(@Nullable RegistryKey<Block> item, MatrixStack matrixStack, OrderedRenderCommandQueue submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
		if (item == null) return;

		Identifier image = getOrGenerateTexture(item);

		if (image == null) return;

		submitNodeCollector.submitCustom(matrixStack, RenderLayers.itemEntityTranslucentCull(image), (pose, buf) -> {
			buf.vertex(pose, 0, 0, 8.5f)
				.color(-1)
				.texture(0, 1)
				.overlay(overlayCoords)
				.light(lightCoords)
				.normal(pose, 0, 0, 1);

			buf.vertex(pose, 1, 0, 8.5f)
				.color(-1)
				.texture(1, 1)
				.overlay(overlayCoords)
				.light(lightCoords)
				.normal(pose, 0, 0, 1);

			buf.vertex(pose, 1, 1, 8.5f)
				.color(-1)
				.texture(1, 0)
				.overlay(overlayCoords)
				.light(lightCoords)
				.normal(pose, 0, 0, 1);

			buf.vertex(pose, 0, 1, 8.5f)
				.color(-1)
				.texture(0, 0)
				.overlay(overlayCoords)
				.light(lightCoords)
				.normal(pose, 0, 0, 1);


			buf.vertex(pose, 1, 0, 7.5f)
				.color(-1)
				.texture(0, 1)
				.overlay(overlayCoords)
				.light(lightCoords)
				.normal(pose, 0, 0, -1);

			buf.vertex(pose, 0, 0, 7.5f)
				.color(-1)
				.texture(1, 1)
				.overlay(overlayCoords)
				.light(lightCoords)
				.normal(pose, 0, 0, -1);

			buf.vertex(pose, 0, 1, 7.5f)
				.color(-1)
				.texture(1, 0)
				.overlay(overlayCoords)
				.light(lightCoords)
				.normal(pose, 0, 0, -1);

			buf.vertex(pose, 1, 1, 7.5f)
				.color(-1)
				.texture(0, 0)
				.overlay(overlayCoords)
				.light(lightCoords)
				.normal(pose, 0, 0, -1);
		});
	}

	private Identifier registerDynamicTexture(RegistryKey<Block> item, NativeImage image) {
		Identifier id = Meow.id(
			"textures/dynamic/syringe/"
				+ item.getValue().getNamespace() + "/"
				+ item.getValue().getPath() + ".png"
		);

		NativeImageBackedTexture texture = new NativeImageBackedTexture(
			() -> "syringe/" + item.getValue(),
			image
		);

		MinecraftClient.getInstance().getTextureManager().registerTexture(id, texture);

		return id;
	}

	private Identifier getOrGenerateTexture(RegistryKey<Block> item) {
		if (CACHE.containsKey(item))
			return CACHE.get(item);

		Optional<RegistryEntry.Reference<Block>> block = Registries.BLOCK.getOptional(item);
		if (block.isEmpty()) return null;

		Block blockVal = block.get().value();
		BlockState defaultBlockState = blockVal.getDefaultState();

		BlockStateModel model = MinecraftClient.getInstance()
			.getBakedModelManager()
			.getBlockStateModels()
			.getModel(defaultBlockState);

		ArrayList<BlockModelPart> output = new ArrayList<>();
		model.addParts(Random.create(0), output);

		BlockModelPart part = output.getFirst();

		List<BakedQuad> allQuads = part.getQuads(Direction.UP);
		Sprite sprite = !allQuads.isEmpty() ? allQuads.getFirst().materialInfo().sprite() : part.particleMaterial().sprite();
		NativeImage topImage = ((SpriteContentsAccessor)sprite.getContents()).meow$getImage();

		for (int y = 0; y < topImage.getHeight(); y++) {
			for (int x = 0; x < topImage.getWidth(); x++) {
				topImage.setColorArgb(x, y, topImage.getColorArgb(x, y));
			}
		}

		NativeImage composed = new NativeImage(syringeImage.getWidth(), syringeImage.getHeight(), false);
		composed.copyFrom(syringeImage);

		for (int y = 0; y < syringeImage.getHeight(); y++) {
			for (int x = 0; x < syringeImage.getWidth(); x++) {
				var sw = sprite.getContents().getWidth();
				var sh = sprite.getContents().getHeight();

				if (composed.getColorArgb(x, y) == 0xFFFF0000) {
					int sx = x * sw / syringeImage.getWidth();
					int sy = y * sh / syringeImage.getHeight();

					composed.setColorArgb(x, y, topImage.getColorArgb(sx, sy));
				}
			}
		}

		Identifier id = registerDynamicTexture(item, composed);

		CACHE.put(item, id);
		return id;
	}

	@Override
	public void collectVertices(Consumer<Vector3fc> consumer) {
	}

	@Override
	public @Nullable RegistryKey<Block> getData(ItemStack stack) {
		return stack.is(ModItems.SYRINGE) ? stack.get(ModDataComponents.INSIDE) : null;
	}

	public static class Unbaked implements SpecialModelRenderer.Unbaked<RegistryKey<Block>> {
		public static final Unbaked INSTANCE = new Unbaked();
		public static final MapCodec<Unbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

		@Override
		public @Nullable SpecialModelRenderer<RegistryKey<Block>> bake(@NonNull BakeContext context) {
			return new SyringeRenderer();
		}

		@Override
		public MapCodec<? extends SpecialModelRenderer.Unbaked<RegistryKey<Block>>> getCodec() {
			return MAP_CODEC;
		}
	}
}

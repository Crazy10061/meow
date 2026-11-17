package org.code.meow.data;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import org.code.meow.Meow;

public class ModDataAttachments {
	public static final AttachmentType<EstrogenInfo> ESTROGEN_INFO = AttachmentRegistry.create(Meow.id("estrogen_info"), builder -> builder
		.initializer(EstrogenInfo::fromDefault)
		.persistent(EstrogenInfo.C)
		.syncWith(EstrogenInfo.PC, AttachmentSyncPredicate.all()));

	public static void init() {
		Meow.LOGGER.info("registering ModDataAttachments for mod " + Meow.MOD_ID);
	}
}

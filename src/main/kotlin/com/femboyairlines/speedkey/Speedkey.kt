package com.femboyairlines.speedkey

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.minecraft.util.Identifier

class Speedkey : ModInitializer {

	companion object {
		val namespace = "speedkey"

		fun idOf(name: String): Identifier {
			return Identifier.of(namespace, name)
		}
	}
	override fun onInitialize() {

		PayloadTypeRegistry.playC2S().register(SpeedPayload.ID, SpeedPayload.CODEC);
		SpeedkeyPacketHandler.register()

	}

}

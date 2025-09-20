package com.femboyairlines.speedkey

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.network.message.MessageType
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket
import net.minecraft.network.packet.s2c.play.TitleS2CPacket
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.Formatting


object SpeedkeyPacketHandler {
	fun register() {
		ServerPlayNetworking.registerGlobalReceiver(SpeedPayload.ID) { payload: SpeedPayload, context: ServerPlayNetworking.Context ->
			val player = context.player()
			if (player != null) {
				handleSpeedPacket(player, payload.speed)
			}
		}
	}

	private fun handleSpeedPacket(player: ServerPlayerEntity, speed: Int) {
		if (speed == 0) {
			player.removeStatusEffect(StatusEffects.HASTE)
		} else {
			player.removeStatusEffect(StatusEffects.HASTE)
			val haste = StatusEffectInstance(StatusEffects.HASTE, -1, speed - 1, false, false)
			player.addStatusEffect(haste)
		}
	}
}
package com.femboyairlines.speedkey.client

import com.femboyairlines.speedkey.SpeedPayload
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.mixin.client.rendering.InGameHudMixin
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.hud.InGameHud
import net.minecraft.client.gui.hud.InGameOverlayRenderer
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import org.lwjgl.glfw.GLFW


class SpeedkeyClient : ClientModInitializer {

	override fun onInitializeClient() {
		// Create a keybind that will send the packet to the server
		val keybinds = listOf(
			KeyBinding(
				"key.speedkey.1xspeed",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_END,
				"category.speedkey.speed"
			),
			KeyBinding(
				"key.speedkey.2xspeed",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_0,
				"category.speedkey.speed"
			),
			KeyBinding(
				"key.speedkey.5xspeed",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_MINUS,
				"category.speedkey.speed"
			),
			KeyBinding(
				"key.speedkey.10xspeed",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_EQUAL,
				"category.speedkey.speed"
			),
			KeyBinding(
				"key.speedkey.100xspeed",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_BACKSPACE,
				"category.speedkey.speed"
			)
		)
		// Register the keybind
		for (bind in keybinds) {
			KeyBindingHelper.registerKeyBinding(bind)
		}
		ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick { client: MinecraftClient ->
			val tinted = true
			while (keybinds[0].wasPressed()) {
				val speed = 0
				ClientPlayNetworking.send(SpeedPayload(speed))
				client.inGameHud.setOverlayMessage(toMsOverlay("1"), tinted)
			}
			while (keybinds[1].wasPressed()) {
				val speed = 5
				ClientPlayNetworking.send(SpeedPayload(speed))
				client.inGameHud.setOverlayMessage(toMsOverlay(hl2m(speed)), tinted)
			}
			while (keybinds[2].wasPressed()) {
				val speed = 20
				ClientPlayNetworking.send(SpeedPayload(speed))
				client.inGameHud.setOverlayMessage(toMsOverlay(hl2m(speed)), tinted)
			}
			while (keybinds[3].wasPressed()) {
				val speed = 45
				ClientPlayNetworking.send(SpeedPayload(speed))
				client.inGameHud.setOverlayMessage(toMsOverlay(hl2m(speed)), tinted)
			}
			while (keybinds[4].wasPressed()) {
				val speed = 495
				ClientPlayNetworking.send(SpeedPayload(speed))
				client.inGameHud.setOverlayMessage(toMsOverlay(hl2m(speed)), tinted)
			}
		})


	}
	fun toMsOverlay(text: String): Text {
		return Text.literal("Mining Speed: ${text}x").formatted(Formatting.GOLD)
	}
	fun hl2m(level: Int): String {
		val num = 1 + (0.2 * level)
		return num.toString()
	}
}

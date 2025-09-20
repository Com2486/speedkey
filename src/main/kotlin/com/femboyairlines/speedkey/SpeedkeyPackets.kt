package com.femboyairlines.speedkey

import io.netty.buffer.Unpooled
import net.minecraft.network.PacketByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.math.BlockPos

data class SpeedPayload(val speed: Int) : CustomPayload {
	companion object {
		val ID = CustomPayload.Id<SpeedPayload>(Speedkey.idOf("speedkeypayload"))
		val CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, SpeedPayload::speed, ::SpeedPayload)
	}

	override fun getId(): CustomPayload.Id<out CustomPayload> {
		return ID
	}
	fun toPacketByteBuf(): PacketByteBuf {
		val buf = PacketByteBuf(Unpooled.buffer())
		buf.writeInt(speed)
		return buf
	}
}
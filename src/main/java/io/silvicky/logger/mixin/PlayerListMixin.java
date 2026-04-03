package io.silvicky.logger.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.Connection;
import net.minecraft.server.players.PlayerList;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.silvicky.logger.MyLogger.loginLogger;

@Mixin(PlayerList.class)
public class PlayerListMixin
{
    @Inject(method = "placeNewPlayer",at= @At(value = "INVOKE", target = "Lnet/minecraft/server/players/PlayerList;broadcastSystemMessage(Lnet/minecraft/network/chat/Component;Z)V"))
    public void inject1(Connection connection, ServerPlayer player, CommonListenerCookie clientData, CallbackInfo ci, @Local(name = "component") MutableComponent mutableText)
    {
        loginLogger.info(mutableText.getString());
    }
}

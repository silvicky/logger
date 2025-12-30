package io.silvicky.logger.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ConnectedClientData;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.silvicky.logger.MyLogger.loginLogger;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin
{
    @Inject(method = "onPlayerConnect",at= @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Z)V"))
    public void inject1(ClientConnection connection, ServerPlayerEntity player, ConnectedClientData clientData, CallbackInfo ci, @Local MutableText mutableText)
    {
        loginLogger.info(mutableText.getString());
    }
}

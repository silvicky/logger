package io.silvicky.logger.mixin;

import net.minecraft.network.DisconnectionInfo;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.silvicky.logger.MyLogger.loginLogger;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin
{
    @Shadow
    public ServerPlayerEntity player;
    @Inject(method = "onDisconnected",at= @At(value = "HEAD"))
    public void inject1(DisconnectionInfo info, CallbackInfo ci)
    {
        loginLogger.info(String.format("%s lost connection: %s", player.getStringifiedName(), info.reason().getString()));
    }
}

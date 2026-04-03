package io.silvicky.logger.mixin;

import net.minecraft.network.DisconnectionDetails;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.silvicky.logger.MyLogger.loginLogger;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerGamePacketListenerImplMixin
{
    @Shadow
    public ServerPlayer player;
    @Inject(method = "onDisconnect",at= @At(value = "HEAD"))
    public void inject1(DisconnectionDetails info, CallbackInfo ci)
    {
        loginLogger.info(String.format("%s lost connection: %s", player.getPlainTextName(), info.reason().getString()));
    }
}

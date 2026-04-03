package io.silvicky.logger.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.chat.ChatType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.silvicky.logger.MyLogger.chatLogger;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin
{
    @Inject(method = "logChatMessage",at= @At(value = "TAIL"))
    public void inject1(Component message, ChatType.Bound params, String prefix, CallbackInfo ci, @Local(name = "decoratedMessage") String string)
    {
        chatLogger.info(string);
    }
}

package io.silvicky.logger.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.message.MessageType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.silvicky.logger.MyLogger.chatLogger;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin
{
    @Inject(method = "logChatMessage",at= @At(value = "TAIL"))
    public void inject1(Text message, MessageType.Parameters params, String prefix, CallbackInfo ci, @Local(ordinal = 1) String string)
    {
        chatLogger.info(string);
    }
}

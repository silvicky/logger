package io.silvicky.logger.mixin;

import com.mojang.brigadier.ParseResults;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.silvicky.logger.MyLogger.cmdLogger;

@Mixin(CommandManager.class)
public class CommandManagerMixin
{
    @Inject(method = "execute",at= @At(value = "HEAD"))
    public void inject1(ParseResults<ServerCommandSource> parseResults, String command, CallbackInfo ci)
    {
        ServerCommandSource source=parseResults.getContext().getSource();
        if(source.getName().equals("@"))return;
        cmdLogger.info(String.format("%s: %s",source.getName(),command));
    }
}

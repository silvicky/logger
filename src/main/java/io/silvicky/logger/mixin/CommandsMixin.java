package io.silvicky.logger.mixin;

import com.mojang.brigadier.ParseResults;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.silvicky.logger.MyLogger.cmdLogger;

@Mixin(Commands.class)
public class CommandsMixin
{
    @Inject(method = "performCommand",at= @At(value = "HEAD"))
    public void inject1(ParseResults<CommandSourceStack> parseResults, String command, CallbackInfo ci)
    {
        CommandSourceStack source=parseResults.getContext().getSource();
        if(source.getTextName().equals("@"))return;
        cmdLogger.info(String.format("%s: %s",source.getTextName(),command));
    }
}

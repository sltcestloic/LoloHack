package org.bleachhack.mixin;

import net.minecraft.entity.vehicle.BoatEntity;
import org.bleachhack.BleachHack;
import org.bleachhack.event.events.BoatMoveEvent;
import org.bleachhack.module.ModuleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BoatEntity.class)
public class MixinBoatEntity {

    @Shadow private boolean pressingLeft;

    @Shadow
    private boolean pressingRight;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/BoatEntity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V"))
    private void onTickInvokeMove(CallbackInfo info) {
        BleachHack.eventBus.post(BoatMoveEvent.get((BoatEntity) (Object) this));
    }

    @Redirect(method = "updatePaddles", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/vehicle/BoatEntity;pressingLeft:Z"))
    private boolean onUpdatePaddlesPressingLeft(BoatEntity boat) {
        if (ModuleManager.getModule("BoatFly").isEnabled()) return false;
        return pressingLeft;
    }

    @Redirect(method = "updatePaddles", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/vehicle/BoatEntity;pressingRight:Z"))
    private boolean onUpdatePaddlesPressingRight(BoatEntity boat) {
        if (ModuleManager.getModule("BoatFly").isEnabled()) return false;
        return pressingRight;
    }
}

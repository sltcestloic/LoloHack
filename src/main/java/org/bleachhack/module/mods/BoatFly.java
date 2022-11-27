package org.bleachhack.module.mods;

import net.minecraft.util.math.Vec3d;
import org.bleachhack.event.events.BoatMoveEvent;
import org.bleachhack.eventbus.BleachSubscribe;
import org.bleachhack.module.Module;
import org.bleachhack.module.ModuleCategory;
import org.bleachhack.setting.module.SettingSlider;

public class BoatFly extends Module {

    public BoatFly() {
        super("BoatFly",
                KEY_UNBOUND,
                ModuleCategory.MOVEMENT,
                "Allows you to fly while on a boat",
                new SettingSlider("Speed", 0, 50, 10, 0));
    }

    @BleachSubscribe
    public void onBoatMove(BoatMoveEvent event) {
        if (event.boat.getPrimaryPassenger() != mc.player) return;

        event.boat.setYaw(mc.player.getYaw());

        float speed = getSetting(0).asSlider().getValueFloat();

        Vec3d forward = new Vec3d(0, 0, speed).rotateY(-(float) Math.toRadians(mc.player.getYaw()));
        Vec3d strafe = forward.rotateY((float) Math.toRadians(90));

        // Apply velocity
        event.boat.setVelocity(Vec3d.ZERO);
        if (mc.options.jumpKey.isPressed())
            event.boat.setVelocity(event.boat.getVelocity().add(0, speed, 0));
        if (mc.options.sprintKey.isPressed())
            event.boat.setVelocity(event.boat.getVelocity().add(0, -speed, 0));
        if (mc.options.backKey.isPressed())
            event.boat.setVelocity(event.boat.getVelocity().add(-forward.x, 0, -forward.z));
        if (mc.options.forwardKey.isPressed())
            event.boat.setVelocity(event.boat.getVelocity().add(forward.x, 0, forward.z));
        if (mc.options.leftKey.isPressed())
            event.boat.setVelocity(event.boat.getVelocity().add(strafe.x, 0, strafe.z));
        if (mc.options.rightKey.isPressed())
            event.boat.setVelocity(event.boat.getVelocity().add(-strafe.x, 0, -strafe.z));

    }
}

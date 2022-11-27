package org.bleachhack.event.events;

import net.minecraft.entity.vehicle.BoatEntity;
import org.bleachhack.event.Event;

public class BoatMoveEvent extends Event {
    private static final BoatMoveEvent INSTANCE = new BoatMoveEvent();

    public BoatEntity boat;

    public static BoatMoveEvent get(BoatEntity entity) {
        INSTANCE.boat = entity;
        return INSTANCE;
    }
}
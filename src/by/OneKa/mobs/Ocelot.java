package by.OneKa.mobs;

import by.OneKa.utils.RandomName;
import net.minecraft.server.v1_13_R2.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftOcelot;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.lang.reflect.Field;
import java.util.Set;

public class Ocelot {

    private static Field goalSelectors;

    public static void initPathfinder(){
        try {
            goalSelectors = PathfinderGoalSelector.class.getDeclaredField("b");
            goalSelectors.setAccessible(true);
        } catch (NoSuchFieldException e){
            System.out.println(e);
        }
    }

    public static void spawnOcelot(Location loc) throws IllegalAccessException {
        Entity entity = loc.getWorld().spawnEntity(loc, EntityType.OCELOT);
        EntityOcelot ocelot = ((CraftOcelot) entity).getHandle();

        Set<?> b = (Set<?>) goalSelectors.get(ocelot.goalSelector);
        b.clear();

        ocelot.goalSelector.a(1, new PathfinderGoalLookAtPlayer(ocelot,EntityHuman.class, 3.0F));
        ocelot.goalSelector.a(2, new PathfinderGoalMeleeAttack(ocelot,1,true));
        ocelot.targetSelector.a(1, new PathfinderGoalHurtByTarget(ocelot,true));
        ocelot.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(ocelot, EntityHuman.class, true));

        ocelot.setCustomName(new ChatComponentText(RandomName.getRandomName()));
        ocelot.setCustomNameVisible(true);
    }
}

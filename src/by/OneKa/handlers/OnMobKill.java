package by.OneKa.handlers;

import by.OneKa.Main;
import by.OneKa.database.MySQL;
import net.minecraft.server.v1_13_R2.DataWatcher;
import net.minecraft.server.v1_13_R2.EntityHuman;
import net.minecraft.server.v1_13_R2.PacketPlayOutEntityMetadata;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class OnMobKill implements Listener {
    public OnMobKill(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void EntityDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Zombie) {
            try {
                by.OneKa.mobs.Ocelot.spawnOcelot(event.getEntity().getLocation());
            } catch (IllegalAccessException e) {
                System.out.println(e);
            }
        }
        if (event.getEntity() instanceof Ocelot && event.getEntity().getCustomName() != null) {
            MySQL.insert(event.getEntity().getKiller().getName(), event.getEntity().getCustomName());

            Location loc = event.getEntity().getLocation();
            Item entityItem = loc.getWorld().dropItem(loc, new ItemStack(Material.LEATHER));
            entityItem.setCustomNameVisible(true);
            DataWatcher data;
            for(Player p : Bukkit.getServer().getOnlinePlayers()){
                System.out.println(p.getName());
                entityItem.setCustomName(p.getName());
                data = ((CraftEntity)entityItem).getHandle().getDataWatcher();
                PacketPlayOutEntityMetadata packetPlayOutEntityMetadata = new PacketPlayOutEntityMetadata(entityItem.getEntityId(),data,true);
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packetPlayOutEntityMetadata);
            }
        }
    }
}

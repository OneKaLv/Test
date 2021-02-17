package by.OneKa;

import by.OneKa.database.MySQL;
import by.OneKa.handlers.OnMobKill;
import by.OneKa.mobs.Ocelot;
import by.OneKa.utils.RandomName;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        new MySQL("localhost","base1","root","1234");
        new OnMobKill(this);
        Ocelot.initPathfinder();
        RandomName.initLetters();
    }

    @Override
    public void onDisable() {

    }
}

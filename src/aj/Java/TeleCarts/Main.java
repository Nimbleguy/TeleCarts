package aj.Java.TeleCarts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.plugin.java.JavaPlugin;

import aj.Java.TeleCarts.Events.ScheduledListner;
import aj.Java.TeleCarts.Listener.TeleCartsListener;

public class Main extends JavaPlugin {
	public static HashMap<Minecart, Location> cartlocs = new HashMap<Minecart, Location>(100);
	public static List<Minecart> carts = new ArrayList<Minecart>(100);
	@Override
	public void onEnable(){
		this.getServer().getPluginManager().registerEvents(new TeleCartsListener(), this);
		getLogger().info("Loading all minecarts...");
		for (World world : Bukkit.getServer().getWorlds()) {
			for(Entity e : world.getEntities()){
				if(e.getType().compareTo(EntityType.MINECART) == 0){
					getLogger().info("Added cart with ID " + e.getEntityId());
					carts.add((Minecart)e);
					cartlocs.put((Minecart)e, e.getLocation());
				}
			}
		}
		getLogger().info("Done!");
		getLogger().info("Scheduling MinecartMoveEvent...");
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new ScheduledListner(), 0, 1);
		getLogger().info("Done!");
		getLogger().info("TeleCarts is ready to teleport!");
	}
	@Override
	public void onDisable(){
		getLogger().info("Removing minecarts from database to be reload-freindly...");
		carts.clear();
		getLogger().info("Cart database cleared!");
		cartlocs.clear();
		getLogger().info("Cart location storage cleared!");
		getLogger().info("Done!");
		getLogger().info("Telecarts has telepoted away.");
	}
}

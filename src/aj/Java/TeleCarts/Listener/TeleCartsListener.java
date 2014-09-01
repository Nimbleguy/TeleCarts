package aj.Java.TeleCarts.Listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.util.Vector;

import aj.Java.TeleCarts.Main;
import aj.Java.TeleCarts.Events.MinecartMoveEvent;

//import aj.Java.TeleCarts.Main;

public class TeleCartsListener implements Listener {
	@EventHandler
	public void playerMove(PlayerMoveEvent ev) {
		Player p = ev.getPlayer();
		Entity e = p.getVehicle();
		// Main.me.getLogger().info("Player moved");
		if (e != null && p.hasPermission("telecarts.use")) {
			// Main.me.getLogger().info("Player in vehicle");
			if (isMinecart(e.getType())) {
				Minecart m = (Minecart) e;
				Location l = new Location(m.getWorld(), m.getLocation().getX(),
						m.getLocation().getY() - 2, m.getLocation().getZ());
				Block b = l.getBlock();
				// Main.me.getLogger().info("Player in minecart");
				if (b != null) {
					if (b.getState() instanceof Sign) {
						// Main.me.getLogger().info("There is a sign");
						Sign s = (Sign) b.getState();
						String[] strs = s.getLines();
						if (strs[0].compareToIgnoreCase(ChatColor.BOLD + ""
								+ ChatColor.RED + "[TeleCarts]") == 0) {
							// Main.me.getLogger().info("SIGN CORRECT! TELEPORTING");
							int x = Integer.valueOf(strs[1]);
							int y = Integer.valueOf(strs[2]);
							int z = Integer.valueOf(strs[3]);
							Vector v = m.getVelocity();
							Location toPlayer = new Location(m.getWorld(), x,
									y + .5, z);
							Location toCart = new Location(m.getWorld(), x,
									y + .5, z);
							World world = toPlayer.getWorld();
							world.loadChunk(world.getChunkAt(toPlayer));
							Block bl = world.getBlockAt(toCart);
							bl.getType();
							toPlayer.setPitch(p.getLocation().getPitch());
							toCart.setPitch(m.getLocation().getPitch());
							toPlayer.setYaw(p.getLocation().getYaw());
							toCart.setYaw(m.getLocation().getYaw());
							p.leaveVehicle();
							// m.remove();
							m.teleport(toCart);
							p.teleport(toPlayer);
							// m = (Minecart)world.spawnEntity(toCart,
							// EntityType.MINECART);
							m.setVelocity(v);
							m.setPassenger(p);
						}
						else if (strs[0].compareToIgnoreCase(ChatColor.BOLD
								+ "" + ChatColor.RED + "[WorldCart]") == 0) {
							String[] st = strs[1].split(",");
							int x = Integer.valueOf(st[0]);
							int y = Integer.valueOf(st[1]);
							int z = Integer.valueOf(st[2]);
							World w = null;
							for(World world : Bukkit.getWorlds()){
								System.out.println(world.getName());
								if(world.getName().equalsIgnoreCase(strs[2])){
									w = world;
								}
							}
							Vector v = m.getVelocity();
							Location toPlayer = new Location(w, x,
									y + .5, z);
							Location toCart = new Location(w, x,
									y + .5, z);
							World world = toPlayer.getWorld();
							world.loadChunk(world.getChunkAt(toPlayer));
							Block bl = world.getBlockAt(toCart);
							bl.getType();
							toPlayer.setPitch(p.getLocation().getPitch());
							toCart.setPitch(m.getLocation().getPitch());
							toPlayer.setYaw(p.getLocation().getYaw());
							toCart.setYaw(m.getLocation().getYaw());
							toCart.setWorld(w);
							toPlayer.setWorld(w);
							p.leaveVehicle();
							// m.remove();
							m.teleport(toCart);
							p.teleport(toPlayer);
							// m = (Minecart)world.spawnEntity(toCart,
							// EntityType.MINECART);
							m.setVelocity(v);
							m.setPassenger(p);
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void cartMove(MinecartMoveEvent e) {
		Minecart m = e.getCart();
		if (m.getPassenger() != null) {
			if (m.getPassenger().getType().compareTo(EntityType.PLAYER) != 0) {
				Entity p = m.getPassenger();
				Location l = new Location(m.getWorld(), m.getLocation().getX(),
						m.getLocation().getY() - 2, m.getLocation().getZ());
				Block b = l.getBlock();
				// Main.me.getLogger().info("Player in minecart");
				if (b != null) {
					if (b.getState() instanceof Sign) {
						// Main.me.getLogger().info("There is a sign");
						Sign s = (Sign) b.getState();
						String[] strs = s.getLines();
						if (strs[0].compareToIgnoreCase(ChatColor.BOLD + ""
								+ ChatColor.RED + "[TeleCarts]") == 0) {
							// Main.me.getLogger().info("SIGN CORRECT! TELEPORTING");
							int x = Integer.valueOf(strs[1]);
							int y = Integer.valueOf(strs[2]);
							int z = Integer.valueOf(strs[3]);
							Vector v = m.getVelocity();
							Location toPlayer = new Location(m.getWorld(), x,
									y + .5, z);
							Location toCart = new Location(m.getWorld(), x,
									y + .5, z);
							World world = toPlayer.getWorld();
							world.loadChunk(world.getChunkAt(toPlayer));
							Block bl = world.getBlockAt(toCart);
							bl.getType();
							toPlayer.setPitch(p.getLocation().getPitch());
							toCart.setPitch(m.getLocation().getPitch());
							toPlayer.setYaw(p.getLocation().getYaw());
							toCart.setYaw(m.getLocation().getYaw());
							// p.leaveVehicle();
							// m.remove();
							m.teleport(toCart);
							p.teleport(toPlayer);
							// m = (Minecart)world.spawnEntity(toCart,
							// EntityType.MINECART);
							m.setVelocity(v);
						}
						/**
						if (strs[0].compareToIgnoreCase(ChatColor.BOLD + ""
								+ ChatColor.RED + "[TeleCarts]") == 0) {
							// Main.me.getLogger().info("SIGN CORRECT! TELEPORTING");
							int x = Integer.valueOf(strs[1]);
							int y = Integer.valueOf(strs[2]);
							int z = Integer.valueOf(strs[3]);
							Vector v = m.getVelocity();
							Location toPlayer = new Location(m.getWorld(), x,
									y + .5, z);
							Location toCart = new Location(m.getWorld(), x,
									y + .5, z);
							World world = toPlayer.getWorld();
							world.loadChunk(world.getChunkAt(toPlayer));
							Block bl = world.getBlockAt(toCart);
							bl.getType();
							toPlayer.setPitch(p.getLocation().getPitch());
							toCart.setPitch(m.getLocation().getPitch());
							toPlayer.setYaw(p.getLocation().getYaw());
							toCart.setYaw(m.getLocation().getYaw());
							// p.leaveVehicle();
							// m.remove();
							m.teleport(toCart);
							p.teleport(toPlayer);
							// m = (Minecart)world.spawnEntity(toCart,
							// EntityType.MINECART);
							m.setVelocity(v);
							m.setPassenger(p);
						} */ else if (strs[0].compareToIgnoreCase(ChatColor.BOLD
								+ "" + ChatColor.RED + "[WorldCart]") == 0) {
							// Main.me.getLogger().info("SIGN CORRECT! TELEPORTING");
							String[] st = strs[1].split(",");
							int x = Integer.valueOf(st[0]);
							int y = Integer.valueOf(st[1]);
							int z = Integer.valueOf(st[2]);
							Vector v = m.getVelocity();
							World w = null;
							for(World world : Bukkit.getWorlds()){
								if(world.getName().equalsIgnoreCase(strs[2])){
									w = world;
								}
							}
							if(w == null){
							}
							Location toPlayer = new Location(
									w, x, y + .5, z);
							Location toCart = new Location(
									w, x, y + .5, z);
							World world = toPlayer.getWorld();
							world.loadChunk(world.getChunkAt(toPlayer));
							Block bl = world.getBlockAt(toCart);
							bl.getType();
							toPlayer.setPitch(p.getLocation().getPitch());
							toCart.setPitch(m.getLocation().getPitch());
							toPlayer.setYaw(p.getLocation().getYaw());
							toCart.setYaw(m.getLocation().getYaw());
							toPlayer.setWorld(w);
							toCart.setWorld(w);
							// p.leaveVehicle();
							// m.remove();
							m.teleport(toCart);
							p.teleport(toPlayer);
							m.setPassenger(p);
							// m = (Minecart)world.spawnEntity(toCart,
							// EntityType.MINECART);
							m.setVelocity(v);
						}
					}
				}
			}
		} else {
			Location l = new Location(m.getWorld(), m.getLocation().getX(), m
					.getLocation().getY() - 2, m.getLocation().getZ());
			Block b = l.getBlock();
			// Main.me.getLogger().info("Player in minecart");
			if (b != null) {
				if (b.getState() instanceof Sign) {
					// Main.me.getLogger().info("There is a sign");
					Sign s = (Sign) b.getState();
					String[] strs = s.getLines();
					if (strs[0].compareToIgnoreCase(ChatColor.BOLD + ""
							+ ChatColor.RED + "[TeleCarts]") == 0) {
						// Main.me.getLogger().info("SIGN CORRECT! TELEPORTING");
						int x = Integer.valueOf(strs[1]);
						int y = Integer.valueOf(strs[2]);
						int z = Integer.valueOf(strs[3]);
						Vector v = m.getVelocity();
						Location toCart = new Location(m.getWorld(), x, y + .5,
								z);
						World world = toCart.getWorld();
						world.loadChunk(world.getChunkAt(toCart));
						Block bl = world.getBlockAt(toCart);
						bl.getType();
						toCart.setPitch(m.getLocation().getPitch());
						toCart.setYaw(m.getLocation().getYaw());
						// m.remove();
						m.teleport(toCart);
						// m = (Minecart)world.spawnEntity(toCart,
						// EntityType.MINECART);
						m.setVelocity(v);
					} else if (strs[0].compareToIgnoreCase(ChatColor.BOLD + ""
							+ ChatColor.RED + "[WorldCart]") == 0) {
						String[] st = strs[1].split(",");
						int x = Integer.valueOf(st[0]);
						int y = Integer.valueOf(st[1]);
						int z = Integer.valueOf(st[2]);
						World w = null;
						for(World world : Bukkit.getWorlds()){
							if(world.getName().equalsIgnoreCase(strs[2])){
								w = world;
							}
						}
						Vector v = m.getVelocity();
						Location toCart = new Location(
								w, x, y + .5, z);
						World world = toCart.getWorld();
						world.loadChunk(world.getChunkAt(toCart));
						Block bl = world.getBlockAt(toCart);
						bl.getType();
						toCart.setPitch(m.getLocation().getPitch());
						toCart.setYaw(m.getLocation().getYaw());
						// m.remove();
						m.teleport(toCart);
						// m = (Minecart)world.spawnEntity(toCart,
						// EntityType.MINECART);
						m.setVelocity(v);
					}
				}
			}
		}
	}

	@EventHandler
	public void signChange(SignChangeEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("telecarts.make")
				&& (e.getLine(0).equalsIgnoreCase("[TeleCarts]") || e
						.getLine(0).equalsIgnoreCase("[TeleCart]"))) {
			if (isInt(e.getLine(1)) && isInt(e.getLine(2))
					&& isInt(e.getLine(3))) {
				e.setLine(0, ChatColor.BOLD + "" + ChatColor.RED
						+ "[TeleCarts]");
			}
		} else if (p.hasPermission("telecarts.make")
				&& (e.getLine(0).equalsIgnoreCase("[WorldCarts]") || e.getLine(
						0).equalsIgnoreCase("[WorldCart]"))) {
			String[] s = e.getLine(1).split(",");
			if (isInt(s[0]) && isInt(s[1]) && isInt(s[2])
					&& Bukkit.getWorld(e.getLine(2)) != null) {
				World w = null;
				for(World world : Bukkit.getWorlds()){
					if(world.getName().equalsIgnoreCase(e.getLine(2))){
						w = world;
					}
				}
				if(w != null){
					e.setLine(0, ChatColor.BOLD
							+ "" + ChatColor.RED + "[WorldCart]");
				}
			}
		}
	}

	// Keeping track of minecarts
	@EventHandler(priority = EventPriority.MONITOR)
	public void vehicleSpawn(VehicleCreateEvent e) {
		if (isMinecart(e.getVehicle().getType())) {
			Main.carts.add((Minecart) e.getVehicle());
			Main.cartlocs.put((Minecart) e.getVehicle(), e.getVehicle()
					.getLocation());
			// System.out.println("Minecart " + e.getVehicle().getEntityId() +
			// " added to database");
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void entDeath(VehicleDestroyEvent e) {
		if (isMinecart(e.getVehicle().getType())) {
			if (Main.carts.contains((Minecart) e.getVehicle())) {
				Main.carts.remove((Minecart) e.getVehicle());
				Main.cartlocs.remove((Minecart) e.getVehicle());
				// System.out.println("Minecart " + e.getVehicle().getEntityId()
				// + " removed from database");
			}
		}
	}

	private boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean isMinecart(EntityType e) {
		switch(e){
		case MINECART:
			return true;
		case MINECART_CHEST:
			return true;
		case MINECART_TNT:
			return true;
		case MINECART_COMMAND:
			return true;
		case MINECART_MOB_SPAWNER:
			return true;
		case MINECART_FURNACE:
			return true;
		case MINECART_HOPPER:
			return true;
		default:
			return false;
		}
	}
}

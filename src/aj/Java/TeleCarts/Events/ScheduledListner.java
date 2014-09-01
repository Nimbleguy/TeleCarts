package aj.Java.TeleCarts.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Minecart;
import org.bukkit.scheduler.BukkitRunnable;

import aj.Java.TeleCarts.Main;

public class ScheduledListner extends BukkitRunnable {
	@Override
	public void run() {
		for (Minecart m : Main.carts) {
			if (Double.parseDouble(String
					.format("%.1f", m.getLocation().getX())) != Double
					.parseDouble(String.format("%.1f", Main.cartlocs.get(m)
							.getX()))
							|| Double.parseDouble(String.format("%.1f", m.getLocation()
									.getY())) != Double.parseDouble(String.format(
											"%.1f", Main.cartlocs.get(m).getY()))
											|| Double.parseDouble(String.format("%.1f", m.getLocation()
													.getZ())) != Double.parseDouble(String.format(
															"%.1f", Main.cartlocs.get(m).getZ()))) {
				Bukkit.getServer()
				.getPluginManager()
				.callEvent(
						new MinecartMoveEvent(m, m.getLocation(),
								Main.cartlocs.get(m)));
				/**
				 * System.out.println(m.getLocation().getX() + " -> " +
				 * Main.cartlocs.get(m).getX() + ", " + m.getLocation().getY() +
				 * " -> " + Main.cartlocs.get(m).getY() + ", " +
				 * m.getLocation().getZ() + " -> " +
				 * Main.cartlocs.get(m).getZ());
				 */
				Main.cartlocs.put(m, m.getLocation());
			}
		}
	}

}

package aj.Java.TeleCarts.Events;

import org.bukkit.Location;
import org.bukkit.entity.Minecart;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MinecartMoveEvent extends Event implements Cancellable {
	private Location nl;
	private Location ol;
	private Minecart cart;
	private boolean isDisabled = false;
	private static final HandlerList handlers = new HandlerList();
	public MinecartMoveEvent(Minecart m, Location newl, Location oldl){
		nl = newl;
		ol = oldl;
		cart = m;
	}

	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return handlers;
	}
	public static HandlerList getHandlerList(){
		return handlers;
	}
	
	public double getOldX(){
		return ol.getX();
	}
	public double getOldY(){
		return ol.getY();
	}
	public double getOldZ(){
		return ol.getZ();
	}
	public double getNewX(){
		return nl.getX();
	}
	public double getNewY(){
		return nl.getY();
	}
	public double getNewZ(){
		return nl.getZ();
	}
	public Minecart getCart(){
		return cart;
	}

	@Override
	public boolean isCancelled() {
		return isDisabled;
	}

	@Override
	public void setCancelled(boolean arg0) {
		if(arg0){
			cart.teleport(ol);
		}
		else{
			cart.teleport(nl);
		}
		isDisabled = arg0;
	}
}

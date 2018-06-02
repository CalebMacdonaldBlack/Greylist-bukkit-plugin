package com.calebmacdonaldblack;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {

    private Logger log;

    @Override
    public void onEnable() {
        log = getLogger();
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }

    private boolean isGreyListed(Entity entity) {
        return entity.hasPermission(String.format("%s.greylisted", this.getDescription().getName()));
    }

    @EventHandler
    public void entityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if (isGreyListed(event.getDamager()) || isGreyListed(event.getEntity())) event.setCancelled(true);
    }

    @EventHandler
    public void entityTargetEvent(EntityTargetEvent event) {
        if (isGreyListed(event.getTarget())) event.setCancelled(true);
    }

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent event) {
        if (isGreyListed(event.getPlayer())) event.setCancelled(true);
    }

    @EventHandler
    public void foodLevelChangeEvent(FoodLevelChangeEvent event) {
        if (isGreyListed(event.getEntity())) event.setCancelled(true);
    }

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (isGreyListed(player))
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 1));
        else if (player.getPotionEffect(PotionEffectType.REGENERATION) != null && player.getPotionEffect(PotionEffectType.REGENERATION).getDuration() > Integer.MAX_VALUE / 2) {
            player.removePotionEffect(PotionEffectType.REGENERATION);
        }
    }
}

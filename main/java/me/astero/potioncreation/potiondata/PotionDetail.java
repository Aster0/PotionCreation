package me.astero.potioncreation.potiondata;

import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import lombok.Getter;
import lombok.Setter;

public class PotionDetail {
	
	@Getter @Setter private ItemStack item, mainItem; // Main item is only for choosen potions.
	@Getter @Setter private int color, price, amount;
	@Getter @Setter private String name;
	@Getter @Setter private PotionEffectType type;
	@Getter @Setter private List<String> description;
	
	
	


}

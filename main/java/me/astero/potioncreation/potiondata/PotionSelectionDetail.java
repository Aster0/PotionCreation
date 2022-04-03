package me.astero.potioncreation.potiondata;

import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import lombok.Setter;

public class PotionSelectionDetail {
	
	@Getter @Setter private String potionBaseName;
	@Getter @Setter private ItemStack potionBaseType;
	@Getter @Setter private int price;

}

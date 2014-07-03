package com.gpl.rpg.GotandaRPG.model.item;

import java.util.HashMap;

import com.gpl.rpg.GotandaRPG.GotandaRPGApplication;
import com.gpl.rpg.GotandaRPG.resource.parsers.ItemTypeParser;
import com.gpl.rpg.GotandaRPG.util.L;

public final class ItemTypeCollection {
	private static final String ITEMTYPE_GOLD = "gold";

	private final HashMap<String, ItemType> itemTypes = new HashMap<String, ItemType>();

	public ItemType getItemType(String id) {
		if (GotandaRPGApplication.DEVELOPMENT_VALIDATEDATA) {
			if (!itemTypes.containsKey(id)) {
				L.log("WARNING: Cannot find ItemType for id \"" + id + "\".");
				return null;
			}
		}
		return itemTypes.get(id);
	}

	public static boolean isGoldItemType(String itemTypeID) {
		if (itemTypeID == null) return false;
		return itemTypeID.equals(ITEMTYPE_GOLD);
	}

	public void initialize(final ItemTypeParser parser, String input) {
		parser.parseRows(input, itemTypes);
	}

	// Unit test method. Not part of the game logic.
	public HashMap<String, ItemType> UNITTEST_getAllItemTypes() {
		return itemTypes;
	}
}

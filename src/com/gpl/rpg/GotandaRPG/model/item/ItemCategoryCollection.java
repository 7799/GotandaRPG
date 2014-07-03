package com.gpl.rpg.GotandaRPG.model.item;

import java.util.HashMap;

import com.gpl.rpg.GotandaRPG.GotandaRPGApplication;
import com.gpl.rpg.GotandaRPG.resource.parsers.ItemCategoryParser;
import com.gpl.rpg.GotandaRPG.util.L;

public final class ItemCategoryCollection {
	private final HashMap<String, ItemCategory> itemCategories = new HashMap<String, ItemCategory>();

	public ItemCategory getItemCategory(String id) {
		if (GotandaRPGApplication.DEVELOPMENT_VALIDATEDATA) {
			if (!itemCategories.containsKey(id)) {
				L.log("WARNING: Cannot find ItemCategory for id \"" + id + "\".");
			}
		}
		return itemCategories.get(id);
	}

	public void initialize(final ItemCategoryParser parser, String input) {
		parser.parseRows(input, itemCategories);
	}

	// Unit test method. Not part of the game logic.
	public HashMap<String, ItemCategory> UNITTEST_getAllItemCategories() {
		return itemCategories;
	}
}

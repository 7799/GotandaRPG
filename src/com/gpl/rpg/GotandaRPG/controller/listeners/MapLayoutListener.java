package com.gpl.rpg.GotandaRPG.controller.listeners;

import com.gpl.rpg.GotandaRPG.model.map.LayeredTileMap;
import com.gpl.rpg.GotandaRPG.model.map.PredefinedMap;
import com.gpl.rpg.GotandaRPG.util.Coord;

public interface MapLayoutListener {
	void onLootBagCreated(PredefinedMap map, Coord p);
	void onLootBagRemoved(PredefinedMap map, Coord p);
	void onMapTilesChanged(PredefinedMap map, LayeredTileMap tileMap);
}

package com.gpl.rpg.GotandaRPG.controller.listeners;

import com.gpl.rpg.GotandaRPG.model.map.LayeredTileMap;
import com.gpl.rpg.GotandaRPG.util.ListOfListeners;
import com.gpl.rpg.GotandaRPG.model.map.PredefinedMap;
import com.gpl.rpg.GotandaRPG.util.Coord;

public final class MapLayoutListeners extends ListOfListeners<MapLayoutListener> implements MapLayoutListener {

	private final Function2<MapLayoutListener, PredefinedMap, Coord> onLootBagCreated = new Function2<MapLayoutListener, PredefinedMap, Coord>() {
		@Override public void call(MapLayoutListener listener, PredefinedMap map, Coord p) { listener.onLootBagCreated(map, p); }
	};

	private final Function2<MapLayoutListener, PredefinedMap, Coord> onLootBagRemoved = new Function2<MapLayoutListener, PredefinedMap, Coord>() {
		@Override public void call(MapLayoutListener listener, PredefinedMap map, Coord p) { listener.onLootBagRemoved(map, p); }
	};

	private final Function2<MapLayoutListener, PredefinedMap, LayeredTileMap> onMapTilesChanged = new Function2<MapLayoutListener, PredefinedMap, LayeredTileMap>() {
		@Override public void call(MapLayoutListener listener, PredefinedMap map, LayeredTileMap tileMap) { listener.onMapTilesChanged(map, tileMap); }
	};

	@Override
	public void onLootBagCreated(PredefinedMap map, Coord p) {
		callAllListeners(this.onLootBagCreated, map, p);
	}

	@Override
	public void onLootBagRemoved(PredefinedMap map, Coord p) {
		callAllListeners(this.onLootBagRemoved, map, p);
	}

	@Override
	public void onMapTilesChanged(PredefinedMap map, LayeredTileMap tileMap) {
		callAllListeners(this.onMapTilesChanged, map, tileMap);
	}
}

package com.gpl.rpg.GotandaRPG.controller.listeners;

import com.gpl.rpg.GotandaRPG.util.ListOfListeners;
import com.gpl.rpg.GotandaRPG.model.map.PredefinedMap;
import com.gpl.rpg.GotandaRPG.util.Coord;

public final class PlayerMovementListeners extends ListOfListeners<PlayerMovementListener> implements PlayerMovementListener {

	private final Function2<PlayerMovementListener, Coord, Coord> onPlayerMoved = new Function2<PlayerMovementListener, Coord, Coord>() {
		@Override public void call(PlayerMovementListener listener, Coord newPosition, Coord previousPosition) { listener.onPlayerMoved(newPosition, previousPosition); }
	};

	private final Function2<PlayerMovementListener, PredefinedMap, Coord> onPlayerEnteredNewMap = new Function2<PlayerMovementListener, PredefinedMap, Coord>() {
		@Override public void call(PlayerMovementListener listener, PredefinedMap map, Coord p) { listener.onPlayerEnteredNewMap(map, p); }
	};

	@Override
	public void onPlayerMoved(Coord newPosition, Coord previousPosition) {
		callAllListeners(this.onPlayerMoved, newPosition, previousPosition);
	}

	@Override
	public void onPlayerEnteredNewMap(PredefinedMap map, Coord p) {
		callAllListeners(this.onPlayerEnteredNewMap, map, p);
	}
}

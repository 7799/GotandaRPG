package com.gpl.rpg.GotandaRPG.controller.listeners;

import com.gpl.rpg.GotandaRPG.model.map.PredefinedMap;
import com.gpl.rpg.GotandaRPG.util.Coord;

public interface PlayerMovementListener {
	void onPlayerMoved(Coord newPosition, Coord previousPosition);
	void onPlayerEnteredNewMap(PredefinedMap map, Coord p);
}

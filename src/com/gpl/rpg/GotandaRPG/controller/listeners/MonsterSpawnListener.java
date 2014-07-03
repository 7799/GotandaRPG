package com.gpl.rpg.GotandaRPG.controller.listeners;

import com.gpl.rpg.GotandaRPG.model.actor.Monster;
import com.gpl.rpg.GotandaRPG.model.map.PredefinedMap;
import com.gpl.rpg.GotandaRPG.util.Coord;
import com.gpl.rpg.GotandaRPG.util.CoordRect;

public interface MonsterSpawnListener {
	void onMonsterSpawned(PredefinedMap map, Monster m);
	void onMonsterRemoved(PredefinedMap map, Monster m, CoordRect previousPosition);
	void onSplatterAdded(PredefinedMap map, Coord p);
	void onSplatterChanged(PredefinedMap map, Coord p);
	void onSplatterRemoved(PredefinedMap map, Coord p);
}

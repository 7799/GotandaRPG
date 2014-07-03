package com.gpl.rpg.GotandaRPG.controller.listeners;

import com.gpl.rpg.GotandaRPG.model.actor.Monster;
import com.gpl.rpg.GotandaRPG.model.map.PredefinedMap;
import com.gpl.rpg.GotandaRPG.util.CoordRect;

public interface MonsterMovementListener {
	void onMonsterSteppedOnPlayer(Monster m);
	void onMonsterMoved(PredefinedMap map, Monster m, CoordRect previousPosition);
}

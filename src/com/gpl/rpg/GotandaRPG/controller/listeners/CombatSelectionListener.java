package com.gpl.rpg.GotandaRPG.controller.listeners;

import com.gpl.rpg.GotandaRPG.model.actor.Monster;
import com.gpl.rpg.GotandaRPG.util.Coord;

public interface CombatSelectionListener {
	void onMonsterSelected(Monster m, Coord selectedPosition, Coord previousSelection);
	void onMovementDestinationSelected(Coord selectedPosition, Coord previousSelection);
	void onCombatSelectionCleared(Coord previousSelection);
}

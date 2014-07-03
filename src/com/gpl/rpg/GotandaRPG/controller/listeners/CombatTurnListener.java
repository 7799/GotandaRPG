package com.gpl.rpg.GotandaRPG.controller.listeners;

import com.gpl.rpg.GotandaRPG.model.actor.Monster;

public interface CombatTurnListener {
	void onCombatStarted();
	void onCombatEnded();
	void onNewPlayerTurn();
	void onMonsterIsAttacking(Monster m);
}

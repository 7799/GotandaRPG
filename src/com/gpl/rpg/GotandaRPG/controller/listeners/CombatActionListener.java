package com.gpl.rpg.GotandaRPG.controller.listeners;

import com.gpl.rpg.GotandaRPG.model.AttackResult;
import com.gpl.rpg.GotandaRPG.model.actor.Monster;

public interface CombatActionListener {
	void onPlayerAttackMissed(Monster target, AttackResult attackResult);
	void onPlayerAttackSuccess(Monster target, AttackResult attackResult);
	void onMonsterAttackMissed(Monster attacker, AttackResult attackResult);
	void onMonsterAttackSuccess(Monster attacker, AttackResult attackResult);
	void onMonsterMovedDuringCombat(Monster m);
	void onPlayerKilledMonster(Monster target);
	void onPlayerStartedFleeing();
	void onPlayerFailedFleeing();
	void onPlayerDoesNotHaveEnoughAP();
}

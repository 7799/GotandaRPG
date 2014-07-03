package com.gpl.rpg.GotandaRPG.controller.listeners;

import com.gpl.rpg.GotandaRPG.model.actor.Monster;
import com.gpl.rpg.GotandaRPG.model.item.Loot;
import com.gpl.rpg.GotandaRPG.model.map.MapObject;

import java.util.Collection;

public interface WorldEventListener {
	void onPlayerStartedConversation(Monster m, String phraseID);
	void onPlayerSteppedOnMonster(Monster m);
	void onPlayerSteppedOnMapSignArea(MapObject area);
	void onPlayerSteppedOnKeyArea(MapObject area);
	void onPlayerSteppedOnRestArea(MapObject area);
	void onPlayerSteppedOnGroundLoot(Loot loot);
	void onPlayerPickedUpGroundLoot(Loot loot);
	void onPlayerFoundMonsterLoot(Collection<Loot> loot, int exp);
	void onPlayerPickedUpMonsterLoot(Collection<Loot> loot, int exp);
	void onPlayerRested();
	void onPlayerDied(int lostExp);
}

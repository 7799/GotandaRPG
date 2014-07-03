package com.gpl.rpg.GotandaRPG.controller.listeners;

import com.gpl.rpg.GotandaRPG.model.actor.Monster;
import com.gpl.rpg.GotandaRPG.util.ListOfListeners;
import com.gpl.rpg.GotandaRPG.model.map.PredefinedMap;
import com.gpl.rpg.GotandaRPG.util.CoordRect;

public final class MonsterMovementListeners extends ListOfListeners<MonsterMovementListener> implements MonsterMovementListener {

	private final Function1<MonsterMovementListener, Monster> onMonsterSteppedOnPlayer = new Function1<MonsterMovementListener, Monster>() {
		@Override public void call(MonsterMovementListener listener, Monster monster) { listener.onMonsterSteppedOnPlayer(monster); }
	};

	private final Function3<MonsterMovementListener, PredefinedMap, Monster, CoordRect> onMonsterMoved = new Function3<MonsterMovementListener, PredefinedMap, Monster, CoordRect>() {
		@Override public void call(MonsterMovementListener listener, PredefinedMap map, Monster monster, CoordRect previousPosition) { listener.onMonsterMoved(map, monster, previousPosition); }
	};

	@Override
	public void onMonsterSteppedOnPlayer(Monster m) {
		callAllListeners(this.onMonsterSteppedOnPlayer, m);
	}

	@Override
	public void onMonsterMoved(PredefinedMap map, Monster m, CoordRect previousPosition) {
		callAllListeners(this.onMonsterMoved, map, m, previousPosition);
	}
}

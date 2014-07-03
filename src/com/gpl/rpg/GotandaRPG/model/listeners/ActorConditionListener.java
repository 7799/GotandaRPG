package com.gpl.rpg.GotandaRPG.model.listeners;

import com.gpl.rpg.GotandaRPG.model.ability.ActorCondition;
import com.gpl.rpg.GotandaRPG.model.actor.Actor;

public interface ActorConditionListener {
	public void onActorConditionAdded(Actor actor, ActorCondition condition);
	public void onActorConditionRemoved(Actor actor, ActorCondition condition);
	public void onActorConditionDurationChanged(Actor actor, ActorCondition condition);
	public void onActorConditionMagnitudeChanged(Actor actor, ActorCondition condition);
	public void onActorConditionRoundEffectApplied(Actor actor, ActorCondition condition);
}

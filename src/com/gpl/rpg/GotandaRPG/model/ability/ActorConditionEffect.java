package com.gpl.rpg.GotandaRPG.model.ability;

import com.gpl.rpg.GotandaRPG.util.ConstRange;

public final class ActorConditionEffect {
	public final ActorConditionType conditionType;
	public final int magnitude;
	public final int duration;
	public final ConstRange chance;

	public ActorConditionEffect(ActorConditionType conditionType, int magnitude, int duration, ConstRange chance) {
		this.conditionType = conditionType;
		this.magnitude = magnitude;
		this.duration = duration;
		this.chance = chance;
	}

	public boolean isRemovalEffect() {
		return magnitude == ActorCondition.MAGNITUDE_REMOVE_ALL;
	}

	public ActorCondition createCondition() { return createCondition(duration); }
	public ActorCondition createCondition(final int duration) {
		return new ActorCondition(conditionType, magnitude, duration);
	}
}

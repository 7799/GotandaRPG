package com.gpl.rpg.GotandaRPG.model.actor;

import java.util.ArrayList;
import java.util.HashMap;

import com.gpl.rpg.GotandaRPG.GotandaRPGApplication;
import com.gpl.rpg.GotandaRPG.resource.parsers.MonsterTypeParser;
import com.gpl.rpg.GotandaRPG.util.L;

public final class MonsterTypeCollection {
	private final HashMap<String, MonsterType> monsterTypesById = new HashMap<String, MonsterType>();

	public MonsterType getMonsterType(String id) {
		if (GotandaRPGApplication.DEVELOPMENT_VALIDATEDATA) {
			if (!monsterTypesById.containsKey(id)) {
				L.log("WARNING: Cannot find MonsterType for id \"" + id + "\".");
			}
		}
		return monsterTypesById.get(id);
	}

	public ArrayList<MonsterType> getMonsterTypesFromSpawnGroup(String spawnGroup) {
		ArrayList<MonsterType> result = new ArrayList<MonsterType>();
		for (MonsterType t : monsterTypesById.values()) {
			if (t.spawnGroup.equalsIgnoreCase(spawnGroup)) result.add(t);
		}

		return result;
	}

	public MonsterType guessMonsterTypeFromName(String name) {
		for (MonsterType t : monsterTypesById.values()) {
			if (t.name.equalsIgnoreCase(name)) return t;
		}
		return null;
	}

	public void initialize(MonsterTypeParser parser, String input) {
		parser.parseRows(input, monsterTypesById);
	}

	// Unit test method. Not part of the game logic.
	public HashMap<String, MonsterType> UNITTEST_getAllMonsterTypes() {
		return monsterTypesById;
	}
}

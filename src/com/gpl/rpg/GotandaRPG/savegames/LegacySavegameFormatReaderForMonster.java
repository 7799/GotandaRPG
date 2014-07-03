package com.gpl.rpg.GotandaRPG.savegames;

import java.io.DataInputStream;
import java.io.IOException;

import com.gpl.rpg.GotandaRPG.model.actor.Monster;
import com.gpl.rpg.GotandaRPG.model.actor.MonsterType;
import com.gpl.rpg.GotandaRPG.util.Coord;

public final class LegacySavegameFormatReaderForMonster {
	public static Monster readFromParcel_pre_v25(DataInputStream src, int fileversion, MonsterType monsterType) throws IOException {
		Monster m = new Monster(monsterType);
		m.position.set(new Coord(src, fileversion));
		m.ap.current = src.readInt();
		m.health.current = src.readInt();
		if (fileversion >= 12) {
			if (src.readBoolean()) m.forceAggressive();
		}
		return m;
	}
}

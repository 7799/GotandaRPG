package com.gpl.rpg.GotandaRPG.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.gpl.rpg.GotandaRPG.context.ControllerContext;
import com.gpl.rpg.GotandaRPG.context.WorldContext;
import com.gpl.rpg.GotandaRPG.model.actor.Player;
import com.gpl.rpg.GotandaRPG.model.map.LayeredTileMap;
import com.gpl.rpg.GotandaRPG.model.map.PredefinedMap;

public final class ModelContainer {

	public final Player player;
	public final InterfaceData uiSelections;
	public final CombatLog combatLog = new CombatLog();
	public final GameStatistics statistics;
	public PredefinedMap currentMap;
	public LayeredTileMap currentTileMap;

	public ModelContainer() {
		player = new Player();
		uiSelections = new InterfaceData();
		statistics = new GameStatistics();
	}

	// ====== PARCELABLE ===================================================================

	public ModelContainer(DataInputStream src, WorldContext world, ControllerContext controllers, int fileversion) throws IOException {
		this.player = Player.readFromParcel(src, world, controllers, fileversion);
		this.currentMap = world.maps.findPredefinedMap(src.readUTF());
		this.uiSelections = new InterfaceData(src, world, fileversion);
		if (uiSelections.selectedPosition != null) {
			this.uiSelections.selectedMonster = currentMap.getMonsterAt(uiSelections.selectedPosition);
		}
		this.statistics = new GameStatistics(src, world, fileversion);
		this.currentTileMap = null;
	}

	public void writeToParcel(DataOutputStream dest, int flags) throws IOException {
		player.writeToParcel(dest, flags);
		dest.writeUTF(currentMap.name);
		uiSelections.writeToParcel(dest, flags);
		statistics.writeToParcel(dest, flags);
	}
}

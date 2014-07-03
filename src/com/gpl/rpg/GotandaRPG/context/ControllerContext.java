package com.gpl.rpg.GotandaRPG.context;

import java.lang.ref.WeakReference;

import android.content.res.Resources;

import com.gpl.rpg.GotandaRPG.GotandaRPGApplication;
import com.gpl.rpg.GotandaRPG.GotandaRPGPreferences;
import com.gpl.rpg.GotandaRPG.controller.ActorStatsController;
import com.gpl.rpg.GotandaRPG.controller.CombatController;
import com.gpl.rpg.GotandaRPG.controller.MapController;
import com.gpl.rpg.GotandaRPG.controller.ConversationController;
import com.gpl.rpg.GotandaRPG.controller.GameRoundController;
import com.gpl.rpg.GotandaRPG.controller.MonsterSpawningController;
import com.gpl.rpg.GotandaRPG.controller.SkillController;
import com.gpl.rpg.GotandaRPG.controller.VisualEffectController;
import com.gpl.rpg.GotandaRPG.controller.ItemController;
import com.gpl.rpg.GotandaRPG.controller.MonsterMovementController;
import com.gpl.rpg.GotandaRPG.controller.MovementController;
import com.gpl.rpg.GotandaRPG.controller.InputController;

public final class ControllerContext {
	//Controllers
	public final MapController mapController;
	public final GameRoundController gameRoundController;
	public final CombatController combatController;
	public final ConversationController conversationController;
	public final VisualEffectController effectController;
	public final ItemController itemController;
	public final MonsterMovementController monsterMovementController;
	public final MonsterSpawningController monsterSpawnController;
	public final MovementController movementController;
	public final ActorStatsController actorStatsController;
	public final InputController inputController;
	public final SkillController skillController;

	public final GotandaRPGPreferences preferences;
	private final WeakReference<GotandaRPGApplication> app;

	public ControllerContext(GotandaRPGApplication app, WorldContext world) {
		this.app = new WeakReference<GotandaRPGApplication>(app);
		this.preferences = app.getPreferences();

		this.mapController = new MapController(this, world);
		this.gameRoundController = new GameRoundController(this, world);
		this.combatController = new CombatController(this, world);
		this.conversationController = new ConversationController(this, world);
		this.effectController = new VisualEffectController(this, world);
		this.itemController = new ItemController(this, world);
		this.monsterMovementController = new MonsterMovementController(this, world);
		this.monsterSpawnController = new MonsterSpawningController(this, world);
		this.movementController = new MovementController(this, world);
		this.actorStatsController = new ActorStatsController(this, world);
		this.inputController = new InputController(this, world);
		this.skillController = new SkillController(this, world);
	}

	public Resources getResources() {
		return app.get().getResources();
	}
}

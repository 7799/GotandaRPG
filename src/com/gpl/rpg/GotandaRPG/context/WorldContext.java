package com.gpl.rpg.GotandaRPG.context;

import com.gpl.rpg.GotandaRPG.VisualEffectCollection;
import com.gpl.rpg.GotandaRPG.conversation.ConversationLoader;
import com.gpl.rpg.GotandaRPG.model.ModelContainer;
import com.gpl.rpg.GotandaRPG.model.ability.ActorConditionTypeCollection;
import com.gpl.rpg.GotandaRPG.model.ability.SkillCollection;
import com.gpl.rpg.GotandaRPG.model.actor.MonsterTypeCollection;
import com.gpl.rpg.GotandaRPG.model.item.DropListCollection;
import com.gpl.rpg.GotandaRPG.model.item.ItemCategoryCollection;
import com.gpl.rpg.GotandaRPG.model.item.ItemTypeCollection;
import com.gpl.rpg.GotandaRPG.model.map.MapCollection;
import com.gpl.rpg.GotandaRPG.model.quest.QuestCollection;
import com.gpl.rpg.GotandaRPG.resource.tiles.TileManager;

public final class WorldContext {
	//Objectcollections
	public final ConversationLoader conversationLoader;
	public final ItemTypeCollection itemTypes;
	public final ItemCategoryCollection itemCategories;
	public final MonsterTypeCollection monsterTypes;
	public final VisualEffectCollection visualEffectTypes;
	public final DropListCollection dropLists;
	public final QuestCollection quests;
	public final ActorConditionTypeCollection actorConditionsTypes;
	public final SkillCollection skills;

	//Objectcollections
	public final TileManager tileManager;

	//Model
	public final MapCollection maps;
	public ModelContainer model;

	public WorldContext() {
		this.conversationLoader = new ConversationLoader();
		this.itemTypes = new ItemTypeCollection();
		this.itemCategories = new ItemCategoryCollection();
		this.monsterTypes = new MonsterTypeCollection();
		this.visualEffectTypes = new VisualEffectCollection();
		this.dropLists = new DropListCollection();
		this.tileManager = new TileManager();
		this.maps = new MapCollection();
		this.quests = new QuestCollection();
		this.actorConditionsTypes = new ActorConditionTypeCollection();
		this.skills = new SkillCollection();
	}
	public WorldContext(WorldContext copy) {
		this.conversationLoader = copy.conversationLoader;
		this.itemTypes = copy.itemTypes;
		this.itemCategories = copy.itemCategories;
		this.monsterTypes = copy.monsterTypes;
		this.visualEffectTypes = copy.visualEffectTypes;
		this.dropLists = copy.dropLists;
		this.tileManager = copy.tileManager;
		this.maps = copy.maps;
		this.quests = copy.quests;
		this.model = copy.model;
		this.actorConditionsTypes = copy.actorConditionsTypes;
		this.skills = copy.skills;
	}
	public void reset() {
		maps.reset();
	}
}
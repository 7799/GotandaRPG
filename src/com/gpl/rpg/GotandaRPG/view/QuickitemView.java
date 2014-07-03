package com.gpl.rpg.GotandaRPG.view;

import java.util.HashSet;

import android.R.color;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import android.widget.RelativeLayout;
import com.gpl.rpg.GotandaRPG.GotandaRPGApplication;
import com.gpl.rpg.GotandaRPG.GotandaRPGPreferences;
import com.gpl.rpg.GotandaRPG.R;
import com.gpl.rpg.GotandaRPG.activity.MainActivity;
import com.gpl.rpg.GotandaRPG.context.ControllerContext;
import com.gpl.rpg.GotandaRPG.context.WorldContext;
import com.gpl.rpg.GotandaRPG.controller.listeners.QuickSlotListener;
import com.gpl.rpg.GotandaRPG.model.item.Inventory;
import com.gpl.rpg.GotandaRPG.model.item.ItemType;
import com.gpl.rpg.GotandaRPG.resource.tiles.TileCollection;

public final class QuickitemView extends LinearLayout implements OnClickListener, QuickSlotListener {
	private static final int NUM_QUICK_SLOTS = Inventory.NUM_QUICK_SLOTS;

	private final WorldContext world;
	private final ControllerContext controllers;
	private final QuickButton[] buttons = new QuickButton[NUM_QUICK_SLOTS];
	private final HashSet<Integer> loadedTileIDs = new HashSet<Integer>();
	private TileCollection tiles = null;

	public QuickitemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		GotandaRPGApplication app = GotandaRPGApplication.getApplicationFromActivityContext(context);
		this.world = app.getWorld();
		this.controllers = app.getControllerContext();
		setFocusable(false);

		int position = app.getPreferences().quickslotsPosition;
		switch(position) {
			case GotandaRPGPreferences.QUICKSLOTS_POSITION_HORIZONTAL_BOTTOM_LEFT:
			case GotandaRPGPreferences.QUICKSLOTS_POSITION_HORIZONTAL_BOTTOM_RIGHT:
			case GotandaRPGPreferences.QUICKSLOTS_POSITION_HORIZONTAL_CENTER_BOTTOM:
				setOrientation(LinearLayout.HORIZONTAL);
				break;
			case GotandaRPGPreferences.QUICKSLOTS_POSITION_VERTICAL_BOTTOM_LEFT:
			case GotandaRPGPreferences.QUICKSLOTS_POSITION_VERTICAL_BOTTOM_RIGHT:
			case GotandaRPGPreferences.QUICKSLOTS_POSITION_VERTICAL_CENTER_LEFT:
			case GotandaRPGPreferences.QUICKSLOTS_POSITION_VERTICAL_CENTER_RIGHT:
				setOrientation(LinearLayout.VERTICAL);
				break;
		}

		Resources res = getResources();
		this.setBackgroundColor(res.getColor(color.transparent));

		for(int i = 0; i < NUM_QUICK_SLOTS; ++i) {
			buttons[i] = new QuickButton(context, null);
			QuickButton item = buttons[i];
			item.setIndex(i);
			item.setItemType(null, world, tiles);
			item.setOnClickListener(this);
			addView(item);
		}
	}

	public boolean isQuickButtonId(int id){
		for(QuickButton item: buttons){
			if(item.getId()==id)
				return true;
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		QuickButton button = (QuickButton)v;
		if(button.isEmpty())
			return;
		controllers.itemController.quickitemUse(button.getIndex());
	}

	@Override
	public void setVisibility(int visibility) {
		if(visibility==VISIBLE)
			refreshQuickitems();
		super.setVisibility(visibility);
	}

	public void refreshQuickitems() {
		loadItemTypeImages();

		for (int i = 0; i < NUM_QUICK_SLOTS; ++i){
			ItemType type = world.model.player.inventory.quickitem[i];
			buttons[i].setItemType(type, world, tiles);
		}
	}

	private void loadItemTypeImages() {
		boolean shouldLoadImages = false;
		for (ItemType type : world.model.player.inventory.quickitem) {
			if (type == null) continue;
			if (!loadedTileIDs.contains(type.iconID)) {
				shouldLoadImages = true;
				break;
			}
		}
		if (!shouldLoadImages) return;

		HashSet<Integer> iconIDs = new HashSet<Integer>();

		for (ItemType type : world.model.player.inventory.quickitem) {
			if (type == null) continue;
			iconIDs.add(type.iconID);
		}

		loadedTileIDs.clear();
		loadedTileIDs.addAll(iconIDs);
		tiles = world.tileManager.loadTilesFor(iconIDs, getResources());
	}

	public void registerForContextMenu(MainActivity mainActivity) {
		for(QuickButton item: buttons)
			mainActivity.registerForContextMenu(item);
	}

	public void setPosition(GotandaRPGPreferences preferences) {
		int positionRelativeTo = R.id.main_mainview;
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		switch (preferences.quickslotsPosition) {
			case GotandaRPGPreferences.QUICKSLOTS_POSITION_HORIZONTAL_BOTTOM_LEFT:
			case GotandaRPGPreferences.QUICKSLOTS_POSITION_VERTICAL_BOTTOM_LEFT:
				params.addRule(RelativeLayout.ALIGN_LEFT, positionRelativeTo);
				params.addRule(RelativeLayout.ALIGN_BOTTOM, positionRelativeTo);
				break;
			case GotandaRPGPreferences.QUICKSLOTS_POSITION_HORIZONTAL_BOTTOM_RIGHT:
			case GotandaRPGPreferences.QUICKSLOTS_POSITION_VERTICAL_BOTTOM_RIGHT:
				params.addRule(RelativeLayout.ALIGN_RIGHT, positionRelativeTo);
				params.addRule(RelativeLayout.ALIGN_BOTTOM, positionRelativeTo);
				break;
			case GotandaRPGPreferences.QUICKSLOTS_POSITION_HORIZONTAL_CENTER_BOTTOM:
				params.addRule(RelativeLayout.CENTER_HORIZONTAL);
				params.addRule(RelativeLayout.ALIGN_BOTTOM, positionRelativeTo);
				break;
			case GotandaRPGPreferences.QUICKSLOTS_POSITION_VERTICAL_CENTER_LEFT:
				params.addRule(RelativeLayout.ALIGN_LEFT, positionRelativeTo);
				params.addRule(RelativeLayout.CENTER_VERTICAL);
				break;
			case GotandaRPGPreferences.QUICKSLOTS_POSITION_VERTICAL_CENTER_RIGHT:
				params.addRule(RelativeLayout.ALIGN_RIGHT, positionRelativeTo);
				params.addRule(RelativeLayout.CENTER_VERTICAL);
				break;
		}

		setLayoutParams(params);
	}

	@Override
	public void onQuickSlotChanged(int slotId) {
		refreshQuickitems();
	}

	@Override
	public void onQuickSlotUsed(int slotId) {
		refreshQuickitems();
	}

	public void subscribe() {
		controllers.itemController.quickSlotListeners.add(this);
	}
	public void unsubscribe() {
		controllers.itemController.quickSlotListeners.remove(this);
	}
}

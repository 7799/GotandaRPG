package com.gpl.rpg.GotandaRPG.view;

import com.gpl.rpg.GotandaRPG.GotandaRPGApplication;
import com.gpl.rpg.GotandaRPG.Dialogs;
import com.gpl.rpg.GotandaRPG.R;
import com.gpl.rpg.GotandaRPG.context.WorldContext;
import com.gpl.rpg.GotandaRPG.model.ability.ActorCondition;
import com.gpl.rpg.GotandaRPG.model.ability.ActorConditionType;

import android.content.Context;
import android.content.res.Resources;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public final class ActorConditionList extends LinearLayout {

	private final WorldContext world;

	public ActorConditionList(Context context, AttributeSet attr) {
		super(context, attr);
		setFocusable(false);
		setOrientation(LinearLayout.VERTICAL);
		GotandaRPGApplication app = GotandaRPGApplication.getApplicationFromActivityContext(context);
		this.world = app.getWorld();
	}

	public void update(Iterable<ActorCondition> conditions) {
		removeAllViews();
		if (conditions == null) return;

		final Context context = getContext();
		final Resources res = getResources();
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

		for (ActorCondition c : conditions) {
			TextView v = (TextView) View.inflate(context, R.layout.inventoryitemview, null);
			world.tileManager.setImageViewTile(res, v, c.conditionType);
			SpannableString content = new SpannableString(describeEffect(res, c));
			content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
			v.setText(content);
			final ActorConditionType conditionType = c.conditionType;
			v.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Dialogs.showActorConditionInfo(context, conditionType);
				}
			});
			this.addView(v, layoutParams);
		}
	}

	private static String describeEffect(Resources res, ActorCondition c) {
		return ActorConditionEffectList.describeEffect(res, c.conditionType, c.magnitude, c.duration);
	}
}

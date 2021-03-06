package com.gpl.rpg.GotandaRPG.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import com.gpl.rpg.GotandaRPG.GotandaRPGApplication;
import com.gpl.rpg.GotandaRPG.R;
import com.gpl.rpg.GotandaRPG.activity.fragment.ShopActivity_Buy;
import com.gpl.rpg.GotandaRPG.activity.fragment.ShopActivity_Sell;

public final class ShopActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		GotandaRPGApplication app = GotandaRPGApplication.getApplicationFromActivity(this);
		if (!app.isInitialized()) { finish(); return; }
		app.setWindowParameters(this);

		setContentView(R.layout.tabbedlayout);

		final Resources res = getResources();

		FragmentTabHost tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		tabHost.addTab(tabHost.newTabSpec("buy")
				.setIndicator(res.getString(R.string.shop_buy))
				,ShopActivity_Buy.class, null);
		tabHost.addTab(tabHost.newTabSpec("sell")
				.setIndicator(res.getString(R.string.shop_sell))
				,ShopActivity_Sell.class, null);
	}
}

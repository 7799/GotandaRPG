package com.gpl.rpg.GotandaRPG.activity;

import com.gpl.rpg.GotandaRPG.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public final class Preferences extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}
}

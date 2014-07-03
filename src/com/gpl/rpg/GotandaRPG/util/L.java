package com.gpl.rpg.GotandaRPG.util;

import com.gpl.rpg.GotandaRPG.GotandaRPGApplication;

import android.util.Log;

public final class L {
	private static final String TAG = "GotandaRPG";

	public static void log(String s) {
		if (GotandaRPGApplication.DEVELOPMENT_DEBUGMESSAGES) {
			Log.d(TAG, s);
		}
	}
}

package com.gpl.rpg.GotandaRPG.controller.listeners;

import com.gpl.rpg.GotandaRPG.controller.VisualEffectController.VisualEffectAnimation;

public interface VisualEffectFrameListener {
	void onNewAnimationFrame(VisualEffectAnimation animation, int tileID, int textYOffset);
	void onAnimationCompleted(VisualEffectAnimation animation);
}

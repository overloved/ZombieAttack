package com.jhu.oose16.zombieattack.view.component.object.cycleplay;

import java.util.Arrays;

import com.jhu.oose16.zombieattack.model.objectstate.ExpirableObjectState;
import com.jhu.oose16.zombieattack.view.activities.R;

public class EffectIceSmallCyclePlay extends CyclePlay {

	public EffectIceSmallCyclePlay(ExpirableObjectState firstObjectState) {
		super(firstObjectState);

		appearance = Arrays.asList(R.drawable.effect_ice_small_appearance_1,
				R.drawable.effect_ice_small_appearance_2,
				R.drawable.effect_ice_small_appearance_3,
				R.drawable.effect_ice_small_appearance_4,
				R.drawable.effect_ice_small_appearance_5);

		disappearance_down = Arrays.asList(
				R.drawable.effect_ice_small_disappearance_1,
				R.drawable.effect_ice_small_disappearance_2,
				R.drawable.effect_ice_small_disappearance_3,
				R.drawable.effect_ice_small_disappearance_4);

		running_down = Arrays.asList(R.drawable.effect_ice_small_on_1);

		initializeBitmap();
	}
}

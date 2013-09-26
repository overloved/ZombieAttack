package com.jhu.oose16.zombieattack.view.component.object.cycleplay;

import java.util.Arrays;

import com.jhu.oose16.zombieattack.model.objectstate.ExpirableObjectState;
import com.jhu.oose16.zombieattack.view.activities.R;

public class EffectFireCyclePlay extends CyclePlay {

	public EffectFireCyclePlay(ExpirableObjectState firstObjectState) {
		super(firstObjectState);

		appearance = Arrays.asList(R.drawable.effect_fire_appearance_1,
				R.drawable.effect_fire_appearance_2,
				R.drawable.effect_fire_appearance_3,
				R.drawable.effect_fire_appearance_4,
				R.drawable.effect_fire_appearance_5,
				R.drawable.effect_fire_appearance_6);

		disappearance_down = Arrays.asList(
				R.drawable.effect_fire_disappearance_1,
				R.drawable.effect_fire_disappearance_2,
				R.drawable.effect_fire_disappearance_3,
				R.drawable.effect_fire_disappearance_4);

		running_down = Arrays.asList(R.drawable.effect_fire_on_1,
				R.drawable.effect_fire_on_2, R.drawable.effect_fire_on_3,
				R.drawable.effect_fire_on_4);

		initializeBitmap();
	}
}

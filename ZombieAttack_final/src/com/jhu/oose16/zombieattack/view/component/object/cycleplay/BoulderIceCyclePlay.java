package com.jhu.oose16.zombieattack.view.component.object.cycleplay;

import java.util.Arrays;

import com.jhu.oose16.zombieattack.model.objectstate.ExpirableObjectState;
import com.jhu.oose16.zombieattack.view.activities.R;

public class BoulderIceCyclePlay extends CyclePlay {

	public BoulderIceCyclePlay(ExpirableObjectState firstObjectState) {
		super(firstObjectState);

		appearance = Arrays.asList(R.drawable.boulder_ice_appearance_1,
				R.drawable.boulder_ice_appearance_2,
				R.drawable.boulder_ice_appearance_3,
				R.drawable.boulder_ice_appearance_4,
				R.drawable.boulder_ice_appearance_5,
				R.drawable.boulder_ice_appearance_6,
				R.drawable.boulder_ice_appearance_7,
				R.drawable.boulder_ice_appearance_8,
				R.drawable.boulder_ice_appearance_9,
				R.drawable.boulder_ice_appearance_10);

		disappearance_left = Arrays.asList(
				R.drawable.boulder_ice_destruction_1,
				R.drawable.boulder_ice_destruction_2,
				R.drawable.boulder_ice_destruction_3,
				R.drawable.boulder_ice_destruction_4,
				R.drawable.boulder_ice_destruction_5,
				R.drawable.boulder_ice_destruction_6,
				R.drawable.boulder_ice_destruction_7,
				R.drawable.boulder_ice_destruction_8,
				R.drawable.boulder_ice_destruction_9,
				R.drawable.boulder_ice_destruction_10);
		
		disappearance_up = Arrays.asList(
				R.drawable.boulder_ice_destruction_1,
				R.drawable.boulder_ice_destruction_2,
				R.drawable.boulder_ice_destruction_3,
				R.drawable.boulder_ice_destruction_4,
				R.drawable.boulder_ice_destruction_5,
				R.drawable.boulder_ice_destruction_6,
				R.drawable.boulder_ice_destruction_7,
				R.drawable.boulder_ice_destruction_8,
				R.drawable.boulder_ice_destruction_9,
				R.drawable.boulder_ice_destruction_10);
		
		disappearance_right = Arrays.asList(
				R.drawable.boulder_ice_destruction_1,
				R.drawable.boulder_ice_destruction_2,
				R.drawable.boulder_ice_destruction_3,
				R.drawable.boulder_ice_destruction_4,
				R.drawable.boulder_ice_destruction_5,
				R.drawable.boulder_ice_destruction_6,
				R.drawable.boulder_ice_destruction_7,
				R.drawable.boulder_ice_destruction_8,
				R.drawable.boulder_ice_destruction_9,
				R.drawable.boulder_ice_destruction_10);
		
		disappearance_down = Arrays.asList(
				R.drawable.boulder_ice_destruction_1,
				R.drawable.boulder_ice_destruction_2,
				R.drawable.boulder_ice_destruction_3,
				R.drawable.boulder_ice_destruction_4,
				R.drawable.boulder_ice_destruction_5,
				R.drawable.boulder_ice_destruction_6,
				R.drawable.boulder_ice_destruction_7,
				R.drawable.boulder_ice_destruction_8,
				R.drawable.boulder_ice_destruction_9,
				R.drawable.boulder_ice_destruction_10);

		running_left = Arrays.asList(R.drawable.boulder_ice_roll_left_1,
				R.drawable.boulder_ice_roll_left_2,
				R.drawable.boulder_ice_roll_left_3,
				R.drawable.boulder_ice_roll_left_4);
		running_right = Arrays.asList(R.drawable.boulder_ice_roll_right_1,
				R.drawable.boulder_ice_roll_right_2,
				R.drawable.boulder_ice_roll_right_3,
				R.drawable.boulder_ice_roll_right_4);
		running_up = Arrays.asList(R.drawable.boulder_ice_roll_up_1,
				R.drawable.boulder_ice_roll_up_2,
				R.drawable.boulder_ice_roll_up_3,
				R.drawable.boulder_ice_roll_up_4,
				R.drawable.boulder_ice_roll_up_5);
		running_down = Arrays.asList(R.drawable.boulder_ice_roll_down_1,
				R.drawable.boulder_ice_roll_down_2,
				R.drawable.boulder_ice_roll_down_3,
				R.drawable.boulder_ice_roll_down_4,
				R.drawable.boulder_ice_roll_down_5);

		initializeBitmap();
	}
}

package com.jhu.oose16.zombieattack.view.component.object.cycleplay;

import java.util.Arrays;

import com.jhu.oose16.zombieattack.model.objectstate.ExpirableObjectState;
import com.jhu.oose16.zombieattack.view.activities.R;

public class BoulderFireCyclePlay extends CyclePlay {

	public BoulderFireCyclePlay(ExpirableObjectState firstObjectState) {
		super(firstObjectState);

		appearance = Arrays.asList(R.drawable.boulder_fire_appearance_1,
				R.drawable.boulder_fire_appearance_2,
				R.drawable.boulder_fire_appearance_3,
				R.drawable.boulder_fire_appearance_4,
				R.drawable.boulder_fire_appearance_5,
				R.drawable.boulder_fire_appearance_6,
				R.drawable.boulder_fire_appearance_7,
				R.drawable.boulder_fire_appearance_8,
				R.drawable.boulder_fire_appearance_9);

		disappearance_up = Arrays.asList(
				R.drawable.boulder_fire_destruction_up_1,
				R.drawable.boulder_fire_destruction_up_2,
				R.drawable.boulder_fire_destruction_up_3,
				R.drawable.boulder_fire_destruction_up_4,
				R.drawable.boulder_fire_destruction_up_5,
				R.drawable.boulder_fire_destruction_up_6,
				R.drawable.boulder_fire_destruction_up_7,
				R.drawable.boulder_fire_destruction_up_8);
		
		disappearance_left = Arrays.asList(
				R.drawable.boulder_fire_destruction_left_1,
				R.drawable.boulder_fire_destruction_left_2,
				R.drawable.boulder_fire_destruction_left_3,
				R.drawable.boulder_fire_destruction_left_4,
				R.drawable.boulder_fire_destruction_left_5,
				R.drawable.boulder_fire_destruction_left_6,
				R.drawable.boulder_fire_destruction_left_7,
				R.drawable.boulder_fire_destruction_left_8);
		
		disappearance_down = Arrays.asList(
				R.drawable.boulder_fire_destruction_down_1,
				R.drawable.boulder_fire_destruction_down_2,
				R.drawable.boulder_fire_destruction_down_3,
				R.drawable.boulder_fire_destruction_down_4,
				R.drawable.boulder_fire_destruction_down_5,
				R.drawable.boulder_fire_destruction_down_6,
				R.drawable.boulder_fire_destruction_down_7,
				R.drawable.boulder_fire_destruction_down_8);
		
		disappearance_right = Arrays.asList(
				R.drawable.boulder_fire_destruction_right_1,
				R.drawable.boulder_fire_destruction_right_2,
				R.drawable.boulder_fire_destruction_right_3,
				R.drawable.boulder_fire_destruction_right_4,
				R.drawable.boulder_fire_destruction_right_5,
				R.drawable.boulder_fire_destruction_right_6,
				R.drawable.boulder_fire_destruction_right_7,
				R.drawable.boulder_fire_destruction_right_8);
		
		running_left = Arrays.asList(R.drawable.boulder_fire_roll_left_1,
				R.drawable.boulder_fire_roll_left_2,
				R.drawable.boulder_fire_roll_left_3,
				R.drawable.boulder_fire_roll_left_4,
				R.drawable.boulder_fire_roll_left_5,
				R.drawable.boulder_fire_roll_left_6);
		running_right = Arrays.asList(R.drawable.boulder_fire_roll_right_1,
				R.drawable.boulder_fire_roll_right_2,
				R.drawable.boulder_fire_roll_right_3,
				R.drawable.boulder_fire_roll_right_4,
				R.drawable.boulder_fire_roll_right_5,
				R.drawable.boulder_fire_roll_right_6);
		running_up = Arrays.asList(R.drawable.boulder_fire_roll_up_1,
				R.drawable.boulder_fire_roll_up_2,
				R.drawable.boulder_fire_roll_up_3,
				R.drawable.boulder_fire_roll_up_4,
				R.drawable.boulder_fire_roll_up_5,
				R.drawable.boulder_fire_roll_up_6);
		running_down = Arrays.asList(R.drawable.boulder_fire_roll_down_1,
				R.drawable.boulder_fire_roll_down_2,
				R.drawable.boulder_fire_roll_down_3,
				R.drawable.boulder_fire_roll_down_4,
				R.drawable.boulder_fire_roll_down_5,
				R.drawable.boulder_fire_roll_down_6);

		initializeBitmap();
	}
}

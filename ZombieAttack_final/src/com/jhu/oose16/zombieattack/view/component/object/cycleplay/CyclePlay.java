package com.jhu.oose16.zombieattack.view.component.object.cycleplay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Bitmap;

import com.jhu.oose16.zombieattack.model.objectstate.DirectionState;
import com.jhu.oose16.zombieattack.model.objectstate.ExpirableObjectState;
import com.jhu.oose16.zombieattack.model.objectstate.InvincibleState;
import com.jhu.oose16.zombieattack.model.objectstate.LifeState;
import com.jhu.oose16.zombieattack.model.objectstate.RunningState;
import com.jhu.oose16.zombieattack.view.attached.BitmapManager;
import com.jhu.oose16.zombieattack.view.attached.FrameTransformation;

public abstract class CyclePlay {

	private int order = 0;
	private List<Integer> currentImageIDs;
	private ExpirableObjectState currentObjectState;

	private Map<ExpirableObjectState, List<Integer>> objectStateImageIDs;

	protected List<Integer> appearance;

	protected List<Integer> disappearance_left;
	protected List<Integer> disappearance_right;
	protected List<Integer> disappearance_up;
	protected List<Integer> disappearance_down;

	protected List<Integer> running_left;
	protected List<Integer> running_right;
	protected List<Integer> running_up;
	protected List<Integer> running_down;

	protected List<Integer> running_invincible_left;
	protected List<Integer> running_invincible_right;
	protected List<Integer> running_invincible_up;
	protected List<Integer> running_invincible_down;

	protected List<Integer> standing_left;
	protected List<Integer> standing_right;
	protected List<Integer> standing_up;
	protected List<Integer> standing_down;

	protected List<Integer> standing_invincible_left;
	protected List<Integer> standing_invincible_right;
	protected List<Integer> standing_invincible_up;
	protected List<Integer> standing_invincible_down;

	public CyclePlay(ExpirableObjectState firstObjectState) {
		objectStateImageIDs = new HashMap<ExpirableObjectState, List<Integer>>();
		currentObjectState = firstObjectState.getCopy(firstObjectState);
	}

	public Bitmap getBitmap(FrameTransformation frameTransformation) {
		return BitmapManager.getBitmap(currentImageIDs.get(order),
				frameTransformation);
	}

	public void playNextImage(ExpirableObjectState objectState) {
		if (!currentObjectState.equals(objectState)) {
			currentObjectState = objectState.getCopy(objectState);
			currentImageIDs = objectStateImageIDs.get(currentObjectState);
			order = 0;
		} else {
			order = (order + 1) % currentImageIDs.size();
		}
	}

	protected void initializeBitmap() {
		objectStateImageIDs.put(new ExpirableObjectState(LifeState.APPEARANCE,
				InvincibleState.NORMAL, DirectionState.DOWN,
				RunningState.RUNNING), appearance);

		objectStateImageIDs
				.put(new ExpirableObjectState(LifeState.DISAPPEARANCE,
						InvincibleState.NORMAL, DirectionState.LEFT,
						RunningState.STANDING), disappearance_left);
		objectStateImageIDs.put(new ExpirableObjectState(
				LifeState.DISAPPEARANCE, InvincibleState.NORMAL,
				DirectionState.RIGHT, RunningState.STANDING),
				disappearance_right);
		objectStateImageIDs.put(new ExpirableObjectState(
				LifeState.DISAPPEARANCE, InvincibleState.NORMAL,
				DirectionState.UP, RunningState.STANDING), disappearance_up);
		objectStateImageIDs
				.put(new ExpirableObjectState(LifeState.DISAPPEARANCE,
						InvincibleState.NORMAL, DirectionState.DOWN,
						RunningState.STANDING), disappearance_down);

		objectStateImageIDs.put(new ExpirableObjectState(LifeState.NORMAL,
				InvincibleState.NORMAL, DirectionState.LEFT,
				RunningState.RUNNING), running_left);
		objectStateImageIDs.put(new ExpirableObjectState(LifeState.NORMAL,
				InvincibleState.NORMAL, DirectionState.RIGHT,
				RunningState.RUNNING), running_right);
		objectStateImageIDs.put(
				new ExpirableObjectState(LifeState.NORMAL,
						InvincibleState.NORMAL, DirectionState.UP,
						RunningState.RUNNING), running_up);
		objectStateImageIDs.put(new ExpirableObjectState(LifeState.NORMAL,
				InvincibleState.NORMAL, DirectionState.DOWN,
				RunningState.RUNNING), running_down);

		objectStateImageIDs.put(new ExpirableObjectState(LifeState.NORMAL,
				InvincibleState.INVINCIBLE, DirectionState.LEFT,
				RunningState.RUNNING), running_invincible_left);
		objectStateImageIDs.put(new ExpirableObjectState(LifeState.NORMAL,
				InvincibleState.INVINCIBLE, DirectionState.RIGHT,
				RunningState.RUNNING), running_invincible_right);
		objectStateImageIDs.put(new ExpirableObjectState(LifeState.NORMAL,
				InvincibleState.INVINCIBLE, DirectionState.UP,
				RunningState.RUNNING), running_invincible_up);
		objectStateImageIDs.put(new ExpirableObjectState(LifeState.NORMAL,
				InvincibleState.INVINCIBLE, DirectionState.DOWN,
				RunningState.RUNNING), running_invincible_down);

		objectStateImageIDs.put(new ExpirableObjectState(LifeState.NORMAL,
				InvincibleState.NORMAL, DirectionState.LEFT,
				RunningState.STANDING), standing_left);
		objectStateImageIDs.put(new ExpirableObjectState(LifeState.NORMAL,
				InvincibleState.NORMAL, DirectionState.RIGHT,
				RunningState.STANDING), standing_right);
		objectStateImageIDs.put(new ExpirableObjectState(LifeState.NORMAL,
				InvincibleState.NORMAL, DirectionState.UP,
				RunningState.STANDING), standing_up);
		objectStateImageIDs.put(new ExpirableObjectState(LifeState.NORMAL,
				InvincibleState.NORMAL, DirectionState.DOWN,
				RunningState.STANDING), standing_down);

		objectStateImageIDs.put(new ExpirableObjectState(LifeState.NORMAL,
				InvincibleState.INVINCIBLE, DirectionState.LEFT,
				RunningState.STANDING), standing_invincible_left);
		objectStateImageIDs.put(new ExpirableObjectState(LifeState.NORMAL,
				InvincibleState.INVINCIBLE, DirectionState.RIGHT,
				RunningState.STANDING), standing_invincible_right);
		objectStateImageIDs.put(new ExpirableObjectState(LifeState.NORMAL,
				InvincibleState.INVINCIBLE, DirectionState.UP,
				RunningState.STANDING), standing_invincible_up);
		objectStateImageIDs.put(new ExpirableObjectState(LifeState.NORMAL,
				InvincibleState.INVINCIBLE, DirectionState.DOWN,
				RunningState.STANDING), standing_invincible_down);

		currentImageIDs = objectStateImageIDs.get(currentObjectState);

		disappearance_left = null;
		disappearance_right = null;
		disappearance_up = null;
		disappearance_down = null;

		running_left = null;
		running_right = null;
		running_up = null;
		running_down = null;

		running_invincible_left = null;
		running_invincible_right = null;
		running_invincible_up = null;
		running_invincible_down = null;

		standing_left = null;
		standing_right = null;
		standing_up = null;
		standing_down = null;

		standing_invincible_left = null;
		standing_invincible_right = null;
		standing_invincible_up = null;
		standing_invincible_down = null;
	}

}

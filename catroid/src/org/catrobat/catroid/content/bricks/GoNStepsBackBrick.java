/**
 *  Catroid: An on-device visual programming system for Android devices
 *  Copyright (C) 2010-2013 The Catrobat Team
 *  (<http://developer.catrobat.org/credits>)
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *  
 *  An additional term exception under section 7 of the GNU Affero
 *  General Public License, version 3, is available at
 *  http://developer.catrobat.org/license_additional_term
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU Affero General Public License for more details.
 *  
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.catroid.content.bricks;

import java.util.List;

import org.catrobat.catroid.R;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.content.actions.ExtendedActions;
import org.catrobat.catroid.formulaeditor.Formula;
import org.catrobat.catroid.ui.fragment.FormulaEditorFragment;
import org.catrobat.catroid.utils.Utils;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class GoNStepsBackBrick implements Brick, OnClickListener {
	private static final long serialVersionUID = 1L;
	private Sprite sprite;
	private Formula steps;

	public GoNStepsBackBrick(Sprite sprite, int stepsValue) {
		this.sprite = sprite;
		steps = new Formula(stepsValue);
	}

	public GoNStepsBackBrick(Sprite sprite, Formula steps) {
		this.sprite = sprite;
		this.steps = steps;
	}

	public GoNStepsBackBrick() {

	}

	@Override
	public int getRequiredResources() {
		return NO_RESOURCES;
	}

	@Override
	public Sprite getSprite() {
		return this.sprite;
	}

	@Override
	public View getView(Context context, int brickId, BaseAdapter adapter) {
		View view = View.inflate(context, R.layout.brick_go_back, null);

		TextView text = (TextView) view.findViewById(R.id.brick_go_back_prototype_text_view);
		EditText edit = (EditText) view.findViewById(R.id.brick_go_back_edit_text);

		steps.setTextFieldId(R.id.brick_go_back_edit_text);
		steps.refreshTextField(view);

		TextView times = (TextView) view.findViewById(R.id.brick_go_back_layers_text_view);

		if (steps.isSingleNumberFormula()) {
			times.setText(view.getResources().getQuantityString(R.plurals.brick_go_back_layer_plural,
					Utils.convertDoubleToPluralInteger(steps.interpretFloat(sprite))));
		} else {

			// Random Number to get into the "other" keyword for values like 0.99 or 2.001 seconds or degrees
			// in hopefully all possible languages
			times.setText(view.getResources().getQuantityString(R.plurals.brick_go_back_layer_plural,
					Utils.TRANSLATION_PLURAL_OTHER_INTEGER));
		}

		text.setVisibility(View.GONE);
		edit.setVisibility(View.VISIBLE);
		edit.setOnClickListener(this);

		return view;
	}

	@Override
	public View getPrototypeView(Context context) {
		View view = View.inflate(context, R.layout.brick_go_back, null);
		TextView times = (TextView) view.findViewById(R.id.brick_go_back_layers_text_view);

		if (steps.isSingleNumberFormula()) {
			times.setText(view.getResources().getQuantityString(R.plurals.brick_go_back_layer_plural,
					Utils.convertDoubleToPluralInteger(steps.interpretFloat(sprite))));
		} else {

			// Random Number to get into the "other" keyword for values like 0.99 or 2.001 seconds or degrees
			// in hopefully all possible languages
			times.setText(view.getResources().getQuantityString(R.plurals.brick_go_back_layer_plural,
					Utils.TRANSLATION_PLURAL_OTHER_INTEGER));
		}
		return view;
	}

	@Override
	public Brick clone() {
		return new GoNStepsBackBrick(getSprite(), steps.clone());
	}

	@Override
	public void onClick(View view) {
		FormulaEditorFragment.showFragment(view, this, steps);
	}

	@Override
	public List<SequenceAction> addActionToSequence(SequenceAction sequence) {
		sequence.addAction(ExtendedActions.goNStepsBack(sprite, steps));
		return null;
	}
}

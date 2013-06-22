package org.catrobat.catroid.ui;

import org.catrobat.catroid.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	int flag = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button clickme = (Button) findViewById(R.id.landscape);
		Button clickme1 = (Button) findViewById(R.id.portrait);
		clickme.setOnClickListener(myhandler);
		clickme1.setOnClickListener(myhandler);
	}

	View.OnClickListener myhandler = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.landscape:
					flag = 1;
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
					break;

				case R.id.portrait:

					Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
					startActivity(i);
					break;
			}
		}
	};

}

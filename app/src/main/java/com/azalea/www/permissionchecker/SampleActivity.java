package com.azalea.www.permissionchecker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cmccmap.permissionchecker.TestUtil;

public class SampleActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);


		TestUtil.toast(SampleActivity.this);
	}
}

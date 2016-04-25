package com.azalea.www.permissionchecker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.cmccmap.permissionchecker.PermissionChecker;
import com.cmccmap.permissionchecker.PermissionRequestor;

public class SampleActivity extends AppCompatActivity {

	TextView mTxtLog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);


		mTxtLog = (TextView) findViewById(R.id.txt_info);

		if (false == PermissionRequestor.reqeustPermissionInAct(SampleActivity.this, new String[]{
				Manifest.permission.RECORD_AUDIO,
				Manifest.permission.CAMERA,
				Manifest.permission.WRITE_CONTACTS
		}, 0x001)) {
			logMe("All rights reserved");
		}

		if (PackageManager.PERMISSION_GRANTED == PermissionChecker.checkSelfPermission(SampleActivity.this, Manifest.permission.RECORD_AUDIO)) {
			logMe("RECORD_AUDIO right");
		}
	}

	private void logMe(String log){
		mTxtLog.append(log + "\n");
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}
}

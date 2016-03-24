package com.azalea.www.permissionchecker;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cmccmap.permissionchecker.PermissionRequestor;

public class SampleActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);

		if (false == PermissionRequestor.reqeustPermissionInAct(SampleActivity.this, new String[]{
				Manifest.permission.RECORD_AUDIO,
				Manifest.permission.CAMERA,
				Manifest.permission.WRITE_CONTACTS
		}, 0x001)) {
			Toast.makeText(SampleActivity.this,"All right",Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}
}

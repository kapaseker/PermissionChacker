package com.azalea.www.permissionchecker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.cmccmap.permissionchecker.PermissionChecker;

public class SampleActivity extends AppCompatActivity {

	TextView mTxtLog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);


		mTxtLog = (TextView) findViewById(R.id.txt_info);

//		if (false == PermissionRequestor.reqeustPermissionInAct(SampleActivity.this, new String[]{
//				Manifest.permission.RECORD_AUDIO,
//				Manifest.permission.CAMERA,
//				Manifest.permission.WRITE_CONTACTS
//		}, 0x001)) {
//			logMe("All rights reserved");
//		}
//
//		if (PackageManager.PERMISSION_GRANTED == PermissionChecker.checkSelfPermission(SampleActivity.this, Manifest.permission.RECORD_AUDIO)) {
//			logMe("RECORD_AUDIO right");
//		}

		findViewById(R.id.btn_req).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (PackageManager.PERMISSION_DENIED == ContextCompat.checkSelfPermission(SampleActivity.this, Manifest.permission.CAMERA)) {
//					requestPermissions(new String[]{Manifest.permission.CAMERA},0x002);

					if (true == ActivityCompat.shouldShowRequestPermissionRationale(SampleActivity.this, Manifest.permission.CAMERA)) {
						logMe("你需要拍照权限");
						ActivityCompat.requestPermissions(SampleActivity.this, new String[]{Manifest.permission.CAMERA}, 0x002);
					} else {
						ActivityCompat.requestPermissions(SampleActivity.this, new String[]{Manifest.permission.CAMERA}, 0x002);
					}

				}
			}
		});
	}

	private void logMe(String log) {
		mTxtLog.append(log + "\n");
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

		switch (requestCode){
			case 0x002:
				if(PermissionChecker.hasDeniedPermission(grantResults)){
					logMe("拒绝此权限了");

					if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
						logMe("用户还可以再次申请哦");
					} else {
						logMe("你自己打开设置，去勾选这个权限吧");
					}

				} else {
					logMe("完美授权了");
				}
				break;
		}

		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}
}

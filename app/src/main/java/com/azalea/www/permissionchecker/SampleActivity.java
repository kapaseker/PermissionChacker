package com.azalea.www.permissionchecker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cmccmap.permissionchecker.PermissionChecker;
import com.cmccmap.permissionchecker.PermissionRequestor;

public class SampleActivity extends AppCompatActivity implements RequestPermissionFragment.OnFragmentInteractionListener {

	TextView mTxtLog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);
		mTxtLog = (TextView) findViewById(R.id.txt_info);
		getSupportFragmentManager().beginTransaction().add(R.id.container, new RequestPermissionFragment()).addToBackStack("Fragment").commit();
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

	@Override
	public void OnRequestPermission(String[] permission, int requestCode) {
		if (PackageManager.PERMISSION_DENIED == PermissionChecker.checkSelfPermission(SampleActivity.this, Manifest.permission.CAMERA)) {
			if (true == ActivityCompat.shouldShowRequestPermissionRationale(SampleActivity.this, Manifest.permission.CAMERA)) {
				logMe("你需要拍照权限");
				PermissionRequestor.reqeustPermissionInAct(SampleActivity.this, new String[]{Manifest.permission.CAMERA}, 0x002);
			} else {
				PermissionRequestor.reqeustPermissionInAct(SampleActivity.this, new String[]{Manifest.permission.CAMERA}, 0x002);
			}
		} else {
			logMe("已经授权了，亲");
		}
	}

	@Override
	public void OnPrintLog(String msg) {
		logMe(msg);
	}
}

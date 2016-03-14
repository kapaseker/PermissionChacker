package com.cmccmap.permissionchecker;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;

/**
 * Created by Panoo on 2016/3/7.
 */
public class PermissionRequestor {

	/**
	 * 在activity的内部进行权限的申请，不发生activity跳转的情况
	 * @param activity 当前的Activity
	 * @param permissions 当前的权限组
	 * @param requestCode 申请id
	 *
	 * @return true 表示有申请的权限，fasle表示没有申请的权限
	 * */
	@TargetApi(Build.VERSION_CODES.M)
	public static boolean reqeustPermissionInAct(Activity activity, String[] permissions, int requestCode) {

		String[] permissionResult = PermissionChecker.processDeniedPermissions(activity, permissions);

		if(permissionResult.length>0){
			activity.requestPermissions(permissionResult,requestCode);
			return true;
		}else {
			return false;
		}
	}
}

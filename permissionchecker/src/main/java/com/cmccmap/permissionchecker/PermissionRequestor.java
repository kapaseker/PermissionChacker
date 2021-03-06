package com.cmccmap.permissionchecker;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

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
	public static boolean reqeustPermissionInAct(Activity activity, String[] permissions, int requestCode) {

		String[] permissionResult = PermissionChecker.processDeniedPermissions(activity, permissions);

		if(permissionResult.length>0){
			ActivityCompat.requestPermissions(activity, permissionResult, requestCode);
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 在Fragment的内部进行权限的申请，不发生Fragment跳转的情况
	 * @param fragment 当前的Fragment
	 * @param permissions 当前的权限组
	 * @param requestCode 申请id
	 *
	 * @return true 表示有申请的权限，fasle表示没有申请的权限
	 * */
	public static boolean reqeustPermissionInFrag(Fragment fragment, String[] permissions, int requestCode) {

		String[] permissionResult = PermissionChecker.processDeniedPermissions(fragment.getActivity(), permissions);

		if (permissionResult.length > 0) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				fragment.requestPermissions(permissionResult, requestCode);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 在Fragment的内部进行权限的申请，不发生Fragment跳转的情况
	 * @param fragment 当前的Fragment
	 * @param permissions 当前的权限组
	 * @param requestCode 申请id
	 *
	 * @return true 表示有申请的权限，fasle表示没有申请的权限
	 * */
	public static boolean reqeustPermissionInFrag(android.support.v4.app.Fragment fragment, String[] permissions, int requestCode) {

		String[] permissionResult = PermissionChecker.processDeniedPermissions(fragment.getActivity(), permissions);

		if (permissionResult.length > 0) {
			fragment.requestPermissions(permissionResult, requestCode);
			return true;
		} else {
			return false;
		}
	}
}

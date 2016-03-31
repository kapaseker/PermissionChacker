package com.cmccmap.permissionchecker;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Panoo on 2016/3/6.
 */

/**
 * 权限的校验器，一个简单的权限校验工具
 */
public class PermissionChecker {

	/**
	 * 检验一个权限组的申请结果并返回
	 * @param context
	 * @param permissions 需要查看结果的权限组
	 * @return 返回对应每个权限的结果
	 * */
	@TargetApi(Build.VERSION_CODES.M)
	public static int[] checkSelfPermissions(Context context, String[] permissions) {

		int length = permissions.length;
		int[] permissionResult = new int[length];

		if (!isSupportPermissionCheck()) {
			for (int i = 0; i < length; ++i) {
				permissionResult[i] = PackageManager.PERMISSION_GRANTED;
			}
		} else {
			for (int i = 0; i < length; ++i) {
				permissionResult[i] = context.checkSelfPermission(permissions[i]);
			}
		}

		return permissionResult;
	}

	/**
	 * 检验一个权限组的申请结果并返回
	 * @param context
	 * @param permissions 需要查看结果的权限组
	 * @return 返回对应每个权限的结果
	 * */
	@TargetApi(Build.VERSION_CODES.M)
	public static int checkSelfPermissions(Context context, String permissions) {

		int permissionResult = 0;

		if (!isSupportPermissionCheck()) {
				permissionResult = PackageManager.PERMISSION_GRANTED;
		} else {
				permissionResult = context.checkSelfPermission(permissions);
		}

		return permissionResult;
	}

	/**
	 * 检验一个权限组中未被申请的权限
	 * @param context
	 * @param permissions 需要查看结果的权限组
	 * @return 返回没有被申请的权限
	 * */
	public static String[] processDeniedPermissions(Context context, String[] permissions) {
		return extractPermissions(context,permissions,PackageManager.PERMISSION_DENIED);
	}


	/**
	 * 检验一个权限组中是否有未被申请的权限
	 * @param grantResult 需要查看权限结果
	 * @return 有没有没被申请的权限
	 * */
	public static boolean hasDeniedPermissions(int[] grantResult){
		return extractPermissions(grantResult,PackageManager.PERMISSION_DENIED);
	}

	/**
	 * 检验一个权限组中已经被申请的权限
	 * @param context
	 * @param permissions 需要查看结果的权限组
	 * @return 返回已经被申请的权限
	 * */
	public static String[] processGrantedPermissions(Context context, String[] permissions) {
		return extractPermissions(context,permissions,PackageManager.PERMISSION_GRANTED);
	}

	/**
	 * 检验一个权限组中是否有已经申请的权限
	 * @param grantResult 需要查看权限结果
	 * @return 有没有被申请的权限
	 * */
	public static boolean hasGrantedPermissions(int[] grantResult){
		return extractPermissions(grantResult,PackageManager.PERMISSION_GRANTED);
	}

	/**
	 * 检验一个权限组是否有未申请的权限
	 * @param context
	 * @param permissions 需要查看结果的权限组
	 * @return true or false
	 * */
	public static boolean hasDeniedPermissions(Context context, String[] permissions) {
		return extractPermissions(context, permissions, PackageManager.PERMISSION_DENIED).length != 0;
	}

	/**
	 * 检验一个权限组是否有已经申请的权限
	 * @param context
	 * @param permissions 需要查看结果的权限组
	 * @return true or false
	 * */
	public static boolean hasGrantedPermissions(Context context, String[] permissions) {
		return extractPermissions(context, permissions, PackageManager.PERMISSION_GRANTED).length != 0;
	}


	/**
	 * 提取一组权限组中满足要求的权限
	 *
	 * @param context
	 * @param permissions 需要查看结果的权限组
	 * @param flag 权限的标识
	 *
	 * @return 返回满足要求的权限
	 */
	public static String[] extractPermissions(Context context, String[] permissions ,int flag) {

		int[] permissionResult = checkSelfPermissions(context, permissions);
		int length = permissionResult.length;
		List<String> permissionList = new LinkedList<String>();
		for (int i = 0; i < length; ++i) {
			if (permissionResult[i] == flag) {
				permissionList.add(permissions[i]);
			}
		}

		return permissionList.toArray(new String[0]);
	}

	/**
	 * 提取一组权限组中满足要求的权限
	 * @param grantResult 需要查看结果的权限组
	 * @param flag        权限的标识
	 * @return 判断是否有满足标识的权限
	 */
	public static boolean extractPermissions(int[] grantResult, int flag) {

		int length = grantResult.length;
		for (int i = 0; i < length; ++i) {
			if (grantResult[i] == flag) {
				return true;
			}
		}
		return false;
	}

	public static boolean isSupportPermissionCheck() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
	}
}

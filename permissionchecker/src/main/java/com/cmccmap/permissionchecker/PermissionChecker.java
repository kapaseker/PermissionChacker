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
		for (int i = 0; i < length; ++i) {
			permissionResult[i] = context.checkSelfPermission(permissions[i]);
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
	 * 检验一个权限组中已经被申请的权限
	 * @param context
	 * @param permissions 需要查看结果的权限组
	 * @return 返回已经被申请的权限
	 * */
	public static String[] processGrantedPermissions(Context context, String[] permissions) {
		return extractPermissions(context,permissions,PackageManager.PERMISSION_GRANTED);
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
	private static String[] extractPermissions(Context context, String[] permissions ,int flag) {

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
}

package com.cmccmap.permissionchecker;

import android.content.Context;

/**
 * Created by Panoo on 2016/3/6.
 */
public class PermissionChecker {
	
	public int[] checkSelfPermissions(Context context, String[] permissions){
		int length = permissions.length;
		int[] permissionResult = new int[length];
		for (int i = 0;i<length;++i){
			permissionResult[i] = context.checkSelfPermission(permissions[i]);
		}
		return permissionResult;
	}
}

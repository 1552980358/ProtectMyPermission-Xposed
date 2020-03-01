package app.github1552980358.android.permissioncontrol.xposed

import android.content.pm.PackageManager
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

/**
 * @File    : Hooking
 * @Author  : 1552980358
 * @Date    : 2020/3/1
 * @TIME    : 16:25
 **/

interface Hooking {
    
    /**
     * [hookPermissionChecking]
     * @description
     *    hock the checking of permission
     *    劫持用于检查限权的方法
     * @author 1552980358
     **/
    fun hookPermissionChecking(lpparam: XC_LoadPackage.LoadPackageParam?) {
        XposedHelpers.findAndHookMethod(
            lpparam?.classLoader?.loadClass("android.content.Context"),
            "checkPermission",
            String,
            Int,
            Int,
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam?): Any {
                    // return gained
                    // 返回已获取
                    return PackageManager.PERMISSION_DENIED
                }
            }
        )
    }
    
    /**
     * [hookPermissionRequest]
     * @description
     *    hook request for permission
     *    劫持获取限权
     * @author 1552980358
     *
     * ! sth problem not solved !
     * ! 有问题为解决 !
     *
     * @hide
     **/
    fun hookPermissionRequest(lpparam: XC_LoadPackage.LoadPackageParam?) {
        XposedHelpers.findAndHookMethod(
            lpparam?.classLoader?.loadClass("android.app.Activity"),
            "requestPermissions",
            object : XC_MethodReplacement() {
                override fun replaceHookedMethod(param: MethodHookParam?): Any {
                    // Do Nothing
                    // 什么也不做
                    return hookPermissionRequestResult(lpparam)
                }
            }
        )
    }
    
    /**
     * [hookPermissionRequestResult]
     * @description
     *    hook result checking method of permission
     *    劫持检查限权是否获取的方法
     * @author 1552980358
     *
     * ! sth problem not solved !
     * ! 有问题为解决 !
     *
     * @hide
     **/
    fun hookPermissionRequestResult(lpparam: XC_LoadPackage.LoadPackageParam?) {
        XposedHelpers.findAndHookMethod(
            lpparam?.classLoader?.loadClass("android.app.Activity"),
            "onRequestPermissionsResult",
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam?) {
                    if (param != null) {
                        param.args[2] = arrayListOf<Int>().apply {
                            for (i in 0 until 100) {
                                add(PackageManager.PERMISSION_DENIED)
                            }
                        }.toArray()
                    }
                }
            }
        )
    }
    
}
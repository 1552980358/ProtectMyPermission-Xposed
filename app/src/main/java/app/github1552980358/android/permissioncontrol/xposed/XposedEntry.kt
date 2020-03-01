package app.github1552980358.android.permissioncontrol.xposed

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage

/**
 * @File    : XposedEntry
 * @Author  : 1552980358
 * @Date    : 2020/3/1
 * @TIME    : 16:23
 **/

class XposedEntry : IXposedHookLoadPackage, Hooking {
    
    /**
     * [handleLoadPackage]
     * @description
     **/
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
    
    }
    
}
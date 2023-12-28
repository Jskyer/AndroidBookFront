package com.example.newhelloworld.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;

public class DialogUtil {

    public static AlertDialog permissionDialog(Context context){
        return new AlertDialog.Builder(context)
                        .setTitle("PodOasis")//设置标题
                        .setMessage("请开启文件访问权限，否则无法正常使用本应用！")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                                context.startActivity(intent);
                            }
                        }).create();
    }


    /** * * @param context 上下文 *
     * @param resource 资源 layout布局 *
     * @param dialogStyle 弹出样式 *
     * @param gravity 方向 *
     * @param width 宽 *
     * @param height 高 *
     * @param animation 动画 */
//    public void customDialog(Context context, int resource,
//                             int dialogStyle,
//                             int gravity,
//                             int width,
//                             int height,
//                             int animation) {
//        View view = View.inflate(context, resource, null);
//        final Dialog dialog = new Dialog(context, dialogStyle);
//        dialog.setContentView(view);
//        WindowManager.LayoutParams layoutParams =
//                dialog.getWindow().getAttributes();
//        layoutParams.width = width;
//        layoutParams.height = height;
////        layoutParams.y = 180;//距离顶部的距离
//        dialog.getWindow().setAttributes(layoutParams);
//        dialog.getWindow().setGravity(gravity);
//        dialog.getWindow().setWindowAnimations(animation);
//        dialog.show();
//    }

}

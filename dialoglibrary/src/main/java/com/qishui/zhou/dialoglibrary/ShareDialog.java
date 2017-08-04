package com.qishui.zhou.dialoglibrary;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

/**
 * 作者：Created by zhou on 2017/8/4 09:15
 * 邮箱：qishuichixi@126.com
 * 版本：V 1.0
 * 描述：
 */

public class ShareDialog extends Dialog implements View.OnClickListener {


    Context mContext;
    String content;

    public ShareDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public ShareDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;

    }

    public ShareDialog(Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }

    protected ShareDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }


    @Override
    public void onClick(View v) {

    }


    OnCloseListener listener;

    public void setListener(OnCloseListener listener) {
        this.listener = listener;
    }

    public interface OnCloseListener {

        void onClick(Dialog dialog, boolean confirm);

    }


}

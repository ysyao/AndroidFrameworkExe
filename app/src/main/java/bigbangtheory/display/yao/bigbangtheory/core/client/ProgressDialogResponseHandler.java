package bigbangtheory.display.yao.bigbangtheory.core.client;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * 在发送请求的时候，显示一个progressDialog，请求结束之后则dismiss，
 * 如果progressDialog在中途被取消掉则对应请求也取消掉。
 * Created by JimmyandHurry on 2015/2/2.
 */
public abstract class ProgressDialogResponseHandler extends CheckIfCouldAccessInternetResponseHandler {
    private ProgressDialog dialog;
    private Context context;

    public ProgressDialogResponseHandler(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog = new ProgressDialog(context);
        dialog.setTitle("正在获取数据");
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                sendCancelMessage();
            }
        });
        dialog.show();
    }

    @Override
    public void onFinish() {
        super.onFinish();
        dismissProgress();
    }

    @Override
    public void onCancel() {
        super.onCancel();
        dismissProgress();
    }

    @Override
    protected void cancelResponse() {
        super.cancelResponse();
        dismissProgress();
    }

    /**
     * 取消等待画面
     */
    private void dismissProgress() {
        if(dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    protected Context getApplicationContext() {
        return context;
    }
}

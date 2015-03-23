package activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by SumitBhatia on 23/03/15.
 * <p/>
 * This activity can contain the default methods which might be used across multiple activities extending from it.
 */
public class BaseActivity extends ActionBarActivity {

    private ProgressDialog progressDialog;

    public void showProgressDialog(Context context, String title, String message, boolean inDeterminate, boolean isCancelable) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(context, title, message, inDeterminate, isCancelable);
        }
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog = null;
    }

    public void showAlertDialog(Context context, String title, String message, boolean isCancelable) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
        .setTitle(title)
        .setCancelable(isCancelable);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void setActionBarTitle(Context context, String title) {
        if (((BaseActivity) context).getSupportActionBar() != null) {
            ((BaseActivity) context).getSupportActionBar().setTitle(title);
        }

    }

    public void hideKeyboard() {
        try {
            InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showKeyboard() {
        try {
            InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

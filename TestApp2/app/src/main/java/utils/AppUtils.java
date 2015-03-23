package utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by SumitBhatia on 22/03/15.
 */
public class AppUtils {

    private static ProgressDialog nDialog;

    public enum URL {

        FACTS_URL("https://dl.dropboxusercontent.com/u/746330/facts.json");

        URL(String url) {
            this.url = url;
        }
        private String url;
        public String getURL() {
            return this.url;
        }
    }


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public static boolean isStringNullOrEmpty(String value){
        if(value == null || value.trim().length()<1) return true;
        return false;
    }


//    These 2 methods can be used if we want a common code to show and dismiss progress dialog.
//    Not needed in this case though

//    public static void showProgressDialog(Context context){
//        nDialog = new ProgressDialog(context); //Here I get an error: The constructor ProgressDialog(PFragment) is undefined
//        nDialog.setMessage("Please wait..");
//        nDialog.setTitle("Note");
//        nDialog.setIndeterminate(false);
//        nDialog.setCancelable(false);
//        nDialog.show();
//    }
//
//    public static void hideProgressDialog(){
//        if(nDialog !=null && nDialog.isShowing()) nDialog.dismiss();
//    }
}

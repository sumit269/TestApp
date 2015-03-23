package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by SumitBhatia on 22/03/15.
 */
public class AppUtils {

    public static enum URL {

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
}

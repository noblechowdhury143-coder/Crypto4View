package com.example.crypto4view;
import android.app.*;import android.os.*;import android.Manifest;import android.content.*;import android.content.pm.PackageManager;import android.webkit.*;
public class MainActivity extends Activity{
 public void onCreate(Bundle b){super.onCreate(b);WebView w=new WebView(this);w.getSettings().setJavaScriptEnabled(true);w.getSettings().setDomStorageEnabled(true);w.setWebViewClient(new WebViewClient());
 w.addJavascriptInterface(new Object(){@JavascriptInterface public void saveAlert(String id,String up,String lo){getSharedPreferences("alerts",0).edit().putString("upper_"+id,up).putString("lower_"+id,lo).apply();}},"AndroidAlerts");
 w.loadUrl("file:///android_asset/index.html");setContentView(w);
 if(Build.VERSION.SDK_INT>=33&&checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)!=PackageManager.PERMISSION_GRANTED)requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS},100);
 Intent i=new Intent(this,PriceAlertService.class);if(Build.VERSION.SDK_INT>=26)startForegroundService(i);else startService(i);}
}
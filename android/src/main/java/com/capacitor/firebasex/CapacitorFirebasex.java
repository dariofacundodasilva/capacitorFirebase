package com.capacitor.firebasex;


import android.content.Context;
import android.util.Log;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.getcapacitor.JSObject;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;


import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;




@NativePlugin()
public class CapacitorFirebasex extends Plugin {

    private String apiKey;
    private String authDomain;
    private String databaseURL;
    private String projectId;
    private String storageBucket;
    private String messagingSenderId;
    private String appId;
    private String measurementId;

    //remote config
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private FirebaseRemoteConfig mSecondaryFirebaseRemoteConfig;

    //Crashlytics
    protected static final String TAG = "FirebasePlugin";
    protected static final String KEY = "badge";

    //---------------------------------------------------

    public void setFirebaseConfig(PluginCall call) {
        this.apiKey = call.getString("apiKey");
        this.authDomain = call.getString("authDomain");
        this.databaseURL  = call.getString("databaseURL");
        this.projectId = call.getString("projectId");
        this.storageBucket = call.getString("storageBucket");
        this.messagingSenderId = call.getString("messagingSenderId");
        this.appId = call.getString("appId");
        this.measurementId = call.getString("measurementId");
    }

    @PluginMethod()
    public void initializeAppFirebase(PluginCall call){
        //default firebase app
        call.success();
    }

    @PluginMethod()
    public void initializeSecondaryAppFirebase(PluginCall call){
        this.setFirebaseConfig(call);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId(appId)
                .setApiKey(apiKey)
                .setDatabaseUrl(databaseURL)
                .setStorageBucket(storageBucket)
                .build();
        Context context = this.getContext();
        FirebaseApp.initializeApp(context , options, projectId);
        call.success();
    }


    //-----------------------------------------------------------------------

    //--------------RemoteConfig--------------------

    @PluginMethod()
    public void initRemoteConfig(PluginCall call){
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(call.getInt("minimumFetchInterval"))
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        call.success();
    }

    @PluginMethod()
    public void initSecondaryRemoteConfig(PluginCall call){
        String projectId = call.getString("projectId");
        FirebaseApp instance = FirebaseApp.getInstance(projectId);
        mSecondaryFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance(instance);
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(call.getInt("minimumFetchInterval"))
                .build();
        mSecondaryFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        call.success();
    }

    @PluginMethod()
    public void fetch(final PluginCall call){
        Boolean isSecondary = call.getBoolean("secondary");
        FirebaseRemoteConfig instanceRemoteConfig = (isSecondary != null && isSecondary == true) ? mSecondaryFirebaseRemoteConfig: mFirebaseRemoteConfig;


        instanceRemoteConfig.fetch()
        .addOnCompleteListener(getActivity(),new OnCompleteListener<Void>(){
            @Override
            public void onComplete(Task<Void> task) {
                if (task.isSuccessful()) {
                    call.success();
                } else {
                    call.error(task.getException().getMessage());
                }
            }
        });
    }

    @PluginMethod()
    public void activate(final PluginCall call){
        Boolean isSecondary = call.getBoolean("secondary");
        FirebaseRemoteConfig instanceRemoteConfig = (isSecondary != null && isSecondary == true) ? mSecondaryFirebaseRemoteConfig: mFirebaseRemoteConfig;

        instanceRemoteConfig.activate()
        .addOnCompleteListener(getActivity(),new OnCompleteListener<Boolean>(){
            @Override
            public void onComplete(Task<Boolean> task) {
                if (task.isSuccessful()) {
                    JSObject data = new JSObject();
                    data.put("result",task.getResult());
                    call.success(data);
                } else {
                    call.error(task.getException().getMessage());
                }
            }
        });
    }

    @PluginMethod()
    public void fetchAndActivate(final PluginCall call){
        Boolean isSecondary = call.getBoolean("secondary");
        FirebaseRemoteConfig instanceRemoteConfig = (isSecondary != null && isSecondary == true) ? mSecondaryFirebaseRemoteConfig: mFirebaseRemoteConfig;


        instanceRemoteConfig.fetchAndActivate()
        .addOnCompleteListener(getActivity(),new OnCompleteListener<Boolean>(){
            @Override
            public void onComplete(Task<Boolean> task) {
                if (task.isSuccessful()) {
                    JSObject data = new JSObject();
                    data.put("result",task.getResult());
                    call.success(data);
                } else {
                    call.error(task.getException().getMessage());
                }
            }
        });
    }



    @PluginMethod()
    public void getString(PluginCall call) {
        Boolean isSecondary = call.getBoolean("secondary");
        String key = call.getString("key");
        String result = (isSecondary != null && isSecondary == true) ? getValueSecondary(key).asString() : getValue(key).asString();
        JSObject data = new JSObject();
        data.put("value",result);
        call.success(data);
    }

    @PluginMethod()
    public void getNumber(PluginCall call) {
        Boolean isSecondary = call.getBoolean("secondary");
        String key = call.getString("key");
        Float result = (isSecondary != null && isSecondary == true) ? (float) getValueSecondary(key).asDouble() : (float) getValue(key).asDouble();
        JSObject data = new JSObject();
        data.put("value",result);
        call.success(data);
    }

    @PluginMethod()
    public void getBytes(PluginCall call) {
        Boolean isSecondary = call.getBoolean("secondary");
        String key = call.getString("key");
        byte[] result = (isSecondary != null && isSecondary == true) ? getValueSecondary(key).asByteArray() : getValue(key).asByteArray();
        JSObject data = new JSObject();
        data.put("value",result);
        call.success(data);
    }

    @PluginMethod()
    public void getBoolean(PluginCall call) {
        Boolean isSecondary = call.getBoolean("secondary");
        String key = call.getString("key");
        boolean result = (isSecondary != null && isSecondary == true) ? getValueSecondary(key).asBoolean() : getValue(key).asBoolean();
        JSObject data = new JSObject();
        data.put("value",result);
        call.success(data);
    }

    private FirebaseRemoteConfigValue getValue(String key) {
        return this.mFirebaseRemoteConfig.getValue(key);
    }

    private FirebaseRemoteConfigValue getValueSecondary(String key) {
        return this.mSecondaryFirebaseRemoteConfig.getValue(key);
    }


    //-------------Remote Config----------------


    //-------------Crashlytics-----------

    @PluginMethod()
    public void setUser(PluginCall call) {
        String id = call.getString("id");
        String name = call.getString("name");
        String email = call.getString("email");

        if (id == null) {
            call.reject("missing id property");
        }
        if (name == null) {
            call.reject("missing name property");
        }
        if (email == null) {
            call.reject("missing email property");
        }

        Crashlytics.setUserIdentifier(id);
        Crashlytics.setUserEmail(email);
        Crashlytics.setUserName(name);

        call.success();
    }

    @PluginMethod()
    public void setCrashlyticsUserId(PluginCall call){
        try {
            String id = call.getString("id");
            if (id == null) {
                call.reject("missing id property");
            }
            Crashlytics.setUserIdentifier(id);
            call.success();
        } catch (Exception e) {
            handleExceptionWithContext(e, call);
        }
    }

    @PluginMethod()
    public void setCrashlyticsCollectionEnabled(PluginCall call){
        Context context = this.getContext();
        Fabric.with(context, new Crashlytics());
        call.success();
    }


    @PluginMethod()
    public void sendCrash(PluginCall call){
        Crashlytics.getInstance().crash();//not working on capacitor
    }

    @PluginMethod()
    public void logException(PluginCall call) throws JSONException {
        String message = call.getString("message");
        try {
            // We can optionally be passed a stack trace generated by stacktrace.js.
            if (call.getObject("stacktrace").length() > 0) {
                JSObject stackTrace = call.getObject("stacktrace");
                StackTraceElement[] trace = new StackTraceElement[stackTrace.length()];
                Iterator stackTraceKeys = stackTrace.keys();
                int i = 0;
                while (stackTraceKeys.hasNext()){
                    JSONObject elem = (JSONObject)stackTraceKeys.next();
                    trace[i] = new StackTraceElement(
                            "undefined",
                            elem.optString("functionName", "undefined"),
                            elem.optString("fileName", "undefined"),
                            elem.optInt("lineNumber", -1)
                    );
                    i++;
                }

                Exception e = new JavaScriptException(message);
                e.setStackTrace(trace);
                Crashlytics.logException(e);
            } else {
                Crashlytics.logException(new JavaScriptException(message));
            }

            Log.e(TAG, message);
            call.success();
        } catch (Exception e) {
            Crashlytics.log("logError errored. Orig error: " + message);
            Crashlytics.log(1, TAG, e.getMessage());
            e.printStackTrace();
            call.error(e.getMessage());
        }
    }

    @PluginMethod()
    public void log(PluginCall call){
        Crashlytics.getInstance().log(call.getString("message","default message"));
        call.success();
    }


    protected static void handleExceptionWithContext(Exception e, PluginCall call){
        String msg = e.toString();
        Log.e(TAG, msg);
        Crashlytics.log(msg);
        call.error(msg);
    }


    //-------------Crashlitics-----------



    @PluginMethod()
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", value);
        call.success(ret);
    }
}

package com.example.testflutter;

import android.app.Application;

public class FlutterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
       /* FlutterBoost.instance().setup(this, new FlutterBoostDelegate() {
            @Override
            public void pushNativeRoute(FlutterBoostRouteOptions options) {
                Intent intent = new Intent(FlutterBoost.instance().currentActivity()
                        , FlutterBoostActivity.class);
                FlutterBoost.instance().currentActivity().startActivityForResult(intent, options.requestCode());
            }

            @Override
            public void pushFlutterRoute(FlutterBoostRouteOptions options) {
                Intent intent = new FlutterBoostActivity.CachedEngineIntentBuilder(FlutterBoostActivity.class)
                        .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                        .destroyEngineWithActivity(false)
                        .uniqueId(options.uniqueId())
                        .url(options.pageName())
                        .urlParams(options.arguments())
                        .build(FlutterBoost.instance().currentActivity());
                FlutterBoost.instance().currentActivity().startActivity(intent);
            }
        }, engine -> {
        });*/
    }
}

















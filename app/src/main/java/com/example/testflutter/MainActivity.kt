package com.example.testflutter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.FlutterView
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class MainActivity : AppCompatActivity() {

    companion object {
        const val CHANNEL_NATIVE = "com.example.testflutter/native"
        const val TAG = "MainActivity"
    }

    var flutterEngin: FlutterEngine? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var flutterLayout = findViewById<FrameLayout>(R.id.fl_flutter)
        var rlFragmentLayout = findViewById<RelativeLayout>(R.id.rl_content)
        var flutterView = FlutterView(this)
        var layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        flutterLayout.addView(flutterView, layoutParams)
        flutterEngin = FlutterEngine(this)
        flutterEngin?.navigationChannel?.setInitialRoute("flutter_first?{\"title\":Flutter界面\"}")
        flutterEngin?.dartExecutor?.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        flutterView.attachToFlutterEngine(flutterEngin!!)
        //Flutter向Activity传递参数
        var methodChannel = MethodChannel(flutterEngin!!.dartExecutor, CHANNEL_NATIVE)
        methodChannel.setMethodCallHandler(object : MethodChannel.MethodCallHandler {
            override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
                when (call.method) {
                    "jumpNative" -> {
                        var intent = Intent(this@MainActivity, SecondActivity::class.java)
                        intent.putExtra("name", call.argument("name") as? String)
                        startActivityForResult(intent, 1)
                    }
                    else -> {
                        result.notImplemented()
                    }
                }
            }
        })
        rlFragmentLayout.setOnClickListener {
            initViews2()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult: " + requestCode + " " + resultCode)
        when (resultCode) {
            RESULT_OK -> {
                if (data != null) {
                    var message = data.getStringExtra("message") ?: ""
                    var map = mutableMapOf<String, Any>()
                    map.put("message", message)
                    var channel = MethodChannel(flutterEngin!!.dartExecutor, CHANNEL_NATIVE)
                    channel.invokeMethod("onActivityResult", map)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        flutterEngin?.lifecycleChannel?.appIsResumed()
    }

    override fun onPause() {
        super.onPause()
        flutterEngin?.lifecycleChannel?.appIsInactive()
    }

    override fun onStop() {
        super.onStop()
        flutterEngin?.lifecycleChannel?.appIsPaused()
    }

    /**
     *   将Fragment嵌入Fragment1
     */
    fun initViews() {
        var flutterFragment = FlutterFragment.withNewEngine()
            .initialRoute("flutter_main")
            .build<FlutterFragment>()
        supportFragmentManager.beginTransaction().add(R.id.fl_flutter, flutterFragment).commit()
    }

    /**
     *   将Fragment嵌入Fragment2
     */
    fun initViews2() {
        supportFragmentManager.beginTransaction().replace(R.id.fl_flutter, FluttterPageFragment()).commit()
    }

    class FluttterPageFragment : Fragment() {
        var flutterEngin1: FlutterEngine? = null
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            var flutterView = FlutterView(context!!)
            flutterEngin1 = FlutterEngine(this.context!!)
            flutterEngin1?.navigationChannel?.setInitialRoute("flutter_first?{\"title\":Flutter界面\"}")
            flutterEngin1?.dartExecutor?.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
            flutterView.attachToFlutterEngine(flutterEngin1!!)
            return flutterView
        }
    }
}



















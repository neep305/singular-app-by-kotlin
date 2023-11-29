package com.jason.singularappbykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jason.singularappbykotlin.databinding.ActivityMainBinding
import com.singular.sdk.Singular
import com.singular.sdk.SingularConfig
import com.singular.sdk.SingularLinkHandler
import com.singular.sdk.SingularLinkParams

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩 객체 획득
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)// setContentView(R.layout.activity_main)

        var navView = findViewById<BottomNavigationView>(R.id.nav_view)
        // 뷰 객체 이용
//        binding.visibleBtn.setOnClickListener {
//            binding.targetView.visibility = View.VISIBLE
//        }
//        binding.invisibleBtn.setOnClickListener {
//            binding.targetView.visibility = View.INVISIBLE
//        }
        // Add your own apikey and secret. (DEVELOPER TOOLS > SDK KEYS)
        val singularConfig = SingularConfig(Constants.API_KEY, Constants.SECRET)
            .withCustomUserId("jasonnam1234")
            .withSessionTimeoutInSec(120)
            .withLoggingEnabled()
            .withDDLTimeoutInSec(300)
            .withGlobalProperty("age_group", "20", true)
            .withSingularLink(intent) {
                params ->
                val deeplink = params.deeplink
                Log.d("deeplink", deeplink)
                val passthrough = params.passthrough
                val isDeferred = params.isDeferred

                Utils.showToast(this, params.deeplink)

                var targetTabIdx = R.id.navigation_deeplink

                if (deeplink.startsWith("http")) targetTabIdx = R.id.navigation_inwebview

                val bundle = Bundle()

                if (targetTabIdx != R.id.navigation_deeplink) {
                    bundle.putString("URL", deeplink)
                } else {
                    bundle.putString("DEEPLINK_KEY", params.deeplink)
                    bundle.putString("PASSTHROUGH_KEY", params.passthrough)
                    bundle.putString("PARAMETER_KEY", params.urlParameters.toString())
                    bundle.putBoolean("IS_DEFERRED_KEY", params.isDeferred)
                }

                runOnUiThread { navController.navigate(targetTabIdx, bundle) }
            }
        Singular.init(this, singularConfig)
    }
}
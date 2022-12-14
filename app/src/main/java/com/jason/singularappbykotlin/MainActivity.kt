package com.jason.singularappbykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.singular.sdk.Singular
import com.singular.sdk.SingularConfig
import com.singular.sdk.SingularLinkHandler
import com.singular.sdk.SingularLinkParams

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add your own apikey and secret. (DEVELOPER TOOLS > SDK KEYS)
        val singularConfig = SingularConfig("<sdk_key>", "<secret>")
            .withSingularLink(intent) {
                params ->
                val deeplink = params.deeplink
                Log.d("deeplink", deeplink)
                val passthrough = params.passthrough
                val isDeferred = params.isDeferred
            }
        Singular.init(this, singularConfig)
    }
}
package com.bumble.challenge1

import android.os.Bundle
import androidx.activity.compose.setContent
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.NodeActivity
import com.bumble.challenge1.ui.theme.ChallengeEasyTheme

class MainActivity : NodeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeEasyTheme {
                NodeHost(integrationPoint = appyxIntegrationPoint) {
                    RootNode(buildContext = it)
                }
            }
        }
    }
}

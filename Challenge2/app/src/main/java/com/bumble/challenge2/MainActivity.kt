package com.bumble.challenge2

import android.os.Bundle
import androidx.activity.compose.setContent
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.NodeActivity
import com.bumble.challenge2.node.root.RootNode
import com.bumble.challenge2.ui.theme.Challenge1Theme

class MainActivity : NodeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Challenge1Theme {
                NodeHost(integrationPoint = appyxIntegrationPoint) {
                    RootNode(buildContext = it)
                }
            }
        }
    }
}

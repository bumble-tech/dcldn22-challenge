package com.bumble.challenge1

import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.pop
import com.bumble.appyx.navmodel.backstack.operation.push
import com.bumble.challenge1.composable.CustomButton
import com.bumble.challenge1.composable.LogoHeader
import com.bumble.challenge1.composable.PeekInsideBackStack
import kotlinx.parcelize.Parcelize

class RootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        initialElement = NavTarget.Child(0),
        savedStateMap = buildContext.savedStateMap
    )
) : ParentNode<RootNode.NavTarget>(
    buildContext = buildContext,
    navModel = backStack
) {

    sealed class NavTarget : Parcelable {

        @Parcelize
        // supply correct index by calling getBackstackIndex()
        data class Child(val index: Int) : NavTarget() {
            override fun toString(): String = index.toString()
        }
    }

    override fun resolve(navTarget: NavTarget, buildContext: BuildContext): Node =
        when (navTarget) {
            is NavTarget.Child -> ChildNode(buildContext, navTarget.index)
        }

    @Composable
    override fun View(modifier: Modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                LogoHeader(Modifier.weight(0.1f))
                PeekInsideBackStack(backStack, Modifier.weight(0.1f))
                BackStackContent(Modifier.weight(0.7f))
                Spacer(Modifier.weight(0.1f))
            }

            Controls(Modifier.align(Alignment.BottomCenter))
        }
    }

    @Composable
    private fun BackStackContent(
        modifier: Modifier = Modifier
    ) {
        Children(
            modifier = modifier.padding(16.dp),
            navModel = backStack,
            transitionHandler = rememberExplodingTransitionHandler()
        )
    }

    @Composable
    fun Controls(modifier: Modifier) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(2.dp)
        ) {
            CustomButton(onClick = { backStack.pop() }) {
                Text("Pop")
                TODO("(1) perform push operation")
            }
            CustomButton(onClick = { backStack.push(NavTarget.Child(getBackstackIndex()))  }) {
                //check that BackStack has a push method
                //maybe use backStack??
                Text("Push")
                TODO("(2) perform pop operation")
            }
        }
    }

    private fun getBackstackIndex() = backStack.elements.value.size
}

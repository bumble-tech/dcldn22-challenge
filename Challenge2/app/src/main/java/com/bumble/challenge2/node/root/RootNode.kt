package com.bumble.challenge2.node.root

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
import com.bumble.challenge2.navmodel.custombackstack.CustomBackStack
import com.bumble.challenge2.navmodel.custombackstack.operation.pop
import com.bumble.challenge2.navmodel.custombackstack.operation.push
import com.bumble.challenge2.navmodel.custombackstack.operation.toggleFreeze
import com.bumble.challenge2.navmodel.custombackstack.transition.rememberBackstackFader
import com.bumble.challenge2.navmodel.custombackstack.transition.rememberRecentsTransitionHandler
import com.bumble.challenge2.node.child.ChildNode
import com.bumble.challenge2.node.root.composable.CustomButton
import com.bumble.challenge2.node.root.composable.LogoHeader
import com.bumble.challenge2.node.root.composable.PeekInsideBackStack

class RootNode(
    buildContext: BuildContext,
    private val backStack: CustomBackStack<NavTarget> = CustomBackStack(
        savedState = buildContext.savedStateMap,
        initialElement = NavTarget.Child(0)
    )
) : ParentNode<RootNode.NavTarget>(
    buildContext = buildContext,
    navModel = backStack
) {

    sealed class NavTarget {
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
                var isFader by remember { mutableStateOf(false) }

                LogoHeader(Modifier.weight(0.1f)) {
                    isFader = !isFader
                }
                PeekInsideBackStack(backStack, Modifier.weight(0.1f))
                Spacer(Modifier.weight(0.1f))
                BackStackContent(
                    isFader = isFader,
                    modifier = Modifier.weight(0.7f)
                )
            }

            Controls(Modifier.align(Alignment.BottomCenter))
        }

    }

    @Composable
    private fun BackStackContent(
        modifier: Modifier = Modifier,
        isFader: Boolean = false
    ) {
        Children(
            modifier = modifier.padding(16.dp),
            navModel = backStack,
            transitionHandler = if (isFader) rememberBackstackFader() else rememberRecentsTransitionHandler()
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
            }
            CustomButton(onClick = { backStack.push(NavTarget.Child(backStack.elements.value.size)) }) {
                Text("Push")
            }
            CustomButton(onClick = { backStack.toggleFreeze() }) {
                // TODO "(6) Text should change when button is toggled. Use CustomBackStack's elements property,
                //  apply some logic and transform to compose state using androidx.compose.runtime.collectAsState"
                val elements = backStack.elements.collectAsState()
                when(elements.value.last().targetState){
                    CustomBackStack.State.Frozen -> Text("Unfreeze")
                    else -> Text("Freeze")
                }
            }
        }
    }
}

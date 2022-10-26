package com.bumble.challenge2.navmodel.custombackstack.transition

import android.annotation.SuppressLint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import com.bumble.appyx.core.navigation.transition.ModifierTransitionHandler
import com.bumble.appyx.core.navigation.transition.TransitionDescriptor
import com.bumble.appyx.core.navigation.transition.TransitionSpec
import com.bumble.challenge2.navmodel.custombackstack.CustomBackStack

class CustomBackstackFader<NavTarget>(
    private val transitionSpec: TransitionSpec<CustomBackStack.State, Float> = { spring() }
) : ModifierTransitionHandler<NavTarget, CustomBackStack.State>() {

    @SuppressLint("ModifierFactoryExtensionFunction")
    override fun createModifier(
        modifier: Modifier,
        transition: Transition<CustomBackStack.State>,
        descriptor: TransitionDescriptor<NavTarget, CustomBackStack.State>
    ): Modifier = modifier.composed {
        val alpha = transition.animateFloat(
            transitionSpec = transitionSpec,
            targetValueByState = {
                when (it) {
                    is CustomBackStack.State.Active -> 1f
                    else -> 0f
                }
            },
            label = ""
        )

        alpha(alpha.value)
    }
}

@Composable
fun <T> rememberBackstackFader(
    transitionSpec: TransitionSpec<CustomBackStack.State, Float> = { spring(stiffness = Spring.StiffnessVeryLow) }
): ModifierTransitionHandler<T, CustomBackStack.State> = remember {
    CustomBackstackFader(transitionSpec = transitionSpec)
}

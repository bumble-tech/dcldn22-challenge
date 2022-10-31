package com.bumble.challenge1

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import com.bumble.appyx.core.navigation.transition.ModifierTransitionHandler
import com.bumble.appyx.core.navigation.transition.TransitionDescriptor
import com.bumble.appyx.core.navigation.transition.TransitionSpec
import com.bumble.appyx.navmodel.backstack.BackStack

class ExplodingTransitionHandler<NavTarget>(
    private val specFloat: TransitionSpec<BackStack.State, Float> = { spring() }
) : ModifierTransitionHandler<NavTarget, BackStack.State>() {

    private data class Props(
        val alpha: Float = 1f,
        val scale: Float = 1f
    )

    private val created = Props(alpha = 0f)
    private val active = created.copy(alpha = 1f)
    private val stashed = created.copy(alpha = 0f)
    private val destroyed = stashed.copy(scale = 2f) // TODO: "(3) scale should be 2f"

    private fun BackStack.State.toProps(): Props =
        when (this) {
            BackStack.State.CREATED -> created
            BackStack.State.ACTIVE -> active
            BackStack.State.STASHED -> stashed
            BackStack.State.DESTROYED -> destroyed
        }

    override fun createModifier(
        modifier: Modifier,
        transition: Transition<BackStack.State>,
        descriptor: TransitionDescriptor<NavTarget, BackStack.State>
    ): Modifier = modifier.composed {

        val alpha by transition.animateFloat(
            transitionSpec = specFloat,
            targetValueByState = { it.toProps().alpha },
            label = ""
        )

        val scale by transition.animateFloat(
            transitionSpec = specFloat,
            targetValueByState = { it.toProps().scale },
            label = ""
        )
        this
            .scale(scale)
            .alpha(alpha)
    }
}

@Composable
fun <R> rememberExplodingTransitionHandler(
    specFloat: TransitionSpec<BackStack.State, Float> = { spring(stiffness = Spring.StiffnessLow) },
): ModifierTransitionHandler<R, BackStack.State> =
    remember {
        ExplodingTransitionHandler(specFloat)
    }

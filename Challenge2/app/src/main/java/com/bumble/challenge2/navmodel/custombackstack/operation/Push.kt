package com.bumble.challenge2.navmodel.custombackstack.operation

import com.bumble.appyx.core.navigation.NavElements
import com.bumble.appyx.core.navigation.NavKey
import com.bumble.challenge2.navmodel.custombackstack.CustomBackStack
import com.bumble.challenge2.navmodel.custombackstack.CustomBackStack.State.Active
import com.bumble.challenge2.navmodel.custombackstack.CustomBackStack.State.Created
import com.bumble.challenge2.navmodel.custombackstack.CustomBackStack.State.Stashed
import com.bumble.challenge2.navmodel.custombackstack.CustomBackStackElement
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * Operation:
 *
 * [A, B, C] + Push(D) = [A, B, C, D]
 */
@Parcelize
class Push<T : Any>(
    private val element: @RawValue T
) : CustomBackStackOperation<T> {

    override fun isApplicable(elements: NavElements<T, CustomBackStack.State>): Boolean =
        elements.any { it.targetState is Active }

    override fun invoke(elements: NavElements<T, CustomBackStack.State>): NavElements<T, CustomBackStack.State> {
        val active = elements.findLast { it.targetState is Active }
        val stashed = Stashed(
            index = elements.indexOf(active),
            size = elements.size
        )
        val convertToStashed = elements.transitionTo(stashed) { it == active }
        val newElement = CustomBackStackElement(
            key = NavKey(element),
            fromState = Created,
            targetState = Active,
            operation = this
        )

        return convertToStashed + newElement
    }
}

fun <T : Any> CustomBackStack<T>.push(element: T) {
    accept(Push(element))
    accept(UpdateSize())
}

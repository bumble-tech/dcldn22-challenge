package com.bumble.challenge2.navmodel.custombackstack.operation

import com.bumble.appyx.core.navigation.NavElements
import com.bumble.challenge2.navmodel.custombackstack.CustomBackStack
import com.bumble.challenge2.navmodel.custombackstack.CustomBackStack.State.Active
import com.bumble.challenge2.navmodel.custombackstack.CustomBackStack.State.Frozen
import kotlinx.parcelize.Parcelize

@Parcelize
class ToggleFreeze<T : Any> : CustomBackStackOperation<T> {

    override fun isApplicable(elements: NavElements<T, CustomBackStack.State>): Boolean =
        elements.any { TODO("(1) Define applicability logic") }


    override fun invoke(elements: NavElements<T, CustomBackStack.State>): NavElements<T, CustomBackStack.State> =
        elements.transitionTo {
            when (it.targetState) {
                is Active -> TODO("(2) change the target state")
                is Frozen -> TODO("(3) change the target state")
                else -> it.targetState
            }
        }
}

fun <T : Any> CustomBackStack<T>.toggleFreeze() {
    accept(ToggleFreeze())
}

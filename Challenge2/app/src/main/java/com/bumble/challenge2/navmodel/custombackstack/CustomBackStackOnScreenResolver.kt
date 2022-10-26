package com.bumble.challenge2.navmodel.custombackstack

import com.bumble.appyx.core.navigation.onscreen.OnScreenStateResolver


object CustomBackStackOnScreenResolver : OnScreenStateResolver<CustomBackStack.State> {

    override fun isOnScreen(state: CustomBackStack.State): Boolean =
        when (state) {
            is CustomBackStack.State.Created,
            is CustomBackStack.State.Active,
            is CustomBackStack.State.Frozen -> true
            is CustomBackStack.State.Stashed -> {
                if (state.size > MAX_ON_SCREEN) {
                    state.index in state.size - MAX_ON_SCREEN..state.size - 2
                } else {
                    true
                }
            }
            is CustomBackStack.State.Destroyed -> false
        }

    const val MAX_ON_SCREEN = 3
}

package com.bumble.challenge2.navmodel.custombackstack.operation

import com.bumble.appyx.core.navigation.Operation
import com.bumble.challenge2.navmodel.custombackstack.CustomBackStack

interface CustomBackStackOperation<T> : Operation<T, CustomBackStack.State>

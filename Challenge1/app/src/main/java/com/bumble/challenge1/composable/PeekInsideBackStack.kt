package com.bumble.challenge1.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumble.appyx.core.navigation.NavElement
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.challenge1.ui.theme.appyx_yellow2
import com.bumble.challenge1.ui.theme.atomic_tangerine
import com.bumble.challenge1.ui.theme.imperial_red
import java.util.*

@Composable
fun <T : Any> PeekInsideBackStack(
    backStack: BackStack<T>,
    modifier: Modifier = Modifier
) {
    val elements = backStack.elements.collectAsState()

    val listState = rememberLazyListState()
    LaunchedEffect(elements.value.lastIndex) {
        listState.animateScrollToItem(index = elements.value.lastIndex)
    }

    LazyRow(
        state = listState,
        modifier = modifier
            .fillMaxWidth()
            .background(if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        elements.value.forEach { element ->
            item {
                BackStackElement(element)
            }
        }
    }
}

@Composable
private fun <T> BackStackElement(
    element: NavElement<T, BackStack.State>,
) {
    Column(
        modifier = Modifier
            .size(60.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(15))
            .background(if (isSystemInDarkTheme()) Color.Transparent else element.targetState.toColor())
            .border(2.dp, element.targetState.toColor(), RoundedCornerShape(15))
            .padding(6.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = element.key.navTarget.toString(),
            color = MaterialTheme.colors.onSurface,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = element.targetState.toString()
                .replace("DESTROYED", "DESTR")
                .uppercase(Locale.getDefault()),
            color = MaterialTheme.colors.onSurface,
            fontSize = 9.sp,
        )
    }
}

private fun BackStack.State.toColor(): Color =
    when (this) {
        BackStack.State.CREATED -> appyx_yellow2
        BackStack.State.ACTIVE -> appyx_yellow2
        BackStack.State.STASHED -> atomic_tangerine
        BackStack.State.DESTROYED -> imperial_red
    }

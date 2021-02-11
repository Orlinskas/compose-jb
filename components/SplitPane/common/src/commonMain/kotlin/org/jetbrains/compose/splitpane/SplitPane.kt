package org.jetbrains.compose.splitpane

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.movable.SplitterState

data class MinimalSizes(
    val firstPlaceableMinimalSize: Dp,
    val secondPlaceableMinimalSize: Dp
)

@Composable
fun VerticalSplitPane(
    splitterState: SplitterState,
    modifier: Modifier = Modifier,
    content: SplitPaneScope.() -> Unit
) {
    val bet: SplitPaneScopeImpl = SplitPaneScopeImpl().apply(content)

    SplitPane(
        modifier,
        isHorizontal = false,
        splitterState,
        bet.minimalSizes,
        bet.firstPlaceableContent,
        bet.secondPlaceableContent,
        { Splitter(isHorizontal = false, splitterState::onMove)}
    )
}

@Composable
fun HorizontalSplitPane(
    splitterState: SplitterState,
    modifier: Modifier = Modifier,
    content: SplitPaneScope.() -> Unit
) {
    val bet = SplitPaneScopeImpl().apply(content)
    SplitPane(
        modifier,
        isHorizontal = true,
        splitterState,
        bet.minimalSizes,
        bet.firstPlaceableContent,
        bet.secondPlaceableContent,
        { Splitter(isHorizontal = true, splitterState::onMove)}
    )
}

@Composable
expect fun Splitter(
    isHorizontal: Boolean,
    consumeMovement: (delta: Float) -> Unit
)

@Composable
expect fun SplitPane(
    modifier: Modifier = Modifier,
    isHorizontal: Boolean = true,
    state: SplitterState,
    minimalSizesConfiguration: MinimalSizes  = MinimalSizes(0.dp, 0.dp),
    first: @Composable ()->Unit,
    second: @Composable ()->Unit,
    separator: @Composable ()->Unit
)
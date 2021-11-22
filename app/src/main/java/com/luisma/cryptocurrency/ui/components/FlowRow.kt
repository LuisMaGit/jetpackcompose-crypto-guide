package com.luisma.cryptocurrency.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

private data class PlaceablePositions(val x: Int, val y: Int)

@Composable
fun FlowRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->

        val placeables = measurables.map {
            it.measure(constraints)
        }

        val placeablePositions = mutableListOf<PlaceablePositions>()
        var height = 0
        var xPosition = 0
        var yPosition = 0
        var highestInTheRow = 0

        placeables.forEachIndexed { index, placeable ->
            placeablePositions.add(PlaceablePositions(xPosition, yPosition))

            if (placeable.height >= highestInTheRow) {
                highestInTheRow = placeable.height
            }

            val nextIndex = if (index + 1 == placeables.count()) index else index + 1
            val nextStartPosition = xPosition + placeables[index].width
            val nextEndPosition = nextStartPosition + placeables[nextIndex].width

            if (nextEndPosition >= constraints.maxWidth) {
                xPosition = 0
                yPosition += highestInTheRow
                height += highestInTheRow
                highestInTheRow = 0
            } else {
                xPosition += placeable.width
            }

            if (index == placeables.count() - 1) {
                height += highestInTheRow
            }
        }


        layout(constraints.maxWidth, height) {
            placeablePositions.forEachIndexed { index, placeablePosition ->
                placeables[index].placeRelative(placeablePosition.x, placeablePosition.y)
            }

        }
    }
}
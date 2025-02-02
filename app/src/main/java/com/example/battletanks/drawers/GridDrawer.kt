package com.example.battletanks.drawers

import android.graphics.Color
import android.view.View
import android.widget.FrameLayout
import com.example.battletanks.CELL_SIZE

class GridDrawer (private val container: FrameLayout?) {
    private val allLines = mutableListOf<View>()

    fun removeGrid() {
        allLines.forEach {
            container?.removeView(it)
        }
    }

    fun drawGrid() {
        drawHorizontalLines()
        drawVerticalLines()
    }

    private fun drawHorizontalLines() {
        var topMargin = 0
        while (topMargin <= container!!.height) {
            val horizontalLine = View(container.context)
            val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 1)
            topMargin += CELL_SIZE
            layoutParams.topMargin = topMargin
            horizontalLine.layoutParams = layoutParams
            horizontalLine.setBackgroundColor(Color.WHITE)
            allLines.add(horizontalLine)
            container.addView(horizontalLine)
        }
    }

    private fun drawVerticalLines() {
        var leftMargin = 0
        while (leftMargin <= container!!.width) {
            val verticalLine = View(container.context)
            val layoutParams = FrameLayout.LayoutParams(1, FrameLayout.LayoutParams.MATCH_PARENT)
            leftMargin += CELL_SIZE
            layoutParams.leftMargin = leftMargin
            verticalLine.layoutParams = layoutParams
            verticalLine.setBackgroundColor(Color.WHITE)
            allLines.add(verticalLine)
            container.addView(verticalLine)
        }
    }
}
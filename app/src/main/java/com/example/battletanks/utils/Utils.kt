package com.example.battletanks.utils

import android.view.View
import com.example.battletanks.binding
import com.example.battletanks.models.Coordinate

fun View.checkViewCanMoveThroughBorder(coordinate: Coordinate): Boolean {
    return coordinate.top >= 0 &&
            coordinate.top + this.height <= binding.container.height &&
            coordinate.left >= 0 &&
            coordinate.left + this.width <= binding.container.width
}

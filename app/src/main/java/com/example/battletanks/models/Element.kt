package com.example.battletanks.models

import com.example.battletanks.enums.Material

data class Element(
    val viewId: Int,
    val material: Material,
    val coordinate: Coordinate,
    val width: Int,
    val height: Int
) {

}

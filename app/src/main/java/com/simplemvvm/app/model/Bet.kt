package com.simplemvvm.app.model

data class Bet(var type: String, var sellIn: Int, var odds: Int, var image: String) {
    override fun toString(): String {
        return "$type, $sellIn, $odds"
    }
}
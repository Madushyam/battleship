package com.example.battleshipsarmada;


sealed class Screen(val route: String) {
    object homepage: Screen("homepage")
    object GamePage : Screen("gamepage")

}
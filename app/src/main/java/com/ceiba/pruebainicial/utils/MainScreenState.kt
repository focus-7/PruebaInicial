package com.ceiba.pruebainicial.utils

enum class MainScreenState : ScreenState {
    INITIAL, LOADING_DATA, SHOW_DATA;

    override fun getInitialState(): ScreenState = INITIAL
}
package com.ceiba.pruebainicial.fragments

import com.ceiba.pruebainicial.utils.ScreenState

enum class MainScreenState : ScreenState {
    INITIAL, LOADING_DATA, SHOW_DATA;

    override fun getInitialState(): ScreenState = INITIAL
}
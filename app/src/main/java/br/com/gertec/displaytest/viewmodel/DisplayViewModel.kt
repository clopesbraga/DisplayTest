package br.com.gertec.displaytest.viewmodel;

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DisplayViewModel : ViewModel() {

    private val _elemento = MutableStateFlow(false)
    val uiElementoState = _elemento.asStateFlow()


    fun verifyStateOfElements(elementos: State<Boolean>) {

        if (elementos.value) _elemento.value = true else _elemento.value = false

    }



}


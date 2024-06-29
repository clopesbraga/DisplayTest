package br.com.gertec.displaytest.viewmodel;

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DisplayViewModel : ViewModel() {

    private val _elemento = MutableStateFlow(false)
    val uiElementoState = _elemento.asStateFlow()


    fun verificaElementosNaTela(elementos: State<Boolean>) {

        if (elementos.value) _elemento.value = true else _elemento.value = false

    }

}


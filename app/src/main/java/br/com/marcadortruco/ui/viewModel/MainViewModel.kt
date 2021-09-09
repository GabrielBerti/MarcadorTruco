package br.com.marcadortruco.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.marcadortruco.R

class MainViewModel(): ViewModel() {

    private val _placarNos = MutableLiveData<String>()
    val placarNos: LiveData<String> = _placarNos

    private val _placarEles = MutableLiveData<String>()
    val placarEles: LiveData<String> = _placarEles

    private val _vitoriaNos = MutableLiveData<String>()
    val vitoriaNos: LiveData<String> = _vitoriaNos

    private val _vitoriaEles = MutableLiveData<String>()
    val vitoriaEles: LiveData<String> = _vitoriaEles

    private val _nomeTime1= MutableLiveData<String>()
    val nomeTime1: LiveData<String> = _nomeTime1

    private val _nomeTime2 = MutableLiveData<String>()
    val nomeTime2: LiveData<String> = _nomeTime2

    private var vitoriaTotalNos: Int = 0
    private var vitoriaTotalEles: Int = 0
    private var VITORIA: String = "Vitória(s): "

    fun inicializaPlacar() {
        if(_placarNos.value == null) {
            _placarNos.value = "0"
            _placarEles.value = "0"
        }
    }

    fun inicializaVitorias() {
        if(_vitoriaNos.value == null) {
            _vitoriaNos.value = "$VITORIA $vitoriaTotalNos"
            _vitoriaEles.value = "$VITORIA $vitoriaTotalEles"
        }
    }

    fun zeraPlacar() {
        _placarNos.value = "0"
        _placarEles.value = "0"
    }

    fun zeraVitorias() {
        vitoriaTotalNos = 0
        vitoriaTotalEles = 0

        _vitoriaNos.value = "$VITORIA $vitoriaTotalNos"
        _vitoriaEles.value = "$VITORIA $vitoriaTotalEles"
    }

    fun somaMaisUmNos() {
        val soma = _placarNos.value?.toInt()?.plus(1)
        _placarNos.value =  soma.toString()
    }

    fun somaMaisUmEles() {
        val soma = _placarEles.value?.toInt()?.plus(1)
        _placarEles.value =  soma.toString()
    }

    fun menosUmNos() {
        val subtracao = _placarNos.value?.toInt()?.minus(1)
        _placarNos.value =  subtracao.toString()
    }

    fun menosUmEles() {
        val subtracao = _placarEles.value?.toInt()?.minus(1)
        _placarEles.value =  subtracao.toString()
    }

    fun somaMaisUmaVitoriaNos() {
        vitoriaTotalNos ++
        _vitoriaNos.value =  "$VITORIA $vitoriaTotalNos"
    }

    fun somaMaisUmaVitoriaEles() {
        vitoriaTotalEles ++
        _vitoriaEles.value =  "$VITORIA $vitoriaTotalEles"
    }

    fun alteraNomeTime1(nomeNovo: String) {
        _nomeTime1.value =  nomeNovo
    }

    fun alteraNomeTime2(nomeNovo: String) {
        _nomeTime2.value =  nomeNovo
    }

}
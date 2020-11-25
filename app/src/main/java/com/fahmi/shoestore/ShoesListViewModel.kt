package com.fahmi.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fahmi.shoestore.model.Shoe

class ShoesListViewModel() : ViewModel() {

    private val _shoesList = MutableLiveData<MutableList<Shoe>>()
    val shoesList: LiveData<MutableList<Shoe>>
        get() = _shoesList

    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
        get() = _shoe

    private val _checkCorrectValue = MutableLiveData<Boolean>()
    val checkCorrectValue: LiveData<Boolean>
        get() = _checkCorrectValue

    private val _checkEmptyForm = MutableLiveData<Boolean>()
    val checkEmptyForm: LiveData<Boolean>
        get() = _checkEmptyForm

    init {
        _shoesList.value = mutableListOf()
        _shoe.value = Shoe("", "", "", "")
    }

    fun addShoesToList() {
        if(
                shoe.value!!.name.isEmpty() ||
                shoe.value!!.company.isEmpty() ||
                shoe.value!!.size.isEmpty() ||
                shoe.value!!.description.isEmpty()
        ) {
            _checkEmptyForm.value = true
        } else {
            _shoesList.value!!.add(shoe.value!!)
            _checkCorrectValue.value = true
            _checkEmptyForm.value = false
            resetShoeForm()
        }

    }

    fun afterCorrectValue() {
        _checkCorrectValue.value = false
    }

    fun resetShoeForm() {
        _shoe.value = Shoe("","","","")
    }
}

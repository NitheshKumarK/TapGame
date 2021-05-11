package com.nithesh.tapgame.title

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TitleViewModel : ViewModel() {

    private var _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

//    this is variable is replaced in the fragment_title.xml(59:13, 69:13)
//    using the layout binding expression
//    val enable: LiveData<Boolean> = Transformations.map(_userName) {
//        it == null
//    }


    init {
        //initialize this variable as null
        _userName.value = null
    }

    fun setUserName(userName: Editable) {
        _userName.value = userName.toString()
    }

}
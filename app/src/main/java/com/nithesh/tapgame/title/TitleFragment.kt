package com.nithesh.tapgame.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.nithesh.tapgame.R
import com.nithesh.tapgame.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    private lateinit var titleBinding: FragmentTitleBinding
    private lateinit var navController: NavController
    private val titleViewModel: TitleViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        titleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title,
            container, false
        )
        titleBinding.titleViewModel = titleViewModel
        titleBinding.lifecycleOwner = this
        navController = this.findNavController()
        titleBinding.nextButton.setOnClickListener {
            onNext()
        }
        titleBinding.doneButton.setOnClickListener {
            onDone()
        }

        // Inflate the layout for this fragment
        return titleBinding.root
    }

    private fun onNext() {
        navController.navigate(
            TitleFragmentDirections
                .actionTitleFragmentToGameFragment(titleViewModel.userName.value!!)
        )
    }

    private fun onDone() {
        if (titleBinding.nameEditText.text.isNotEmpty()) {
            titleViewModel.setUserName(titleBinding.nameEditText.text)
        } else {
            Toast.makeText(this.context, "enter user name", Toast.LENGTH_SHORT).show()
        }
    }

}
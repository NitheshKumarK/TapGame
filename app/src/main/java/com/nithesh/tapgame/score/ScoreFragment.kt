package com.nithesh.tapgame.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.nithesh.tapgame.R
import com.nithesh.tapgame.database.GameScoreDatabase
import com.nithesh.tapgame.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {
    private lateinit var scoreBinding: FragmentScoreBinding
    private val scoreArgs: ScoreFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        scoreBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_score,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val database = GameScoreDatabase.getDatabase(application).getGameScoreDao()
        val scoreViewModelFactory = ScoreViewModelFactory(scoreArgs.score, database, application)
        val scoreViewModel = ViewModelProvider(this, scoreViewModelFactory)
            .get(ScoreViewModel::class.java)
        scoreBinding.scoreViewModel = scoreViewModel
        scoreBinding.lifecycleOwner = this
        return scoreBinding.root
    }

}
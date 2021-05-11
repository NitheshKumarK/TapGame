package com.nithesh.tapgame.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nithesh.tapgame.R
import com.nithesh.tapgame.database.GameScoreDatabase
import com.nithesh.tapgame.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var gameBinding: FragmentGameBinding
    private val gameArgs: GameFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        gameBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game, container, false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = GameScoreDatabase.getDatabase(application).getGameScoreDao()
        val gameViewModelFactory = GameViewModelFactory(gameArgs.userName, dataSource, application)
        val gameViewModel = ViewModelProvider(this, gameViewModelFactory)
            .get(GameViewModel::class.java)
        gameViewModel.isGameCompleted.observe(viewLifecycleOwner, {
            if (it == true) {
                findNavController().navigate(
                    GameFragmentDirections
                        .actionGameFragmentToScoreFragment(gameViewModel.score.value ?: 0)
                )
                gameViewModel.onGameFinish()
            }
        })
        gameBinding.gameViewModel = gameViewModel

        gameBinding.lifecycleOwner = this

        return gameBinding.root
    }
}
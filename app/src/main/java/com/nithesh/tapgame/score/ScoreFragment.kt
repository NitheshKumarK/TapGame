package com.nithesh.tapgame.score

import android.os.Bundle
import android.view.*
import android.widget.Toast
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
    private lateinit var scoreViewModel: ScoreViewModel
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
        setHasOptionsMenu(true)
        val application = requireNotNull(this.activity).application
        val database = GameScoreDatabase.getDatabase(application).getGameScoreDao()
        val scoreViewModelFactory = ScoreViewModelFactory(scoreArgs.score, database, application)
        scoreViewModel = ViewModelProvider(this, scoreViewModelFactory)
            .get(ScoreViewModel::class.java)
        scoreBinding.scoreViewModel = scoreViewModel
        scoreBinding.lifecycleOwner = this
        return scoreBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        scoreViewModel.deleteTable()
        Toast.makeText(this.context, "All user deleted", Toast.LENGTH_SHORT).show()
        return super.onOptionsItemSelected(item)
    }

}
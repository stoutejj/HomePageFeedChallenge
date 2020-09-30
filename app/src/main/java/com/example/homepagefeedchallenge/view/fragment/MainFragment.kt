package com.example.homepagefeedchallenge.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.homepagefeedchallenge.R
import com.example.homepagefeedchallenge.data.model.HomePageFeed
import com.example.homepagefeedchallenge.data.network.WebServices
import com.example.homepagefeedchallenge.data.repository.HomePageRepositoryImpl
import com.example.homepagefeedchallenge.view.adapter.HomePageFeedAdapter
import com.example.homepagefeedchallenge.viewmodel.MainViewModel
import com.example.homepagefeedchallenge.viewmodel.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private val feedAdapter: HomePageFeedAdapter = HomePageFeedAdapter(mutableListOf())
    private val viewModelFactory = MainViewModelFactory(
        HomePageRepositoryImpl(
            WebServices.instance
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_feeds.adapter = feedAdapter

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        setupObservers()
        viewModel.fetchHomePage()
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner, { state ->
            when (state) {
                is MainViewModel.MainViewState.Loading -> showProgressBar()
                is MainViewModel.MainViewState.Success -> showData(state.page.cards)
                is MainViewModel.MainViewState.Error -> showErrorMessage(state.errorMessage)
            }
        })
    }

    private fun showErrorMessage(errorMessage: String) {
        progress_circular.visibility = View.GONE
        Snackbar.make(rv_feeds,"Unable to Fetch Data...", Snackbar.LENGTH_INDEFINITE)
            .setAction("REDO",View.OnClickListener {
                viewModel.fetchHomePage()
            })
            .show()
    }

    private fun showData(cards: List<HomePageFeed>) {
        progress_circular.visibility = View.GONE
        rv_feeds.visibility = View.VISIBLE
        feedAdapter.updateImages(cards)
    }

    private fun showProgressBar() {
        progress_circular.visibility = View.VISIBLE
        rv_feeds.visibility = View.GONE
    }


}

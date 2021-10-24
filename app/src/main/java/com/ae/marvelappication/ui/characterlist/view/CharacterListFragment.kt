package com.ae.marvelappication.ui.characterlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ae.marvelappication.R
import com.ae.marvelappication.common.listener.SelectItemListener
import com.ae.marvelappication.common.reponse.Resource
import com.ae.marvelappication.common.reponse.Status
import com.ae.marvelappication.common.resource.ResourceProvider
import com.ae.marvelappication.databinding.MarvelAppFragmentCharacterListBinding
import com.ae.marvelappication.dto.ResultsItem
import com.ae.marvelappication.ui.characterlist.adapter.CharacterItem
import com.ae.marvelappication.ui.characterlist.viewmodel.CharacterListViewModel
import com.ae.marvelappication.util.show
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CharacterListFragment : Fragment(), SelectItemListener {

    private val binding: MarvelAppFragmentCharacterListBinding by lazy {
        MarvelAppFragmentCharacterListBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var resourceProvider: ResourceProvider

    private val viewModel: CharacterListViewModel by viewModels()
    private val scrollingSection: Section = Section()
    private lateinit var groupAdapter: GroupieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupViewModel()
    }

    private fun setupAdapter() {
        groupAdapter = GroupieAdapter()
        groupAdapter.add(scrollingSection)

        binding.characterRv.apply {
            adapter = groupAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            addOnScrollListener(onScrollListener)
        }
    }

    private fun setupViewModel() {
        viewModel.getEvents.observe(viewLifecycleOwner, Observer(this::handlerResponse))
        viewModel.getAllCharactersByPaging()
    }

    private fun handlerResponse(result: Resource<List<ResultsItem>>) {
        when (result.status) {
            Status.SUCCESS -> {
                viewModel.isLoading = false
                result.data?.let { response ->
                    if (response.isEmpty()) {
                        showEmptyState(resourceProvider.getString(R.string.marvel_app_message_empty))
                    } else {
                        showList()
                        setListAdapter(response)
                    }
                }
            }
            Status.ERROR -> {
                result.message?.let {
                    showEmptyState(it)
                } ?: kotlin.run {
                    showEmptyState(resourceProvider.getString(R.string.marvel_app_general_error))
                }
            }
            else -> showEmptyState(resourceProvider.getString(R.string.marvel_app_general_error))
        }
    }

    private val onScrollListener: RecyclerView.OnScrollListener by lazy {
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

                viewModel.onLoadMoreItems(
                    visibleItemCount,
                    firstVisibleItemPosition,
                    totalItemCount
                )
            }
        }
    }

    private fun setListAdapter(response: List<ResultsItem>) {
        val list: List<CharacterItem> = response
            .map { CharacterItem(requireContext(), it, this) }
        scrollingSection.apply { addAll(list) }
    }

    private fun showList() {
        binding.emptyStateView.hideEmptyState()
        binding.characterRv.show()
    }

    private fun showEmptyState(message: String) {
        binding.emptyStateView.setMessage(message)
        binding.emptyStateView.showEmptyState()
    }

    override fun goToDetail(character: ResultsItem) {
        val bundle = bundleOf(CHARACTER_SELECTED to character)
        NavHostFragment
            .findNavController(this)
            .navigate(R.id.action_characterListFragment_to_characterDetailFragment, bundle)
    }

    private companion object {
        const val CHARACTER_SELECTED: String = "characterSelected"
    }
}
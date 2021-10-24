package com.ae.marvelappication.ui.characterdetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ae.marvelappication.R
import com.ae.marvelappication.common.reponse.Resource
import com.ae.marvelappication.common.reponse.Status
import com.ae.marvelappication.common.resource.ResourceProvider
import com.ae.marvelappication.databinding.MarvelAppFragmentCharacterDetailBinding
import com.ae.marvelappication.dto.ResultsItem
import com.ae.marvelappication.ui.characterdetail.viewmodel.CharacterDetailViewModel
import com.ae.marvelappication.util.getDetailItems
import com.ae.marvelappication.util.show
import com.bumptech.glide.Glide
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import timber.log.Timber


@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private val binding: MarvelAppFragmentCharacterDetailBinding by lazy {
        MarvelAppFragmentCharacterDetailBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var resourceProvider: ResourceProvider

    private val viewModel: CharacterDetailViewModel by viewModels()
    private var characterSelected: ResultsItem? = null
    private val section: Section = Section()
    private lateinit var groupAdapter: GroupieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCharacter()
        setupView()
        setupAdapter()
        getCharacterDetail()
    }

    private fun setupAdapter() {
        groupAdapter = GroupieAdapter()
        groupAdapter.add(section)

        binding.itemDetailRv.apply {
            adapter = groupAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL, false
            )
        }
    }

    private fun setupView() {
        characterSelected?.let {
            binding.characterDetailTitle.text = it.name
            binding.characterDetailDescription.text = if (it.description.isEmpty()) {
                resourceProvider.getString(R.string.marvel_app_character_not_data)
            } else {
                it.description
            }
        } ?: kotlin.run {
            binding.characterDetailTitle.text =
                resourceProvider.getString(R.string.marvel_app_character_not_data)
        }
    }

    private fun getCharacterDetail() {
        characterSelected?.let {
            viewModel.getCharacterById(it.id)
                .observe(viewLifecycleOwner, Observer(this::handlerResponse))
        }
    }

    private fun getCharacter(){
        arguments?.let {
            characterSelected = it.getParcelable(CHARACTER_SELECTED)
        }
    }

    private fun handlerResponse(result: Resource<List<ResultsItem>>) {
        when (result.status) {
            Status.SUCCESS -> {
                result.data?.let { response ->
                    if (response.isNotEmpty()) {
                        val character = response[0]
                        setImage(character.thumbnail.path)
                        setupItemsCounter(character)
                    }
                }
            }
            Status.ERROR -> {
                Timber.e(result.message)
                Toast.makeText(
                    requireContext(),
                    R.string.marvel_app_general_error,
                    Toast.LENGTH_LONG
                ).show()
            }
            else -> {
                Toast.makeText(
                    requireContext(),
                    resourceProvider.getString(R.string.marvel_app_general_error),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun setupItemsCounter(result: ResultsItem) {
        val list = getDetailItems(resourceProvider, result)
        section.apply { addAll(list) }
        binding.itemDetailRv.show()
    }

    private fun setImage(url: String) {
        Glide.with(this)
            .load(url + IMAGE_VARIANT)
            .placeholder(R.drawable.avatar_generic)
            .centerCrop()
            .error(R.drawable.avatar_generic)
            .into(binding.characterDetailImage)
    }

    private companion object {
        const val CHARACTER_SELECTED: String = "characterSelected"
        const val IMAGE_VARIANT: String = "/landscape_incredible.jpg"
    }
}
package com.ae.marvelappication.ui.characterdetail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ae.marvelappication.common.reponse.Resource
import com.ae.marvelappication.common.reponse.ResponseHandler
import com.ae.marvelappication.dto.ResultsItem
import com.ae.marvelappication.ui.characterdetail.usercase.CharacterDetailUserCase
import com.ae.marvelappication.util.characterMock
import com.ae.marvelappication.util.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CharacterDetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var mockUseCase: CharacterDetailUserCase

    @Mock
    private lateinit var mockResponseHandler: ResponseHandler

    @Mock
    private lateinit var mockObserver: Observer<Resource<List<ResultsItem>>>

    private lateinit var viewModel: CharacterDetailViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testCoroutineDispatcher)

        viewModel = CharacterDetailViewModel(mockUseCase, mockResponseHandler)
    }

    @Test
    fun `Get character detail should be success`() = runBlockingTest {
        val mockId = 3333
        val charactersExpect = arrayListOf(characterMock.copy(id = mockId))
        val expectedResponse = mockResponseHandler.handleSuccess(charactersExpect)

        BDDMockito.given(mockUseCase.invoke(mockId)).willReturn(charactersExpect)

        viewModel.getCharacterById(mockId).observeForever(mockObserver)
        viewModel.getCharacterById(mockId).getOrAwaitValue()

        Mockito.verify(mockObserver).onChanged(expectedResponse)
    }

    @Test
    fun `Get character detail should be success and return value`() = runBlockingTest {
        val mockId = 3333
        val charactersExpect = arrayListOf(characterMock.copy(id = mockId))
        val expectedResponse = mockResponseHandler.handleSuccess(charactersExpect)

        BDDMockito.given(mockUseCase.invoke(mockId)).willReturn(charactersExpect)

        viewModel.getCharacterById(mockId).observeForever(mockObserver)
        val action = viewModel.getCharacterById(mockId).getOrAwaitValue()

        Assert.assertEquals(
            expectedResponse,
            action
        )
    }

    @Test(expected = Exception::class)
    fun `Get character detail should be fail`() = runBlockingTest {
        val mockId = 3333
        val expectedException = Exception("")
        val expectedResponse =
            mockResponseHandler.handleException<List<ResultsItem>>(expectedException)

        BDDMockito.given(mockUseCase.invoke(mockId)).willThrow(expectedException)

        viewModel.getCharacterById(mockId).observeForever(mockObserver)
        viewModel.getCharacterById(mockId).getOrAwaitValue()

        Mockito.verify(mockObserver).onChanged(expectedResponse)
    }

    @Test(expected = Exception::class)
    fun `Get character detail should be fail and return error`() = runBlockingTest {
        val mockId = 3333
        val expectedException = Exception("")
        val expectedResponse =
            mockResponseHandler.handleException<List<ResultsItem>>(expectedException)

        BDDMockito.given(mockUseCase.invoke(mockId)).willThrow(expectedException)

        viewModel.getCharacterById(mockId).observeForever(mockObserver)
        val action = viewModel.getCharacterById(mockId).getOrAwaitValue()

        Assert.assertEquals(
            expectedResponse,
            action
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}
package com.ae.marvelappication.ui.characterdetail.usercase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ae.marvelappication.ui.characterdetail.repository.CharacterDetailRepository
import com.ae.marvelappication.util.characterMock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CharacterDetailUserCaseImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var repository: CharacterDetailRepository

    private lateinit var userCase: CharacterDetailUserCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testCoroutineDispatcher)

        userCase = CharacterDetailUserCaseImpl(repository)
    }

    @Test
    fun `Invoke get character by id should be success`() = runBlockingTest {
        val mockId = 3333
        val charactersExpect = arrayListOf(characterMock.copy(id = mockId))

        BDDMockito.given(repository.getCharacterById(mockId)).willReturn(charactersExpect)

        assertEquals(charactersExpect, userCase.invoke(mockId))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}
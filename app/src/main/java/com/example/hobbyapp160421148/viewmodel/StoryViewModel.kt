package com.example.hobbyapp160421148.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hobbyapp160421148.model.AppDatabase
import com.example.hobbyapp160421148.model.Story
import kotlinx.coroutines.launch

class StoryViewModel(application: Application) : AndroidViewModel(application) {

    private val storyDao = AppDatabase.getDatabase(application).storyDao()

    private val _storyList = MutableLiveData<List<Story>>()
    val storyList: LiveData<List<Story>> = _storyList

    init {
        loadStories()
    }

    private fun loadStories() {
        viewModelScope.launch {
            storyDao.getAllStories().collect { stories ->
                if (stories.isEmpty()) {
                    insertDummyStories()
                } else {
                    _storyList.postValue(stories)
                }
            }
        }
    }

    private fun insertDummyStories() {
        viewModelScope.launch {
            val dummyStoryLists = listOf(
                Story(
                    title = "The Story of Kagura",
                    author = "MLBB",
                    description = "Upon the Cadia Riverlands, there exists a mysterious profession called Onmyouji Master that uses the yin yang arts to control spirits and see into the future. The Great Dragon bestowed this special power to channel yin and yang on people of the Cadia Riverlands, and one of his own disciples became the first-ever Onmyouji Master.",
                    imageUrl = "https://www.ulti.asia/id/wp-content/uploads/sites/2/2023/08/Kagura.jpg",
                    content = "I-It's the Seimei Umbrella!\n\nA miracle from the Seimei Umbrella!\n\nThe crowd exclaimed in surprise, while Kagura stared at the big umbrella in her hand and smiled: So, it was you who were calling for me...\n\nThe Seimei Umbrella kept Kagura's company from then on. It witnessed every trouble she'd ever caused, experienced all her joy, sadness and anger as she gradually grew up, and understood her feelings for Hayabusa Hayabusa which changed from friendship to secret affection...",
                    category = "MOBA"
                ),
                Story(
                    title = "Tracy Reznik",
                    author = "Identity V",
                    description = "A Mechanic who have a job to decode but do kiting and rescuing more for some reason.",
                    imageUrl = "https://static.wikia.nocookie.net/id5/images/6/6f/SummerVacationArt.jpg/revision/latest/scale-to-width-down/250?cb=20210809162302",
                    content = "Mechanic\nA Decoder\nWho kite\nAnd rescuing\nThe 4th Law: Make It Satisfying",
                    category = "Survival Horror"
                ),
                Story(
                    title = "The Magic of Servais Le Roy",
                    author = "Identity V",
                    description = "A Magician who have a job to kite but always decode and never chased by hunter for some reason.",
                    imageUrl = "https://pbs.twimg.com/media/F0FL3TeaQAUnOK3?format=jpg&name=4096x4096",
                    content = "A Magician\nA container\nHave 3 decoy\nDecoder....",
                    category = "Survival Horror"
                ),
                Story(
                    title = "Little Girl",
                    author = "Little Girl Hater",
                    description = "A girl that is little and useless for not popping the last cipher for some reason.",
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvt_yRyHbrVC4Mfb2KQeoyhSqhHyP3ShTNPdNvyqvwBQ&s",
                    content = "Useless child\nUseless Girl\nUseless for being alive",
                    category = "Survival Horror"
                )
            )

            storyDao.insertStories(dummyStoryLists)
            _storyList.postValue(dummyStoryLists)
        }
    }
}

package com.example.hobbyapp160421148.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hobbyapp160421148.model.Story

class StoryViewModel : ViewModel() {

    private val _storyList = MutableLiveData<List<Story>>()
    val storyList: LiveData<List<Story>> = _storyList

    init {
        fetchDummyStory()
    }

    private fun fetchDummyStory() {
        val dummyStoryLists = listOf(
            Story(
                "The Story of Kagura",
                "MLBB",
                "Upon the Cadia Riverlands, there exists a mysterious profession called Onmyouji Master that uses the yin yang arts to control spirits and see into the future. The Great Dragon bestowed this special power to channel yin and yang on people of the Cadia Riverlands, and one of his own disciples became the first-ever Onmyouji Master.",
                "https://www.ulti.asia/id/wp-content/uploads/sites/2/2023/08/Kagura.jpg",
                listOf(
                    "I-It's the Seimei Umbrella!",

                    "A miracle from the Seimei Umbrella!",

                    "The crowd exclaimed in surprise, while Kagura stared at the big umbrella in her hand and smiled: So, it was you who were calling for me...",

                    "The Seimei Umbrella kept Kagura's company from then on. It witnessed every trouble she'd ever caused, experienced all her joy, sadness and anger as she gradually grew up, and understood her feelings for Hayabusa Hayabusa which changed from friendship to secret affection..."
                )
            ),
            Story(
                "Tracy Reznik",
                "Identity V",
                "A Mechanic who have a job to decode but do kiting and rescuing more for some reason.",
                "https://static.wikia.nocookie.net/id5/images/6/6f/SummerVacationArt.jpg/revision/latest/scale-to-width-down/250?cb=20210809162302",
                listOf(
                    "Mechanic",
                    "A Decoder",
                    "Who kite",
                    "And rescuing",
                    "The 4th Law: Make It Satisfying"
                )

            ),
            Story(
                "The Magic of Servais Le Roy",
                "Identity V",
                "A Magician who have a job to kite but always decode and never chased by hunter for some reason.",
                "https://pbs.twimg.com/media/F0FL3TeaQAUnOK3?format=jpg&name=4096x4096",
                listOf(
                    "A Magician",
                    "A container",
                    "Have 3 decoy",
                    "Decoder...."
                )

            ),
            Story(
                "Little Girl",
                "Little Girl Hater",
                "A girl that is little and useless for not popping the last cipher for some reason.",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvt_yRyHbrVC4Mfb2KQeoyhSqhHyP3ShTNPdNvyqvwBQ&s",
                listOf(
                    "Useless child",
                    "Useless Girl",
                    "Useless for being alive"
                )

            ),


        )
        _storyList.value = dummyStoryLists
    }
}
package com.lakedev.gamefindthepair

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.lakedev.gamefindthepair.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var firstCard: FlipCardView? = null
    private var secondCard: FlipCardView? = null
    private var countSteps = 0
    private var listOfCards = listOf<FlipCardView>()
    private var cardTypesList = listOf(
        R.drawable.aigirl,
        R.drawable.img,
        R.drawable.colorful_anime_girl,
        R.drawable.sitroute_mobile,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate((this.layoutInflater))
        setContentView(binding.root)

        initCardList()
        shuffleCardTypes()
        updateCountTV()
        onResetButtonClick()
        setClickListenersToCards()
    }

    private fun initCardList() {
        with(binding) {
            listOfCards = listOf(
                flip1,
                flip2,
                flip3,
                flip4,
                flip5,
                flip6,
                flip7,
                flip8,
                flip9,
                flip10,
                flip11,
                flip12,
                flip13,
                flip14,
                flip15,
                flip16,
            )
        }
    }

    private fun shuffleCardTypes() {
        listOfCards = listOfCards.shuffled()
        for (i in listOfCards.indices step 4) {
            listOfCards[i].frontSideImageId = cardTypesList[0]
            listOfCards[i + 1].frontSideImageId = cardTypesList[1]
            listOfCards[i + 2].frontSideImageId = cardTypesList[2]
            listOfCards[i + 3].frontSideImageId = cardTypesList[3]
        }
    }

    private fun updateCountTV() {
        binding.countTextView.text = getString(R.string.count, countSteps)
    }

    private fun onResetButtonClick() {
        binding.resetBtn.setOnClickListener {
            shuffleCardTypes()
            for (card in listOfCards) {
                card.closeFlipCard()
                card.showFlipCard()
            }
            countSteps = 0
            firstCard = null
            secondCard = null
            updateCountTV()
        }
    }

    private fun setClickListenersToCards() {
        for (card in listOfCards) {
            onFlipCardClick(card)
        }
    }

    private fun onFlipCardClick(card: FlipCardView) {
        card.setOnClickListener {
            if (firstCard == null) {
                firstCard = card
                countSteps++
                card.openFlipCard()
            } else if (secondCard == null) {
                secondCard = card
                card.openFlipCard()

                lifecycleScope.launch {
                    delay(1000)
                    compareTwoCards()
                }
            }
            updateCountTV()
        }
    }

    private fun compareTwoCards() {
        if (firstCard?.frontSideImageId == secondCard?.frontSideImageId) {
            firstCard?.hideFlipCard()
            secondCard?.hideFlipCard()
        } else {
            firstCard?.closeFlipCard()
            secondCard?.closeFlipCard()
        }
        firstCard = null
        secondCard = null
    }
}
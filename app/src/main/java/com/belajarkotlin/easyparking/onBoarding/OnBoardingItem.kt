package com.belajarkotlin.easyparking.onBoarding

import com.belajarkotlin.easyparking.R

class OnBoardingItem(
    val title: Int,
    val text: Int,
    val image: Int
) {
    companion object{
        fun get():List<OnBoardingItem> {
            return listOf(
                OnBoardingItem(R.string.on_boarding_title_1,R.string.on_boarding_text_1,R.drawable.image1),
                OnBoardingItem(R.string.on_boarding_title_2,R.string.on_boarding_text_2,R.drawable.image2),
                OnBoardingItem(R.string.on_boarding_title_3,R.string.on_boarding_text_3,R.drawable.image3),
                OnBoardingItem(R.string.on_boarding_title_4,R.string.on_boarding_text_4, R.drawable.image4)
            )
        }
    }
}
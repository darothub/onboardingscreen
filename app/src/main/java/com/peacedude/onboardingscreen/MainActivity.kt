package com.peacedude.onboardingscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var onboardingAdapter: OnboardingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupOnboardingItems()
        setupIndicators()
        setCurrentOnboardingIndicator(0)
        viewPager.adapter = onboardingAdapter
        viewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentOnboardingIndicator(position)
            }

        })

        nextButton.setOnClickListener {
            if(viewPager.currentItem + 1 < onboardingAdapter.itemCount){
                viewPager.setCurrentItem(viewPager.currentItem + 1)
                prevButton.setText("Previous")
                prevButton.visibility = View.VISIBLE
            }
            else{
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }

        prevButton.setOnClickListener {
            if(viewPager.currentItem  < onboardingAdapter.itemCount){
                viewPager.setCurrentItem(viewPager.currentItem - 1)
            }
        }
    }

    private fun setCurrentOnboardingIndicator(index: Int) {
        val childCount = indicatorLayout.childCount
        for (i in 0 until childCount){
            val imageV = indicatorLayout.getChildAt(i) as ImageView
            if(i == index){
                imageV.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.indicator_active)
                )
            }
            else{
                imageV.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.indicator_inactive)
                )
            }
        }
        if(index == onboardingAdapter.itemCount -1){
            nextButton.setText("Start")
        }else if(index == 0){
            prevButton.visibility = View.GONE
            nextButton.setText("Next")
        }
        else{
            nextButton.setText("Next")
        }

    }

    private fun setupIndicators() {
        val indicators = java.util.ArrayList<ImageView>()
        val indicatorSize = onboardingAdapter.itemCount
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(80, 0, 80, 0)
        for (i in 0 until indicatorSize){
            indicators.add(i, ImageView(this))
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                this, R.drawable.indicator_inactive
            ))
            indicators[i].layoutParams = layoutParams
            indicatorLayout.addView(indicators[i])
        }
    }

    private fun setupOnboardingItems(){
        val onboardingItems = ArrayList<OnboardingItem>()
        val itemSofaOne = OnboardingItem()
        itemSofaOne.title = "Sofa one exquisite"
        itemSofaOne.description = "It is highly comfortable at an affordable price"
        itemSofaOne.image = R.drawable.sofa1
        val itemSofaTwo = OnboardingItem()
        itemSofaTwo.title = "Sofa two exquisite"
        itemSofaTwo.description = "It is highly comfortable at an affordable price"
        itemSofaTwo.image = R.drawable.sofa2
        val itemSofaThree = OnboardingItem()
        itemSofaThree.title = "Sofa three exquisite"
        itemSofaThree.description = "It is highly comfortable at an affordable price"
        itemSofaThree.image = R.drawable.sofa3
        onboardingItems.add(itemSofaOne)
        onboardingItems.add(itemSofaTwo)
        onboardingItems.add(itemSofaThree)

        onboardingAdapter = OnboardingAdapter(onboardingItems)

    }
}
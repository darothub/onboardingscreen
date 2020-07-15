package com.peacedude.onboardingscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OnboardingAdapter(onboardingItems: ArrayList<OnboardingItem>) : RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {
    var onboardingItems:List<OnboardingItem> = onboardingItems
    class OnboardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title = itemView.findViewById<TextView>(R.id.text_title)
        var description = itemView.findViewById<TextView>(R.id.description)
        var image = itemView.findViewById<ImageView>(R.id.onboarding_image)

        fun setInit(onboardingItem: OnboardingItem){
            title.text = onboardingItem.title
            description.text = onboardingItem.description
            image.setImageResource(onboardingItem.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        return OnboardingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_container_onboarding, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return onboardingItems.size
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.setInit(onboardingItems[position])
    }
}
package com.fh.examples.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.fragment.app.Fragment
import com.fh.examples.R
import com.fh.examples.databinding.FragmentBannerBinding

class Banner : Fragment(R.layout.fragment_banner) {

    private lateinit var binding: FragmentBannerBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBannerBinding.bind(view)



        binding.showBanner.setOnClickListener {
            if (binding.bannerCard.visibility == View.GONE) {
//                val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)
//                binding.bannerCard.startAnimation(animation)
//                binding.bannerCard.visibility = View.VISIBLE
                binding.bannerCard.expand()


            }
        }

        binding.okay.setOnClickListener {
//            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_down)
//            binding.bannerCard.startAnimation(animation)
//            binding.bannerCard.visibility = View.GONE
            binding.bannerCard.collapse()

        }


    }

    private fun View.expand() {
        this@expand.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val targetHeight = this@expand.measuredHeight

        this@expand.layoutParams.height = 1
        this@expand.visibility = View.VISIBLE
        val animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                this@expand.layoutParams.height = if (interpolatedTime == 1f)
                    ViewGroup.LayoutParams.WRAP_CONTENT
                else
                    (targetHeight * interpolatedTime).toInt()
                this@expand.requestLayout()
            }

            override fun willChangeBounds(): Boolean = true
        }

        animation.duration =
            (targetHeight / this@expand.context.resources.displayMetrics.density).toInt().toLong()
        this@expand.startAnimation(animation)
    }

    private fun View.collapse() {
        val initialHeight = this.measuredHeight

        val animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    this@collapse.visibility = View.GONE
                } else {
                    this@collapse.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                    this@collapse.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean = true
        }

        animation.duration =
            (initialHeight / this.context.resources.displayMetrics.density).toInt().toLong()
        this.startAnimation(animation)
    }


}
package com.example.fitness.ui.bodyBuilding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.fitness.R

class BodyBuildingFragment : Fragment() {

    private lateinit var communityViewModel: BodyBuildingViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        communityViewModel =
                ViewModelProviders.of(this).get(BodyBuildingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_body, container, false)

        communityViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }
}
package com.example.fitness.ui.bodyBuilding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.fitness.R

class bodyBuildingFragment : Fragment() {

    private lateinit var communityViewModel: bodyBuildingViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        communityViewModel =
                ViewModelProviders.of(this).get(bodyBuildingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_body, container, false)

        communityViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }
}
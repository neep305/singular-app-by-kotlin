package com.jason.singularappbykotlin.ui.ads

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jason.singularappbykotlin.R

class AdsFragment : Fragment() {

    companion object {
        fun newInstance() = AdsFragment()
    }

    private lateinit var viewModel: AdsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ads, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
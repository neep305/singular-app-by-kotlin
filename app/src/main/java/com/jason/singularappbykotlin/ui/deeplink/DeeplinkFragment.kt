package com.jason.singularappbykotlin.ui.deeplink

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jason.singularappbykotlin.R

class DeeplinkFragment : Fragment() {

    companion object {
        fun newInstance() = DeeplinkFragment()
    }

    private lateinit var viewModel: DeeplinkViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deeplink, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DeeplinkViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
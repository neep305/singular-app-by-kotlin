package com.jason.singularappbykotlin.ui.inweb

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jason.singularappbykotlin.R

class InwebFragment : Fragment() {

    companion object {
        fun newInstance() = InwebFragment()
    }

    private lateinit var viewModel: InwebViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inweb, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InwebViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
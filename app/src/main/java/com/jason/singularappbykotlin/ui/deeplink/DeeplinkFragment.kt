package com.jason.singularappbykotlin.ui.deeplink

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.firebase.inject.Deferred
import com.jason.singularappbykotlin.R
import com.jason.singularappbykotlin.Utils
import com.jason.singularappbykotlin.databinding.FragmentDeeplinkBinding
import com.jason.singularappbykotlin.databinding.FragmentHomeBinding

class DeeplinkFragment : Fragment() {

    companion object {
        fun newInstance() = DeeplinkFragment()
    }

    private lateinit var viewModel: DeeplinkViewModel
    private lateinit var binding: FragmentDeeplinkBinding
    lateinit var inputMethodManager: InputMethodManager

    private lateinit var etDeeplinkValue: EditText
    private lateinit var etQueryParams: EditText
    private lateinit var etPassthrough: EditText
    private lateinit var etIsDeferred: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeeplinkBinding.inflate(inflater, container, false)

        etDeeplinkValue = binding.etDeeplinkValue
        etPassthrough = binding.etPassthrough
        etQueryParams = binding.etQueryParams
        etIsDeferred = binding.etIsDeferred

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments

        if (bundle != null) {
            binding.etDeeplinkValue.setText(bundle.getString("DEEPLINK_KEY"))
            binding.etPassthrough.setText(bundle.getString("PASSTHROUGH_KEY"))
            val isDeferred = if (bundle.getBoolean("IS_DEFERRED_KEY")) "YES" else "NO"
            binding.etIsDeferred.setText(isDeferred)
            binding.etQueryParams.setText(bundle.getString("PARAMETER_KEY"))
        } else {
            Utils.showToast(requireContext(), "No arguments returned")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DeeplinkViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
package com.jason.singularappbykotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.jason.singularappbykotlin.R
import com.jason.singularappbykotlin.Utils
import com.jason.singularappbykotlin.databinding.FragmentHomeBinding
import com.singular.sdk.Singular
import org.json.JSONArray
import org.json.JSONObject

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding

    lateinit var inputMethodManager: InputMethodManager

    private var isSimplePurchase = false;
    private var isRevenueToCancel = false;

    var currencyCode = arrayOf("KRW", "USD", "EUR", "CNY", "JPN", "GBR", "INR")
    var selectedCurrency : String = "KRW"

    private lateinit var etEventName : EditText
    private lateinit var etPrice : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false) //inflater.inflate(R.layout.fragment_home, container, false)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            currencyCode
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selctedItemView: View?, position: Int, id: Long) {
                selectedCurrency = currencyCode[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.buttonPurchase.setOnClickListener {
            sendRevenue(isSimplePurchase, isRevenueToCancel, binding.eventNameText, selectedCurrency, binding.priceText)
        }

        binding.buttonInappEvent.setOnClickListener {
            sendRevenue(etEventName.text.toString())
        }

        binding.ctvPurchaseWithoutReceipt.setOnCheckedChangeListener { compoundButton, b ->
            isSimplePurchase = b
        }

        binding.cbCancelPurchase.setOnCheckedChangeListener { compoundButton, b -> isRevenueToCancel = b }

        binding.buttonMoveToWeb.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("_dl", "http://singular-web-app.herokuapp.com")
            NavHostFragment.findNavController(this).navigate(R.id.action_home_to_webview, bundle)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun sendRevenue(eventName: String) {
        try {
            val contents = JSONArray()
            val eventArgs = JSONObject()
            eventArgs.put("sku", "UPC-018627610014")
            eventArgs.put("qty", 2)
            eventArgs.put("unit_price", 8.99)
            eventArgs.put("currency", "USD")
            contents.put(eventArgs)

            Singular.eventJSON(eventName, eventArgs)

            Utils.showToast(requireContext(), "$eventName sent")
            etEventName.clearFocus()
            inputMethodManager.hideSoftInputFromWindow(view?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun sendRevenue(isSimpleRevenue: Boolean, isRevenueToCancel: Boolean, eventNameText: EditText, currencyCode: String, priceText: EditText) {
        var eventName = if (isRevenueToCancel) "cancellation" else eventNameText.text.toString()
        var priceDouble = priceText.text.toString().toDouble()

        // Call Singular Revenue Method
        Singular.customRevenue(eventName, currencyCode, priceDouble)
    }
}
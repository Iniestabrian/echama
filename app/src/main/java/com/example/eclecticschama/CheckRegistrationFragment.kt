package com.example.eclecticschama

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.eclecticschama.data.PhoneNumberTextWatcher
import com.example.eclecticschama.databinding.FragmentCheckRegistrationBinding


class CheckRegistrationFragment : Fragment() {
    private lateinit var binding: FragmentCheckRegistrationBinding

    private var COUNTRY_CODE:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckRegistrationBinding.inflate(inflater,container,false)


        setOnclickListener()


        return binding.root
    }




    private fun setOnclickListener(){
        binding.btnSend.setOnClickListener {
            val editTextPhoneNumber = binding.edtNumber

            if (isFieldEmpty(editTextPhoneNumber, "Phone number is required")) {
                // Field is empty, return or handle as needed
                return@setOnClickListener
            }

            val phoneNumber = editTextPhoneNumber.text.toString().trim()

            // Format the phone number
            val formattedPhoneNumber = formatPhoneNumber(phoneNumber)


            if (validatePhoneNumber(formattedPhoneNumber)) {
                findNavController().navigate(R.id.action_checkRegistrationFragment_to_registerFragment)
            } else {
                // Invalid phone number, handle the error messages
                if (phoneNumber.length < 9) {
                    editTextPhoneNumber.error = "Phone number is too short"
                } else if (phoneNumber.length > 13) {
                    editTextPhoneNumber.error = "Phone number is too long"
                } else {
                    editTextPhoneNumber.error = "Invalid phone number"
                }
            }
        }
    }




    private fun validatePhoneNumber(phoneNumber: String): Boolean {
        val phoneNumberPattern = Regex("^\\+?\\d{1,3}\\s?\\d{9}\$|^\\d{9}\$|^\\d{10}\$")

        //val phoneNumberPattern2 = Regex("^\\+?\\d{1,3}\\s?\\d{9}\$|^\\d{10}\$")
        return phoneNumberPattern.matches(phoneNumber)
    }




    fun formatPhoneNumber(unformattedPhone:String):String{
        var formatPhone:String=""

        var countryCode:String =COUNTRY_CODE

        if (countryCode.isEmpty()){
            countryCode = "254"
        }

        if( unformattedPhone.startsWith("+")){
            formatPhone = unformattedPhone.substring(1)
        }
        if (unformattedPhone.startsWith(countryCode)){
            formatPhone = unformattedPhone
        }

        if (unformattedPhone.startsWith("01") || unformattedPhone.startsWith("07"))
        {
            formatPhone = countryCode + unformattedPhone.substring(1)
        }
        else if (unformattedPhone.startsWith("7") && unformattedPhone.length < 10){
            formatPhone =countryCode + unformattedPhone
        }
            return formatPhone.trim()
    }



    private fun isFieldEmpty(field: EditText, errorMessage: String): Boolean {
        val value = field.text.toString().trim()
        if (value.isEmpty()) {
            field.error = errorMessage
            return true
        }
        return false
    }


}
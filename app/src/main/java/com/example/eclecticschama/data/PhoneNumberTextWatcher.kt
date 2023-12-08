package com.example.eclecticschama.data
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.example.eclecticschama.databinding.FragmentCheckRegistrationBinding

class PhoneNumberTextWatcher(private val editText: EditText) : TextWatcher {


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // Not needed for this example
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Not needed for this example
    }

    override fun afterTextChanged(s: Editable?) {
        val phoneNumber = s.toString().trim()

        if (isValidPhoneNumber(phoneNumber)) {
            // Phone number is valid
            editText.error = null
        } else {
            // Phone number is invalid
            editText.error = "Invalid phone number"
        }
    }

   fun isValidPhoneNumber(phoneNumber: String): Boolean {
       val phoneNumberPattern = Regex("^\\+\\d{1,3}\\s?\\d{10}\$")

       return phoneNumberPattern.matches(phoneNumber)

    }
}


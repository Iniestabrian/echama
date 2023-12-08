package com.example.eclecticschama

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.eclecticschama.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentRegisterBinding.inflate(inflater,container,false)



        binding.btnSignUp.setOnClickListener {
            if (validateForm())
            {
                findNavController().navigate(R.id.action_registerFragment_to_dashboardFragment)

            }
        }

        binding.edIdNumber.requestFocus()
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.edIdNumber, InputMethodManager.SHOW_IMPLICIT)

        navigateBack()



        return binding.root
    }




    private fun validateIdNumber(): Boolean {
        val idNumber = binding.edIdNumber.text.toString().trim()

        // Check if the ID is empty
        if (isFieldEmpty(binding.edIdNumber, "ID Number cannot be empty")) {
            return false
        }

        // Check if the ID is less than 8 characters
        if (idNumber.length < 8) {
            binding.edIdNumber.error = "ID Number must be at least 8 characters"
            return false
        }

        // Check if the ID is more than 8 characters
        if (idNumber.length > 8) {
            binding.edIdNumber.error = "ID Number cannot be more than 8 characters"
            return false
        }

        // If the ID is not empty and not more than 8 characters
        return true
    }

    private fun validateNames(): Boolean {
        return !isFieldEmpty(binding.edSurname, "SURNAME cannot be empty") &&
                !isFieldEmpty(binding.edOtherNames, "Other Names cannot be empty")
    }

    private fun validateEmail(): Boolean {
        val email = binding.edEmail.text.toString().trim()

        if (email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.edEmail.error = "Invalid email format"
            return false
        }
        // If email is empty or matches the correct format
        return true

    }

    private fun validateCheckbox(): Boolean {
        return if (!binding.checkbox.isChecked) {
            Toast.makeText(requireContext(), "Check the checkbox to accept terms of service", Toast.LENGTH_SHORT).show()
            false
        } else true
    }

    private fun isFieldEmpty(field: EditText, errorMessage: String): Boolean {
        val value = field.text.toString().trim()
        if (value.isEmpty()) {
            field.error = errorMessage
            return true
        }
        return false
    }

    private fun validateForm(): Boolean {
        return validateIdNumber() && validateNames() && validateEmail() && validateCheckbox()
    }












 private fun navigateBack(){

     binding.backButton.setOnClickListener{

         findNavController().navigateUp()
     }
 }












}
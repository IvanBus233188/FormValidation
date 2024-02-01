package com.example.formvalidationtextinputedittext

import androidx.appcompat.app.AppCompatActivity

import com.example.formvalidationtextinputedittext.databinding.ActivityMainBinding

import android.os.Bundle
import android.util.Patterns

import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        emailFocusListener()
        passwordFocusListener()
        phoneFocusListener()
        userFocusListener()

        binding.submitButton.setOnClickListener{submitForm()}
    }

    private fun submitForm() {

        binding.emailContainer.helperText=validarEmail()
        binding.passwordContainer.helperText=validPassword()
        binding.phoneContainer.helperText=validarPhone()
        binding.userContainer.helperText=validUser()
        val email = validarEmail() ==null
        val password = validPassword()==null
        val phone = validPassword()==null
        val user = validUser()==null
        if (email&&password&&phone&&user){
            resetForm()
        }else{
            invalidarForm()
        }
    }

    private fun invalidarForm() {
        var message =""
        if(binding.emailContainer.helperText!=null){
            message += "\n\nEmail: "+binding.emailContainer.helperText
        }
        if(binding.passwordContainer.helperText!=null){
            message += "\n\nPassword: "+binding.passwordContainer.helperText
        }
        if(binding.phoneContainer.helperText!=null){
            message += "\n\nPhone: "+binding.phoneContainer.helperText
        }
        if(binding.userContainer.helperText!=null){
            message += "\n\nUser: "+binding.userContainer.helperText
        }
        AlertDialog.Builder(this).setTitle("Formulario Invalido")
            .setMessage(message)
            .setPositiveButton("okay"){_,_->

            }.show()
    }

    private fun resetForm() {
        var message = "Email " + binding.emailText.text
        message += "\nPassword"+binding.passwordText.text
        message += "\nPhone"+binding.phoneText.text
        message += "\nUser"+binding.userText.text
        AlertDialog.Builder(this).setTitle("Formulario Valido")
            .setMessage(message)
            .setPositiveButton("okay"){_,_->
                binding.emailText.text=null
                binding.passwordText.text=null
                binding.phoneText.text=null
                binding.userText.text=null
                binding.emailContainer.helperText=getString(R.string.required)
                binding.passwordContainer.helperText=getString(R.string.required)
                binding.phoneContainer.helperText=getString(R.string.required)
                binding.userContainer.helperText=getString(R.string.required)
            }.show()


    }


    private fun emailFocusListener(){
        binding.emailText.setOnFocusChangeListener { _, focused ->
            if (!focused){
                binding.emailContainer.helperText=validarEmail()
            }
        }
    }
    private fun phoneFocusListener(){
        binding.phoneText.setOnFocusChangeListener { _, focused ->
            if (!focused){
                binding.phoneContainer.helperText=validarPhone()
            }
        }
    }



    private fun passwordFocusListener(){
        binding.passwordText.setOnFocusChangeListener { _, focused ->
            if (!focused){
                binding.passwordContainer.helperText=validPassword()
            }
        }
    }

    private fun userFocusListener(){
        binding.userText.setOnFocusChangeListener { _, focused ->
            if (!focused){
                binding.userContainer.helperText=validUser()
            }
        }
    }
    private fun validPassword(): String?
    {
        val passwordText = binding.passwordText.text.toString()

        if(passwordText.length < 8)
        {
            return "Minimum 8 Character Password"
        }
        if(!passwordText.matches(".*[A-Z].*".toRegex()))
        {
            return "Must Contain 1 Upper-case Character"
        }
        if(!passwordText.matches(".*[a-z].*".toRegex()))
        {
            return "Must Contain 1 Lower-case Character"
        }
        if(!passwordText.matches(".*[@#\$%^&+=].*".toRegex()))
        {
            return "Must Contain 1 Special Character (@#\$%^&+=)"
        }

        return null
    }
    private fun validarEmail(): String? {
        var email:String = binding.emailText.text.toString()

        if (email.isEmpty()){
            return "Required"
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return "Correo invalido"
        }

        return null
    }
    private fun validarPhone(): String? {
        var phone:String = binding.phoneText.text.toString()
        if(!phone.matches(".*[0-9].*".toRegex()))
        {
            return "Must be all digits"
        }
        if(phone.length !=10)
        {
            return "Must be 10 digits"
        }
        return null
    }

    private fun validUser(): String?
    {
        val userText = binding.userText.text.toString()

        if(userText.length < 5)
        {
            return "Minimum 5 Character"
        }
        if(!userText.matches(".*[A-Z].*".toRegex()))
        {
            return "Must Contain 1 Upper-case Character"
        }

        return null
    }
}
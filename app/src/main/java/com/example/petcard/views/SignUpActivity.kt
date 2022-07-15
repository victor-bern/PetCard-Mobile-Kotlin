package com.example.petcard.views

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.petcard.databinding.ActivitySignUpBinding
import com.example.petcard.viewmodels.SignUpActivityViewModel
import com.google.android.material.snackbar.Snackbar

class SignUpActivity : AppCompatActivity() {
    private lateinit var viewModel: SignUpActivityViewModel
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[SignUpActivityViewModel::class.java]

        setContentView(binding.root)

        handleObservers()
        binding.buttonSignup.setOnClickListener { handleButtonSignUpClick() }

    }

    private fun handleObservers() {
        viewModel.user.observe(this) {
            if (it != null) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        viewModel.error.observe(this) {
            if (it.isNotEmpty()) {
                Snackbar.make(this, binding.root, it, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleButtonSignUpClick() {
        val fieldNotValid = validateFields(binding.editNameSignUp, binding.editEmailSignup, binding.editPasswordSignup, binding.editRepeatPasswordSignup)
        if (fieldNotValid != null) {
            fieldNotValid.error = "Campo não pode ser vazio"
            return
        }
        val name = binding.editNameSignUp.text.toString()
        val email = binding.editEmailSignup.text.toString()
        val password = binding.editPasswordSignup.text.toString()
        val passwordRepeated = binding.editRepeatPasswordSignup.text.toString()

        if (!viewModel.validatePasswords(password, passwordRepeated)) {
            binding.editPasswordSignup.error = "Senhas precisam ser iguais!"
            return
        }

        viewModel.createUser(name, email, password)

    }

    private fun validateFields(vararg editTexts: EditText): EditText? {
        editTexts.forEach {
            it.error = null
            if (it.text.toString().isEmpty()) {
                return it
            }
        }
        return null
    }


}
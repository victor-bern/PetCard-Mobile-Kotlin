package com.example.petcard.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.petcard.databinding.ActivityLoginBinding
import com.example.petcard.viewmodels.LoginActivityVIewModel
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginActivityVIewModel
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[LoginActivityVIewModel::class.java]

        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener { handleLoginButton() }
        binding.textSignup.setOnClickListener { handleTextSignUpClick() }
        handleObservers()
    }

    private fun handleTextSignUpClick() {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    private fun handleLoginButton() {
        binding.editEmail.error = null
        binding.editPassword.error = null
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        if (email.isEmpty()) {
            binding.editEmail.error = "Campo não pode estar vazio"
            return
        }
        if (password.isEmpty()) {
            binding.editPassword.error = "Campo não pode estar vazio"
            return
        }

        viewModel.doLogin(email, password)
    }

    private fun handleObservers() {
        viewModel.isLogged.observe(this) {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Snackbar.make(this, binding.root, "Email ou senha incorretos", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
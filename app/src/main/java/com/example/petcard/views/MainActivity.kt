package com.example.petcard.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petcard.R
import com.example.petcard.databinding.ActivityMainBinding
import com.example.petcard.utils.PetsPreference
import com.example.petcard.viewmodels.MainActivityViewModel
import com.example.petcard.views.adapters.ListPetAdapter
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter = ListPetAdapter()
    private lateinit var petsPreference: PetsPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        binding.recyclerPetsList.layoutManager = LinearLayoutManager(this)
        binding.recyclerPetsList.adapter = adapter

        petsPreference = PetsPreference(this)

        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        binding.floatingAddPet.setOnClickListener { handleFloatingClick() }

        handleObservers()
        viewModel.getPets()
    }

    override fun onResume() {
        viewModel.getPets()
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.action_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_settings -> {
            petsPreference.clear()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun handleFloatingClick() {
        startActivity(Intent(this, RegisterPet::class.java))
    }

    private fun handleObservers() {
        viewModel.pets.observe(this) {
            adapter.updatePets(it)
        }

        viewModel.errors.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        }
    }

}

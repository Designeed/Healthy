package com.example.healthy.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.healthy.data.repository.SharedPrefRepositoryImpl
import com.example.healthy.databinding.FragmentSettingsBinding
import com.example.healthy.domain.use_cases.general.SwitchThemeUseCase
import com.example.healthy.domain.use_cases.shared.NotificationService
import com.example.healthy.domain.use_cases.sharedPref.GetThemeUseCase
import com.example.healthy.domain.use_cases.sharedPref.SaveThemeUseCase
import com.example.healthy.domain.util.Themes

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var repository: SharedPrefRepositoryImpl
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        repository = SharedPrefRepositoryImpl(requireContext())

        setDefaultValue()
        setRadioButtonClick()

        return binding.root
    }

    private fun setRadioButtonClick() {
        binding.themeButtons.setOnCheckedChangeListener { radioGroup, i ->
            lifecycleScope.launchWhenCreated {
                SaveThemeUseCase().execute(Themes.from(i), repository)
                val savedTheme = GetThemeUseCase().execute(repository)
                SwitchThemeUseCase().execute(savedTheme)
            }
        }
    }

    private fun setDefaultValue() {
        lifecycleScope.launchWhenCreated {
            binding.themeButtons.check(GetThemeUseCase().execute(repository).value)
        }
    }
}
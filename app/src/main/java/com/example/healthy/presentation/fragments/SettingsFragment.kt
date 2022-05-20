package com.example.healthy.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.lifecycleScope
import com.example.healthy.R
import com.example.healthy.data.repository.SharedPrefRepositoryImpl
import com.example.healthy.databinding.FragmentSettingsBinding
import com.example.healthy.domain.use_cases.general.SwitchThemeUseCase
import com.example.healthy.domain.use_cases.sharedPref.GetThemeUseCase
import com.example.healthy.domain.use_cases.sharedPref.SaveThemeUseCase
import com.example.healthy.domain.util.Themes
import kotlinx.coroutines.launch

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
            when(true) {
                binding.rbLight.isChecked -> changeTheme(Themes.Light)
                binding.rbDark.isChecked -> changeTheme(Themes.Dark)
                binding.rbAuto.isChecked -> changeTheme(Themes.Auto)
            }
        }
    }

    private fun changeTheme(theme: Themes) {
        lifecycleScope.launch {
            SaveThemeUseCase().execute(theme, repository)
            SwitchThemeUseCase().execute(theme)
        }
    }

    private fun setDefaultValue() {
        lifecycleScope.launch {
            val currentGroup = GetThemeUseCase().execute(repository)
            when (currentGroup) {
                Themes.Light -> binding.themeButtons.check(binding.rbLight.id)
                Themes.Dark -> binding.themeButtons.check(binding.rbDark.id)
                Themes.Auto -> binding.themeButtons.check(binding.rbAuto.id)
            }
        }
    }
}
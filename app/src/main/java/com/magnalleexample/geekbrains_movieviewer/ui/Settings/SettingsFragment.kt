package com.magnalleexample.geekbrains_movieviewer.ui.Settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.preference.MultiSelectListPreference
import androidx.preference.PreferenceFragmentCompat
import com.magnalleexample.geekbrains_movieviewer.R
import com.magnalleexample.geekbrains_movieviewer.app

class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var viewModel: SettingsViewModel

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        val languagesMultiSelectedListPreference =
            findPreference<MultiSelectListPreference>("languages_multi_select_list")
        viewModel.repo = activity?.app?.repository
        viewModel.loadData()
        viewModel.languagesList.observe(viewLifecycleOwner, { languagesList ->
            languagesMultiSelectedListPreference?.apply {
                this.entries =
                    languagesList?.map { "${it.english_name}(${it.name})" }?.toTypedArray()
                        ?: arrayOf<String>()
                this.entryValues =
                    languagesList?.map { it.iso_639_1 }?.toTypedArray() ?: arrayOf<String>()
                this.isEnabled = true
            }
        }
        )
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            it?.let {
                viewModel.disableError()
                Toast.makeText(this.context, it, Toast.LENGTH_LONG).show()
            }
        })
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
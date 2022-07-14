package dimas_ok.shoppinglist.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import dimas_ok.shoppinglist.R

class SettingsFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preferences, rootKey)
    }
}
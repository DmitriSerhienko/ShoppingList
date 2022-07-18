package dimas_ok.shoppinglist.settings

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import dimas_ok.shoppinglist.R
import dimas_ok.shoppinglist.billing.BillingManager

class SettingsFragment: PreferenceFragmentCompat() {
    private lateinit var removeAdsPref: Preference
    private lateinit var bManager: BillingManager
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preferences, rootKey)
        init()
    }
    private fun init(){
        bManager = BillingManager(activity as AppCompatActivity)
        removeAdsPref = findPreference("remove_ads_key")!!
        removeAdsPref.setOnPreferenceClickListener {
            bManager.startConnection()
            true
        }
    }

    override fun onDestroy() {
        bManager.closeConnection()
        super.onDestroy()
    }
}
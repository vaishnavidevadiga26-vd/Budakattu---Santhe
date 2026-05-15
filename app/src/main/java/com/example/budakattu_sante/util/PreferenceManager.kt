package com.example.budakattu_sante.util

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceManager @Inject constructor(@ApplicationContext context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun saveUser(id: String, role: String) {
        prefs.edit().putString("user_id", id).putString("user_role", role).apply()
    }

    fun getUserId(): String? = prefs.getString("user_id", null)
    fun getUserRole(): String? = prefs.getString("user_role", null)

    fun clear() {
        prefs.edit().clear().apply()
    }
}

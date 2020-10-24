package tn.thinkit.challenge.utilities

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Parcelable
import com.google.gson.Gson

object SharedPreferencesObject {
    // private constructor()
    // private constructor(activity: Activity)

    private var instance: SharedPreferences? = null

    private fun getInstance(activity: Activity): SharedPreferences {
        if (instance == null) {
            instance = activity.getPreferences(Context.MODE_PRIVATE)
        }
        return instance!!
    }

    fun editSharedPreferencesString(
        activity: Activity,
        key: String,
        value: String?
    ) {
        with(getInstance(activity).edit()) {
            putString(key, value)
            commit()
        }
    }

    fun getSharedPreferencesString(
        activity: Activity,
        key: String,
        defaultValue: String?
    ): String? {
        return getInstance(activity).getString(key, defaultValue)
    }

    fun editSharedPreferencesBoolean(
        activity: Activity,
        key: String,
        value: Boolean
    ) {
        with(getInstance(activity).edit()) {
            putBoolean(key, value)
            commit()
        }
    }

    fun getSharedPreferencesBoolean(
        activity: Activity,
        key: String
    ): Boolean? {
        return getInstance(activity).getBoolean(key, false)
    }

    fun editSharedPreferencesParcelable(
        activity: Activity,
        key: String,
        value: Parcelable?
    ) {
        val json = Gson().toJson(value)
        with(getInstance(activity).edit()) {
            putString(key, json)
            commit()
        }
    }

    fun <T : Parcelable> getSharedPreferencesParcelable(
        activity: Activity,
        key: String,
        ofClass: Class<T>
    ): Parcelable? {
        val json = getInstance(activity).getString(key, null)
        return Gson().fromJson(json, ofClass)
    }
}




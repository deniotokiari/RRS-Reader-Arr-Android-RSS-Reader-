package by.deniotokiari.core.extensions

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

private inline fun <T> SharedPreferences.delegate(
    defaultValue: T,
    key: String?,
    crossinline getter: SharedPreferences.(String, T) -> T,
    crossinline setter: SharedPreferences.Editor.(String, T) -> SharedPreferences.Editor
): ReadWriteProperty<Any, T> {
    return object : ReadWriteProperty<Any, T> {

        override fun getValue(thisRef: Any, property: KProperty<*>): T = getter(key ?: property.name, defaultValue)

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) = edit().setter(key ?: property.name, value).apply()

    }
}

fun SharedPreferences.int(defaultValue: Int = 0, key: String? = null) = delegate(defaultValue, key, SharedPreferences::getInt, SharedPreferences.Editor::putInt)

fun SharedPreferences.string(defaultValue: String = "", key: String? = null) = delegate(defaultValue, key, SharedPreferences::getString, SharedPreferences.Editor::putString)

fun SharedPreferences.boolean(defaultValue: Boolean = false, key: String? = null) = delegate(defaultValue, key, SharedPreferences::getBoolean, SharedPreferences.Editor::putBoolean)
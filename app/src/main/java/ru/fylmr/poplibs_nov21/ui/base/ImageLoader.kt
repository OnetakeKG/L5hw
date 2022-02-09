package ru.fylmr.poplibs_nov21.ui.base

interface ImageLoader<T> {

    fun loadInto(url: String, container: T)
}

package com.polohach.weather.enums

enum class ItemViewType {
    HEADER,
    ITEM;

    operator fun invoke() = ordinal

    companion object {
        fun from(position: Int): ItemViewType = values().find { it() == position } ?: ITEM
    }
}

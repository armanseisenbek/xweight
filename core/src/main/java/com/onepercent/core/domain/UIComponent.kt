package com.onepercent.core.domain

sealed class UIComponent {

    data class Dialog(val title: String, val description: String): UIComponent()

    data class None(val message: String): UIComponent()

    data class Snackbar(val message: String): UIComponent()

    data class Toast(val message: String): UIComponent()

    // snackbar, toast, notification
}

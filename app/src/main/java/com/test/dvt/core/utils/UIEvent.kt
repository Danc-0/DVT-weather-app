package com.test.dvt.core.utils

sealed class UIEvent {
    data class ShowSnackBar(val message: String) : UIEvent()
}
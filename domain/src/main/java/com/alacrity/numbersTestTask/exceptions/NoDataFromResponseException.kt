package com.alacrity.numbersTestTask.exceptions

class NoDataFromResponseException(message: String = "Undefined", exception: Throwable? = null) : NumberTestTaskException(message, exception)
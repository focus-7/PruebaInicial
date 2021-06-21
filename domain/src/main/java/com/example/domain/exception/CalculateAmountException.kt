package com.example.domain.exception

import java.lang.RuntimeException

class CalculateAmountException(ERROR_CALCULATE_AMOUNT: String = "No se logro calcular el pago.") :
    RuntimeException(ERROR_CALCULATE_AMOUNT)
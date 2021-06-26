package com.example.domain.exception

class CalculateAmountException(message: String = "No se logro calcular el pago.") :
    RuntimeException(message)
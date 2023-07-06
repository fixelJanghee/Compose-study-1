package com.example.compose_study_1.ui.calculator

sealed class CalculatorOperation(val symbol: String) {
    object Add : CalculatorOperation("+")
    object Sub : CalculatorOperation("-")
    object Mul : CalculatorOperation("×")
    object Div : CalculatorOperation("÷")
}
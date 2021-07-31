package ru.voronezhtsev.calcserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@SpringBootApplication
class CalcServerApplication

fun main(args: Array<String>) {
    runApplication<CalcServerApplication>(*args)
}

enum class Operation {
    ADD, SUB
}

data class Expression(val left: Double, val right: Double, val operation: Operation)

@RestController
class OperationController {
    @PostMapping()
    fun calc(@RequestBody expression: Expression): String {
        return if (expression.operation == Operation.ADD) {
            (expression.left + expression.right).toString();
        } else {
            (expression.left - expression.right).toString();
        }
    }
}

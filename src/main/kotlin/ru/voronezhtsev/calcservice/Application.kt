package ru.voronezhtsev.calcservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType.OAS_30
import springfox.documentation.spring.web.plugins.Docket

@SpringBootApplication
class Application {
    @Bean
    fun api(): Docket {
        return Docket(OAS_30)
            .select()
            .apis(RequestHandlerSelectors.basePackage("ru.voronezhtsev.calcservice"))
            .paths(PathSelectors.any())
            .build()
    }
}

data class Expression(val left: Double, val right: Double, val operation: String)
data class Data(val lon: Double, val lat: Double, val temp: Double)

@RestController
class Controller {
    @PostMapping()
    fun calc(@RequestBody expression: Expression): String {
        return if (expression.operation == "+") {
            (expression.left + expression.right).toString()
        } else {
            (expression.left - expression.right).toString()
        }
    }

    @GetMapping("/temp")
    fun get(): Data {
        return Data(1.0, 2.0, -3.0)
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
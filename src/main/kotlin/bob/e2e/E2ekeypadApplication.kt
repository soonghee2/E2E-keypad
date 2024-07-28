package bob.e2e

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication//이거 입력하면 위에 자동으로 import

class E2ekeypadApplication {
}

fun main(args: Array<String>) {
    runApplication<E2ekeypadApplication>(*args)
}

//http://localhost:8080/test/a
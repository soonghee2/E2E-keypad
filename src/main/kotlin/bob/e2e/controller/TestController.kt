package bob.e2e.controller

import org.springframework.web.bind.annotation.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.random.Random

@RestController
class TestController {

    private val logger: Logger = LoggerFactory.getLogger(TestController::class.java)

    private val hashes = mapOf(
        0 to "8c348dc7cf2ca7a26fbca7d924c3617d78620f18",
        1 to "59eb68ea39325cd3346fff29291e9d64516bab8c",
        2 to "7374438bc311a369dd8092cb5ccf1c15678ba13c",
        3 to "ba320084152da55f5272ebef64a3759f959c16",
        4 to "06def764a9957c2b91b02c9fe4abcde48a4886c4",
        5 to "63e27314c677e60ecee18b809102fd2f74dbdaca",
        6 to "7a69ca8888bf1cb0f032f290589351337253c8cb",
        7 to "58dc795192d5982e43fce5e151962baad57aaa23",
        8 to "5d2467f373ba0b82916b466ceb3b4ced8ff8bd3c",
        9 to "f61cbae9db225cf89805bb58d276648740c50bd7"
    )

    @GetMapping("/hash/{number}")
    fun getHash(@PathVariable number: String): String {
        val num = number.replace("_", "").toIntOrNull() ?: return "Invalid number"
        return hashes[num] ?: "Invalid number"
    }

    @PostMapping("/verify")
    fun verifyHash(@RequestBody payload: Map<String, String>): String {
        val hash = payload["hash"] ?: return "No hash provided"
        val number = hashes.entries.find { it.value == hash }?.key
        return if (number != null) {
            logger.info("Received hash: $hash, corresponding number: $number")
            "Valid hash for number: $number"
        } else {
            logger.info("Received invalid hash: $hash")
            "Invalid hash"
        }
    }

    @GetMapping("/keypad")
    fun getShuffledKeypad(): List<String> {
        val keypad = (0..9).map { it.toString() }.toMutableList()
        keypad.add("blank")
        keypad.add("blank")
        keypad.shuffle(Random(System.currentTimeMillis()))
        return keypad
    }
}

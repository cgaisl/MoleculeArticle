package at.cgaisl.moleculearticle.common.data

import kotlinx.coroutines.delay

data class Mail(
    val id: Int,
    val subject: String,
    val sender: String,
    val body: String,
)

object MailRepository {
    suspend fun loadMail(id: Int): Mail {
        delay(1000)
        return data.first { it.id == id }
    }

    suspend fun getAllMail(): List<Mail> {
        delay(1)
        return data
    }
}

private val data = listOf(
    Mail(
        id = 0,
        subject = "Hello There",
        sender = "Obi Wan Kenobi",
        body = "General Kenobi! You are a bold one!"
    ),
    Mail(
        id = 1,
        subject = "Do you want to get rich?",
        sender = "Elon Musk",
        body = "Hello, would you be interested in investing in the next get-rich-quick scheme? Then reply now with your credit card information, your mother’s maiden name, and the last four digits of your social security number.\nSincerely\nElon Musk\n"
    ),
    Mail(
        id = 2,
        subject = "You are eligable for a free vacation!",
        sender = "Nigerian Prince",
        body = "Send me exactly $1000 and I will send you $1,000,000 in return!"
    ),
)
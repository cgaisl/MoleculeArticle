package at.cgaisl.moleculearticle.common

import androidx.compose.runtime.*
import at.cgaisl.moleculearticle.common.data.Mail
import at.cgaisl.moleculearticle.common.data.MailRepository

sealed class ContentRendering {
    data class Loaded(
        val subject: String,
        val sender: String,
        val body: String,
    ) : ContentRendering()

    object Loading : ContentRendering()
}


@Composable
fun contentRendering(mailId: Int): ContentRendering {
    var mail: Mail? by remember { mutableStateOf(null) }

    LaunchedEffect(mailId) {
        mail = null
        mail = MailRepository.loadMail(mailId)
    }

    return mail?.let {
        ContentRendering.Loaded(
            subject = it.subject,
            sender = it.sender,
            body = it.body,
        )
    } ?: ContentRendering.Loading
}
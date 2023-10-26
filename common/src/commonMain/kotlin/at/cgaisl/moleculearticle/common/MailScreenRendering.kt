package at.cgaisl.moleculearticle.common

import androidx.compose.runtime.*

data class MailScreenRendering(
    val list: InboxRendering,
    val detail: ContentRendering?,
)


@Composable
fun mailScreenRendering(): MailScreenRendering {
    var selectedMailId: Int? by remember { mutableStateOf(null) }

    return MailScreenRendering(
        list = inboxRendering(selectedMailId) {
            selectedMailId = it
        },
        detail = selectedMailId?.let { contentRendering(it) }
    )
}
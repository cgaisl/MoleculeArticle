package at.cgaisl.moleculearticle.common

import androidx.compose.runtime.*
import at.cgaisl.moleculearticle.common.data.Mail
import at.cgaisl.moleculearticle.common.data.MailRepository

data class InboxItemRendering(
    val selected: Boolean,
    val subject: String,
    val sender: String,
    val onClick: () -> Unit
)

data class InboxRendering(
    val InboxItemRenderings: List<InboxItemRendering>,
)


@Composable
fun inboxRendering(
    selectedMailId: Int?,
    onSelectMail: (Int) -> Unit,
): InboxRendering {
    var mails: List<Mail> by remember { mutableStateOf(emptyList()) }

    LaunchedEffect(Unit) {
        mails = MailRepository.getAllMail()
    }

    return InboxRendering(
        InboxItemRenderings = mails.map { mail ->
            mail.toInboxItemRendering(
                selected = mail.id == selectedMailId
            ) {
                onSelectMail(mail.id)
            }
        }
    )
}

fun Mail.toInboxItemRendering(selected: Boolean, onClick: () -> Unit) = InboxItemRendering(
    subject = subject,
    sender = sender,
    onClick = onClick,
    selected = selected
)

package at.cgaisl.moleculearticle.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import at.cgaisl.moleculearticle.common.ContentRendering
import at.cgaisl.moleculearticle.common.InboxItemRendering
import at.cgaisl.moleculearticle.common.InboxRendering
import at.cgaisl.moleculearticle.android.statemanagement.MailViewModel

@Composable
fun MailScreen() {
    val viewModel: MailViewModel = viewModel()
    val renderings by viewModel.renderings.collectAsState()

    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MailList(renderings.list)
        renderings.detail?.let { MailDetail(it) }
    }
}

@Composable
private fun MailList(rendering: InboxRendering) {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
    ) {
        rendering.InboxItemRenderings.forEach {
            MailListItem(it)
        }
    }
}

@Composable
private fun MailListItem(rendering: InboxItemRendering) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (rendering.selected) Color.LightGray else Color.White
            )
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(8.dp)
            .clickable { rendering.onClick() },
    ) {
        Column {
            Text(
                rendering.subject,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
            Text(rendering.sender)
        }
    }
}

@Composable
private fun MailDetail(rendering: ContentRendering) {
    when (rendering) {
        is ContentRendering.Loaded -> {
            Column(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(8.dp)
            ) {
                Text(
                    "subject: ${rendering.subject}",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                )
                Text(
                    "from: ${rendering.sender}",
                    fontStyle = FontStyle.Italic,
                )
                Text(rendering.body)
            }
        }

        ContentRendering.Loading ->  {
            CircularProgressIndicator()
        }
    }
}
import app.cash.molecule.RecompositionMode
import app.cash.molecule.moleculeFlow
import app.cash.turbine.test
import at.cgaisl.moleculearticle.common.ContentRendering
import at.cgaisl.moleculearticle.common.contentRendering
import at.cgaisl.moleculearticle.common.data.MailRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ContentRenderingTest {
    @Test
    fun contentRenderingTest() = runTest {
        moleculeFlow(RecompositionMode.Immediate) {
            contentRendering(0)
        }.test {
            val mail = MailRepository.loadMail(0)
            val expectedRendering = ContentRendering.Loaded(
                subject = mail.subject,
                sender = mail.sender,
                body = mail.body,
            )

            assertEquals(ContentRendering.Loading, awaitItem())
            assertEquals(expectedRendering, awaitItem())

            cancel()
        }
    }
}
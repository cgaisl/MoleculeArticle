package at.cgaisl.moleculearticle.android.statemanagement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import at.cgaisl.moleculearticle.common.mailScreenRendering


class MailViewModel : ViewModel() {
    val renderings = viewModelScope.launchMolecule(mode = RecompositionMode.Immediate) {
        mailScreenRendering()
    }
}



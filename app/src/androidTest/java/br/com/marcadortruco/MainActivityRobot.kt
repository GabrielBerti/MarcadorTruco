package br.com.marcadortruco

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers
import org.hamcrest.core.AllOf.allOf

open class MainActivityRobot {

    internal fun clicaBotaoMaisUmNos(qtdCliques: Int) {

        for (i in 1..qtdCliques) {
            onView(
                allOf(
                    withId(R.id.floating_action_button_mais_um_nos),
                    isDisplayed()
                )
            ).perform(click())
        }
    }

    internal fun clicaBotaoMaisUmEles(qtdCliques: Int) {

        for (i in 1..qtdCliques) {
            onView(
                allOf(
                    withId(R.id.floating_action_button_mais_um_eles),
                    isDisplayed()
                )
            ).perform(click())
        }
    }

    internal fun clicaBotaoMenosUmNos(qtdCliques: Int) {
        for (i in 1..qtdCliques) {
            onView(
                allOf(
                    withId(R.id.floating_action_button_menos_um_nos),
                    isDisplayed()
                )
            ).perform(click())
        }
    }

    internal fun clicaBotaoMenosUmEles(qtdCliques: Int) {
        for (i in 1..qtdCliques) {
            onView(
                allOf(
                    withId(R.id.floating_action_button_menos_um_eles),
                    isDisplayed()
                )
            ).perform(click())
        }
    }

    internal fun comparaTextViewNos(texto: String) {
        onView(
            allOf(
                withText(texto),
                withId(R.id.textViewNos)
            )
        ).check(matches(isDisplayed()))
    }

    internal fun comparaTextViewEles(texto: String) {
        onView(
            allOf(
                withText(texto),
                withId(R.id.textViewEles)
            )
        ).check(matches(isDisplayed()))
    }

    internal fun comparaPlacarNos(texto: String) {
        onView(
            allOf(
                withText(texto),
                withId(R.id.placar_nos)
            )
        ).check(matches(isDisplayed()))
    }

    internal fun comparaPlacarEles(texto: String) {
        onView(
            allOf(
                withText(texto),
                withId(R.id.placar_eles)
            )
        ).check(matches(isDisplayed()))
    }

    internal fun comparaVitoriaNos(texto: String) {
        onView(
            allOf(
                withText(CoreMatchers.endsWith(texto)),
                withId(R.id.vitoriasNos)
            )
        ).check(matches(isDisplayed()))
    }

    internal fun comparaVitoriaEles(texto: String) {
        onView(
            allOf(
                withText(CoreMatchers.endsWith(texto)),
                withId(R.id.vitorias_eles)
            )
        ).check(matches(isDisplayed()))
    }

    internal fun cliqueTextViewNos() {
        onView(
            allOf(
                withId(R.id.textViewNos),
                isDisplayed()
            )
        ).perform(click())
    }

    internal fun cliqueTextViewEles() {
        onView(
            allOf(
                withId(R.id.textViewEles),
                isDisplayed()
            )
        ).perform(click())
    }

    internal fun procuraViewComHint(hintTexto: String, texto: String) {
        onView(withHint(hintTexto))
            .perform(typeText(texto));
    }

    internal fun procuraBotaoDialogComTexto(texto: String) {
        onView(withText(texto))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
            .perform(click());
    }

    internal fun cliqueBotaoReiniciarPalrtida() {
        onView(
            allOf(
                withId(R.id.floating_action_button_reiniciar_partida),
                isDisplayed()
            )
        ).perform(click())
    }
}
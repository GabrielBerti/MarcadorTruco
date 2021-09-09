package br.com.marcadortruco

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import br.com.marcadortruco.ui.activity.MainActivity
import org.junit.Before
import org.junit.Test

class MainActivityTest() : MainActivityRobot() {

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun deve_AparecerTextViewComNomeDosTimes_QuandoAbrirTela() {
        onView(withId(R.id.textViewNos))
            .check(ViewAssertions.matches(withText("Nós")))

        onView(withId(R.id.textViewEles))
            .check(ViewAssertions.matches(withText("Eles")))
    }

    @Test
    fun deve_AdicionarCincoPontosProTimeNos_QuandoClicarCincoVezesNoBotaoMaisUm() {
        clicaBotaoMaisUmNos(5)
        comparaPlacarNos("5")
    }

    @Test
    fun deve_AdicionarSetePontosProTimeEles_QuandoClicarCincoVezesNoBotaoMaisUm() {
        clicaBotaoMaisUmEles(7)
        comparaPlacarEles("7")
    }

    @Test
    fun deve_SubtrairDoisPontosProTimeNos_QuandoClicarDuasVezesNoBotaoMenosUm() {
        clicaBotaoMaisUmNos(5)
        clicaBotaoMenosUmNos(2)
        comparaPlacarNos("3")
    }

    @Test
    fun deve_SubtrairTresPontosProTimeEles_QuandoClicarTresVezesNoBotaoMenosUm() {
        clicaBotaoMaisUmEles(7)
        clicaBotaoMenosUmEles(3)
        comparaPlacarEles("4")
    }

    @Test
    fun deve_AdicionarUmaVitoriasProTimeNos_QuandoClicarDozeVezesNoBotaoMaisUm() {
        clicaBotaoMaisUmNos(12)
        comparaVitoriaNos("1")
    }

    @Test
    fun deve_AdicionarUmaVitoriasProTimeEles_QuandoClicarDozeVezesNoBotaoMaisUm() {
        clicaBotaoMaisUmEles(12)
        comparaVitoriaEles("1")
    }

    @Test
    fun deve_ZerarPlacares_QuandoClicarNoBotaoDeReiniciarPartida() {
        clicaBotaoMaisUmNos(1)
        clicaBotaoMaisUmEles(1)
        cliqueBotaoReiniciarPalrtida()
        procuraBotaoDialogComTexto("SIM")
        comparaPlacarNos("0")
        comparaPlacarEles("0")
    }

    @Test
    fun deve_AtualizarNomePrimeiroTime_QuandoClicarNoNomeEAlterarNomeDoPrimeiroTime() {
        cliqueTextViewNos()
        procuraViewComHint(DIGITE_NOME_DO_SEU_TIME, NOME_TIME_1)
        procuraBotaoDialogComTexto(OK)
        comparaTextViewNos(NOME_TIME_1)
    }

    @Test
    fun deve_AtualizarNomeSegundoTime_QuandoClicarNoNomeEAlterarNomeDoSegundoTime() {
        cliqueTextViewEles()
        procuraViewComHint(DIGITE_NOME_DO_SEU_TIME, NOME_TIME_2)
        procuraBotaoDialogComTexto(OK)
        comparaTextViewEles(NOME_TIME_2)
    }

    companion object {
        private val DIGITE_NOME_DO_SEU_TIME: String = "Digite o nome do seu time..."
        private val OK: String = "OK"
        private val NOME_TIME_1: String = "Nome Time 1"
        private val NOME_TIME_2: String = "Nome Time 2"
    }
}
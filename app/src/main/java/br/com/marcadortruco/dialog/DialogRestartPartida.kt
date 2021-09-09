package br.com.marcadortruco.dialog

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.marcadortruco.R

class DialogRestartPartida(val context: Context, val view: View): AppCompatActivity() {

    private var alerta: AlertDialog? = null
    var VITORIA: String = "Vitória(s): "

    fun mostraDialogRestartPartida2() {
        //Cria o gerador do AlertDialog
        val builder = AlertDialog.Builder(context)
        //define o titulo
        builder.setTitle("Reiniciar")
        //define a mensagem
        builder.setMessage("Recomeçar o jogo ?")
        //define um botão como positivo
        builder.setPositiveButton(
            "Sim"
        ) { arg0, arg1 ->
            configuraBotaoRestartGame2()
        }
        //define um botão como negativo.
        builder.setNegativeButton(
            "Não"
        ) { arg0, arg1 ->
            null
        }
        //cria o AlertDialog
        alerta = builder.create()
        //Exibe
        alerta!!.show()
    }

    private fun configuraBotaoRestartGame2(){
        val buttonRestartPlay = findViewById<View>(R.id.floating_action_button_reiniciar_partida)
        buttonRestartPlay.setOnClickListener{ view ->
            val placarNos = findViewById<View>(R.id.placar_nos) as TextView
            val vitoriaNos = findViewById<View>(R.id.vitoriasNos) as TextView

            val placarEles = findViewById<View>(R.id.placar_eles) as TextView
            val vitoriaEles = findViewById<View>(R.id.vitorias_eles) as TextView

            placarNos.setText("0")
            vitoriaNos.setText("$VITORIA 0")
            placarEles.setText("0")
            vitoriaEles.setText("$VITORIA 0")

        }
    }

}
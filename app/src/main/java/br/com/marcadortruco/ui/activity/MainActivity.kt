package br.com.marcadortruco.ui.activity

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.marcadortruco.ui.viewModel.MainViewModel
import br.com.marcadortruco.R


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private val NOME_DO_TIME_EXCEDEU_LIMITE_DE_CARACTERES = R.string.nome_time_excedeu_limite_caracteres.toString()
    private var alerta: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        setObervables()
        mainViewModel.inicializaPlacar()
        mainViewModel.inicializaVitorias()
        setListeners()
    }

    private fun setObervables() {
        configuraPlacares()
        configuraVitorias()
        configuraNomeDosTimes()
    }

    private fun configuraNomeDosTimes() {
        val lblNos = findViewById<View>(R.id.textViewNos)
        lblNos.setOnClickListener() {
            showDialogAlteraNomeTime(R.string.nos.toString())
        }

        mainViewModel.nomeTime1.observe(this, Observer {
            val lblNomeTimeNos = findViewById<TextView>(R.id.textViewNos)
            lblNomeTimeNos.setText(mainViewModel.nomeTime1.value)
        })

        val lblEles = findViewById<View>(R.id.textViewEles)
        lblEles.setOnClickListener() {
            showDialogAlteraNomeTime(R.string.eles.toString())
        }

        mainViewModel.nomeTime2.observe(this, Observer {
            val lblNomeTimeEles = findViewById<TextView>(R.id.textViewEles)
            lblNomeTimeEles.setText(mainViewModel.nomeTime2.value)
        })
    }

    private fun configuraVitorias() {
        mainViewModel.vitoriaNos.observe(this, Observer {
            val vitoriaNos = findViewById<View>(R.id.vitoriasNos) as TextView
            vitoriaNos.setText(mainViewModel.vitoriaNos.value)
        })

        mainViewModel.vitoriaEles.observe(this, Observer {
            val vitoriaEles = findViewById<View>(R.id.vitorias_eles) as TextView
            vitoriaEles.setText(mainViewModel.vitoriaEles.value)
        })
    }

    private fun configuraPlacares() {
        mainViewModel.placarNos.observe(this, Observer {
            val placarNos = findViewById<View>(R.id.placar_nos) as TextView
            placarNos.setText(mainViewModel.placarNos.value)
        })

        mainViewModel.placarEles.observe(this, Observer {
            val placarEles = findViewById<View>(R.id.placar_eles) as TextView
            placarEles.setText(mainViewModel.placarEles.value)
        })
    }

    private fun setListeners() {
        configuraBotaoMaisUmNos()
        configuraBotaoMaisUmEles()
        configuraBotaoMenosUmNos()
        configuraBotaoMenosUmEles()
        configuraBotaoRestartGame()
    }

    fun showDialogAlteraNomeTime(nosOuEles: String){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Nome do Time")

        val input = EditText(this)
        input.setHint("Digite o nome do seu time...")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            if(nosOuEles == R.string.nos.toString()) {
                if(input.text.length > 14){
                    Toast.makeText(this, NOME_DO_TIME_EXCEDEU_LIMITE_DE_CARACTERES, LENGTH_LONG).show()
                } else {
                    mainViewModel.alteraNomeTime1(input.text.toString())
                }

            } else {
                if(input.text.length > 14){
                    Toast.makeText(this, NOME_DO_TIME_EXCEDEU_LIMITE_DE_CARACTERES, LENGTH_LONG).show()
                } else {
                    mainViewModel.alteraNomeTime2(input.text.toString())
                }
            }

        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }

    fun mostraDialogRestartPartida() {
        //Cria o gerador do AlertDialog
        val builder = AlertDialog.Builder(this)
        //define o titulo
        builder.setTitle("Reiniciar")
        //define a mensagem
        builder.setMessage("Recomeçar o jogo ?")
        //define um botão como positivo
        builder.setPositiveButton(
            "Sim"
        ) { _, _->
            mainViewModel.zeraPlacar()
            mainViewModel.zeraVitorias()
        }
        //define um botão como negativo.
        builder.setNegativeButton(
            "Não"
        ) { _, _ ->
            null
        }
        //cria o AlertDialog
        alerta = builder.create()
        //Exibe
        alerta!!.show()
    }

    private fun configuraBotaoRestartGame(){
        val buttonRestartGame = findViewById<View>(R.id.floating_action_button_reiniciar_partida)
        buttonRestartGame.setOnClickListener() {
            mostraDialogRestartPartida()
        }
    }

    private fun configuraBotaoMaisUmNos() {
        val buttonMaisUmNos = findViewById<View>(R.id.floating_action_button_mais_um_nos)
        buttonMaisUmNos.setOnClickListener { view ->
            if (mainViewModel.placarNos.value?.toInt() == 11) {
                val lblNos = findViewById<TextView>(R.id.textViewNos).text

                mainViewModel.somaMaisUmaVitoriaNos()
                mainViewModel.zeraPlacar()

                Toast.makeText(this, "Vencedor: " + lblNos + " ! :D", Toast.LENGTH_SHORT).show()

            } else {
                mainViewModel.somaMaisUmNos()
            }
        }
    }

    private fun configuraBotaoMaisUmEles() {
        val buttonMaisUmEles = findViewById<View>(R.id.floating_action_button_mais_um_eles)
        buttonMaisUmEles.setOnClickListener {
            if (mainViewModel.placarEles.value?.toInt() == 11) {
                val lblEles = findViewById<TextView>(R.id.textViewEles).text

                mainViewModel.somaMaisUmaVitoriaEles()
                mainViewModel.zeraPlacar()

                Toast.makeText(this, "Vencedor: " + lblEles + " ! :(((", Toast.LENGTH_SHORT).show()
            } else {
                mainViewModel.somaMaisUmEles()
            }
        }
    }

    private fun configuraBotaoMenosUmNos() {
        val buttonMenosUmNos = findViewById<View>(R.id.floating_action_button_menos_um_nos)
        buttonMenosUmNos.setOnClickListener {
            if (mainViewModel.placarNos.value?.toInt()!! >= 1) {
                mainViewModel.menosUmNos()
            }
        }
    }

    private fun configuraBotaoMenosUmEles() {
        val buttonMenosUmEles = findViewById<View>(R.id.floating_action_button_menos_um_eles)
        buttonMenosUmEles.setOnClickListener {
            if (mainViewModel.placarEles.value?.toInt()!! >= 1) {
                mainViewModel.menosUmEles()
            }
        }
    }
}
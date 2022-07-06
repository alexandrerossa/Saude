package pt.ipg.saude

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.net.Uri
import android.view.MenuItem

import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class EliminaDoutorFragment : Fragment(){

    private lateinit var textViewDoutor: TextView
    private lateinit var textViewDataNascimento: TextView
    private lateinit var textViewEspecialidade: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_elimina_doutor
        return inflater.inflate(R.layout.fragment_elimina_doutor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewDoutor = view.findViewById(R.id.textViewDoutorEliminaNome)
        textViewDataNascimento = view.findViewById(R.id.textViewDoutorEliminaDataNascimento)
        textViewEspecialidade = view.findViewById(R.id.textViewDoutorEliminaEspecialidade)

        val doutor = DadosApp.doutorSelecionado!!
        textViewDoutor.setText(doutor.nome_doutor)
        textViewDataNascimento.setText(doutor.dataNascimento)
        textViewEspecialidade.setText(doutor.especialidade)
    }

    fun navegaListaDoutor() {
        findNavController().navigate(R.id.action_eliminaDoutorFragment_to_ListaDoutorFragment)
    }

    fun elimina() {
        val uriDoutor = Uri.withAppendedPath(
            ContentProviderSaude.TABELA_DOUTOR_PATH,
            DadosApp.doutorSelecionado!!.id.toString()
        )

        val registos = activity?.contentResolver?.delete(
            uriDoutor,
            null,
            null
        )

        if (registos != 1) {
            Toast.makeText(
                requireContext(),
                R.string.erro_eliminar_doutor,
                Toast.LENGTH_LONG
            ).show()
            return
        }

        Toast.makeText(
            requireContext(),
            R.string.doutor_eliminado_sucesso,
            Toast.LENGTH_LONG
        ).show()
        navegaListaDoutor()
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_confirma_eliminar_doutor -> elimina()
            R.id.action_cancelar_eliminar_doutor -> navegaListaDoutor()
            else -> return false
        }

        return true
    }



}
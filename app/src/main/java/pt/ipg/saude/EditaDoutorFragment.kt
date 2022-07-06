package pt.ipg.saude

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.database.Cursor
import android.net.Uri
import android.widget.EditText
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController

    class EditaDoutorFragment : Fragment() {

    private lateinit var editTextNome: EditText
    private lateinit var editTextDataNascimento: EditText
    private lateinit var editTextEspecialidade: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
            DadosApp.fragment = this
            (activity as MainActivity).menuAtual = R.menu.menu_edita_doutor

            return inflater.inflate(R.layout.fragment_edita_doutor, container, false)

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextNome = view.findViewById(R.id.editTextDoutorEditaNome)
        editTextDataNascimento = view.findViewById(R.id.editTextDoutorEditaDataNascimento)
        editTextEspecialidade = view.findViewById(R.id.editTextDoutorEditarEspecialidade)

        editTextNome.setText(DadosApp.doutorSelecionado!!.nome_doutor)
        editTextDataNascimento.setText(DadosApp.doutorSelecionado!!.dataNascimento)
        editTextEspecialidade.setText(DadosApp.doutorSelecionado!!.especialidade)
    }

    fun navegaListaDoutor() {
        findNavController().navigate(R.id.action_cancelar_edita_DoutorFragment_to_ListaDoutorFragment)
    }

    fun guardar() {
        val nome = editTextNome.text.toString()
        if (nome.isEmpty()) {
            editTextNome.setError(getString(R.string.preencha_nome))
            editTextNome.requestFocus()
            return
        }

        val dataNascimento = editTextDataNascimento.text.toString()
        if (dataNascimento.isEmpty()) {
            editTextDataNascimento.setError(getString(R.string.preencha_dataNascimento))
            editTextDataNascimento.requestFocus()
            return
        }

        val especialidade = editTextEspecialidade.text.toString()
        if (especialidade.isEmpty()) {
            editTextEspecialidade.setError(getString(R.string.preencha_especialidade))
            editTextEspecialidade.requestFocus()
            return
        }



        val doutor = DadosApp.doutorSelecionado!!
        doutor.nome_doutor = nome
        doutor.dataNascimento = dataNascimento
        doutor.especialidade = especialidade

        val uriSaude= Uri.withAppendedPath(
            ContentProviderSaude.TABELA_DOUTOR_PATH,
            doutor.id.toString()
        )

        val registos = activity?.contentResolver?.update(
            uriSaude,
            doutor.toContentValues(),
            null,
            null
        )

        if (registos != 1) {
            Toast.makeText(
                requireContext(),
                R.string.erro_alterar_doutor,
                Toast.LENGTH_LONG
            ).show()
            return
        }

        Toast.makeText(
            requireContext(),
            R.string.doutor_guardado_sucesso,
            Toast.LENGTH_LONG
        ).show()
        navegaListaDoutor()
    }



    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar_novo_doutor -> guardar()
            R.id.action_cancelar_novo_doutor -> navegaListaDoutor()
            else -> return false
        }

        return true
    }

}
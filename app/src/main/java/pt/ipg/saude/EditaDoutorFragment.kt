package pt.ipg.saude

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navArgument
import android.database.Cursor
import android.net.Uri
import android.widget.EditText
import android.widget.SimpleCursorAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

    class EditaDoutorFragment : Fragment(),LoaderManager.LoaderCallbacks<Cursor> {

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

        editTextNome = view.findViewById(R.id.editTextDoutorNome)
        editTextDataNascimento = view.findViewById(R.id.editTextDoutorDataNascimento)
        editTextEspecialidade = view.findViewById(R.id.editTextDoutorEspecialidade)

        LoaderManager.getInstance(this)
            .initLoader(ID_LOADER_MANAGER_CATEGORIAS, null, this)


        editTextNome.setText(DadosApp.doutorSelecionado!!.nome_doutor)
        editTextDataNascimento.setText(DadosApp.doutorSelecionado!!.dataNascimento)
        editTextEspecialidade.setText(DadosApp.doutorSelecionado!!.especialidade)
    }

    fun navegaListaDoutor() {
        findNavController().navigate(R.id.action_editaDoutorFragment_to_listaDoutoresFragment)
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

        val uriCovid = Uri.withAppendedPath(
            ContentProviderSaude.TABELA_DOUTOR_PATH,
            doutor.id.toString()
        )

        val registos = activity?.contentResolver?.update(
            uriCovid,
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

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            requireContext(),
            ContentProviderSaude.TABELA_DOUTOR_PATH,
            TabelaBDConsultas.TODAS_COLUNAS,
            null, null,
            TabelaBDDoutores.CAMPO_NOME_DOUTOR,

            )
    }

        override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
            TODO("Not yet implemented")
        }

        override fun onLoaderReset(loader: Loader<Cursor>) {
            TODO("Not yet implemented")
        }


    companion object {
        const val ID_LOADER_MANAGER_CATEGORIAS = 0
    }



}
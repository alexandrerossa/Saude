package pt.ipg.saude

import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TestBaseDados {
    private fun getAppContext() = InstrumentationRegistry.getInstrumentation().targetContext
    private fun getBdCovidOpenHelper() = BDSaudeOpenHelper(getAppContext())

    private fun insereDoutores(tabela: TabelaDoutores, doutor: Doutor): Long {
        val id = tabela.insert(doutor.toContentValues())
        assertNotEquals(-1, id)

        return id
    }

    private fun getDoutorBaseDados(tabela: TabelaDoutores, id: Long): Doutor {
        val cursor = tabela.query(
            TabelaDoutores.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf(id.toString()),
            null, null, null
        )

        assertNotNull(cursor)
        assert(cursor!!.moveToNext())

        return Doutor.fromCursor(cursor)
    }

    private fun getPacienteBaseDados(tabela: TabelaPacientes, id: Long): Paciente {
        val cursor = tabela.query(
            TabelaPacientes.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf(id.toString()),
            null, null, null
        )

        assertNotNull(cursor)
        assert(cursor!!.moveToNext())

        return Paciente.fromCursor(cursor)
    }

    @Before
    fun apagaBaseDados(){
        getAppContext().deleteDatabase(BDSaudeOpenHelper.NOME_BASE_DADOS)
    }

    @Test
    fun consegueAbrirBaseDados(){
        val dbOpenHelper = BDSaudeOpenHelper(getAppContext())
        val db = dbOpenHelper.readableDatabase
        assert(db.isOpen)
        db.close()
    }

    @Test
    fun consegueInserirDoutores() {
        val db = getBdCovidOpenHelper().writableDatabase
        val tabelaDoutores = TabelaDoutores(db)

        val doutor = Doutor(nome = "Francisco", dataNascimento = "12-12-2001", especialidade = "pediatria")
        doutor.id = insereDoutores(tabelaDoutores, doutor)

        assertEquals(doutor, getDoutorBaseDados(tabelaDoutores, doutor.id))

        db.close()
    }


}
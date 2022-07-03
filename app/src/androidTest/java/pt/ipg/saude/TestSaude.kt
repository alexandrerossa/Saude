package pt.ipg.saude

import android.database.sqlite.SQLiteDatabase
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
    private fun appContext() = InstrumentationRegistry.getInstrumentation().targetContext

    private fun getWritableDatabase(): SQLiteDatabase {
        val openHelper = BDSaudeOpenHelper(appContext())
        return openHelper.writableDatabase
    }

    private fun insereDoutor(db: SQLiteDatabase, doutor: Doutor) {
        doutor.id = TabelaBDDoutores(db).insert(doutor.toContentValues())
        assertNotEquals(-1, doutor.id)
    }

    private fun inserePaciente(db: SQLiteDatabase, paciente: Paciente) {
        paciente.id = TabelaBDPacientes(db).insert(paciente.toContentValues())
        assertNotEquals(-1, paciente.id)
    }

    private fun insereConsulta(db: SQLiteDatabase, consulta: Consultas ){
        consulta.id = TabelaBDConsultas(db).insert(consulta.toContentValues())
        assertNotEquals(-1, consulta.id)
    }

    @Before
    fun apagaBaseDados(){
        appContext().deleteDatabase(BDSaudeOpenHelper.NOME)
    }

    @Test
    fun consegueAbrirBaseDados(){
        val dbOpenHelper = BDSaudeOpenHelper(appContext())
        val db = dbOpenHelper.readableDatabase
        assert(db.isOpen)
        db.close()
    }

    @Test
    fun consegueInserirDoutor() {
        val db = getWritableDatabase()

        insereDoutor(db,Doutor("João","01-10-1999","Pediatria"))

        db.close()
    }

    @Test
    fun consegueInserirPaciente() {
        val db = getWritableDatabase()

        inserePaciente(db, Paciente("Francisco","12-12-2001","Masculino","Pediatria","João"))

        db.close()
    }

    @Test
    fun consegueInserirConsulta() {
        val db = getWritableDatabase()

        val doutor = Doutor("João","01-10-1999","Pediatria")
        insereDoutor(db, doutor)

        val consulta = Consultas("Otorrinolaringología", 1, 5)
        insereConsulta(db, consulta)

        db.close()
    }

    @Test
    fun consegueAlterarConsulta() {
        val db = getWritableDatabase()

        val consulta = Consultas("Teste",0,0)
        insereConsulta(db, consulta)

        consulta.nome = "Pediatria"
        consulta.macasocupadas = 1
        consulta.macasdisponiveis = 2

        val registosAlterados = TabelaBDConsultas(db).update(
            consulta.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${consulta.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueAlterarPaciente() {
        val db = getWritableDatabase()

        val paciente = Paciente("Teste","","","","")
        inserePaciente(db, paciente)

        paciente.nome = "João"
        paciente.dataNascimento = "01-04-2000"
        paciente.sexo = "M"
        paciente.tipo_consulta = "Pediatria"
        paciente.doutor_responsavel = "Pediatria"

        val registosAlterados = TabelaBDPacientes(db).update(
            paciente.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${paciente.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueAlterarDoutor() {
        val db = getWritableDatabase()

        val doutor = Doutor("Teste","","")
        insereDoutor(db, doutor)

        doutor.nome_doutor = "Pedro"
        doutor.dataNascimento = "11-07-1999"
        doutor.especialidade = "Pediatria"

        val registosAlterados = TabelaBDDoutores(db).update(
            doutor.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${doutor.id}"))

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueEliminarDoutor() {
        val db = getWritableDatabase()

        val doutor = Doutor("Teste","","")
        insereDoutor(db, doutor)

        val registosEliminados = TabelaBDDoutores(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${doutor.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueEliminarPaciente() {
        val db = getWritableDatabase()

        val paciente = Paciente("Teste","","","","")
        inserePaciente(db, paciente)

        val registosEliminados = TabelaBDPacientes(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${paciente.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueEliminarConsulta() {
        val db = getWritableDatabase()

        val consulta = Consultas("Teste",0,0)
        insereConsulta(db, consulta)

        val registosEliminados = TabelaBDConsultas(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${consulta.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueLerDoutor() {
        val db = getWritableDatabase()

        val doutor = Doutor("Tiago","","")
        insereDoutor(db, doutor)

        val cursor = TabelaBDDoutores(db).query(
            TabelaBDDoutores.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf("${doutor.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val categBD = Doutor.fromCursor(cursor)

        assertEquals(doutor, categBD)
    }

    @Test
    fun consegueLerPaciente() {
        val db = getWritableDatabase()

        val paciente = Paciente("Francisco","","","","")
        inserePaciente(db, paciente)

        val cursor = TabelaBDPacientes(db).query(
            TabelaBDPacientes.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf("${paciente.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val categBD = Paciente.fromCursor(cursor)

        assertEquals(paciente, categBD)
    }

}
package pt.ipg.saude

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDPacientes(db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_NOME_PACIENTE TEXT NOT NULL, $CAMPO_DATA_DE_NASCIMENTO INTEGER NOT NULL, $CAMPO_SEXO TEXT NOT NULL, $CAMPO_TIPO_CONSULTA TEXT NOT NULL, $CAMPO_DOUTOR_RESPONSAVEL TEXT NOT NULL, FOREIGN KEY (${TabelaPacientes.CAMPO_TIPO_CONSULTA}) REFERENCES ${TabelaConsultas.CAMPO_NOME_CONSULTA}, FOREIGN KEY (${TabelaPacientes.CAMPO_DOUTOR_RESPONSAVEL}) REFERENCES ${TabelaDoutores.CAMPO_NOME_DOUTOR} )")
    }

    companion object {
        const val NOME = "pacientes"
        const val CAMPO_NOME_PACIENTE = "nome"
        const val CAMPO_DATA_DE_NASCIMENTO = "data_de_nascimento"
        const val CAMPO_SEXO = "sexo"
        const val CAMPO_TIPO_CONSULTA = "tipo_consulta"
        const val CAMPO_DOUTOR_RESPONSAVEL = "doutor_responsavel"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, CAMPO_NOME_PACIENTE, CAMPO_DATA_DE_NASCIMENTO, CAMPO_SEXO, CAMPO_TIPO_CONSULTA, CAMPO_DOUTOR_RESPONSAVEL)
    }


}
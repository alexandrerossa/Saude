package pt.ipg.saude

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaPacientes(db: SQLiteDatabase) {
    private val db: SQLiteDatabase = db

    fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ( ${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_NOME_PACIENTE TEXT NOT NULL, $CAMPO_DATA_DE_NASCIMENTO INTEGER NOT NULL, $CAMPO_SEXO TEXT NOT NULL, $CAMPO_TIPO_CONSULTA INTEGER, $CAMPO_DOUTOR_RESPONSAVEL INTEGER, FOREIGN KEY ($CAMPO_TIPO_CONSULTA) REFERENCES ${TabelaConsultas.CAMPO_NOME_CONSULTA}, FOREIGN KEY (${CAMPO_DOUTOR_RESPONSAVEL}) REFERENCES ${TabelaDoutores.CAMPO_NOME_DOUTOR} )" )
    }

    fun insert(values: ContentValues): Long {
        return db.insert(NOME_TABELA,null, values)
    }

    fun update(values: ContentValues, whereClause: String, whereArgs: Array<String>): Int {
        return db.update(NOME_TABELA, values, whereClause, whereArgs)
    }

    fun delete(whereClause: String, whereArgs: Array<String>): Int {
        return db.delete(NOME_TABELA,whereClause, whereArgs)
    }

    fun query(
        columns: Array<String>,
        selection: String,
        selectionArgs: Array<String>,
        groupBy: String,
        having: String,
        orderBy: String
    ): Cursor? {
        return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy)
    }



    companion object{
        const val NOME_TABELA = "pacientes"
        const val CAMPO_NOME_PACIENTE = "nome"
        const val CAMPO_DATA_DE_NASCIMENTO = "data_de_nascimento"
        const val CAMPO_SEXO = "sexo"
        const val CAMPO_TIPO_CONSULTA = "tipo_consulta"
        const val CAMPO_DOUTOR_RESPONSAVEL = "doutor_responsavel"
    }
}
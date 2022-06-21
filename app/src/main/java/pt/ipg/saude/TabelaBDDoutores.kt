package pt.ipg.saude

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDDoutores(db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_NOME_DOUTOR TEXT NOT NULL, $CAMPO_DATA_DE_NASCIMENTO TEXT NOT NULL, $CAMPO_ESPECIALIDADE TEXT NOT NULL)")
    }

    companion object {
        const val NOME = "doutores"
        const val CAMPO_NOME_DOUTOR = "nome"
        const val CAMPO_DATA_DE_NASCIMENTO = "data_de_nascimento"
        const val CAMPO_ESPECIALIDADE = "especialidade"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, NOME, CAMPO_NOME_DOUTOR, CAMPO_DATA_DE_NASCIMENTO, CAMPO_ESPECIALIDADE)
    }
}
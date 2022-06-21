package pt.ipg.saude

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDConsultas(db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_NOME_CONSULTA TEXT NOT NULL, $CAMPO_MACAS_OCUPADAS INTEGER, $CAMPO_MACAS_DISPONIVEIS INTEGER)")
    }

    companion object {
        const val NOME = "consultas"
        const val CAMPO_NOME_CONSULTA = "tipo_de_consulta"
        const val CAMPO_MACAS_OCUPADAS = "num_macas_ocupadas"
        const val CAMPO_MACAS_DISPONIVEIS = "num_macas_disponiveis"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, NOME, CAMPO_NOME_CONSULTA, CAMPO_MACAS_OCUPADAS, CAMPO_MACAS_DISPONIVEIS)
    }
}
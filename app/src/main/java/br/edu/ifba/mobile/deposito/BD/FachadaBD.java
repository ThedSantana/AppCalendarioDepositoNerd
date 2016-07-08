package br.edu.ifba.mobile.deposito.BD;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bsteles on 06/07/2016.
 */
public class FachadaBD extends SQLiteOpenHelper {
    private static FachadaBD instancia = null;

    public static FachadaBD criarInstancia(Context context){
        if (instancia == null){
            instancia = new FachadaBD(context);
        }
        return instancia;
    }

    public static FachadaBD getInstancia(){
        return instancia;
    }

    private static String NOME_BANCO = "DEPOSITO";
    private static int VERSAO_BANCO = 1;

    public FachadaBD(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    private static String COMANDO_CRIACAO_TABELA_EVENTO =
            "CREATE TABLE EVENTO( CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "CATEGORIA TEXT, NOMEEVENTO TEXT, DATA TEXT);";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COMANDO_CRIACAO_TABELA_EVENTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
        // TODO Auto-generated method stub
    }

    public long inserir(Evento evento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("CATEGORIA", evento.getCategoria());
        valores.put("NOMEEVENTO", evento.getNomeEvento());
        valores.put("DATA", evento.getData());


        long codigo = db.insert("EVENTO", null, valores);
        return codigo;
    }

    public long atualizar(Evento evento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("CATEGORIA", evento.getCategoria());
        valores.put("NOMEEVENTO", evento.getNomeEvento());
        valores.put("DATA", evento.getData());

        long codigo = db.update("EVENTO", valores, "CODIGO = " + evento.getCodigoEvento(), null);
        return codigo;
    }

    public int remover(Evento evento) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete("EVENTO", "CODIGO = " + evento.getCodigoEvento(), null);
    }

    public List<Evento> listarEventos() {
        List<Evento> eventos = new ArrayList<Evento>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selecao = "SELECT CODIGO, CATEGORIA, NOMEEVENTO, DATA FROM EVENTO ORDER BY CATEGORIA, DATA";

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selecao, null);
        if(cursor != null){
            boolean temProximo = cursor.moveToFirst();
            while (temProximo){
               Evento evento = new Evento();
                evento.setCodigoEvento(cursor.getLong(cursor.getColumnIndex("CODIGO")));
                evento.setCategoria(cursor.getString(cursor.getColumnIndex("CATEGORIA")));
                evento.setNomeEvento(cursor.getString(cursor.getColumnIndex("NOMEEVENTO")));
                evento.setData(cursor.getString(cursor.getColumnIndex("DATA")));


                eventos.add(evento);

                temProximo = cursor.moveToNext();
            }
        }
        return eventos;
    }

}

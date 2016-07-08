package br.edu.ifba.mobile.deposito.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.deposito.BD.Evento;
import br.edu.ifba.mobile.deposito.BD.FachadaBD;

/**
 * Created by Bsteles on 06/07/2016.
 */

public class GravacaoEventos extends AsyncTask<Void, Void, String> {

    private Context contexto = null;
    private Evento evento = null;

    public GravacaoEventos(Context contexto, Evento evento){
        this.evento = evento;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";
        long codigo = -1;
        if(evento.getCodigoEvento() == -1){
            codigo = FachadaBD.getInstancia().inserir(evento);
        }
        else{
            codigo = FachadaBD.getInstancia().atualizar(evento);
        }
        if(codigo > 0){
            mensagem = "Evento gravado!";
        }
        else{
            mensagem = "Erro de gravação!";
        }
        return mensagem;
    }
    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
    }
}

package br.edu.ifba.mobile.deposito.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.deposito.BD.Evento;
import br.edu.ifba.mobile.deposito.BD.FachadaBD;


public class RemocaoEventos extends AsyncTask<Void, Void,String> {

    private Context contexto = null;
    private Evento evento = null;

    public RemocaoEventos(Context contexto, Evento evento){
        this.evento = evento;
        this.contexto = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        if(evento.getCodigoEvento()!=-1){
            if(FachadaBD.getInstancia().remover(evento)==0){
                mensagem="Problemas de remoção!";
            }else
                mensagem="Evento removido!";
        }else{
            mensagem="Selecione um Evento!";
        }
        return mensagem;
    }

    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto,mensagem,Toast.LENGTH_LONG).show();
    }
}

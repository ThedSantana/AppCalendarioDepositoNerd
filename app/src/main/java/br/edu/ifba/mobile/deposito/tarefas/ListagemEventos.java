package br.edu.ifba.mobile.deposito.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifba.mobile.deposito.BD.Evento;
import br.edu.ifba.mobile.deposito.BD.FachadaBD;


/**
 * Created by Bsteles on 06/07/2016.
 */
public class ListagemEventos extends AsyncTask<Void, Void, List<Evento>> {

    private Context contexto = null;
    private ListView listaEventos = null;

    public ListagemEventos(Context contexto, ListView listaEventos){
        this.contexto = contexto;
        this.listaEventos = listaEventos;
    }

    @Override
    protected List<Evento> doInBackground(Void... params) {
        List<Evento> eventos = FachadaBD.getInstancia().listarEventos();

        return eventos;
    }
    @Override
    protected void onPostExecute(List<Evento> eventos){
        if(eventos.isEmpty()) {
            Toast.makeText(contexto, "Lista vazia. Cadastre um evento", Toast.LENGTH_LONG).show();
        }
        else{
            ArrayAdapter<Evento> adaptador = new ArrayAdapter<Evento>(contexto, android.R.layout.simple_list_item_single_choice, eventos);
            listaEventos.setAdapter(adaptador);
        }
    }
}

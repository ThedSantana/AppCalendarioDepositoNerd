package br.edu.ifba.mobile.deposito.fragmentos;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.ifba.mobile.deposito.BD.Evento;
import br.edu.ifba.mobile.deposito.tarefas.ListagemEventos;
import br.edu.ifba.mobile.deposito.R;
import br.edu.ifba.mobile.deposito.tarefas.RemocaoEventos;


/**
 * Created by Bsteles on 06/07/2016.
 */
public class FragmentoListaEventos extends Fragment {

    private static FragmentoListaEventos instancia = null;

    public static FragmentoListaEventos getInstancia(){
        if (instancia == null){
            instancia = new FragmentoListaEventos();
        }
        return instancia;
    }

    private View tela = null;
    private ListView lista = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        tela = inflador.inflate(R.layout.fragmento_lista_eventos, vgrupo, false);

        preparar();

        return tela;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflador){
        super.onCreateOptionsMenu(menu, inflador);
        inflador.inflate(R.menu.menu_deposito_nerd_app, menu);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        long id = item.getItemId();
        if (id != AdapterView.INVALID_ROW_ID){
            if (id == R.id.remover_eventos){
                RemocaoEventos remocao = new RemocaoEventos(this.getContext(), this.getEventoSelecionado());
                remocao.execute();
                atualizar();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void preparar(){
        lista = (ListView) tela.findViewById(R.id.listaEventos);
        this.setHasOptionsMenu(true);
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void atualizar(){
        ListagemEventos listagem = new ListagemEventos(this.getContext(), lista);
        listagem.execute();
    }

    public Evento getEventoSelecionado(){
        Evento evento = new Evento();

        int posicao = lista.getCheckedItemPosition();
        if (posicao != ListView.INVALID_POSITION){
            evento = (Evento) lista.getItemAtPosition(posicao);
        }

        return evento;
    }
}

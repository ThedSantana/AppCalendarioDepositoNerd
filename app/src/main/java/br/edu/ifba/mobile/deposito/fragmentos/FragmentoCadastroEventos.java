package br.edu.ifba.mobile.deposito.fragmentos;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import br.edu.ifba.mobile.deposito.BD.Evento;
import br.edu.ifba.mobile.deposito.R;
import br.edu.ifba.mobile.deposito.tarefas.GravacaoEventos;


/**
 * Created by Bsteles on 06/07/2016.
 */

public class FragmentoCadastroEventos extends Fragment {

    private static FragmentoCadastroEventos instancia = null;

    public static FragmentoCadastroEventos getInstancia(){
        if (instancia==null){
            instancia=new FragmentoCadastroEventos();
        }
        return instancia;
    }

    private View tela=null;

    private EditText nomeEvento = null;
    private Spinner categorias = null;
    private DatePicker data = null;
    private Button botaoGravar = null;


    private Evento evento = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        tela=inflador.inflate(R.layout.fragmento_cadastro_eventos, vgrupo, false);

        preparar();
        return tela;
    }

    private void preparar(){
        nomeEvento = (EditText)tela.findViewById(R.id.nomeEvento);
        categorias = (Spinner)tela.findViewById(R.id.spinnerCategorias);
        data = (DatePicker) tela.findViewById(R.id.datePickerData);
        botaoGravar = (Button)tela.findViewById(R.id.botaoGravar);


        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContexto(), R.array.categorias, android.R.layout.simple_spinner_item);//verificar getcontexto
        categorias.setAdapter(adapter);

        botaoGravar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                GravacaoEventos gravacao = new GravacaoEventos(getContexto(), getEventos());
                    gravacao.execute();
                    limparCampos();

            }
        });


    }

    @TargetApi(Build.VERSION_CODES.M)
    private Context getContexto(){
        return this.getContext();
    }

    private String dateForString(){
        String dia = String.valueOf(data.getDayOfMonth());
        String mes = String.valueOf(data.getMonth());
        String ano = String.valueOf(data.getYear());

        String data = dia +"/"+ mes +"/"+ ano;

        return data;
    }

    private Evento getEventos(){ // seta valores do objeto com conteudo dos componentes
       // evento = new Evento();
        evento.setNomeEvento(nomeEvento.getText().toString());
        evento.setCategoria(categorias.getSelectedItem().toString());
        evento.setData(dateForString());
        return evento;
    }

    public void exibirEventoSelecionado(){
        evento = FragmentoListaEventos.getInstancia().getEventoSelecionado();

        if(evento.getCodigoEvento() == -1){
            limparCampos();
        } else
            carregarCampos();
    }

    private void limparCampos(){
        nomeEvento.setText("");

    }

    private void carregarCampos(){
        nomeEvento.setText(evento.getNomeEvento());
    }
}

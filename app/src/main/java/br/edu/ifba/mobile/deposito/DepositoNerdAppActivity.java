package br.edu.ifba.mobile.deposito;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import br.edu.ifba.mobile.deposito.BD.FachadaBD;
import br.edu.ifba.mobile.deposito.fragmentos.FragmentoCadastroEventos;
import br.edu.ifba.mobile.deposito.fragmentos.FragmentoInformacao;
import br.edu.ifba.mobile.deposito.fragmentos.FragmentoListaEventos;
/**
 * Created by Bsteles on 06/07/2016.
 */
public class DepositoNerdAppActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager paginador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito_nerd_app);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        paginador = (ViewPager) findViewById(R.id.container);
        paginador.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(paginador);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        paginador.addOnPageChangeListener(this);
        FachadaBD.criarInstancia(this.getApplicationContext());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position==1){ // na tela 0 nao faz nada, qnd passar pra 1 lista as tarefas
            FragmentoCadastroEventos.getInstancia().exibirEventoSelecionado();

        } else if(position == 2){ // qnd passar pra 2 exibe a tarefa selecionada na 1
            FragmentoListaEventos.getInstancia().atualizar();

        } /*else if(position == 3) {
        FragmentoCadastroEventos.getInstancia().exibirEventoSelecionado();
    }*/
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position) {
            Fragment frag = null;

            switch(position){
                case 0:
                    frag = FragmentoInformacao.getInstancia();
                    break;
                case 1:
                    frag =FragmentoCadastroEventos.getInstancia();

                    break;
                case 2:
                    frag = FragmentoListaEventos.getInstancia();

                    break;

            }

            return frag;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Informação";
                case 1:
                    return "Adicionar";
                case 2:
                    return "Eventos";

            }
            return null;
        }
    }
}

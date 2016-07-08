package br.edu.ifba.mobile.deposito.BD;

/**
 * Created by Bsteles on 06/07/2016.
 */
public class Evento {
    private long codigoEvento=-1;
    private String categoria;
    private String nomeEvento;
    private String data;

    public long getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(long codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return categoria + "    |   " +nomeEvento+"   "+data;

    }
}

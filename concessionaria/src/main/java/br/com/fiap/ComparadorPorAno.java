package br.com.fiap;

import java.util.Comparator;

public class ComparadorPorAno implements Comparator<Veiculo> {

    @Override
    public int compare(Veiculo o1, Veiculo o2) {
        return Integer.compare(o1.getAno(), o2.getAno());
    }
    
}

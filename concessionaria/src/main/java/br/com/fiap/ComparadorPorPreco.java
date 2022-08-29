package br.com.fiap;

import java.util.Comparator;

public class ComparadorPorPreco implements Comparator<Veiculo> {

    @Override
    public int compare(Veiculo o1, Veiculo o2) {
        return Double.compare(o1.getPreco(), o2.getPreco());
    }
    
}

package br.com.fiap.controller;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.VeiculoDao;
import br.com.fiap.model.Veiculo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML private TextField textFieldMarca;
    @FXML private TextField textFieldModelo;
    @FXML private TextField textFieldPreco;
    @FXML private TextField textFieldAno;
    @FXML private TextField textFieldPlaca;
    @FXML private ListView<Veiculo> listView;

    private VeiculoDao veiculoDao;

    public PrimaryController(){
        try {
            veiculoDao = new VeiculoDao();
        } catch (SQLException e) {
            mostrarAlerta("Erro de conexao com BD " + e.getMessage());
        }
    }
    
    public void salvar(){
        try {
            veiculoDao.inserir(carregarVeiculoDoFormulario());
            mostrarAlerta("Veículo cadastrado com sucesso");
            limparFormulario();
        } catch (SQLException e) {
            mostrarAlerta("Erro de SQL. " + e.getMessage());
        }
       

    }

    private void limparFormulario(){
        textFieldMarca.setText("");
        textFieldModelo.setText("");
        textFieldPreco.setText("");
        textFieldAno.setText("");
        textFieldPlaca.setText("");
    }

    private Veiculo carregarVeiculoDoFormulario(){
        //TODO tratar erro de conversão
        String marca = textFieldMarca.getText();
        String modelo = textFieldModelo.getText();
        int ano = Integer.valueOf( textFieldAno.getText() );
        double preco = Double.valueOf(textFieldPreco.getText());
        String placa = textFieldPlaca.getText();

        Veiculo veiculo = new Veiculo(marca, modelo, ano, preco, placa);
        return veiculo;
    }

    private void mostrarAlerta(String mensagem){
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setContentText(mensagem);
        alerta.show();
    }

    public void ordernarPorPreco(){
        try {
            List<Veiculo> lista = veiculoDao.listarTodos();
            lista.sort((o1, o2) -> Double.compare(o1.getPreco(), o2.getPreco()));
            atualizarLista(lista);
        } catch (SQLException e) {
            mostrarAlerta("Erro de SQL " + e.getMessage());
        }
    }

    public void ordenarPorAno(){
        try {
            List<Veiculo> lista = veiculoDao.listarTodos();
            lista.sort((o1, o2) -> Integer.compare(o1.getAno(), o2.getAno()));
            atualizarLista(lista);
        } catch (SQLException e) {
            mostrarAlerta("Erro de SQL " + e.getMessage());
        }
    }

    private void atualizarLista(List<Veiculo> lista){
        listView.getItems().clear();
        listView.getItems().addAll(lista);
    }

    public void mostrarTodos(){
        try {
            List<Veiculo> lista = veiculoDao.listarTodos();
            atualizarLista(lista);
        } catch (SQLException e) {
            mostrarAlerta("Erro de SQL " + e.getMessage());
        }
    }

    public void filtarPorMarca(String marca){
         try {
            List<Veiculo> lista = veiculoDao.buscarPorMarca(marca);
            atualizarLista(lista);
        } catch (SQLException e) {
            mostrarAlerta("Erro de SQL " + e.getMessage());
        }
    }
    
}

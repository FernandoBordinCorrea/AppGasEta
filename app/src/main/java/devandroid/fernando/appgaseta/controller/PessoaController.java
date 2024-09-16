package devandroid.fernando.appgaseta.controller;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import devandroid.fernando.appgaseta.model.Pessoa;
import devandroid.fernando.appgaseta.view.MainActivity;

public class PessoaController {

    SharedPreferences preferences;
    public static final String NOME_PREFERENCES = "pref_listavip";
    SharedPreferences.Editor listaVip;

    public PessoaController(MainActivity mainActivity){

        preferences = mainActivity.getSharedPreferences(NOME_PREFERENCES,0);
        listaVip = preferences.edit();
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }



    public void salvar(Pessoa pessoa) {

        listaVip.putString("PrimeiroNome",pessoa.getPrimeiroNome());
        listaVip.putString("Sobrenome",pessoa.getSobrenome());
        listaVip.putString("Curso",pessoa.getCursoDesejado());
        listaVip.putString("Telefone",pessoa.getTelefoneContato());
        listaVip.apply();

    }

    public Pessoa buscar(Pessoa pessoa){

        pessoa.setPrimeiroNome(preferences.getString("PrimeiroNome",""));
        pessoa.setSobrenome(preferences.getString("Sobrenome",""));
        pessoa.setCursoDesejado(preferences.getString("Curso",""));
        pessoa.setTelefoneContato(preferences.getString("Telefone",""));

        return pessoa;

    }

    public void limpar(){

        listaVip.clear();
        listaVip.apply();

    }
}

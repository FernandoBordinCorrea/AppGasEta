package devandroid.fernando.appgaseta.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import devandroid.fernando.appgaseta.R;
import devandroid.fernando.appgaseta.apoio.UtilGasEta;
import devandroid.fernando.appgaseta.controller.CombustivelController;
import devandroid.fernando.appgaseta.model.Combustivel;

public class GasEtaActivity extends AppCompatActivity {

    EditText editGasolina;
    EditText editEtanol;

    TextView txtResultado;


    Button btnCalcular;
    Button btnLimpar;
    Button btnSalvar;
    Button btnFinalizar;

    double precoGasolina;
    double precoEtanol;

    String recomendacao;

    Combustivel combustivelGas;
    Combustivel combustivelEta;

    CombustivelController controller;

    List<Combustivel> dados;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaseta);

        controller = new CombustivelController(GasEtaActivity.this);

        dados = controller.getListaDeDados();

        /*Combustivel objAlteracao = dados.get(1);
        objAlteracao.setNomeDoCombustivel("TESTE");
        objAlteracao.setPrecoDoCombustivel(5.97);
        objAlteracao.setRecomendacao("TESTE!!!!!!!!");
        controller.alterar(objAlteracao);

        controller.deletar(2);*/


        editGasolina = findViewById(R.id.editGasolina);
        editEtanol = findViewById(R.id.editEtanol);

        txtResultado = findViewById(R.id.txtResultado);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnFinalizar = findViewById(R.id.btnFinalizar);


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isDadosOk = true;

                if (TextUtils.isEmpty(editGasolina.getText())) {
                    editGasolina.setError("*Obrigatorio");
                    editGasolina.requestFocus();
                    isDadosOk = false;
                }

                if (TextUtils.isEmpty(editEtanol.getText())) {
                    editEtanol.setError("*Obrigatorio");
                    editEtanol.requestFocus();
                    isDadosOk = false;

                }

                if (isDadosOk) {
                    precoGasolina = Double.parseDouble(editGasolina.getText().toString());
                    precoEtanol = Double.parseDouble(editEtanol.getText().toString());

                    recomendacao = UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol);

                    txtResultado.setText(recomendacao);

                    btnSalvar.setEnabled(true);

                } else {
                    Toast.makeText(GasEtaActivity.this,
                            "Preencha os campos obrigatorios !!!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editGasolina.setText("");
                editEtanol.setText("");

                btnSalvar.setEnabled(false);

                controller.limpar();

            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                combustivelGas = new Combustivel();
                combustivelEta = new Combustivel();


                combustivelGas.setNomeDoCombustivel("Gasolina");
                combustivelGas.setPrecoDoCombustivel(precoGasolina);

                combustivelEta.setNomeDoCombustivel("Etanol");
                combustivelEta.setPrecoDoCombustivel(precoEtanol);

                combustivelGas.setRecomendacao(UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol));
                combustivelEta.setRecomendacao(UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol));

                controller.salvar(combustivelGas);
                controller.salvar(combustivelEta);


            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(GasEtaActivity.this, "Volte Sempre", Toast.LENGTH_LONG).show();
                finish();

            }
        });


    }
}

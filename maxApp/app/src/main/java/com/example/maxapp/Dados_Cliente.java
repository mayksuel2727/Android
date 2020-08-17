package com.example.maxapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dados_Cliente extends AppCompatActivity {
    private String id, codigo, rezSocial, nomeFant, cnpj, ramoAtvi,
            endereco, nomeCliente, telefone, celular, conjuge, tipo,
            time, email, datNasc, datNascConj, data, hora, status;

    private TextView txtCodigo, txtRazaoSoc, txtNomeFant, txtCNPJ,
            txtRamoAtiv, txtEndereco, txtTelef, txtNomeCli, txtCel,
            txtConj, txtTipo, txtTime, txtEmail, txtDatNasc,
            txtDataNascCon, txtTime2, txtDateAtual, txtHoraAtual,txtStatus ;

    private Button btnVerificaStatus, btnDados, btnHisPedidos, btnAlvaras, btnFechar;
    private RelativeLayout mensagem;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Date dataHoraAtual = new Date();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dados__cliente);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Dados do cliente");
        inicializarComponetes();
        buscarDados(); // buscar os dados no fireBase
        mensagem.setVisibility(View.INVISIBLE);
        eventoClick();

    }
    //inicialização dos componentes
    private void inicializarComponetes() {
        txtCodigo = (TextView) findViewById(R.id.txtCodigo);
        txtRazaoSoc = (TextView) findViewById(R.id.txtRazaoSocial);
        txtNomeFant = (TextView) findViewById(R.id.txtNomeFantasia);
        txtCNPJ = (TextView) findViewById(R.id.txtCNPJ);
        txtRamoAtiv = (TextView) findViewById(R.id.txtRamoAtividade);
        txtEndereco = (TextView) findViewById(R.id.txtEdereco);
        txtConj = (TextView) findViewById(R.id.txtConjuger);
        txtDataNascCon = (TextView) findViewById(R.id.txtDataNascConj);
        txtDatNasc = (TextView) findViewById(R.id.txtDataNasc);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtTelef = (TextView) findViewById(R.id.txtTelefone);
        txtNomeCli = (TextView) findViewById(R.id.txtNomeCliente);
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtTime2 = (TextView) findViewById(R.id.txtTime2);
        txtTipo = (TextView) findViewById(R.id.txtTipo);
        txtCel = (TextView) findViewById(R.id.txtCelular);
        txtHoraAtual = (TextView) findViewById(R.id.txtHoraAtual);
        txtDateAtual = (TextView) findViewById(R.id.txtdataAtual);
        txtStatus = (TextView) findViewById(R.id.txtStatus);

        btnVerificaStatus = (Button) findViewById(R.id.btnVerifiStatus);
        btnDados = (Button) findViewById(R.id.btnDados);
        btnHisPedidos = (Button) findViewById(R.id.btnHisPedidos);
        btnAlvaras = (Button) findViewById(R.id.btnAlvaras);
        btnFechar = (Button) findViewById(R.id.btnFechar);

        mensagem = (RelativeLayout) findViewById(R.id.mensagem);
    }

    private void buscarDados() {

        db.collection("Cliente")//faço uma busca primeiro na coleção Cliente
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                                codigo = (String) documentSnapshot.get("codigo");
                                rezSocial = (String) documentSnapshot.get("razao_social");
                                nomeFant = (String) documentSnapshot.get("nomeFantasia");
                                cnpj = (String) documentSnapshot.get("cnpj");
                                ramoAtvi = (String) documentSnapshot.get("ramo_atividade");
                                endereco = (String) documentSnapshot.get("endereco");
                                id = (String) documentSnapshot.get("id");
                                status = (String) documentSnapshot.get("status");
                                txtCodigo.setText(codigo);
                                txtRazaoSoc.setText(rezSocial);
                                txtNomeFant.setText(nomeFant);
                                txtCNPJ.setText(cnpj);
                                txtRamoAtiv.setText(ramoAtvi);
                                txtEndereco.setText(endereco);
                                txtStatus.setText(status);

                                db.collection("Cliente")//e me aproveitando do id do cliente ja faço uma busca em contatos que tem a chave de seu document a mesma do id de cliente
                                        .document(id)
                                        .collection("contatos")
                                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if(task.isSuccessful()){
                                            for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                                nomeCliente = (String) documentSnapshot.get("nome");
                                                celular = (String) documentSnapshot.get("celular");
                                                conjuge = (String) documentSnapshot.get("conjuge");
                                                datNascConj = (String) documentSnapshot.get("dataNascimentoConjuge");
                                                email = (String) documentSnapshot.get("e_mail");
                                                datNasc = (String) documentSnapshot.get("data_nascimento");
                                                telefone = (String) documentSnapshot.get("telefone");
                                                time = (String) documentSnapshot.get("time");
                                                tipo = (String) documentSnapshot.get("tipo");

                                                SimpleMaskFormatter simpleMaskFormatterCel = new SimpleMaskFormatter("(NN) NNNNN-NNNN");//uso uma mascara para deixar todos os dados formatados
                                                MaskTextWatcher maskTextWatcherCel = new MaskTextWatcher(txtCel, simpleMaskFormatterCel);
                                                txtCel.addTextChangedListener(maskTextWatcherCel);

                                                SimpleMaskFormatter simpleMaskFormatterDate = new SimpleMaskFormatter("NNNN/NN/NN");
                                                MaskTextWatcher maskTextWatcherDate = new MaskTextWatcher(txtDatNasc, simpleMaskFormatterDate);
                                                txtDatNasc.addTextChangedListener(maskTextWatcherDate);
                                                MaskTextWatcher maskTextWatcherDate1 = new MaskTextWatcher(txtDataNascCon, simpleMaskFormatterDate);
                                                txtDataNascCon.addTextChangedListener(maskTextWatcherDate1);
                                                txtCel.setText(celular);
                                                txtConj.setText(conjuge);
                                                txtDataNascCon.setText(datNascConj);
                                                txtDatNasc.setText(datNasc);
                                                txtEmail.setText(email);
                                                txtNomeCli.setText(nomeCliente);
                                                txtTelef.setText(telefone);
                                                txtTime.setText(time);
                                                txtTime2.setText(time);
                                                txtTipo.setText(tipo);
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    }
                });


    }

    private void eventoClick() {
        btnVerificaStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //uso o setVisibility para facilitar
                mensagem.setVisibility(View.VISIBLE);
                data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
                hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
                txtDateAtual.setText(data);
                txtHoraAtual.setText(hora);
            }
        });

        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensagem.setVisibility(View.INVISIBLE);
            }
        });

        btnHisPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Hist_Pedidos.class));
                finish();
            }
        });

        btnAlvaras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Alvaras.class));
                finish();
            }
        });
    }

}
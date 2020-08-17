package com.example.maxapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maxapp.fragments.LegendaFragment;
import com.example.maxapp.model.Pedido;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Hist_Pedidos extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private FirestoreRecyclerAdapter adapter;

    private Button tbnDados, btnAlvaras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hist__pedidos);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Hist. de Pedidos");



        recyclerView = (RecyclerView) findViewById(R.id.recycleView);

        addapter();//metado adapter

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        eventoClick();// onde fica toda as ações dos botões

    }

    private void eventoClick() {

        tbnDados = (Button) findViewById(R.id.btnDados);
        btnAlvaras = (Button) findViewById(R.id.btnAlvaras);

        tbnDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Dados_Cliente.class));
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

    private void addapter() {

        db = FirebaseFirestore.getInstance();//é instaciado o banco de dados, Claud firestore do webService Firebase
        Query query = db.collection("pedidos");//é feita o acesso na coleção
        FirestoreRecyclerOptions<Pedido> options = new FirestoreRecyclerOptions.Builder<Pedido>()//usei uma api que facilita o uso de recycler view com o firestore
                .setQuery(query, Pedido.class).
                        build();

        adapter = new FirestoreRecyclerAdapter<Pedido, PedidoViewHolder>(options) {
            @NonNull
            @Override
            public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pedidos_item, parent, false);
                return new PedidoViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull PedidoViewHolder holder, int position, @NonNull Pedido model) {


                holder.txtClinte.setText(model.getNOMECLIENTE());
                holder.txtRca.setText(model.getNumero_ped_Rca());
                holder.txtErp.setText(model.getNumero_ped_erp());
                holder.txtCodigo.setText(model.getCodigoCliente());
                holder.txtStatus.setText(model.getStatus());
                holder.txtDinheiro.setText(model.getDinheiro());

                //maneira mas pratica que achei de converte os texto no BD para imagens
                holder.imgAguardadndo.setVisibility(View.INVISIBLE);
                holder.txtAtencao.setVisibility(View.INVISIBLE);
                holder.imgSucess.setVisibility(View.INVISIBLE);
                holder.imgAlerta.setVisibility(View.INVISIBLE);
                holder.imgCorte.setVisibility(View.INVISIBLE);
                holder.imgDevolocao.setVisibility(View.INVISIBLE);
                holder.imgTele.setVisibility(View.INVISIBLE);
                holder.imgFalta.setVisibility(View.INVISIBLE);
                holder.imgCancelado.setVisibility(View.INVISIBLE);
                holder.txtRecusado.setVisibility(View.INVISIBLE);
                holder.Processamento.setVisibility(View.INVISIBLE);
                holder.txtPendente.setVisibility(View.INVISIBLE);
                holder.txtBloqueado.setVisibility(View.INVISIBLE);
                holder.txtLiberado.setVisibility(View.INVISIBLE);
                holder.txtFaturado.setVisibility(View.INVISIBLE);
                holder.txtCancelado.setVisibility(View.INVISIBLE);
                holder.txtOcamento.setVisibility(View.INVISIBLE);
                holder.txtMontado.setVisibility(View.INVISIBLE);

                if ("SUCESSO".equals(model.getCritica())){
                    holder.imgSucess.setVisibility(View.VISIBLE);
                }if ("FALHA_PARCIAL".equals(model.getCritica())){
                    holder.imgAlerta.setVisibility(View.VISIBLE);
                }
                if("PEDIDO_SOFREU_CORTE".equals(model.getLegendas())|"PEDIDO_SOFREU_CORTE".equals(model.getLegenda())){
                    holder.imgCorte.setVisibility(View.VISIBLE);
                }if ("PEDIDO_FEITO_TELEMARKETING".equals(model.getLegendas())|"PEDIDO_FEITO_TELEMARKETING".equals(model.getLegenda())){
                    holder.imgTele.setVisibility(View.VISIBLE);
                }if ("PEDIDO_CANCELADO_ERP".equals(model.getLegendas())|"PEDIDO_CANCELADO_ERP".equals(model.getLegenda())){
                    holder.imgCancelado.setVisibility(View.VISIBLE);
                }

                if ("Processado".equals(model.getStatus())){
                    holder.txtMontado.setVisibility(View.VISIBLE);
                }if ("Em processamento".equals(model.getStatus())){
                    holder.Processamento.setVisibility(View.VISIBLE);

                }if ("Pendente".equals(model.getStatus())){
                    holder.txtPendente.setVisibility(View.VISIBLE);
                }


            }
        };

    }

    //class viewHolder
    private class PedidoViewHolder extends RecyclerView.ViewHolder {
        private TextView txtRca, txtErp, txtDate, txtClinte, txtStatus, txtDinheiro, txtAtencao, txtCodigo, txtRecusado,
                txtBloqueado, txtLiberado, txtMontado, txtFaturado, txtCancelado, txtOcamento;
        private ImageView imgTele, imgDevolocao, imgCancelado, imgFalta, imgCorte, imgAguardadndo, imgSucess, imgAlerta;
        private FrameLayout Processamento, txtPendente;


        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);

            inicializarComponentes();

        }
        //feita toda a inicialização dos componentes
        private void inicializarComponentes() {
            txtRecusado = itemView.findViewById(R.id.txt_Recusado);
            txtPendente = itemView.findViewById(R.id.txt_Pendente);
            txtBloqueado = itemView.findViewById(R.id.txt_Bloqueado);
            txtLiberado = itemView.findViewById(R.id.txt_Liberado);
            txtMontado = itemView.findViewById(R.id.txt_Montado);
            txtFaturado = itemView.findViewById(R.id.txt_Faturado);
            txtCancelado = itemView.findViewById(R.id.txt_Cancelado);
            txtOcamento = itemView.findViewById(R.id.txt_Ocamento);
            txtRca = itemView.findViewById(R.id.txtN_Ped_RCA);
            txtErp = itemView.findViewById(R.id.txtN_Ped_ERP);
            txtDate = itemView.findViewById(R.id.txtData);
            txtClinte = itemView.findViewById(R.id.txtCliente);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtDinheiro = itemView.findViewById(R.id.txtDinheiro);
            txtAtencao = itemView.findViewById(R.id.txtAtencao);
            txtCodigo = itemView.findViewById(R.id.txtCodigo);
            imgTele =itemView.findViewById(R.id.imgTelemarketing);
            imgDevolocao = itemView.findViewById(R.id.imgDevolacao);
            imgCancelado = itemView.findViewById(R.id.imgCancelado);
            imgFalta = itemView.findViewById(R.id.imgfalta);
            imgCorte = itemView.findViewById(R.id.imgCorte);
            imgAguardadndo = itemView.findViewById(R.id.imgAguardado);
            imgSucess = itemView.findViewById(R.id.imgSucesso);
            imgAlerta = itemView.findViewById(R.id.imgAlerta);
            Processamento = itemView.findViewById(R.id.Processamento);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Fragment selectedFragment = new LegendaFragment();
       switch (item.getItemId()){
           case R.id.legendas2:
           selectedFragment = new LegendaFragment();

           break;
       }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont, selectedFragment).commit();
        return true;
    }
}
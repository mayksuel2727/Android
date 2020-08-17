package com.example.maxapp.model;

public class Pedido {
    private String NOMECLIENTE, critica, codigoCliente, data, legendas, numero_ped_Rca, numero_ped_erp, status, tipo, legenda, dinheiro;

    public Pedido() {
    }

    public Pedido(String NOMECLIENTE, String critica, String codigoCliente, String data, String legendas, String numero_ped_Rca, String numero_ped_erp,String dinheiro, String status, String tipo, String legenda) {
        this.NOMECLIENTE = NOMECLIENTE;
        this.critica = critica;
        this.codigoCliente = codigoCliente;
        this.data = data;
        this.legendas = legendas;
        this.numero_ped_Rca = numero_ped_Rca;
        this.numero_ped_erp = numero_ped_erp;
        this.status = status;
        this.tipo = tipo;
        this.dinheiro = dinheiro;
        this.legenda = legenda;
    }

    public String getDinheiro() {
        return dinheiro;
    }

    public String getLegenda() {
        return legenda;
    }

    public String getNOMECLIENTE() {
        return NOMECLIENTE;
    }

    public String getCritica() {
        return critica;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public String getData() {
        return data;
    }

    public String getLegendas() {
        return legendas;
    }

    public String getNumero_ped_Rca() {
        return numero_ped_Rca;
    }

    public String getNumero_ped_erp() {
        return numero_ped_erp;
    }

    public String getStatus() {
        return status;
    }

    public String getTipo() {
        return tipo;
    }
}

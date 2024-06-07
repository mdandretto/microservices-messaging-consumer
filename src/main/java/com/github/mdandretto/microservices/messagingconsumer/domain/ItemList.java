package com.github.mdandretto.microservices.messagingconsumer.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class ItemList implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoPedido;
    private String produto;
    private float preco;
    private int quantidade;
    public String getCodigoPedido() {
        return codigoPedido;
    }
    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }
    public String getCodigoCliente() {
        return produto;
    }
    public void setProduto(String produto) {
        this.produto = produto;
    }
    public float getPreco() {
        return preco;
    }
    public void setPreco(float preco) {
        this.preco = preco;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
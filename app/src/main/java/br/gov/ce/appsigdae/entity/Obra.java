package br.gov.ce.appsigdae.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by 39091 on 02/08/2017.
 */

public class Obra implements Serializable {

    private Integer id;

    private String codigoObra;

    private String descricaoObra;

    private String municipio;

    private String statusObra;

    private String contratanteNomeFantasia;

    private BigDecimal valorAtual;

    private BigDecimal totalExecutado;

    private BigDecimal saldoAMedir;

    private String contratadaNomeFantasia;

    public Obra() {

    }

    public Obra(Integer id, String codigoObra, String descricaoObra, String municipio, String statusObra, String contratanteNomeFantasia, BigDecimal valorAtual, BigDecimal totalExecutado, BigDecimal saldoAMedir, String contratadaNomeFantasia) {
        this.id = id;
        this.codigoObra = codigoObra;
        this.descricaoObra = descricaoObra;
        this.municipio = municipio;
        this.statusObra = statusObra;
        this.contratanteNomeFantasia = contratanteNomeFantasia;
        this.valorAtual = valorAtual;
        this.totalExecutado = totalExecutado;
        this.saldoAMedir = saldoAMedir;
        this.contratadaNomeFantasia = contratadaNomeFantasia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoObra() {
        return codigoObra;
    }

    public void setCodigoObra(String codigoObra) {
        this.codigoObra = codigoObra;
    }

    public String getDescricaoObra() {
        return descricaoObra;
    }

    public void setDescricaoObra(String descricaoObra) {
        this.descricaoObra = descricaoObra;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getStatusObra() {
        return statusObra;
    }

    public void setStatusObra(String statusObra) {
        this.statusObra = statusObra;
    }

    public String getContratanteNomeFantasia() {
        return contratanteNomeFantasia;
    }

    public void setContratanteNomeFantasia(String contratanteNomeFantasia) {
        this.contratanteNomeFantasia = contratanteNomeFantasia;
    }

    public BigDecimal getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(BigDecimal valorAtual) {
        this.valorAtual = valorAtual;
    }

    public BigDecimal getTotalExecutado() {
        return totalExecutado;
    }

    public void setTotalExecutado(BigDecimal totalExecutado) {
        this.totalExecutado = totalExecutado;
    }

    public BigDecimal getSaldoAMedir() {
        return saldoAMedir;
    }

    public void setSaldoAMedir(BigDecimal saldoAMedir) {
        this.saldoAMedir = saldoAMedir;
    }

    public String getContratadaNomeFantasia() {
        return contratadaNomeFantasia;
    }

    public void setContratadaNomeFantasia(String contratadaNomeFantasia) {
        this.contratadaNomeFantasia = contratadaNomeFantasia;
    }

    @Override
    public String toString() {
        return "Obra{" +
                "id=" + id +
                ", codigoObra='" + codigoObra + '\'' +
                ", descricaoObra='" + descricaoObra + '\'' +
                ", municipio='" + municipio + '\'' +
                ", statusObra='" + statusObra + '\'' +
                ", contratanteNomeFantasia='" + contratanteNomeFantasia + '\'' +
                ", valorAtual=" + valorAtual +
                ", totalExecutado=" + totalExecutado +
                ", saldoAMedir=" + saldoAMedir +
                ", contratadaNomeFantasia='" + contratadaNomeFantasia + '\'' +
                '}';
    }
}

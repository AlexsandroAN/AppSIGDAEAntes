package br.gov.ce.appsigdae.entity;

import java.io.Serializable;

/**
 * Created by 39091 on 26/08/2016.
 */
public class VoObras implements Serializable {

    private Integer id;


    private String codigoObra;


    private String descricaoObra;


    private String municipio;


    private String statusObra;


    private String contratanteNomeFantasia;


    private String contratadaNomeFantasia;

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

    public String getContratadaNomeFantasia() {
        return contratadaNomeFantasia;
    }

    public void setContratadaNomeFantasia(String contratadaNomeFantasia) {
        this.contratadaNomeFantasia = contratadaNomeFantasia;
    }

    public VoObras() {

    }
}

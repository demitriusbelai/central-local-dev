/**
 * Copyright (C) 2018 Demitrius Belai
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package br.unesp.fc.central.vo;

import java.io.Serializable;

public class SistemaVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idSistema;
    private String nome;
    private String descricao;
//    private String urlEntrada;
//    private String icone;
//    private Boolean ativo;
//    private Boolean visivel;
//    private String clientId;
//    private String clientSecret;

    public Integer getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(Integer idSistema) {
        this.idSistema = idSistema;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

//    public String getUrlEntrada() {
//        return urlEntrada;
//    }
//
//    public void setUrlEntrada(String urlEntrada) {
//        this.urlEntrada = urlEntrada;
//    }
//
//    public String getIcone() {
//        return icone;
//    }
//
//    public void setIcone(String icone) {
//        this.icone = icone;
//    }
//
//    public Boolean getAtivo() {
//        return ativo;
//    }
//
//    public void setAtivo(Boolean ativo) {
//        this.ativo = ativo;
//    }
//
//    public Boolean getVisivel() {
//        return visivel;
//    }
//
//    public void setVisivel(Boolean visivel) {
//        this.visivel = visivel;
//    }
//
//    public String getClientId() {
//        return clientId;
//    }
//
//    public void setClientId(String clientId) {
//        this.clientId = clientId;
//    }
//
//    public String getClientSecret() {
//        return clientSecret;
//    }
//
//    public void setClientSecret(String clientSecret) {
//        this.clientSecret = clientSecret;
//    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idSistema == null) ? 0 : idSistema.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SistemaVO other = (SistemaVO) obj;
        if (idSistema == null) {
            if (other.idSistema != null)
                return false;
        } else if (!idSistema.equals(other.idSistema))
            return false;
        return true;
    }

}

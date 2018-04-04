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

import br.unesp.fc.central.vo.UsuarioVO;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class CustomAuthenticationDetails {

    private String remoteAddress;
    private String sessionId;
    private String tokenValue;
    private String tokenType;
    private Object decodedDetails;
    private Integer idUsuario;
    private String nome;
    private String cpf;
    private String email;

    public CustomAuthenticationDetails(OAuth2AuthenticationDetails details, UsuarioVO usuario) {
        this.remoteAddress = details.getRemoteAddress();
        this.sessionId = details.getSessionId();
        this.tokenValue = details.getTokenValue();
        this.tokenType = details.getTokenValue();
        this.decodedDetails = details.getDecodedDetails();
        this.idUsuario = usuario.getIdUsuario();
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Object getDecodedDetails() {
        return decodedDetails;
    }

    public void setDecodedDetails(Object decodedDetails) {
        this.decodedDetails = decodedDetails;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

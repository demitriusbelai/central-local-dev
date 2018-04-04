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
package br.unesp.fc.central.service;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import br.unesp.fc.central.vo.PerfilVO;
import br.unesp.fc.central.vo.SistemaVO;
import br.unesp.fc.central.vo.UsuarioVO;

@Service
public class CentralService {

    @Value("${service-url}")
    private String serviceUrl;

    @Autowired
    private RestTemplateFactory restTemplateFactory;

    public List<UsuarioVO> listarUsuarioPorNome(String nome) {
        RestTemplate restTemplate = restTemplateFactory.get();
        URI url = UriComponentsBuilder.fromHttpUrl(serviceUrl).path("/v1/usuarios")
                .queryParam("ativo", true)
                .queryParam("nome", nome)
                .build().encode().toUri();
        UsuarioVO[] usuarios = restTemplate.getForObject(url, UsuarioVO[].class);
        return Arrays.asList(usuarios);
    }

    public List<UsuarioVO> listarUsuarioPorCpf(String cpf) {
        RestTemplate restTemplate = restTemplateFactory.get();
        URI url = UriComponentsBuilder.fromHttpUrl(serviceUrl).path("/v1/usuarios")
                .queryParam("ativo", true).queryParam("cpf", cpf)
                .build().encode().toUri();
        UsuarioVO[] usuarios = restTemplate.getForObject(url, UsuarioVO[].class);
        return Arrays.asList(usuarios);
    }

    public List<UsuarioVO> listarUsuarioPorEmail(String email) {
        RestTemplate restTemplate = restTemplateFactory.get();
        URI url = UriComponentsBuilder.fromHttpUrl(serviceUrl).path("/v1/usuarios")
                .queryParam("ativo", true).queryParam("email", email)
                .build().encode().toUri();
        UsuarioVO[] usuarios = restTemplate.getForObject(url, UsuarioVO[].class);
        return Arrays.asList(usuarios);
    }

    public UsuarioVO buscarUsuarioPorIdentificacao(String identificacao) {
        RestTemplate restTemplate = restTemplateFactory.get();
        URI url = UriComponentsBuilder.fromHttpUrl(serviceUrl).path("/v1/usuarios")
                .queryParam("identificacao", identificacao)
                .build().encode().toUri();
        UsuarioVO[] usuarios = restTemplate.getForObject(url, UsuarioVO[].class);
        return usuarios.length > 0 ? usuarios[0] : null;
    }

//    public SistemaVO buscarSistemaPorId(Integer id) {
//        RestTemplate restTemplate = restTemplateFactory.get();
//        URI url = UriComponentsBuilder.fromHttpUrl(serviceUrl).path("/v1/sistemas/" + id)
//                .build().encode().toUri();
//        return restTemplate.getForObject(url, SistemaVO.class);
//    }

//    public List<SistemaVO> listarSistema() {
//        RestTemplate restTemplate = restTemplateFactory.get();
//        URI url = UriComponentsBuilder.fromHttpUrl(serviceUrl).path("/v1/sistemas")
//                .build().encode().toUri();
//        SistemaVO[] sistemas = restTemplate.getForObject(url, SistemaVO[].class);
//        return Arrays.asList(sistemas);
//    }

    public List<PerfilVO> listarPerfisPorUsuario(UsuarioVO usuario) {
        RestTemplate restTemplate = restTemplateFactory.get();
        URI url = UriComponentsBuilder.fromHttpUrl(serviceUrl)
                .path("/v1/usuarios/" + usuario.getIdUsuario() + "/perfis")
                .build().encode().toUri();
        PerfilVO[] perfis = restTemplate.getForObject(url, PerfilVO[].class);
        return Arrays.asList(perfis);
    }

}

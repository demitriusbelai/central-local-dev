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
package br.unesp.fc.central;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.unesp.fc.central.service.CentralService;
import br.unesp.fc.central.vo.PerfilVO;
import br.unesp.fc.central.vo.SistemaVO;
import br.unesp.fc.central.vo.UsuarioVO;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private CentralService centralService;

    private Pattern cpfPattern = Pattern.compile("\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}");

    private Pattern emailPattern = Pattern.compile("\\S+@\\S+");

    @RequestMapping("/usuarios/pesquisar")
    public List<UsuarioVO> pesquisar(@RequestParam("q") String q) {
        q = q.trim();
        if (cpfPattern.matcher(q).matches()) {
            return centralService.listarUsuarioPorCpf(q);
        }
        if (emailPattern.matcher(q).matches()) {
            return centralService.listarUsuarioPorEmail(q);
        }
        return centralService.listarUsuarioPorNome(q);
    }

    @RequestMapping("/usuarios/{id}")
    public UsuarioVO buscarUsuario(@PathVariable String id) {
        return centralService.buscarUsuarioPorIdentificacao(id);
    }

    @RequestMapping("/usuarios/{id}/sistemas")
    public List<SistemaVO> listarSistemasUsuario(@PathVariable String id) {
        UsuarioVO usuario = centralService.buscarUsuarioPorIdentificacao(id);
        List<PerfilVO> perfis = centralService.listarPerfisPorUsuario(usuario);
        return perfis.stream().map(p -> {
            SistemaVO s = new SistemaVO();
            s.setIdSistema(p.getIdSistema());
            s.setNome(p.getNomeSistema());
            s.setDescricao(p.getDescricaoSistema());
            return s;
        }).distinct().collect(Collectors.toList());
    }

    @RequestMapping("/sistemas")
    public List<SistemaVO> listar(Authentication authentication) {
        UsuarioVO usuario = centralService.buscarUsuarioPorIdentificacao(authentication.getName());
        List<PerfilVO> perfis = centralService.listarPerfisPorUsuario(usuario);
        return perfis.stream().map(p -> {
            SistemaVO s = new SistemaVO();
            s.setIdSistema(p.getIdSistema());
            s.setNome(p.getNomeSistema());
            s.setDescricao(p.getDescricaoSistema());
            return s;
        }).distinct().collect(Collectors.toList());
    }

    @RequestMapping("/user")
    public UsuarioVO user(Authentication authentication) {
        // UsuarioVO usuario =
        // centralService.buscarUsuarioPorIdentificacao(authentication.getName());
        UsuarioVO usuario = (UsuarioVO) authentication.getPrincipal();
        return usuario;
    }

}

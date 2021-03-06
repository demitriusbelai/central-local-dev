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
package br.unesp.fc.central.auth;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import br.unesp.fc.central.service.CentralService;
import br.unesp.fc.central.vo.PerfilVO;
import br.unesp.fc.central.vo.UsuarioVO;

@Component
public class CentralAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CentralService centralService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        UsernamePasswordAuthenticationToken authToken =
                (UsernamePasswordAuthenticationToken) authentication;
        UsuarioVO usuario = centralService.buscarUsuarioPorIdentificacao(authToken.getName());
        if (usuario == null) {
            return null;
        }
        List<PerfilVO> perfis = centralService.listarPerfisPorUsuario(usuario);
        List<SimpleGrantedAuthority> authorities =
                perfis.stream().filter(p -> StringUtils.hasText(p.getAuthority()))
                        .map(p -> new SimpleGrantedAuthority(p.getAuthority()))
                        .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(usuario, null, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}

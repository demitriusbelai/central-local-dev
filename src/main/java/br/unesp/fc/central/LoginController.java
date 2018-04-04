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

import br.unesp.fc.central.vo.CustomAuthenticationDetails;
import br.unesp.fc.central.vo.UsuarioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private Environment env;

    @ResponseBody
    @RequestMapping("/user")
    public Authentication user(Authentication authentication) {
        if (authentication instanceof OAuth2Authentication
                && authentication.getPrincipal() instanceof UsuarioVO
                && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
            ((OAuth2Authentication) authentication).setDetails(new CustomAuthenticationDetails(
                    (OAuth2AuthenticationDetails) authentication.getDetails(),
                    (UsuarioVO) authentication.getPrincipal()));
        }
        return authentication;
    }

    @RequestMapping("/acessar/{id}")
    public ResponseEntity<String> acessar(@PathVariable Integer id) {
        String key = "url-entrada-" + id;
        String url = env.getProperty(key);
        if (url == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    "URL de redirecionamento não encontrado para essa aplicação. Preencher em 'application.properties' na chave '"
                            + key + "'");
        }
        // return "redirect:" + url;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", url);
        return new ResponseEntity<String>(null, headers, HttpStatus.FOUND);
    }

}

# Central Local Dev

Esta aplicação tem como finalidade ser utilizado pela equipe de desenvolvimento da UNESP para facilitar os testes nas aplicações com diferentes usuários. Ele utiliza a API da central de acessos para listar os usuários e seus sistemas.

Essa aplicação foi escrito utilizando Java com o framework Spring no _back-end_ e Angular no _front-end_. 

## Requisito

É necessário ter `maven` e `npm` instalados para poder executar.

## Configuração

Toda configuração é feita no arquivo `src/main/resources/application.properties`. Deve-se preencher o ID do cliente `clientId` e a chave de acesso `clientSecret` para acessar a API da Central de Acessos.

Neste arquivo também deve ser preenchido os clientes que utilizarão esta aplicação como autenticador conforme modelo encontrado no próprio arquivo.

Como a central de acessos não permite a consulta de um sistema pela API externa, essa aplicação não tem conhecimento do endereço utilizado para acessar o sistema. Por isso, faz necessário o preenchimento dessa informação também no formato `url-entrada-{idSistema}=http://localhost:8080/`, onde `{idSistema}` é o ID do sistema que se quer acessar. Caso essa informação não seja preenchida, no momento em que se for acessar o sistema mostrará uma mensagem de erro informando o ID do sistema.


## Compilação

Antes de compilar a aplicação Java, deve-se primeira compilar o front-end escrito em Angular que se encontra no diretório `ui`. Para facilitar, foi adicionado tarefas ao maven para isso. Execute `mvn exec:exec@npm-install` para rodar o `npm install`. Depois execute `mvn exec:exec@ng-build` para executar o `ng build -prod`, os arquivos são copiados para o diretório `/src/main/resources/static` para serem servidos pelo Spring.

## Execução

Para executar a aplicação rode `mvn spring-boot:run`. A aplicação estará funcionando no endereço [http://localhost:8090/](http://localhost:8090/).

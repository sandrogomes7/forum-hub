# Forum Hub

Forum Hub é uma aplicação de fórum desenvolvida com Spring Boot que permite a criação, listagem, atualização e exclusão de tópicos. Além disso, possui autenticação JWT e documentação de API com Swagger.

## Funcionalidades

- **Criação de Tópicos:** Permite que os usuários registrem novos tópicos.
- **Listagem de Tópicos:** Lista todos os tópicos registrados.
- **Atualização de Tópicos:** Permite a atualização dos dados de um tópico específico.
- **Exclusão de Tópicos:** Permite a exclusão de um tópico específico.
- **Autenticação JWT:** Utiliza tokens JWT para autenticar e autorizar usuários.
- **Documentação da API:** Utiliza Swagger para documentar e testar a API.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Swagger
- JWT (JSON Web Token)
- Lombok
- Maven

## Instalação e Configuração

1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/forum_hub.git
    ```

2. Navegue até o diretório do projeto:
    ```bash
    cd forum_hub
    ```

3. Configure as variáveis de ambiente no arquivo `application.properties`:
    ```properties
    api.security.token.secret=SUA_CHAVE_SECRETA
    ```

4. Execute a aplicação:
    ```bash
    mvn spring-boot:run
    ```

5. Acesse a documentação da API no Swagger:
    ```
    http://localhost:8080/swagger-ui.html
    ```

## Endpoints Principais

### Tópicos

- **POST /topicos:** Registra um novo tópico.
- **GET /topicos:** Lista todos os tópicos com paginação.
- **PUT /topicos/{id}:** Atualiza um tópico específico.
- **GET /topicos/{id}:** Obtém os detalhes de um tópico específico.
- **DELETE /topicos/{id}:** Exclui um tópico específico.

### Autenticação

- **POST /login:** Autentica um usuário e retorna um token JWT.

## Estrutura do Projeto

- `com.sandro.forum_hub`: Contém a classe principal da aplicação.
- `com.sandro.forum_hub.infra.springdoc`: Configurações do Swagger.
- `com.sandro.forum_hub.infra.security`: Classes de segurança e filtro JWT.
- `com.sandro.forum_hub.domain.usuario`: Entidades e serviços relacionados ao usuário.
- `com.sandro.forum_hub.domain.topico`: Entidades e serviços relacionados aos tópicos.
- `com.sandro.forum_hub.domain.repository`: Repositórios JPA para acesso aos dados.
- `com.sandro.forum_hub.controller`: Controladores REST para os endpoints da aplicação.




# 🍔 Fastfood Orders API

API REST para gerenciamento do ciclo de pedidos de um fast food, desenvolvida com Spring Boot 3 e Java 21.

---

## 📋 Sobre o Projeto

A **Fastfood Orders API** é um sistema backend que permite gerenciar clientes, cardápio e pedidos de um estabelecimento de fast food. Ela controla todo o ciclo de vida de um pedido — da criação até a entrega — com transições de status validadas e autenticação por papéis (roles).

---

## 🚀 Tecnologias Utilizadas

| Tecnologia | Versão |
|---|---|
| Java | 21 |
| Spring Boot | 3.2.2 |
| Spring Data JPA | — |
| Spring Security | — |
| Spring Validation | — |
| PostgreSQL | 15 |
| Hibernate | — |
| Docker / Docker Compose | — |
| Maven | — |

---

## 🗂️ Estrutura do Projeto

```
src/main/java/com/ricardomedeiros/fastfoodorders/
├── config/
│   ├── SecurityConfig.java       # Configuração de autenticação e autorização
│   └── TestConfig.java           # Carga inicial de dados (CommandLineRunner)
├── controller/
│   ├── ClientController.java     # Endpoints de clientes
│   ├── MenuController.java       # Endpoints do cardápio
│   └── OrderController.java      # Endpoints de pedidos
├── dto/
│   ├── CreateOrderDTO.java       # Payload para criação de pedido
│   └── OrderItemRequestDTO.java  # Payload para itens do pedido
├── entities/
│   ├── Client.java               # Entidade cliente
│   ├── Menu.java                 # Entidade item do cardápio
│   ├── Order.java                # Entidade pedido (com máquina de estados)
│   └── OrderItem.java            # Entidade item de pedido
├── enums/
│   ├── Category.java             # Categorias do cardápio
│   ├── OrderStatus.java          # Status do pedido
│   └── PaymentStatus.java        # Status do pagamento
├── exceptions/
│   └── ActionNotAllowedException.java  # Exceção de ação inválida
├── repositories/
│   ├── ClientRepository.java
│   ├── MenuRepository.java
│   ├── OrderItemRepository.java
│   └── OrderRepository.java
└── services/
    ├── ClientService.java
    ├── MenuService.java
    ├── OrderItemService.java
    └── OrderService.java
```

---

## ⚙️ Pré-requisitos

- Java 21+
- Maven 3.8+
- Docker e Docker Compose

---

## ▶️ Como Executar

### 1. Subir o banco de dados com Docker

```bash
docker-compose up -d
```

Isso criará um container PostgreSQL com as seguintes configurações:

| Parâmetro | Valor |
|---|---|
| Host | `localhost` |
| Porta | `5432` |
| Database | `fastfood` |
| Usuário | `fastfood_user` |
| Senha | `fastfood_pass` |

### 2. Executar a aplicação

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

A API ficará disponível em: `http://localhost:8080`

> **Dados de teste:** Ao iniciar com o perfil `dev`, a aplicação carrega automaticamente 4 itens no cardápio (Hamburguer, Coke, Nutella, French fries).

---

## 🔐 Autenticação

A API utiliza **HTTP Basic Authentication** com três papéis (roles):

| Usuário | Senha | Papel | Permissões |
|---|---|---|---|
| `client` | `123` | CLIENT | Criar pedidos, visualizar pedidos |
| `restaurant` | `123` | RESTAURANT | Atualizar status, visualizar pedidos |
| `admin` | `123` | ADMIN | Visualizar pedidos |

> ⚠️ As credenciais estão em memória (InMemoryUserDetailsManager) e são apenas para fins de desenvolvimento.

---

## 📦 Entidades e Enums

### OrderStatus (ciclo de vida do pedido)

```
CREATED → CONFIRMED → IN_PREPARATION → READY → DELIVERED
```

Cancelamento permitido apenas nos status `CREATED` e `CONFIRMED`.

### PaymentStatus

`PENDING` | `PAID` | `FAILED` | `CANCELED`

### Category (cardápio)

`BURGUER` | `DRINK` | `SIDE` | `DESSERT` | `COMBO`

---

## 🛠️ Endpoints da API

### Clientes — `/clients`

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/clients` | Lista todos os clientes |
| GET | `/clients/{id}` | Busca cliente por ID |
| POST | `/clients` | Cadastra novo cliente |
| DELETE | `/clients/{id}` | Remove cliente |

**Exemplo de corpo para criação de cliente:**
```json
{
  "name": "João Silva",
  "email": "joao@email.com",
  "phone": "11999999999"
}
```

---

### Cardápio — `/menu`

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/menu` | Lista todos os itens do cardápio |
| GET | `/menu/{id}` | Busca item por ID |
| POST | `/menu` | Cadastra novo item |
| DELETE | `/menu/{id}` | Remove item do cardápio |

**Exemplo de corpo para criação de item:**
```json
{
  "name": "X-Burguer",
  "description": "Pão, carne, queijo, alface",
  "price": 18.90,
  "category": "BURGUER",
  "available": true
}
```

---

### Pedidos — `/orders`

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/orders` | Lista todos os pedidos |
| GET | `/orders/{id}` | Busca pedido por ID |
| POST | `/orders` | Cria novo pedido |
| PUT | `/orders/{id}/cancel` | Cancela pedido |
| PUT | `/orders/{id}/items` | Atualiza itens do pedido (somente status CONFIRMED) |

**Exemplo de corpo para criação de pedido:**
```json
{
  "clientId": 1,
  "items": [
    { "menuItemId": 1, "quantity": 2 },
    { "menuItemId": 2, "quantity": 1 }
  ]
}
```

**Exemplo de corpo para atualização de itens:**
```json
[
  { "menuItemId": 3, "quantity": 1 },
  { "menuItemId": 4, "quantity": 2 }
]
```

---

## 🏗️ Avaliação Técnica do Projeto

### ✅ Pontos Positivos

- **Máquina de estados robusta:** A entidade `Order` implementa transições de status via mapa de transições permitidas (`allowedTransitions`), lançando `ActionNotAllowedException` para mudanças inválidas — bom uso de encapsulamento de regra de negócio na entidade.
- **Separação de responsabilidades:** Arquitetura em camadas bem definida (Controller → Service → Repository).
- **Docker Compose:** Facilita a configuração do ambiente de desenvolvimento.
- **Perfis de configuração:** Separação entre `application.properties`, `application-dev.properties` e `application-test.properties`.
- **Uso de DTOs:** Evita expor entidades diretamente na criação de pedidos.

### ⚠️ Pontos de Melhoria

- **Segurança em produção:** O `SecurityConfig` usa `InMemoryUserDetailsManager` com senhas simples (`123`) e `withDefaultPasswordEncoder()` (deprecated). Em produção, deve-se usar autenticação baseada em banco de dados com JWT.
- **Tratamento de exceções:** Não há um `@ControllerAdvice` global. Exceções como `RuntimeException("Order not found")` retornarão 500 ao invés de 404.
- **Validação de entrada:** As entidades e DTOs não possuem anotações de validação (`@NotNull`, `@NotBlank`, etc.), o que pode permitir dados inconsistentes.
- **Testes:** O projeto conta apenas com o teste de contexto gerado automaticamente pelo Spring Initializr — faltam testes unitários e de integração.
- **CSRF desabilitado implicitamente:** O `SecurityConfig` configura `.httpBasic()` sem desabilitar explicitamente o CSRF, o que pode causar problemas em requisições POST/PUT/DELETE via ferramentas como Postman/curl.
- **Import não utilizado:** `OrderController` importa `org.aspectj.weaver.ast.Or` sem uso.
- **Mensagem de erro genérica em MenuService:** O `findById` lança `"Order not found"` no lugar de `"Menu item not found"`.
- **`OrderItemService` subutilizado:** A classe existe e tem lógica de adicionar itens, mas não é injetada em nenhum controller — toda a lógica de items é orquestrada pelo `OrderService`.

---

## 📄 Licença

Este projeto é de uso educacional/pessoal, desenvolvido por **Ricardo Medeiros**.

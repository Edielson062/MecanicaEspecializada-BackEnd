# MecanicaEspecializada-BackEnd

## Descrição do Projeto

Este projeto **Back-End Java Spring** foi desenvolvido para o Projeto Integrador do 5º período do curso de Engenharia de Software. Ele tem como objetivo principal o **gerenciamento de ordens de serviço** para uma oficina mecânica.

---

## ⚙️ Funcionalidades Principais

O sistema oferece as seguintes funcionalidades para gerenciar operações de oficina mecânica:

-   **Cadastro de Entidades:**
    -   Marcas de veículos
    -   Modelos de veículos
    -   Veículos
    -   Peças
    -   Serviços
    -   Clientes (Pessoa Física e Jurídica)
-   **Gerenciamento de Ordens de Serviço (OS):** Criação, edição, visualização e acompanhamento do status das OS.
-   **Relatório Gerencial:** Geração de relatório para análise de desempenho e tomada de decisões.

---

## 🛠️ Requisitos Técnicos

Para executar este projeto localmente, você precisará dos seguintes softwares:

-   **Java Development Kit (JDK):** Versão **17** ou superior.

-   **Banco de Dados H2:** Este projeto utiliza o H2 como banco de dados embarcado e em memória para desenvolvimento e testes.
---


## 🚀 Como Executar o Projeto Localmente

Siga os passos abaixo para clonar e executar o projeto em sua máquina:

1.  **Clone o Repositório:**
    ```bash
    git clone https://github.com/Edielson062/MecanicaEspecializada-BackEnd.git
    ```

2.  **Acesse o Diretório do Projeto:**
    ```bash
    cd MecanicaEspecializada-BackEnd
    ```

3.  **Execute o Projeto:**

    Execute a aplicação em sua IDE de preferência, a aplicação estará disponível em `http://localhost:8080` (porta padrão do Spring Boot).

---

## 📂 Estrutura do Projeto

A organização do código segue as boas práticas de um projeto Spring Boot, facilitando a navegação e o entendimento:

```
Markdown

# 📂 Estrutura do Projeto

A organização do código segue as boas práticas de um projeto Spring Boot e padrões de design comuns, facilitando a navegação e o entendimento:

src
└── main
├── java
│   └── com
│       └── senai
│           └── projetointegrador
│               └── mecanicaespecializadabackend
│                   ├── controller     // Camada de apresentação (REST Controllers para as APIs)
│                   ├── dto            // Data Transfer Objects (DTOs) para troca de dados com a API
│                   ├── enums          // Enumerações utilizadas em todo o sistema
│                   ├── factory        // Implementações do padrão de projeto Factory
│                   ├── model          // Entidades JPA (representação das tabelas do banco de dados)
│                   ├── repository     // Interfaces do Spring Data JPA para acesso a dados
│                   ├── service        // Lógica de negócio da aplicação (serviços)
│                   ├── strategy       // Implementações do padrão de projeto Strategy
│                   └── MecanicaEspecializadaBackendApplication.java // Classe principal da aplicação Spring Boot
└── resources
└── application.properties // Arquivo de configuração principal da aplicação (portas, banco de dados, etc.)
```

---

## 🤝 Guia de Contribuição

Ficamos felizes com o seu interesse em contribuir para o projeto! Para garantir um processo colaborativo e organizado, por favor, siga estas diretrizes:

### 1. Reportando Bugs

Antes de abrir uma nova issue, por favor, verifique se o bug já foi reportado em [Issues](https://github.com/Edielson062/MecanicaEspecializada-BackEnd/issues).

- Se não encontrar, abra uma nova issue, fornecendo uma descrição clara, passos para reproduzir o erro e, se possível, logs ou screenshots.

### 2. Sugerindo Melhorias

-   Abra uma nova issue com o título começando com `[Sugestão]`.
-   Descreva a melhoria proposta e explique por que ela seria benéfica para o projeto.

### 3. Submetendo Pull Requests (PRs)

Se você deseja contribuir com código, siga este fluxo:

1.  **Faça um Fork** do projeto para o seu próprio GitHub.
2.  **Crie uma Nova Branch** a partir da branch `main`. Utilize um nome descritivo para a sua branch (Ex: `feature/adicionar-relatorio-vendas`).
    ```bash
    git checkout -b feature/nome-da-sua-feature
    ```
3.  **Implemente suas Alterações.** Certifique-se de que seu código está bem comentado e segue as convenções do projeto.
4.  **Crie Commits Claros e Descritivos.** Cada commit deve representar uma alteração lógica única.
    ```bash
    git commit -m "feat: Adiciona funcionalidade de relatório de vendas"
    ```
5.  **Execute os Testes.** Garanta que todas as funcionalidades existentes continuam funcionando corretamente.
6.  **Envie suas Alterações** para o seu fork no GitHub:
    ```bash
    git push origin feature/nome-da-sua-feature
    ```
7.  **Abra um Pull Request (PR)** da sua branch no seu fork para a branch `main` do repositório original.
    -   Forneça uma **descrição clara e detalhada** das mudanças realizadas no PR.

Seu PR será revisado e feedback será fornecido. Agradecemos sua colaboração!

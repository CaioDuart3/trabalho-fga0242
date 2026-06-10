# Trabalho FGA0242 - Curadoria de Dados Cientificos

Projeto desenvolvido para a disciplina **Tecnicas de Programacao para
Plataformas Emergentes (FGA0242)** da Universidade de Brasilia.

O objetivo do trabalho e implementar, com apoio de testes unitarios, regras de
deduplicacao e padronizacao de nomes de autores em registros de publicacoes
cientificas.

## Integrantes do grupo

| Foto | Nome | Matricula |
|:----:|:-----|:----------|
| <img src="https://github.com/caioduart3.png" width="80" height="80" alt="Foto de Caio Ferreira Duarte"> | [Caio Ferreira Duarte](https://github.com/caioduart3) | 231026901 |
| <img src="https://github.com/danielle-soaress.png" width="80" height="80" alt="Foto de Nome completo 2"> | [Danielle Soares da Silva](https://github.com/danielle-soaress) | 231012058 |
| <img src="https://github.com/felixlaryssa.png" width="80" height="80" alt="Foto de Nome completo 3"> | [Laryssa Felix Ribeiro Lopes](https://github.com/felixlaryssa) | 231026840 |

## Tecnologias utilizadas

| Item | Versao |
|:-----|:-------|
| Linguagem | Java 17 |
| Gerenciador de dependencias e build | Apache Maven |
| Framework de testes unitarios | JUnit 4.13.2 |
| Executor de testes | Maven Surefire Plugin 3.5.2 |
| Leitura do banco de dados (JSON) | Gson 2.11.0 |

## Como clonar o repositorio

1. Escolha uma pasta no seu computador para armazenar o projeto.

2. Clone o repositorio:

   ```bash
   git clone https://github.com/CaioDuart3/trabalho-fga0242.git
   ```

3. Acesse a pasta criada:

   ```bash
   cd trabalho-fga0242
   ```

## Como executar os testes

1. Acesse o diretorio do projeto Maven:

   ```bash
   cd projeto
   ```

2. Execute todos os testes automatizados:

   ```bash
   mvn test
   ```

3. Ao final da execucao, confira se o Maven indica sucesso na suite de testes.

Tambem e possivel executar o comando a partir da raiz do repositorio usando:

```bash
mvn -f projeto/pom.xml test
```

## Casos de deduplicacao contemplados

O trabalho contempla os cinco casos solicitados no enunciado:

| Caso | Descricao | Classe principal | Classe de teste |
|:----:|:----------|:-----------------|:----------------|
| 1 | Diferencas de grafia tipograficas | `DiferencasDeGrafia` | `TestDiferencasDeGrafia` |
| 2 | Sobrenome com iniciais dos nomes | `SobrenomeComIniciais` | `TestSobrenomeComIniciais` |
| 3 | Particulas `de` e abreviacoes opcionais | `ParticulasDeEAbreviacoesOpcionais` | `TestParticulasDeEAbreviacoesOpcionais` |
| 4 | Iniciais dos nomes agrupadas com sobrenome | `IniciaisAgrupadasComSobrenome` | `TestIniciaisAgrupadasComSobrenome` |
| 5 | IDs diferentes para o mesmo autor | `IDsDiferentesMesmoAutor` | `TestIDsDiferentesMesmoAutor` |

## Estrutura do repositorio

```text
.
|-- README.md
|-- docs
|   `-- enunciado.md
`-- projeto
    |-- pom.xml
    `-- src
        |-- main
        |   |-- java
        |   |   `-- tppe
        |   |       |-- Main.java
        |   |       |-- entities
        |   |       |   |-- DiferencasDeGrafia.java
        |   |       |   |-- IDsDiferentesMesmoAutor.java
        |   |       |   |-- IniciaisAgrupadasComSobrenome.java
        |   |       |   |-- ParticulasDeEAbreviacoesOpcionais.java
        |   |       |   `-- SobrenomeComIniciais.java
        |   |       `-- repository
        |   |           |-- NomesPadraoRepository.java
        |   |           `-- dto
        |   |               |-- Autor.java
        |   |               |-- BaseDeNomes.java
        |   |               `-- Registro.java
        |   `-- resources
        |       `-- nomes-padrao.json
        `-- test
            `-- java
                `-- entities
                    |-- TestDiferencasDeGrafia.java
                    |-- TestIDsDiferentesMesmoAutor.java
                    |-- TestIniciaisAgrupadasComSobrenome.java
                    |-- TestParticulasDeEAbreviacoesOpcionais.java
                    `-- TestSobrenomeComIniciais.java
```

## Organização do código

- `docs/enunciado.md`: enunciado original do trabalho pratico.
- `projeto/pom.xml`: configuracao do Maven, incluindo Java, JUnit, Surefire e Gson.
- `projeto/src/main/java/tppe/entities`: classes responsaveis pelas regras de
  curadoria e deduplicacao.
- `projeto/src/main/java/tppe/repository`: acesso aos dados e carregamento do
  banco de autores.
- `projeto/src/main/java/tppe/repository/dto`: DTOs utilizados para mapear o
  JSON da base de dados.
- `projeto/src/main/resources/nomes-padrao.json`: banco de dados (JSON) com os
  exemplos do enunciado e respectivos dados padrao-ouro.
- `projeto/src/test/java/entities`: testes unitarios dos cinco casos do
  enunciado.
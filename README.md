# design-patterns-ifsc

Repositório utilizado para criação do projeto final da UC de Design Patterns

## Integrantes

| Nome | Matrícula |
|------|-----------|
| HANNELY THAYS MASKE | 202210506929 |
| HENRIQUE GOULART DE DEUS | 202010009073 |
| JALMIR WINTER | 202110808910 |

## Sobre o Projeto

Sistema de Hospital desenvolvido como parte da avaliação da disciplina de **Padrões de Projeto** do Instituto Federal de Santa Catarina.

### Padrões de Projeto Implementados

- **Singleton** (Criacional) - Gerenciamento único de médicos
- **Builder** (Criacional) - Construção de pacientes
- **Adapter** (Estrutural) - Padronização de tipos de atendimento
- **Observer** (Comportamental) - Sistema de notificações

## Documentação

Veja o documento completo sobre os design patterns em: [doc/design-patterns.md](src/main/resources/doc/design-patterns.md)

## Como Executar

### Compilação e Execução

```bash
# Compilar o projeto
mvn clean compile

# Executar a aplicação
mvn spring-boot:run
```

A aplicação apresentará um menu interativo para:
1. Cadastrar pacientes (Builder)
2. Cadastrar médicos (Singleton)
3. Realizar atendimentos (Adapter)
4. Enviar notificações (Observer)
5. Listar pacientes
6. Listar médicos
7. Estatísticas do Hospital
8. Sair
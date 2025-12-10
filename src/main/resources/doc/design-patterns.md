# Design Patterns - Sistema Hospitalar

## 1. Introdução

Este documento apresenta os design patterns implementados no sistema de gestão hospitalar. O projeto utiliza uma combinação de padrões criacionais, estruturais e comportamentais para criar sua arquitetura.

---

## 2. Padrões Implementados

### 2.1 Singleton (Criacional)

**Descrição:**
O padrão Singleton garante que uma classe tenha apenas uma única instância durante toda a execução da aplicação, fornecendo um ponto de acesso global para essa instância.

**Aplicabilidade no Projeto:**
No sistema hospitalar, o padrão Singleton foi utilizado na classe `HospitalManager` para gerenciar a base de dados de médicos. Isso garante que exista apenas um registro único de médicos na aplicação.

**Justificativa:**
- **Unicidade de Dados:** Apenas um gerenciador deve manter a lista de médicos, evitando inconsistências nos dados;
- **Controle Centralizado:** Facilita o controle de acesso aos dados médicos;
- **Eficiência de Recursos:** Evita múltiplas instâncias consumindo memória desnecessariamente.

**Benefício para o Caso de Uso:**
Em um hospital real, não pode haver múltiplos registros de médicos em simultâneo, o que tornaria o sistema inconsistente. O Singleton garante a integridade e unicidade dessa informação.

---

### 2.2 Builder (Criacional)

**Descrição:**
O padrão Builder é um padrão criacional que facilita a construção de objetos complexos através de uma interface fluente, permitindo a construção passo a passo.

**Aplicabilidade no Projeto:**
Na classe `Paciente`, o padrão Builder permite construir objetos de pacientes de forma flexível, sem a necessidade de múltiplos construtores sobrecarregados. O paciente pode ser criado com apenas os dados necessários.

**Justificativa:**
- **Flexibilidade:** Permite criar pacientes com diferentes combinações de atributos;
- **Legibilidade:** O código fica mais claro e compreensível no ponto de instanciação;
- **Segurança:** Garante que o objeto seja criado em um estado válido;
- **Manutenibilidade:** Facilita a adição de novos atributos sem quebrar código existente.

**Exemplo de Uso:**
```
Paciente paciente = new Paciente.Builder()
    .nome("Jalmir Winter")
    .cpf("123.456.789-11")
    .idade(36)
    .email("jalmir@gmail.com")
    .telefone("(47) 9999-9999")
    .build();
```

**Benefício para o Caso de Uso:**
Em uma aplicação de hospital, pacientes possuem diversos atributos. O Builder permite que o cadastro seja feito de forma simples e direta, sem exigir todos os parâmetros de uma só vez.

---

### 2.3 Adapter (Estrutural)

**Descrição:**
O padrão Adapter permite que objetos com interfaces incompatíveis trabalhem juntos, atuando como um intermediário que converte a interface de uma classe em outra esperada pelo cliente.

**Aplicabilidade no Projeto:**
As classes `AtendimentoConsultaAdapter` e `AtendimentoEmergenciaAdapter` adaptam diferentes tipos de atendimento (consulta e emergência) para uma interface comum `TipoAtendimentoAdapter`, permitindo que o sistema trate diferentes atendimentos de forma uniforme.

**Justificativa:**
- **Reutilização:** Permite que classes legadas ou de terceiros sejam integradas sem modificação;
- **Padronização:** Oferece uma interface uniforme para diferentes tipos de atendimento;
- **Desacoplamento:** Reduz a dependência entre os componentes da aplicação;

**Benefício para o Caso de Uso:**
Um hospital precisa lidar com diferentes tipos de atendimento. O Adapter permite que todos sejam tratados de forma consistente, facilitando o fluxo de atendimentos.

---

### 2.4 Observer (Comportamental)

**Descrição:**
O padrão Observer define uma relação de um-para-muitos entre objetos, de modo que quando um objeto muda de estado, todos os seus dependentes são notificados automaticamente.

**Aplicabilidade no Projeto:**
No sistema de notificações, `GerenciadorAtendimento` atua como Subject, enquanto `NotificadorEmail` e `NotificadorSMS` atuam como Observers. Quando um atendimento é realizado, todos os notificadores inscritos são acionados para enviar as notificações.

**Justificativa:**
- **Desacoplamento:** O gerenciador não precisa conhecer os detalhes de cada notificador;
- **Manutenibilidade:** Cada notificador é responsável apenas por sua lógica;
- **Escalabilidade:** A adição de novos observadores é simples e não afeta o sistema existente.

**Exemplo de Fluxo:**
1. Paciente realiza atendimento com médico;
2. `GerenciadorAtendimento` notifica que um atendimento foi realizado;
3. `NotificadorEmail` envia e-mail ao paciente;
4. `NotificadorSMS` envia SMS ao paciente;
5. Novos notificadores podem ser adicionados sem modificar nada.

**Benefício para o Caso de Uso:**
Em um caso real de um hospital, múltiplos sistemas precisam ser notificados quando um atendimento ocorre. O Observer permite essa comunicação aconteça de forma eficiente.

---

## 3. Conclusão

A combinação desses quatro padrões de projeto cria uma arquitetura que se utiliza das boas práticas de design de software:

- **Singleton** garante unicidade de recursos;
- **Builder** facilita a criação de objetos;
- **Adapter** permite integração de diferentes componentes de forma flexivel;
- **Observer** estabelece comunicação entre componentes.

Essa abordagem resulta em um código mais limpo, manutenível e escalável.

---

## 4. Vídeos Demonstrativos

Os seguintes vídeos demonstram a implementação e funcionamento dos padrões de projeto utilizados neste projeto:

- [**Video padrões de projeto - Parte 1**](https://drive.google.com/file/d/1W4TH_Ar5qKUCBAAV7wxxYrwm7Fl2saRJ/view?usp=sharing)
- [**Video padrões de projeto - Parte 2**](https://drive.google.com/file/d/1HUCypLCWwx7lfZgofTiHXS_kzOqfC2Bj/view?usp=sharing)

---

**Data:** 08/12/2025
**Disciplina:** Padrões de Projeto  
**Instituição:** Instituto Federal de Santa Catarina


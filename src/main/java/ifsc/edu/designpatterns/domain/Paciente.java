package ifsc.edu.designpatterns.domain;

import lombok.Data;

@Data
public class Paciente {
    private String nome;
    private String cpf;
    private int idade;
    private String email;
    private String telefone;

    private Paciente(Builder builder) {
        this.nome = builder.nome;
        this.cpf = builder.cpf;
        this.idade = builder.idade;
        this.email = builder.email;
        this.telefone = builder.telefone;
    }

    public static class Builder {
        private String nome;
        private String cpf;
        private int idade;
        private String email;
        private String telefone;

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder idade(int idade) {
            this.idade = idade;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder telefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        public Paciente build() {
            if (this.nome == null || this.cpf == null || this.nome.isEmpty() || this.cpf.isEmpty()) {
                throw new IllegalArgumentException("Nome e CPF são obrigatórios");
            }
            return new Paciente(this);
        }
    }
}


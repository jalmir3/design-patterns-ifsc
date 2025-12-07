package ifsc.edu.designpatterns.behavioral;

public enum TipoAtendimento {
    CONSULTA("Consulta Médica"),
    EMERGENCIA("Atendimento de Emergência");

    private final String descricao;

    TipoAtendimento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}


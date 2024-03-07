package medi.voll.api.domain.medico;


import jakarta.validation.constraints.NotNull;
import medi.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedicos(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}

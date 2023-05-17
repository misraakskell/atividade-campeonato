package uefa.atividadecampeonato.jogos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uefa.atividadecampeonato.jogos.domain.Jogos;
import uefa.atividadecampeonato.jogos.requests.JogosPostRequestBody;
import uefa.atividadecampeonato.jogos.requests.JogosPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class JogosMapper {
    public static final JogosMapper INSTANCE = Mappers.getMapper(JogosMapper.class);

    public abstract Jogos toJogos(JogosPostRequestBody jogosPostRequestBody);

    public abstract Jogos toJogos(JogosPutRequestBody jogosPutRequestBody);
}

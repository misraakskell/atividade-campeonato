package uefa.atividadecampeonato.tabela.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uefa.atividadecampeonato.tabela.domain.Tabela;
import uefa.atividadecampeonato.tabela.requests.TabelaPostRequestBody;
import uefa.atividadecampeonato.tabela.requests.TabelaPutRequestBody;
import uefa.atividadecampeonato.times.mapper.TimesMapper;

@Mapper(componentModel = "spring")
public abstract class TabelaMapper {
    public static final TabelaMapper INSTANCE = Mappers.getMapper(TabelaMapper.class);

    public abstract Tabela toTabela(TabelaPostRequestBody tabelaPostRequestBody);

    public abstract Tabela toTabela(TabelaPutRequestBody tabelaPutRequestBody);
}

package uefa.atividadecampeonato.campeonato.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uefa.atividadecampeonato.campeonato.domain.Campeonato;
import uefa.atividadecampeonato.campeonato.requests.CampeonatoPostRequestBody;
import uefa.atividadecampeonato.campeonato.requests.CampeonatoPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class CampeonatoMapper {

    public static final CampeonatoMapper INSTANCE = Mappers.getMapper(CampeonatoMapper.class);

    public abstract Campeonato toCampeonato(CampeonatoPostRequestBody campeonatoPostRequestBody);

    public abstract Campeonato toCampeonato(CampeonatoPutRequestBody campeonatoPutRequestBpdy);
}

package uefa.atividadecampeonato.times.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uefa.atividadecampeonato.times.domain.Times;
import uefa.atividadecampeonato.times.requests.TimesPostRequestBody;
import uefa.atividadecampeonato.times.requests.TimesPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class TimesMapper {
    public static final TimesMapper INSTANCE = Mappers.getMapper(TimesMapper.class);

    public abstract Times toTimes(TimesPostRequestBody timesPostRequestBody);

    public abstract Times toTimes(TimesPutRequestBody timesPostRequestBody);
}

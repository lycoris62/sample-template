package ex.sample.domain.sample1.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import ex.sample.domain.sample1.domain.Sample;
import ex.sample.domain.sample1.dto.response.CreateSampleRes;
import ex.sample.domain.sample1.dto.response.GetSampleRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = SPRING)
public interface SampleMapper {

    @Mapping(target = "money", source = "money.amount")
    GetSampleRes toGetSampleRes(Sample sample);

    @Mapping(target = "money", source = "money.amount")
    CreateSampleRes toCreateSampleRes(Sample sample);
}

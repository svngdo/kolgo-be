package com.dtu.kolgo.config;

import com.dtu.kolgo.dto.enterprise.EnterpriseProfileDto;
import com.dtu.kolgo.dto.kol.KolProfileDto;
import com.dtu.kolgo.model.Enterprise;
import com.dtu.kolgo.model.Kol;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        modelMapper.typeMap(Kol.class, KolProfileDto.class)
                .addMapping(src -> src.getUser().getFirstName(), KolProfileDto::setFirstName)
                .addMapping(src -> src.getUser().getLastName(), KolProfileDto::setLastName)
                .addMapping(src -> src.getAddress().getCity().getId(), KolProfileDto::setCityId)
                .addMapping(src -> src.getAddress().getDetails(), KolProfileDto::setAddressDetails);
        modelMapper.typeMap(Enterprise.class, EnterpriseProfileDto.class)
                .addMapping(src -> src.getUser().getFirstName(), EnterpriseProfileDto::setFirstName)
                .addMapping(src -> src.getUser().getLastName(), EnterpriseProfileDto::setLastName)
                .addMapping(src -> src.getAddress().getCity().getId(), EnterpriseProfileDto::setCityId)
                .addMapping(src -> src.getAddress().getDetails(), EnterpriseProfileDto::setAddressDetails);
        return modelMapper;
    }

}

package com.dtu.kolgo.config;

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
//                .addMapping(src -> src.getUser().getFirstName(), KolDto::setFirstName)
//                .addMapping(src -> src.getUser().getLastName(), KolDto::setLastName)
//                .addMapping(src -> src.getUser().getEmail(), KolDto::setEmail)
//                .addMapping(src -> src.getUser().getAvatar(), KolDto::setAvatar);
//        modelMapper.typeMap(Enterprise.class, EntDto.class)
//                .addMapping(src -> src.getUser().getFirstName(), EntDto::setFirstName)
//                .addMapping(src -> src.getUser().getLastName(), EntDto::setLastName)
//                .addMapping(src -> src.getUser().getEmail(), EntDto::setEmail)
//                .addMapping(src -> src.getUser().getAvatar(), EntDto::setAvatar);
        return modelMapper;
    }

}

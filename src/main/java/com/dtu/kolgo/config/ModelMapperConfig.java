package com.dtu.kolgo.config;

import com.dtu.kolgo.dto.enterprise.EnterpriseDto;
import com.dtu.kolgo.dto.enterprise.EnterpriseProfileDto;
import com.dtu.kolgo.dto.kol.KolDto;
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
        modelMapper.typeMap(Kol.class, KolDto.class)
                .addMapping(src -> src.getUser().getId(), KolDto::setUserId)
                .addMapping(src -> src.getUser().getFirstName(), KolDto::setFirstName)
                .addMapping(src -> src.getUser().getLastName(), KolDto::setLastName)
                .addMapping(src -> src.getUser().getEmail(), KolDto::setEmail)
                .addMapping(src -> src.getUser().getAvatar(), KolDto::setAvatar)
                .addMapping(src -> src.getUser().getRole(), KolDto::setRole)
                .addMapping(src -> src.getAddress().getCity(), KolDto::setCity)
                .addMapping(src -> src.getAddress().getDetails(), KolDto::setAddressDetails);
        modelMapper.typeMap(Enterprise.class, EnterpriseDto.class)
                .addMapping(src -> src.getUser().getId(), EnterpriseDto::setUserId)
                .addMapping(src -> src.getUser().getFirstName(), EnterpriseDto::setFirstName)
                .addMapping(src -> src.getUser().getLastName(), EnterpriseDto::setLastName)
                .addMapping(src -> src.getUser().getEmail(), EnterpriseDto::setEmail)
                .addMapping(src -> src.getUser().getAvatar(), EnterpriseDto::setAvatar)
                .addMapping(src -> src.getUser().getRole(), EnterpriseDto::setRole)
                .addMapping(src -> src.getAddress().getCity(), EnterpriseDto::setCity)
                .addMapping(src -> src.getAddress().getDetails(), EnterpriseDto::setAddressDetails);
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
//        modelMapper.typeMap(Feedback.class, FeedbackDto.class)
//                .addMapping(src -> src.getSender().getkk)
        return modelMapper;
    }

}

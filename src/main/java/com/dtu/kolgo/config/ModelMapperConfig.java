package com.dtu.kolgo.config;

import com.dtu.kolgo.dto.CampaignDto;
import com.dtu.kolgo.dto.enterprise.EnterpriseDto;
import com.dtu.kolgo.dto.kol.KolDto;
import com.dtu.kolgo.dto.message.ChatDto;
import com.dtu.kolgo.model.Campaign;
import com.dtu.kolgo.model.Chat;
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
                .addMapping(Kol::getFieldIds, KolDto::setFieldIds)
                .addMapping(Kol::getFieldNames, KolDto::setFieldNames)
                .addMapping(Kol::getImageNames, KolDto::setImages);
        modelMapper.typeMap(Enterprise.class, EnterpriseDto.class)
                .addMapping(src -> src.getUser().getId(), EnterpriseDto::setUserId)
                .addMapping(src -> src.getUser().getFirstName(), EnterpriseDto::setFirstName)
                .addMapping(src -> src.getUser().getLastName(), EnterpriseDto::setLastName)
                .addMapping(src -> src.getUser().getEmail(), EnterpriseDto::setEmail)
                .addMapping(src -> src.getUser().getAvatar(), EnterpriseDto::setAvatar)
                .addMapping(src -> src.getUser().getRole(), EnterpriseDto::setRole);
        modelMapper.typeMap(Chat.class, ChatDto.class)
                .addMapping(Chat::getUserIds, ChatDto::setUserIds);
        modelMapper.typeMap(Campaign.class, CampaignDto.class)
                .addMapping(Campaign::getImageNames, CampaignDto::setImages);

        return modelMapper;
    }

}

package com.reservations.config;

import com.reservations.dto.RoomDTO;
import com.reservations.model.Room;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
@Configuration
public class MapperConfig {
    @Bean(name = "defaultMapper")
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean(name = "roomMapper")
    public ModelMapper medicMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(RoomDTO.class, Room.class)
                .addMapping(RoomDTO::getPrice, (dest, v) ->dest.setPrice((BigDecimal) v));
        modelMapper.createTypeMap(Room.class, RoomDTO.class)
                .addMapping(Room::getPrice, (dest, v) ->dest.setPrice((BigDecimal) v));
        return modelMapper;
    }
}

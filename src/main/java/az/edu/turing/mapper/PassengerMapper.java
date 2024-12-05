package az.edu.turing.mapper;

import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.model.dto.PassengerDto;

public class PassengerMapper implements EntityMapper <PassengerDto, PassengerEntity> {

    @Override
    public PassengerDto toDto(PassengerEntity passengerEntity) {

        return PassengerDto.PassengerDtoBuilder.builder()
                .id(passengerEntity.getId())
                .name(passengerEntity.getName())
                .surname(passengerEntity.getSurname())
                .email(passengerEntity.getEmail())
                .phoneNumber(passengerEntity.getPhoneNumber())
                .isDeleted(passengerEntity.getDeleted())
                .createdAt(passengerEntity.getCreatedAt())
                .updatedAt(passengerEntity.getUpdatedAt())
                .build();
    }
}

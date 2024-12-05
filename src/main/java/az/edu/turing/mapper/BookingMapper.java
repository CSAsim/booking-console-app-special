package az.edu.turing.mapper;

import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.model.dto.BookingDto;

public class BookingMapper implements EntityMapper<BookingDto, BookingEntity>{

    @Override
    public BookingDto toDto(BookingEntity bookingEntity) {

        return BookingDto.BookingDtoBuilder.builder()
                .id(bookingEntity.getId())
                .totalAmount(bookingEntity.getTotalAmount())
                .createdAt(bookingEntity.getCreatedAt())
                .updatedAt(bookingEntity.getUpdatedAt())
                .bookingStatus(bookingEntity.getBookingStatus())
                .build();
    }
}

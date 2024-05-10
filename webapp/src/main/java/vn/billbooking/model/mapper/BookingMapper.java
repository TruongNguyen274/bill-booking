package vn.billbooking.model.mapper;

import vn.billbooking.model.dto.BookingDTO;
import vn.billbooking.model.entity.Booking;

import java.util.List;

public interface BookingMapper {

    //Map Entity to DTO
    BookingDTO toDTO(Booking booking);

    List<BookingDTO> toListDTO(List<Booking> bookings);

    //Map DTO to Entity
    Booking toEntity(BookingDTO bookingDTO);


}

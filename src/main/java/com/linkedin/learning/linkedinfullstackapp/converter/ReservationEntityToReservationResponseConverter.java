package com.linkedin.learning.linkedinfullstackapp.converter;

import com.linkedin.learning.linkedinfullstackapp.entity.ReservationEntity;
import com.linkedin.learning.linkedinfullstackapp.model.response.ReservationResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;


public class ReservationEntityToReservationResponseConverter implements Converter<ReservationEntity, ReservationResponse> {
    @Override
    public ReservationResponse convert(ReservationEntity source) {
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setCheckin(source.getCheckin());
        reservationResponse.setCheckout(source.getCheckout());
        return reservationResponse;
    }
}

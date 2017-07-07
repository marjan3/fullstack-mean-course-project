package com.linkedin.learning.linkedinfullstackapp.converter;


import com.linkedin.learning.linkedinfullstackapp.entity.ReservationEntity;
import com.linkedin.learning.linkedinfullstackapp.model.request.ReservationRequest;
import org.springframework.core.convert.converter.Converter;

public class ReservationRequestToReservationEntityConverter implements Converter<ReservationRequest, ReservationEntity>{

    @Override
    public ReservationEntity convert(ReservationRequest source) {
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setCheckin(source.getCheckin());
        reservationEntity.setCheckout(source.getCheckout());
        if(null!=source.getId()){
            reservationEntity.setId(source.getId());
        }
        return reservationEntity;
    }
}

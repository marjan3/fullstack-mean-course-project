package com.linkedin.learning.linkedinfullstackapp.converter;

import com.linkedin.learning.linkedinfullstackapp.entity.RoomEntity;
import com.linkedin.learning.linkedinfullstackapp.model.Links;
import com.linkedin.learning.linkedinfullstackapp.model.response.ReservableRoomResponse;
import com.linkedin.learning.linkedinfullstackapp.model.Self;
import com.linkedin.learning.linkedinfullstackapp.rest.ResourceConstants;
import org.springframework.core.convert.converter.Converter;

public class RoomEntityToReservableRoomResponseConverter implements Converter<RoomEntity, ReservableRoomResponse> {

    @Override
    public ReservableRoomResponse convert(RoomEntity source) {
        ReservableRoomResponse response= new ReservableRoomResponse();
        response.setId(source.getId());
        response.setPrice(Integer.valueOf(source.getPrice()));
        response.setRoomNumber(source.getRoomNumber());
        Links links= new Links();
        Self self = new Self();
        self.setRef(ResourceConstants.ROOM_RESERVATION_V1 + "/" + source.getId());
        links.setSelf(self);
        response.setLinks(links);
        return response;
    }
}

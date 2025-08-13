package com.reservations.controller;

import com.reservations.dto.RoomDTO;
import com.reservations.model.Room;
import com.reservations.service.IRoomService;
import com.reservations.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final IRoomService service;
    private final MapperUtil mapperUtil;
    @GetMapping
    public ResponseEntity<List<RoomDTO>> findAll() throws Exception{
        List<RoomDTO> list = mapperUtil.mapList(service.findAll(), RoomDTO.class, "roomMapper");
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Room> save(@Valid @RequestBody RoomDTO dto) throws Exception{
        Room obj = service.save(mapperUtil.map(dto, Room.class, "roomMapper"));
        return ResponseEntity.ok().body(mapperUtil.map(obj, Room.class, "roomMapper"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> update(@Valid @RequestBody RoomDTO dto, @PathVariable("id") Integer id) throws Exception{
        Room obj = service.update(mapperUtil.map(dto, Room.class, "roomMapper"), id);
        return ResponseEntity.ok().body(mapperUtil.map(obj, RoomDTO.class, "roomMapper"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> findById(@PathVariable("id") Integer id) throws Exception{
        RoomDTO obj = mapperUtil.map(service.findById(id), RoomDTO.class);
        return ResponseEntity.ok().body(obj);
    }
}

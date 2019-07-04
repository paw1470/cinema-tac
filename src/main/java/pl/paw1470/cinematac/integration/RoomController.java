package pl.paw1470.cinematac.integration;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.paw1470.cinematac.core.model.RoomDAO;
import pl.paw1470.cinematac.core.ports.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<RoomDAO> getAll() {
        return roomService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public RoomDAO create(@RequestBody RoomDAO roomDAO) {
        return roomService.add(roomDAO);
    }
}

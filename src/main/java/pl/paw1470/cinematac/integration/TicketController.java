package pl.paw1470.cinematac.integration;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.paw1470.cinematac.core.model.TicketDAO;
import pl.paw1470.cinematac.core.ports.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public TicketDAO createTicket(@RequestBody TicketDAO ticketDAO) {
        return ticketService.add(ticketDAO);
    }

    @GetMapping()
    public List<TicketDAO> getAll() {
        return ticketService.getAll();
    }

    @GetMapping("/seance/{id}")
    public List<TicketDAO> getBySeance(@PathVariable Long id) {
        return ticketService.getBySeance(id);
    }
}

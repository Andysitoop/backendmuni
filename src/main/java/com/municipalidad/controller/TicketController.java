package com.municipalidad.controller;

import com.municipalidad.model.Ticket;
import com.municipalidad.model.Usuario;
import com.municipalidad.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/crear")
    public ResponseEntity<Ticket> crearTicket(@RequestParam Ticket.TipoServicio tipoServicio,
                                            @RequestParam Long usuarioId) {
        // TODO: Obtener usuario del servicio de autenticación
        Usuario usuario = new Usuario(); // Temporal
        usuario.setId(usuarioId);
        Ticket ticket = ticketService.crearTicket(tipoServicio, usuario);
        return ResponseEntity.ok(ticket);
    }

    @PutMapping("/{id}/atender")
    public ResponseEntity<Ticket> atenderTicket(@PathVariable Long id,
                                              @RequestParam Long atendidoPorId) {
        // TODO: Obtener usuario del servicio de autenticación
        Usuario atendidoPor = new Usuario(); // Temporal
        atendidoPor.setId(atendidoPorId);
        Ticket ticket = ticketService.atenderTicket(id, atendidoPor);
        return ticket != null ? ResponseEntity.ok(ticket) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Ticket> finalizarTicket(@PathVariable Long id) {
        Ticket ticket = ticketService.finalizarTicket(id);
        return ticket != null ? ResponseEntity.ok(ticket) : ResponseEntity.notFound().build();
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<Ticket>> obtenerTicketsPendientes() {
        return ResponseEntity.ok(ticketService.obtenerTicketsPendientes());
    }

    @GetMapping("/tipo/{tipoServicio}")
    public ResponseEntity<List<Ticket>> obtenerTicketsPorTipo(@PathVariable Ticket.TipoServicio tipoServicio) {
        return ResponseEntity.ok(ticketService.obtenerTicketsPorTipo(tipoServicio));
    }
} 
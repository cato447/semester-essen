package dev.cato447.semesteressen.controller;

import dev.cato447.semesteressen.domain.Event;
import dev.cato447.semesteressen.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents(@RequestParam(required = false) String name){
        List<Event> events = new ArrayList<>();

        if (name == null){
            events.addAll(eventRepository.findAll());
        } else {
            events.addAll(eventRepository.findByNameContaining(name));
        }

        if (events.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(events, HttpStatus.OK);
        }
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id){
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Event with id = " + id));

        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping("/events")
    public ResponseEntity<Event> createTutorial(@RequestBody Event tutorial) {
        Event saved_event = eventRepository.save(tutorial);
        return new ResponseEntity<>(saved_event, HttpStatus.CREATED);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateTutorial(@PathVariable("id") long id, @RequestBody Event event) {
        Event saved_event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Tutorial with id = " + id));

        saved_event.updateEvent(event);

        return new ResponseEntity<>(eventRepository.save(saved_event), HttpStatus.OK);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        eventRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/events")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        eventRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Event>> findByPublished() {
        List<Event> events = eventRepository.findByPublished(true);

        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

}

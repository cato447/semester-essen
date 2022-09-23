package dev.cato447.semesteressen.repository;

import dev.cato447.semesteressen.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    public Optional<Event> findById(Long id);
    public Event findByName(String name);
    public List<Event> findByNameContaining(String name);
    public List<Event> findByPublished(boolean isPublished);
    public List<Event> findEventsByParticipantsId(Long id);
}

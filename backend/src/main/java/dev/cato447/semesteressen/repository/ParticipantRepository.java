package dev.cato447.semesteressen.repository;

import dev.cato447.semesteressen.domain.Event;
import dev.cato447.semesteressen.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
   public List<Participant> findByEvents(Event event);
   public List<Participant> findParticipantsByEventsId(Long id);
}

package dev.cato447.semesteressen.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Event")
@Getter @Setter
@Table(name = "event")
@NoArgsConstructor
public class Event {

    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    private String name;

    private boolean published;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "event_participants",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "part_id") })
    private Set<Participant> participants = new HashSet<>();

    @Column(name = "event_date")
    private LocalDateTime dateTime;
    private int maxParticipants;

    public Event(String name, boolean published, LocalDateTime dateTime, int maxParticipants) {
        this.name = name;
        this.published = published;
        this.dateTime = dateTime;
        this.maxParticipants = maxParticipants;
    }

    public void addParticipant(Participant participant){
        this.participants.add(participant);
        participant.getEvents().add(this);
    }

    public void removeParticipant(Participant participant){
        this.participants.remove(participant);
        participant.getEvents().remove(this);
    }

    public void updateEvent(Event new_event){
        if (new_event.getName() != null) {
            this.setName(new_event.getName());
        }
        if (new_event.isPublished() != this.isPublished()) {
            this.setPublished(new_event.isPublished());
        }
        if (new_event.getDateTime() != null){
            this.setDateTime(new_event.getDateTime());
        }
        if (new_event.getMaxParticipants() != 0) {
            this.setMaxParticipants(new_event.getMaxParticipants());
        }
    }

}
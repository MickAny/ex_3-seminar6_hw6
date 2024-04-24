package seminar6.ex_3.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @JsonFormat(pattern = "YYYY/MM/dd HH:mm")
    private LocalDateTime localDateTime = LocalDateTime.now();

    public Note(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
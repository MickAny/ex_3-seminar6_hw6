package seminar6.ex_3.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seminar6.ex_3.aspects.TrackNoteAction;
import seminar6.ex_3.domain.Note;
import seminar6.ex_3.repositories.NoteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteRepository noteRepository;

    /**
     * Создание записи
     *
     * @param note запись
     * @return запись
     */
    @PostMapping
    @TrackNoteAction
    public ResponseEntity<Note> createNote(@RequestBody Note note){
        return new ResponseEntity<>(noteRepository.save(note), HttpStatus.CREATED);
    }

    /**
     * Возвращает список всех записей
     *
     * @return список всех записей
     */
    @GetMapping
    @TrackNoteAction
    public ResponseEntity<List<Note>> getAllNotes(){
        return new ResponseEntity<>(noteRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Возвращает запись по ID
     *
     * @param id ID записи
     * @return запись
     */
    @GetMapping("/{id}")
    @TrackNoteAction
    public ResponseEntity<Note> getNoteById(@PathVariable Long id){
        Optional<Note> foundNote = noteRepository.findTaskById(id);
        if(foundNote.isPresent()){
            return new ResponseEntity<>(foundNote.get(), HttpStatus.FOUND);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Изменяет данные записи по её ID
     *
     * @param id ID записи
     * @param note новая запись
     * @return видоизмененная запись
     */
    @PutMapping("/{id}")
    @TrackNoteAction
    public ResponseEntity<Note> editNote(@PathVariable Long id, @RequestBody Note note){
        Optional<Note> foundNote = noteRepository.findTaskById(id);
        if(foundNote.isPresent()){
            Note changedNote = foundNote.get();
            changedNote.setTitle(note.getTitle());
            changedNote.setText(note.getText());
            return new ResponseEntity<>(noteRepository.save(changedNote), HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Удаляет запись по её ID
     *
     * @param id ID записи
     * @return статус удаления записи
     */
    @DeleteMapping("/{id}")
    @TrackNoteAction
    public ResponseEntity<Note> deleteNote(@PathVariable Long id){
        Optional<Note> foundNote = noteRepository.findTaskById(id);
        if(foundNote.isPresent()){
            noteRepository.delete(foundNote.get());

            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}

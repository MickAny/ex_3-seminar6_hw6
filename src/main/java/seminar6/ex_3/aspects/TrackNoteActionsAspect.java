package seminar6.ex_3.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TrackNoteActionsAspect {

    @AfterReturning(value = "@annotation(TrackNoteAction)", returning = "returnedValue")
    public void log(Object returnedValue) {
        System.out.println("Method executed and returned " + returnedValue);
    }
}

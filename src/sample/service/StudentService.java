package sample.service;

public interface StudentService {

    boolean attachStudentToLaboratoryWork(Long studentId, Long moduleId, Long visibleQuery);

    boolean hasStudentLaboratoryWork(Long userId, Long labId);

    void finishLab(Long userId, Long labId);

}

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository examSessionRepository;
    private final StudentRepository studentRepository;

    public ExamSessionServiceImpl(ExamSessionRepository examSessionRepository,
                                  StudentRepository studentRepository) {
        this.examSessionRepository = examSessionRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public ExamSession createSession(ExamSession session) {

        if (session == null) {
            return null;
        }

        if (session.getExamDate() == null ||
            session.getExamDate().isBefore(LocalDate.now())) {
            return null;
        }

        if (session.getStudents() == null ||
            session.getStudents().isEmpty()) {
            return null;
        }

        return examSessionRepository.save(session);
    }

    @Override
    public ExamSession getSession(Long sessionId) {
        return examSessionRepository.findById(sessionId).orElse(null);
    }
}

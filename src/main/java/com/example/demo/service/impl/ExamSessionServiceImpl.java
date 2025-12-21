@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository examSessionRepository;

    public ExamSessionServiceImpl(ExamSessionRepository examSessionRepository) {
        this.examSessionRepository = examSessionRepository;
    }

    @Override
    public ExamSession createSession(ExamSession session) {

        if (session.getExamDate() == null) return null;
        if (session.getExamDate().isBefore(LocalDate.now())) return null;
        if (session.getStudents() == null || session.getStudents().isEmpty()) return null;

        return examSessionRepository.save(session);
    }

    @Override
    public ExamSession getSession(Long sessionId) {
        return examSessionRepository.findById(sessionId).orElse(null);
    }
}

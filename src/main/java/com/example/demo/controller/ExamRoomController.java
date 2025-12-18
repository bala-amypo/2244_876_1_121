@RestController
@RequestMapping("/rooms")
public class ExamRoomController {

    private final ExamRoomService service;

    public ExamRoomController(ExamRoomService service) {
        this.service = service;
    }

    @PostMapping
    public ExamRoom save(@RequestBody ExamRoom room) {
        return service.save(room);
    }

    @GetMapping
    public List<ExamRoom> getAll() {
        return service.getAll();
    }
}

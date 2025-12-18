@RestController
@RequestMapping("/seating")
public class SeatingPlanController {

    private final SeatingPlanService service;

    public SeatingPlanController(SeatingPlanService service) {
        this.service = service;
    }

    @PostMapping("/{sessionId}")
    public SeatingPlan generate(@PathVariable Long sessionId) {
        return service.generatePlan(sessionId);
    }
}

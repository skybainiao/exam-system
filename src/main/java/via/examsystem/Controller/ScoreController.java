package via.examsystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import via.examsystem.Service.ScoreService;
import via.examsystem.model.Score;

@CrossOrigin(
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH},
        allowCredentials = "true"
)
@RestController
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping("/")
    public ResponseEntity<Score> saveScore(@RequestBody Score score) {
        Score savedScore = scoreService.saveScore(score);
        return ResponseEntity.ok(savedScore);
    }
}

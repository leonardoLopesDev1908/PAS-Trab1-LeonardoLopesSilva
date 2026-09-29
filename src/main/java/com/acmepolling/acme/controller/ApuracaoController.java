import com.acmepolling.acme.common.ApiConstants;
import com.acmepolling.acme.controller.dto.response.ResponseCandidatoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.API_APURACAO)
public class ApuracaoController {

//    private final ApuracaoService service;
//    private final CandidatoService candidatoService;

//    public ApuracaoController(ApuracaoService service, CandidatoService candidatoService) {
//        this.service = service;
//        this.candidatoService = candidatoService;
//    }

    @GetMapping("/consultaeleito")
    public ResponseEntity<ResponseCandidatoDto> consultaEleito(@RequestParam String cep) {
        ResponseCandidatoDto response = service.
        return ResponseEntity.ok();
    }

    @GetMapping("/consultalocalidade")
    public ResponseEntity<ResponseLocalidadeDto> consultaLocalidade(@RequestParam String cep) {

    }

    @GetMapping("/listamaisvotadospartido")
    public ResponseEntity<ResponseCandidatoDto> consultaMaisVotados() {

    }

    @GetMapping()
}

import com.acmepolling.acme.common.ApiConstants;
import com.acmepolling.acme.controller.dto.request.ResponseCandidatoVotos;
import com.acmepolling.acme.controller.dto.response.ResponseCandidatoDto;
import com.acmepolling.acme.controller.dto.response.ResponseLocalidadeDto;
import com.acmepolling.acme.service.apuracao.ApuracaoService;
import com.acmepolling.acme.service.candidato.CandidatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.API_APURACAO)
public class ApuracaoController {

    private final ApuracaoService service;
    private final CandidatoService candidatoService;

    public ApuracaoController(ApuracaoService service, CandidatoService candidatoService) {
        this.service = service;
        this.candidatoService = candidatoService;
    }

//    @GetMapping("/consultaeleito")
//    public ResponseEntity<ResponseCandidatoDto> consultaEleito(@RequestParam String cep) {
//        ResponseCandidatoDto response = service.consultaEleito(cep);
//        return ResponseEntity.ok();
//    }
//
//    @GetMapping("/consultalocalidade")
//    public ResponseEntity<ResponseLocalidadeDto> consultaLocalidade(@RequestParam String cep) {
//        ResponseLocalidadeDto dto
//    }

    @GetMapping("/listamaisvotadospartido")
    public ResponseEntity<ResponseCandidatoDto> consultaMaisVotados() {

    }

    @GetMapping("/consultacandidato")
    public ResponseEntity<ResponseCandidatoVotos> consultaCandidato(@RequestParam String numero) {

    }
}

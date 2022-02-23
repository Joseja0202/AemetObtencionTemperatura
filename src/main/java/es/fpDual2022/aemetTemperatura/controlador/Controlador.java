package es.fpDual2022.aemetTemperatura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.fpDual2022.aemetTemperatura.utilidades.UtilidadesMunicipio;

@RestController
@RequestMapping("/temperatura")
public class Controlador {

	@Autowired
	private UtilidadesMunicipio utilidadesMunicipio;

	@GetMapping("/datos/{codMun}")
	public ResponseEntity<?> getDatos(@PathVariable Long codMun) {

		try {
			return new ResponseEntity<>(this.utilidadesMunicipio.getDatos(codMun), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}

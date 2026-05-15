package cl.usm.calculadoraspring.controllers;

import cl.usm.calculadoraspring.entities.CalculadoraRequest;
import cl.usm.calculadoraspring.services.CalculadoraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculadoraControllerTest {

    CalculadoraController calculadoraController;

    @Mock
    CalculadoraService calculadoraService;

    @BeforeEach
    void setUp(){
        // Solución: Pasamos manualmente el mock al constructor del controlador
        calculadoraController = new CalculadoraController(calculadoraService);
    }

    @Test
    void calcular_sumaOk(){
        CalculadoraRequest request = new CalculadoraRequest();

        request.setN1(1.0);
        request.setN2(2.0);
        request.setOperation("+");

        when(calculadoraService.calcular(anyString(), anyDouble(), anyDouble())).thenReturn(1.0);

        ResponseEntity<?> responseEntity = this.calculadoraController.calcular(request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void calcular_sumaNOk(){
        CalculadoraRequest request = new CalculadoraRequest();

        request.setN1(1.0);
        request.setN2(2.0);
        request.setOperation("+");

        when(calculadoraService.calcular(anyString(), anyDouble(), anyDouble())).thenThrow(new NullPointerException());

        ResponseEntity<?> responseEntity = this.calculadoraController.calcular(request);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

}

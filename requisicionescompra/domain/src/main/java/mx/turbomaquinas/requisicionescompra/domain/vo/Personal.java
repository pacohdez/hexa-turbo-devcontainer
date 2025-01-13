package mx.turbomaquinas.requisicionescompra.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Personal {
    String nombre;
    String departamento;
    int numeroEmpleado;
}

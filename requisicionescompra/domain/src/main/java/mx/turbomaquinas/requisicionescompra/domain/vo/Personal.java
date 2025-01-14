package mx.turbomaquinas.requisicionescompra.domain.vo;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.turbomaquinas.requisicionescompra.domain.specification.DeptoValidoPersonalSpec;
import mx.turbomaquinas.requisicionescompra.domain.specification.Specification;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Personal {
    String nombre;
    String departamento;
    int numeroEmpleado;

    public static Personal of(String nombre, String departamento, 
                                int numeroEmpleado, Set<String> deptosActivos) {
        Specification<Personal> spec = new DeptoValidoPersonalSpec(deptosActivos);
        Personal personal = new Personal(nombre, departamento, numeroEmpleado);
        if (!spec.isSatisfiedBy(personal)) {
            throw new IllegalArgumentException("El departamento no es válido");
        }
        return personal;
    }
}

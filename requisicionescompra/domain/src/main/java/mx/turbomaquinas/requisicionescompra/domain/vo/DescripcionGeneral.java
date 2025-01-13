package mx.turbomaquinas.requisicionescompra.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mx.turbomaquinas.requisicionescompra.domain.specification.DescripcionNoMax1000CaracteresSpec;
import mx.turbomaquinas.requisicionescompra.domain.specification.DescripcionNotNullOrEmptySpec;
import mx.turbomaquinas.requisicionescompra.domain.specification.Specification;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DescripcionGeneral {
    String descripcion;

    public static DescripcionGeneral of(String descripcion) {
        Specification<DescripcionGeneral> spec = new DescripcionNotNullOrEmptySpec();
        DescripcionGeneral descripcionGeneral = new DescripcionGeneral(descripcion);
        if (!spec.isSatisfiedBy(descripcionGeneral)) {
            throw new IllegalArgumentException("La descripción no puede ser nula o vacía");
        }

        Specification<DescripcionGeneral> specMaxLength = new DescripcionNoMax1000CaracteresSpec();
        if (!specMaxLength.isSatisfiedBy(descripcionGeneral)) {
            throw new IllegalArgumentException("La descripción no puede tener más de 1000 caracteres");
        }
        return descripcionGeneral;
    }
}

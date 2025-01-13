package mx.turbomaquinas.requisicionescompra.domain.specification;

import mx.turbomaquinas.requisicionescompra.domain.vo.DescripcionGeneral;

public class DescripcionNotNullOrEmptySpec implements Specification<DescripcionGeneral> {

    @Override
    public boolean isSatisfiedBy(DescripcionGeneral descripcionGeneral) {
        return descripcionGeneral.getDescripcion() != null && !descripcionGeneral.getDescripcion().isEmpty();
    }

}

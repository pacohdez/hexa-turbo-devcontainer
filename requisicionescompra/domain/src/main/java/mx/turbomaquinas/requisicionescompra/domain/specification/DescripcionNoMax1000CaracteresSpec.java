package mx.turbomaquinas.requisicionescompra.domain.specification;

import mx.turbomaquinas.requisicionescompra.domain.vo.DescripcionGeneral;

public class DescripcionNoMax1000CaracteresSpec implements Specification<DescripcionGeneral> {

    @Override
    public boolean isSatisfiedBy(DescripcionGeneral descripcionGeneral) {
        return descripcionGeneral.getDescripcion().length() <= 1000;
    }

}

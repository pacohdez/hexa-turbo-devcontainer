package mx.turbomaquinas.requisicionescompra.domain.specification;

import java.util.Set;

import mx.turbomaquinas.requisicionescompra.domain.vo.Personal;

public class DeptoValidoPersonalSpec implements Specification<Personal> {
    private Set<String> deptosActivos;

    public DeptoValidoPersonalSpec(Set<String> deptosActivos) {
        this.deptosActivos = deptosActivos;
    }

    @Override
    public boolean isSatisfiedBy(Personal personal) {
        return deptosActivos.contains(personal.getDepartamento());
    }

}

package mx.turbomaquinas.requisicionescompra.domain.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import mx.turbomaquinas.requisicionescompra.domain.vo.DescripcionGeneral;
import mx.turbomaquinas.requisicionescompra.domain.vo.Personal;
import mx.turbomaquinas.requisicionescompra.domain.vo.RequisicionId;
import mx.turbomaquinas.requisicionescompra.domain.vo.TipoRequisicion;

@Data
@SuperBuilder
public abstract sealed class RequisicionCompra 
    permits RequisicionInsumos, RequisicionServicios {

    private final RequisicionId id;
    private final DescripcionGeneral descripcionGeneral;
    private final Personal solicitante;
    private final Personal autorizador;
    private final Personal receptor;
    private final TipoRequisicion tipoRequisicion;

}

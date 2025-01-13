package mx.turbomaquinas.requisicionescompra.domain;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import mx.turbomaquinas.requisicionescompra.domain.vo.DescripcionGeneral;
import mx.turbomaquinas.requisicionescompra.domain.vo.RequisicionId;

public class VOTest {

    @Test
    void withId_CuandoUUIDValido_EntoncesCreaRequisicionId() {
        RequisicionId generated = RequisicionId.withoutId(); //UUID v7 válido
        RequisicionId requisicionId = RequisicionId.withId(generated.getId().toString());
        System.out.println("withId_CuandoUUIDValido_EntoncesCreaRequisicionId: " + requisicionId.getId());
        assertEquals(generated.getId(), requisicionId.getId());
    }

    @Test
    void withId_CuandoUUIDInvalido_EntoncesLanzaExcepcion() {
        String invalidUUID = "12345678-1234-5678-9234-567890abcdef"; //UUID no v7
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RequisicionId.withId(invalidUUID);
        });

        System.out.println("withId_CuandoUUIDInvalido_EntoncesLanzaExcepcion: " + invalidUUID + " " + exception.getMessage());
        assertTrue(exception.getMessage().contains("Version"));
    }

    @Test
    void withoutId_CuandoSeGeneraAutomaticamente_EntoncesCreaUUIDValido() {
        RequisicionId requisicionId = RequisicionId.withoutId();
        UUID uuid = requisicionId.getId();
        System.out.println("withoutId_CuandoSeGeneraAutomaticamente_EntoncesCreaUUIDValido: " + 
            requisicionId.getId() + " version: " + uuid.version() + " variante: " + uuid.variant());
        assertEquals(7, uuid.version());
        assertEquals(2, uuid.variant());
    }

    @Test
    void validUUIDv7_CuandoUUIDInvalidoPorVariante_EntoncesRetornaFalse() {
        UUID invalidVariantUUID = new UUID(0x7012345612345678L, 0x1234567890abcdefL);
        // Variante incorrecta
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new RequisicionId(invalidVariantUUID);
        });
        System.out.println("validUUIDv7_CuandoUUIDInvalidoPorVariante_EntoncesRetornaFalse: " +
                invalidVariantUUID + " " + exception.getMessage());
        System.out.println("UUID version: " + invalidVariantUUID.version() + " variante: " + invalidVariantUUID.variant());
        assertTrue(exception.getMessage().contains("Variante"));
    }

    @Test
    void crearDescripcionGeneral_CuandoDescripcionValida_EntoncesCreaDescripcionGeneral() {
        String descripcion = "Requicision para Stock";
        DescripcionGeneral descripcionGeneral = DescripcionGeneral.of(descripcion);
        System.out.println("crearDescripcionGeneral_CuandoDescripcionValida_EntoncesCreaDescripcionGeneral: " + descripcionGeneral.getDescripcion());
        assertEquals(descripcion, descripcionGeneral.getDescripcion());
    }

    @Test
    void crearDescripcionGeneral_CuandoDescripcionNula_EntoncesLanzaExcepcion() {
        String descripcion = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            DescripcionGeneral.of(descripcion);
        });
        System.out.println("crearDescripcionGeneral_CuandoDescripcionNula_EntoncesLanzaExcepcion: " + exception.getMessage());
        assertTrue(exception.getMessage().contains("nula"));
    }

    @Test
    void crearDescripcionGeneral_CuandoDescripcionMenor1000Caracteres_EntoncesCreaDescripcionGeneral() {
        String descripcion = "Requicision para Stock";
        DescripcionGeneral descripcionGeneral = DescripcionGeneral.of(descripcion);
        System.out.println("crearDescripcionGeneral_CuandoDescripcionMenor1000Caracteres_EntoncesCreaDescripcionGeneral: " + descripcionGeneral.getDescripcion());
        assertEquals(descripcion, descripcionGeneral.getDescripcion());
    }

    @Test
    void crearDescripcionGeneral_CuandoDescripcionMayor1000Caracteres_EntoncesLanzaExcepcion() {
        final StringBuilder descripcion = new StringBuilder("Requicision para Stock");
        for (int i = 0; i < 1000; i++) {
            descripcion.append("a");
        }
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            DescripcionGeneral.of(descripcion.toString());
        });
        System.out.println("crearDescripcionGeneral_CuandoDescripcionMayor1000Caracteres_EntoncesLanzaExcepcion: " + exception.getMessage());
        assertTrue(exception.getMessage().contains("1000"));
    }

}

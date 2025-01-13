package mx.turbomaquinas.requisicionescompra.domain.vo;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class RequisicionId {

    private static final SecureRandom random = new SecureRandom();
	@Getter
	private final UUID id;
	private String error;

	public RequisicionId(UUID id) {
		if (!ValidUUIDv7(id)) {
			throw new IllegalArgumentException(this.error);
		}
		this.id = id;
	}

	public static RequisicionId withId(String id) {
		UUID uuid = UUID.fromString(id);
		return new RequisicionId(uuid);
	}

	public static RequisicionId withoutId() {
		byte[] value = randomBytes();
		ByteBuffer buf = ByteBuffer.wrap(value);
		long high = buf.getLong();
		long low = buf.getLong();
		return new RequisicionId(new UUID(high, low));
	}

	public static byte[] randomBytes() {
		// random bytes
		byte[] value = new byte[16];
		random.nextBytes(value);

		// current timestamp in ms
		ByteBuffer timestamp = ByteBuffer.allocate(Long.BYTES);
		timestamp.putLong(System.currentTimeMillis());

		// timestamp
		System.arraycopy(timestamp.array(), 2, value, 0, 6);

		// version and variant
		value[6] = (byte) ((value[6] & 0x0F) | 0x70);
		value[8] = (byte) ((value[8] & 0x3F) | 0x80);

		return value;
	}

	private boolean ValidUUIDv7(UUID uuid) {
		boolean isValid = true;
		StringBuilder errors = new StringBuilder();

		if (uuid.version() != 7) {
			errors.append("Version ").append(uuid.version()).append(" del UUID invalida. ");
			isValid = false;
		}

		if (uuid.variant() != 2) {
			errors.append("Variante ").append(uuid.variant()).append(" del UUID invalida. ");
			isValid = false;
		}

		if(!isValid) {
			error = errors.toString();
		}

		return isValid;
	}
}
package DNA.Core;

import java.io.IOException;

import DNA.IO.BinaryReader;
import DNA.IO.BinaryWriter;

public class PrivacyPayloadTransaction extends Transaction {
	public EncryptedPayloadType payloadType;
	public byte[] payload;
	public PayloadEncryptType encryptType;
	public PayloadEncryptAttr encryptAttr;
	
	protected PrivacyPayloadTransaction() {
		super(TransactionType.PrivacyPayload);
	}

	protected void deserializeExclusiveData(BinaryReader reader) throws IOException {
		byte[] bb = new byte[1];
		reader.read(bb);
		payloadType = EncryptedPayloadType.from(bb[0]);
		payload = reader.readVarBytes();
		reader.read(bb);
		encryptType = PayloadEncryptType.from(bb[0]);
		encryptAttr.deserialize(reader);
	}
	
	protected void serializeExclusiveData(BinaryWriter writer) throws IOException {
		writer.write(new byte[]{(byte)payloadType.value()});
		writer.writeVarBytes(payload);
		writer.write(new byte[]{(byte)encryptType.value()});
		encryptAttr.serialize(writer);
	}
}

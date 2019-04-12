package DNA.Core;

import java.io.IOException;
import java.math.BigInteger;

import org.bouncycastle.math.ec.ECPoint;

import DNA.Helper;
import DNA.Cryptography.ECC;
import DNA.IO.BinaryReader;
import DNA.IO.BinaryWriter;

/**
 * 账本状态控制者交易
 * 
 * @author 12146
 *
 */
public class StateUpdaterTransaction extends Transaction {
	public ECPoint issuer;
	public BookKeeperAction action;
	public byte[] cert;
	public String namespace;
	
	public StateUpdaterTransaction() {
		super(TransactionType.StateUpdaterTransaction);
	}
	
	@Override
	protected void deserializeExclusiveData(BinaryReader reader) throws IOException {
		issuer = ECC.secp256r1.getCurve().createPoint(
        		new BigInteger(1,reader.readVarBytes()), new BigInteger(1,reader.readVarBytes()));
		action = BookKeeperAction.valueOf(reader.readByte());
		cert = reader.readVarBytes();
		namespace = reader.readVarString();
	}
	
	@Override
	protected void serializeExclusiveData(BinaryWriter writer) throws IOException {
		writer.writeVarBytes(Helper.removePrevZero(issuer.getXCoord().toBigInteger().toByteArray()));
        writer.writeVarBytes(Helper.removePrevZero(issuer.getYCoord().toBigInteger().toByteArray()));
        writer.writeByte(action.value());
        writer.writeVarBytes(cert);
        writer.writeVarString(namespace);
	}
}

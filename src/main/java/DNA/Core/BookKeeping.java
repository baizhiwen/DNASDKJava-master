package DNA.Core;

import java.io.IOException;

import DNA.IO.BinaryReader;
import DNA.IO.BinaryWriter;

public class BookKeeping extends Transaction {
	private long nonce; // nonce is not exist when version=2

	public BookKeeping() {
		super(TransactionType.BookKeeping);
	}

	@Override
	protected void deserializeExclusiveData(BinaryReader reader) throws IOException {
		if(version == 3) {
			nonce = reader.readLong();
		}
	}
	
	@Override
	protected void serializeExclusiveData(BinaryWriter writer) throws IOException {
		if(version == 3) {
			writer.writeLong(nonce);
		}
	}
}

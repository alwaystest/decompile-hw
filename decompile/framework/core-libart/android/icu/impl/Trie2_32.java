package android.icu.impl;

import android.icu.text.UTF16;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class Trie2_32 extends Trie2 {
    Trie2_32() {
    }

    public static Trie2_32 createFromSerialized(ByteBuffer bytes) throws IOException {
        return (Trie2_32) Trie2.createFromSerialized(bytes);
    }

    public final int get(int codePoint) {
        if (codePoint >= 0) {
            if (codePoint < 55296 || (codePoint > UTF16.LEAD_SURROGATE_MAX_VALUE && codePoint <= 65535)) {
                return this.data32[(this.index[codePoint >> 5] << 2) + (codePoint & 31)];
            } else if (codePoint <= 65535) {
                return this.data32[(this.index[((codePoint - 55296) >> 5) + 2048] << 2) + (codePoint & 31)];
            } else if (codePoint < this.highStart) {
                return this.data32[(this.index[this.index[(codePoint >> 11) + 2080] + ((codePoint >> 5) & 63)] << 2) + (codePoint & 31)];
            } else if (codePoint <= 1114111) {
                return this.data32[this.highValueIndex];
            }
        }
        return this.errorValue;
    }

    public int getFromU16SingleLead(char codeUnit) {
        return this.data32[(this.index[codeUnit >> 5] << 2) + (codeUnit & 31)];
    }

    public int serialize(OutputStream os) throws IOException {
        DataOutputStream dos = new DataOutputStream(os);
        int bytesWritten = serializeHeader(dos) + 0;
        for (int i = 0; i < this.dataLength; i++) {
            dos.writeInt(this.data32[i]);
        }
        return bytesWritten + (this.dataLength * 4);
    }

    public int getSerializedLength() {
        return ((this.header.indexLength * 2) + 16) + (this.dataLength * 4);
    }

    int rangeEnd(int startingCP, int limit, int value) {
        int cp = startingCP;
        loop0:
        while (cp < limit) {
            int index2Block;
            int block;
            if (cp < 55296 || (cp > UTF16.LEAD_SURROGATE_MAX_VALUE && cp <= 65535)) {
                index2Block = 0;
                block = this.index[cp >> 5] << 2;
            } else if (cp < 65535) {
                index2Block = 2048;
                block = this.index[((cp - 55296) >> 5) + 2048] << 2;
            } else if (cp < this.highStart) {
                index2Block = this.index[(cp >> 11) + 2080];
                block = this.index[((cp >> 5) & 63) + index2Block] << 2;
            } else if (value == this.data32[this.highValueIndex]) {
                cp = limit;
            }
            if (index2Block == this.index2NullOffset) {
                if (value != this.initialValue) {
                    break;
                }
                cp += 2048;
            } else if (block == this.dataNullOffset) {
                if (value != this.initialValue) {
                    break;
                }
                cp += 32;
            } else {
                int startIx = block + (cp & 31);
                int limitIx = block + 32;
                for (int ix = startIx; ix < limitIx; ix++) {
                    if (this.data32[ix] != value) {
                        cp += ix - startIx;
                        break loop0;
                    }
                }
                cp += limitIx - startIx;
            }
        }
        if (cp > limit) {
            cp = limit;
        }
        return cp - 1;
    }
}

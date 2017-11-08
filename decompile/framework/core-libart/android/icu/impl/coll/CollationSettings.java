package android.icu.impl.coll;

import java.util.Arrays;

public final class CollationSettings extends SharedObject {
    static final /* synthetic */ boolean -assertionsDisabled;
    static final int ALTERNATE_MASK = 12;
    public static final int BACKWARD_SECONDARY = 2048;
    public static final int CASE_FIRST = 512;
    public static final int CASE_FIRST_AND_UPPER_MASK = 768;
    public static final int CASE_LEVEL = 1024;
    public static final int CHECK_FCD = 1;
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    static final int MAX_VARIABLE_MASK = 112;
    static final int MAX_VARIABLE_SHIFT = 4;
    static final int MAX_VAR_CURRENCY = 3;
    static final int MAX_VAR_PUNCT = 1;
    static final int MAX_VAR_SPACE = 0;
    static final int MAX_VAR_SYMBOL = 2;
    public static final int NUMERIC = 2;
    static final int SHIFTED = 4;
    static final int STRENGTH_MASK = 61440;
    static final int STRENGTH_SHIFT = 12;
    static final int UPPER_FIRST = 256;
    public int fastLatinOptions = -1;
    public char[] fastLatinPrimaries = new char[CollationFastLatin.LATIN_LIMIT];
    long minHighNoReorder;
    public int options = 8208;
    public int[] reorderCodes = EMPTY_INT_ARRAY;
    long[] reorderRanges;
    public byte[] reorderTable;
    public long variableTop;

    public void setFlag(int r1, boolean r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: android.icu.impl.coll.CollationSettings.setFlag(int, boolean):void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: jadx.core.utils.exceptions.DecodeException: Unknown instruction: not-int
	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:569)
	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:56)
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:102)
	... 7 more
*/
        /*
        // Can't load method instructions.
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.CollationSettings.setFlag(int, boolean):void");
    }

    public void setFlagDefault(int r1, int r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: android.icu.impl.coll.CollationSettings.setFlagDefault(int, int):void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: jadx.core.utils.exceptions.DecodeException: Unknown instruction: not-int
	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:569)
	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:56)
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:102)
	... 7 more
*/
        /*
        // Can't load method instructions.
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.coll.CollationSettings.setFlagDefault(int, int):void");
    }

    CollationSettings() {
    }

    public CollationSettings clone() {
        CollationSettings newSettings = (CollationSettings) super.clone();
        newSettings.fastLatinPrimaries = (char[]) this.fastLatinPrimaries.clone();
        return newSettings;
    }

    public boolean equals(Object other) {
        if (other == null || !getClass().equals(other.getClass())) {
            return false;
        }
        CollationSettings o = (CollationSettings) other;
        if (this.options != o.options) {
            return false;
        }
        if (((this.options & 12) == 0 || this.variableTop == o.variableTop) && Arrays.equals(this.reorderCodes, o.reorderCodes)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int h = this.options << 8;
        if ((this.options & 12) != 0) {
            h = (int) (((long) h) ^ this.variableTop);
        }
        h ^= this.reorderCodes.length;
        for (int i = 0; i < this.reorderCodes.length; i++) {
            h ^= this.reorderCodes[i] << i;
        }
        return h;
    }

    public void resetReordering() {
        this.reorderTable = null;
        this.minHighNoReorder = 0;
        this.reorderRanges = null;
        this.reorderCodes = EMPTY_INT_ARRAY;
    }

    void aliasReordering(CollationData data, int[] codesAndRanges, int codesLength, byte[] table) {
        int[] codes;
        int i = 1;
        int i2 = 0;
        if (codesLength == codesAndRanges.length) {
            codes = codesAndRanges;
        } else {
            codes = new int[codesLength];
            System.arraycopy(codesAndRanges, 0, codes, 0, codesLength);
        }
        int rangesStart = codesLength;
        int rangesLimit = codesAndRanges.length;
        int rangesLength = rangesLimit - codesLength;
        if (table == null || (rangesLength != 0 ? rangesLength < 2 || (codesAndRanges[codesLength] & 65535) != 0 || (codesAndRanges[rangesLimit - 1] & 65535) == 0 : reorderTableHasSplitBytes(table))) {
            setReordering(data, codes);
            return;
        }
        this.reorderTable = table;
        this.reorderCodes = codes;
        int firstSplitByteRangeIndex = codesLength;
        while (firstSplitByteRangeIndex < rangesLimit && (codesAndRanges[firstSplitByteRangeIndex] & 16711680) == 0) {
            firstSplitByteRangeIndex++;
        }
        if (firstSplitByteRangeIndex == rangesLimit) {
            if (!-assertionsDisabled) {
                if (!reorderTableHasSplitBytes(table)) {
                    i2 = 1;
                }
                if (i2 == 0) {
                    throw new AssertionError();
                }
            }
            this.minHighNoReorder = 0;
            this.reorderRanges = null;
        } else {
            if (!-assertionsDisabled) {
                if (table[codesAndRanges[firstSplitByteRangeIndex] >>> 24] != (byte) 0) {
                    i = 0;
                }
                if (i == 0) {
                    throw new AssertionError();
                }
            }
            this.minHighNoReorder = ((long) codesAndRanges[rangesLimit - 1]) & Collation.MAX_PRIMARY;
            setReorderRanges(codesAndRanges, firstSplitByteRangeIndex, rangesLimit - firstSplitByteRangeIndex);
        }
    }

    public void setReordering(CollationData data, int[] codes) {
        if (codes.length == 0 || (codes.length == 1 && codes[0] == 103)) {
            resetReordering();
            return;
        }
        UVector32 rangesList = new UVector32();
        data.makeReorderRanges(codes, rangesList);
        int rangesLength = rangesList.size();
        if (rangesLength == 0) {
            resetReordering();
            return;
        }
        int rangesStart;
        int[] ranges = rangesList.getBuffer();
        if (!-assertionsDisabled) {
            if ((rangesLength >= 2 ? 1 : null) == null) {
                throw new AssertionError();
            }
        }
        if (!-assertionsDisabled) {
            Object obj = ((ranges[0] & 65535) != 0 || (ranges[rangesLength - 1] & 65535) == 0) ? null : 1;
            if (obj == null) {
                throw new AssertionError();
            }
        }
        this.minHighNoReorder = ((long) ranges[rangesLength - 1]) & Collation.MAX_PRIMARY;
        byte[] table = new byte[256];
        int b = 0;
        int firstSplitByteRangeIndex = -1;
        for (int i = 0; i < rangesLength; i++) {
            int pair = ranges[i];
            int limit1 = pair >>> 24;
            while (b < limit1) {
                table[b] = (byte) (b + pair);
                b++;
            }
            if ((16711680 & pair) != 0) {
                table[limit1] = (byte) 0;
                b = limit1 + 1;
                if (firstSplitByteRangeIndex < 0) {
                    firstSplitByteRangeIndex = i;
                }
            }
        }
        while (b <= 255) {
            table[b] = (byte) b;
            b++;
        }
        if (firstSplitByteRangeIndex < 0) {
            rangesLength = 0;
            rangesStart = 0;
        } else {
            rangesStart = firstSplitByteRangeIndex;
            rangesLength -= firstSplitByteRangeIndex;
        }
        setReorderArrays(codes, ranges, rangesStart, rangesLength, table);
    }

    private void setReorderArrays(int[] codes, int[] ranges, int rangesStart, int rangesLength, byte[] table) {
        Object obj = 1;
        if (codes == null) {
            codes = EMPTY_INT_ARRAY;
        }
        if (!-assertionsDisabled) {
            if ((codes.length == 0 ? 1 : null) != (table == null ? 1 : null)) {
                obj = null;
            }
            if (obj == null) {
                throw new AssertionError();
            }
        }
        this.reorderTable = table;
        this.reorderCodes = codes;
        setReorderRanges(ranges, rangesStart, rangesLength);
    }

    private void setReorderRanges(int[] ranges, int rangesStart, int rangesLength) {
        if (rangesLength == 0) {
            this.reorderRanges = null;
            return;
        }
        this.reorderRanges = new long[rangesLength];
        int i = 0;
        while (true) {
            int i2 = i + 1;
            int rangesStart2 = rangesStart + 1;
            this.reorderRanges[i] = ((long) ranges[rangesStart]) & 4294967295L;
            if (i2 < rangesLength) {
                i = i2;
                rangesStart = rangesStart2;
            } else {
                return;
            }
        }
    }

    public void copyReorderingFrom(CollationSettings other) {
        if (other.hasReordering()) {
            this.minHighNoReorder = other.minHighNoReorder;
            this.reorderTable = other.reorderTable;
            this.reorderRanges = other.reorderRanges;
            this.reorderCodes = other.reorderCodes;
            return;
        }
        resetReordering();
    }

    public boolean hasReordering() {
        return this.reorderTable != null;
    }

    private static boolean reorderTableHasSplitBytes(byte[] table) {
        if (!-assertionsDisabled) {
            if (!(table[0] == (byte) 0)) {
                throw new AssertionError();
            }
        }
        for (int i = 1; i < 256; i++) {
            if (table[i] == (byte) 0) {
                return true;
            }
        }
        return false;
    }

    public long reorder(long p) {
        byte b = this.reorderTable[((int) p) >>> 24];
        if (b != (byte) 0 || p <= 1) {
            return ((((long) b) & 255) << 24) | (16777215 & p);
        }
        return reorderEx(p);
    }

    private long reorderEx(long p) {
        if (!-assertionsDisabled) {
            if ((this.minHighNoReorder > 0 ? 1 : null) == null) {
                throw new AssertionError();
            }
        }
        if (p >= this.minHighNoReorder) {
            return p;
        }
        long q = p | 65535;
        int i = 0;
        while (true) {
            long r = this.reorderRanges[i];
            if (q < r) {
                return (((long) ((short) ((int) r))) << 24) + p;
            }
            i++;
        }
    }

    public void setStrength(int value) {
        int noStrength = this.options & -61441;
        switch (value) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 15:
                this.options = (value << 12) | noStrength;
                return;
            default:
                throw new IllegalArgumentException("illegal strength value " + value);
        }
    }

    public void setStrengthDefault(int defaultOptions) {
        this.options = (STRENGTH_MASK & defaultOptions) | (this.options & -61441);
    }

    static int getStrength(int options) {
        return options >> 12;
    }

    public int getStrength() {
        return getStrength(this.options);
    }

    public boolean getFlag(int bit) {
        return (this.options & bit) != 0;
    }

    public void setCaseFirst(int value) {
        Object obj = 1;
        if (!-assertionsDisabled) {
            if (!(value == 0 || value == 512 || value == 768)) {
                obj = null;
            }
            if (obj == null) {
                throw new AssertionError();
            }
        }
        this.options = (this.options & -769) | value;
    }

    public void setCaseFirstDefault(int defaultOptions) {
        this.options = (defaultOptions & 768) | (this.options & -769);
    }

    public int getCaseFirst() {
        return this.options & 768;
    }

    public void setAlternateHandlingShifted(boolean value) {
        int noAlternate = this.options & -13;
        if (value) {
            this.options = noAlternate | 4;
        } else {
            this.options = noAlternate;
        }
    }

    public void setAlternateHandlingDefault(int defaultOptions) {
        this.options = (defaultOptions & 12) | (this.options & -13);
    }

    public boolean getAlternateHandling() {
        return (this.options & 12) != 0;
    }

    public void setMaxVariable(int value, int defaultOptions) {
        int noMax = this.options & -113;
        switch (value) {
            case -1:
                this.options = (defaultOptions & 112) | noMax;
                return;
            case 0:
            case 1:
            case 2:
            case 3:
                this.options = (value << 4) | noMax;
                return;
            default:
                throw new IllegalArgumentException("illegal maxVariable value " + value);
        }
    }

    public int getMaxVariable() {
        return (this.options & 112) >> 4;
    }

    static boolean isTertiaryWithCaseBits(int options) {
        return (options & 1536) == 512;
    }

    static int getTertiaryMask(int options) {
        return isTertiaryWithCaseBits(options) ? 65343 : Collation.ONLY_TERTIARY_MASK;
    }

    static boolean sortsTertiaryUpperCaseFirst(int options) {
        return (options & 1792) == 768;
    }

    public boolean dontCheckFCD() {
        return (this.options & 1) == 0;
    }

    boolean hasBackwardSecondary() {
        return (this.options & 2048) != 0;
    }

    public boolean isNumeric() {
        return (this.options & 2) != 0;
    }

    static {
        boolean z;
        if (CollationSettings.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        -assertionsDisabled = z;
    }
}

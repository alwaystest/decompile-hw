package com.avast.android.shepherd.obfuscated;

import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: Unknown */
public class g {
    private byte[] a;
    private byte[] b;
    private Cipher c;
    private a d;
    private boolean e;

    /* compiled from: Unknown */
    public enum a {
        ENCRYPT_MODE,
        DECRYPT_MODE
    }

    protected g() {
        try {
            this.c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.e = false;
            this.d = a.ENCRYPT_MODE;
        } catch (Exception e) {
            throw new h(e);
        } catch (Exception e2) {
            throw new h(e2);
        }
    }

    private int a(byte[] bArr) {
        if (this.e) {
            return 0;
        }
        int length = this.b.length + 0;
        if (bArr.length >= this.b.length) {
            System.arraycopy(this.b, 0, bArr, 0, this.b.length);
            this.e = true;
            return length;
        }
        throw new h("insufficient output buffer size");
    }

    private int a(byte[] bArr, int i) {
        int i2 = 0;
        try {
            if (!this.e) {
                if (i >= 16) {
                    if (this.b == null) {
                        this.b = new byte[16];
                    }
                    System.arraycopy(bArr, 0, this.b, 0, 16);
                    this.e = true;
                    i2 = this.b.length + 0;
                    this.c.init(2, new SecretKeySpec(this.a, "AES"), new IvParameterSpec(this.b));
                } else {
                    throw new h("insufficient input buffer size");
                }
            }
            return i2;
        } catch (Exception e) {
            throw new h(e);
        } catch (Exception e2) {
            throw new h(e2);
        }
    }

    public static int a(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        g gVar = new g();
        gVar.a(a.ENCRYPT_MODE, bArr);
        return bArr3 != null ? gVar.a(bArr2, i, bArr3) : gVar.a(i);
    }

    public final int a(int i) {
        try {
            return this.d != a.ENCRYPT_MODE ? i : this.c.getOutputSize(i) + 16;
        } catch (Exception e) {
            throw new h(e);
        }
    }

    public final int a(byte[] bArr, int i, byte[] bArr2) {
        try {
            if (this.d == a.ENCRYPT_MODE) {
                int a = a(bArr2) + 0;
                return this.c.doFinal(bArr, 0, i, bArr2, a) + a;
            } else if (this.d != a.DECRYPT_MODE) {
                return 0;
            } else {
                int a2 = a(bArr, i);
                return this.c.doFinal(bArr, a2, i - a2, bArr2, 0) + 0;
            }
        } catch (Exception e) {
            throw new h(e);
        } catch (Exception e2) {
            throw new h(e2);
        } catch (Exception e22) {
            throw new h(e22);
        }
    }

    public final void a(a aVar, byte[] bArr) {
        a(aVar, bArr, null);
    }

    public final void a(a aVar, byte[] bArr, byte[] bArr2) {
        if (bArr == null && this.a == null) {
            throw new h("invalid key");
        }
        if (bArr != null) {
            this.a = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.a, 0, this.a.length);
        }
        try {
            AlgorithmParameters.getInstance("AES");
            this.e = false;
            if (aVar == a.ENCRYPT_MODE) {
                this.d = a.ENCRYPT_MODE;
                Key secretKeySpec = new SecretKeySpec(this.a, "AES");
                if (bArr2 != null) {
                    if (bArr2.length != 16) {
                        throw new h("IV length mismatch");
                    }
                }
                if (bArr2 != null) {
                    this.b = new byte[16];
                    System.arraycopy(bArr2, 0, this.b, 0, this.b.length);
                } else {
                    SecureRandom secureRandom = new SecureRandom();
                    this.b = new byte[16];
                    secureRandom.nextBytes(this.b);
                }
                this.c.init(1, secretKeySpec, new IvParameterSpec(this.b));
            } else if (aVar == a.DECRYPT_MODE) {
                this.d = a.DECRYPT_MODE;
            }
        } catch (Exception e) {
            throw new h(e);
        } catch (Exception e2) {
            throw new h(e2);
        } catch (Exception e22) {
            throw new h(e22);
        }
    }
}

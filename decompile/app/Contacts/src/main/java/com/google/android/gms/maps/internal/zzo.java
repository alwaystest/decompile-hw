package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: Unknown */
public interface zzo extends IInterface {

    /* compiled from: Unknown */
    public static abstract class zza extends Binder implements zzo {

        /* compiled from: Unknown */
        private static class zza implements zzo {
            private IBinder zzoz;

            zza(IBinder iBinder) {
                this.zzoz = iBinder;
            }

            public IBinder asBinder() {
                return this.zzoz;
            }

            public void zza(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnMapReadyCallback");
                    if (iGoogleMapDelegate != null) {
                        iBinder = iGoogleMapDelegate.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzoz.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.maps.internal.IOnMapReadyCallback");
        }

        public static zzo zzcK(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMapReadyCallback");
            return (queryLocalInterface != null && (queryLocalInterface instanceof zzo)) ? (zzo) queryLocalInterface : new zza(iBinder);
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface("com.google.android.gms.maps.internal.IOnMapReadyCallback");
                    zza(com.google.android.gms.maps.internal.IGoogleMapDelegate.zza.zzcv(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.maps.internal.IOnMapReadyCallback");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void zza(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException;
}

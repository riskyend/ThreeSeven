package com.google.android.gms.ads.internal.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzck;
import com.google.android.gms.internal.zzfs;
import com.google.android.gms.internal.zzfw;
import com.google.android.gms.location.places.Place;
import com.mopub.volley.Request.Method;
import com.nianticlabs.pokemongoplus.ble.BluetoothGattSupport;
import com.upsight.mediation.mraid.properties.MRAIDResizeProperties;
import com.upsight.mediation.vast.activity.PlayerControls;
import spacemadness.com.lunarconsole.R;

public interface zzs extends IInterface {

    public static abstract class zza extends Binder implements zzs {

        private static class zza implements zzs {
            private IBinder zznJ;

            zza(IBinder iBinder) {
                this.zznJ = iBinder;
            }

            public IBinder asBinder() {
                return this.zznJ;
            }

            public void destroy() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zznJ.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getMediationAdapterClassName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zznJ.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isLoading() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zznJ.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isReady() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zznJ.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void pause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zznJ.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void resume() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zznJ.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setManualImpressionsEnabled(boolean manualImpressionsEnabled) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    if (manualImpressionsEnabled) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.zznJ.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void showInterstitial() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zznJ.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void stopLoading() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zznJ.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(AdSizeParcel adSizeParcel) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    if (adSizeParcel != null) {
                        obtain.writeInt(1);
                        adSizeParcel.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zznJ.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzn com_google_android_gms_ads_internal_client_zzn) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    obtain.writeStrongBinder(com_google_android_gms_ads_internal_client_zzn != null ? com_google_android_gms_ads_internal_client_zzn.asBinder() : null);
                    this.zznJ.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzo com_google_android_gms_ads_internal_client_zzo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    obtain.writeStrongBinder(com_google_android_gms_ads_internal_client_zzo != null ? com_google_android_gms_ads_internal_client_zzo.asBinder() : null);
                    this.zznJ.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzu com_google_android_gms_ads_internal_client_zzu) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    obtain.writeStrongBinder(com_google_android_gms_ads_internal_client_zzu != null ? com_google_android_gms_ads_internal_client_zzu.asBinder() : null);
                    this.zznJ.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzv com_google_android_gms_ads_internal_client_zzv) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    obtain.writeStrongBinder(com_google_android_gms_ads_internal_client_zzv != null ? com_google_android_gms_ads_internal_client_zzv.asBinder() : null);
                    this.zznJ.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzck com_google_android_gms_internal_zzck) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    obtain.writeStrongBinder(com_google_android_gms_internal_zzck != null ? com_google_android_gms_internal_zzck.asBinder() : null);
                    this.zznJ.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzfs com_google_android_gms_internal_zzfs) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    obtain.writeStrongBinder(com_google_android_gms_internal_zzfs != null ? com_google_android_gms_internal_zzfs.asBinder() : null);
                    this.zznJ.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzfw com_google_android_gms_internal_zzfw, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    obtain.writeStrongBinder(com_google_android_gms_internal_zzfw != null ? com_google_android_gms_internal_zzfw.asBinder() : null);
                    obtain.writeString(str);
                    this.zznJ.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd zzaM() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zznJ.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd zzbk = com.google.android.gms.dynamic.zzd.zza.zzbk(obtain2.readStrongBinder());
                    return zzbk;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public AdSizeParcel zzaN() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zznJ.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    AdSizeParcel zzc = obtain2.readInt() != 0 ? AdSizeParcel.CREATOR.zzc(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return zzc;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzaP() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zznJ.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean zzb(AdRequestParcel adRequestParcel) throws RemoteException {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    if (adRequestParcel != null) {
                        obtain.writeInt(1);
                        adRequestParcel.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zznJ.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.ads.internal.client.IAdManager");
        }

        public static zzs zzk(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzs)) ? new zza(iBinder) : (zzs) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            AdSizeParcel adSizeParcel = null;
            int i = 0;
            boolean isReady;
            switch (code) {
                case R.styleable.LoadingImageView_imageAspectRatio /*1*/:
                    IBinder asBinder;
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    zzd zzaM = zzaM();
                    reply.writeNoException();
                    if (zzaM != null) {
                        asBinder = zzaM.asBinder();
                    }
                    reply.writeStrongBinder(asBinder);
                    return true;
                case R.styleable.LoadingImageView_circleCrop /*2*/:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    destroy();
                    reply.writeNoException();
                    return true;
                case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_CENTER /*3*/:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    isReady = isReady();
                    reply.writeNoException();
                    reply.writeInt(isReady ? 1 : 0);
                    return true;
                case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_LEFT /*4*/:
                    AdRequestParcel zzb;
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    if (data.readInt() != 0) {
                        zzb = AdRequestParcel.CREATOR.zzb(data);
                    }
                    isReady = zzb(zzb);
                    reply.writeNoException();
                    if (isReady) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_CENTER /*5*/:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    pause();
                    reply.writeNoException();
                    return true;
                case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_RIGHT /*6*/:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    resume();
                    reply.writeNoException();
                    return true;
                case Method.PATCH /*7*/:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    zza(com.google.android.gms.ads.internal.client.zzo.zza.zzg(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case BluetoothGattSupport.GATT_INSUF_AUTHENTICATION /*8*/:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    zza(com.google.android.gms.ads.internal.client.zzu.zza.zzm(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case Place.TYPE_BAR /*9*/:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    showInterstitial();
                    reply.writeNoException();
                    return true;
                case Place.TYPE_BEAUTY_SALON /*10*/:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    stopLoading();
                    reply.writeNoException();
                    return true;
                case Place.TYPE_BICYCLE_STORE /*11*/:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    zzaP();
                    reply.writeNoException();
                    return true;
                case Place.TYPE_BOOK_STORE /*12*/:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    adSizeParcel = zzaN();
                    reply.writeNoException();
                    if (adSizeParcel != null) {
                        reply.writeInt(1);
                        adSizeParcel.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case Place.TYPE_BOWLING_ALLEY /*13*/:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    if (data.readInt() != 0) {
                        adSizeParcel = AdSizeParcel.CREATOR.zzc(data);
                    }
                    zza(adSizeParcel);
                    reply.writeNoException();
                    return true;
                case Place.TYPE_BUS_STATION /*14*/:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    zza(com.google.android.gms.internal.zzfs.zza.zzP(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case Place.TYPE_CAFE /*15*/:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    zza(com.google.android.gms.internal.zzfw.zza.zzT(data.readStrongBinder()), data.readString());
                    reply.writeNoException();
                    return true;
                case Place.TYPE_CAR_RENTAL /*18*/:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    String mediationAdapterClassName = getMediationAdapterClassName();
                    reply.writeNoException();
                    reply.writeString(mediationAdapterClassName);
                    return true;
                case Place.TYPE_CAR_REPAIR /*19*/:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    zza(com.google.android.gms.internal.zzck.zza.zzs(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case PlayerControls.TEXT_FONT_SIZE /*20*/:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    zza(com.google.android.gms.ads.internal.client.zzn.zza.zzf(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case Place.TYPE_CASINO /*21*/:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    zza(com.google.android.gms.ads.internal.client.zzv.zza.zzn(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case Place.TYPE_CEMETERY /*22*/:
                    boolean z;
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    if (data.readInt() != 0) {
                        z = true;
                    }
                    setManualImpressionsEnabled(z);
                    reply.writeNoException();
                    return true;
                case Place.TYPE_CHURCH /*23*/:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManager");
                    isReady = isLoading();
                    reply.writeNoException();
                    if (isReady) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.ads.internal.client.IAdManager");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void destroy() throws RemoteException;

    String getMediationAdapterClassName() throws RemoteException;

    boolean isLoading() throws RemoteException;

    boolean isReady() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void setManualImpressionsEnabled(boolean z) throws RemoteException;

    void showInterstitial() throws RemoteException;

    void stopLoading() throws RemoteException;

    void zza(AdSizeParcel adSizeParcel) throws RemoteException;

    void zza(zzn com_google_android_gms_ads_internal_client_zzn) throws RemoteException;

    void zza(zzo com_google_android_gms_ads_internal_client_zzo) throws RemoteException;

    void zza(zzu com_google_android_gms_ads_internal_client_zzu) throws RemoteException;

    void zza(zzv com_google_android_gms_ads_internal_client_zzv) throws RemoteException;

    void zza(zzck com_google_android_gms_internal_zzck) throws RemoteException;

    void zza(zzfs com_google_android_gms_internal_zzfs) throws RemoteException;

    void zza(zzfw com_google_android_gms_internal_zzfw, String str) throws RemoteException;

    zzd zzaM() throws RemoteException;

    AdSizeParcel zzaN() throws RemoteException;

    void zzaP() throws RemoteException;

    boolean zzb(AdRequestParcel adRequestParcel) throws RemoteException;
}

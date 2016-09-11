package com.google.android.gms.common.server.response;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.internal.zzmj;
import com.google.android.gms.internal.zzmk;
import com.google.android.gms.internal.zzmu;
import com.google.android.gms.internal.zzmv;
import com.google.android.gms.location.places.Place;
import com.mopub.volley.Request.Method;
import com.nianticlabs.pokemongoplus.ble.BluetoothGattSupport;
import com.upsight.mediation.mraid.properties.MRAIDResizeProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import spacemadness.com.lunarconsole.R;

public class SafeParcelResponse extends FastJsonResponse implements SafeParcelable {
    public static final zze CREATOR;
    private final String mClassName;
    private final int mVersionCode;
    private final FieldMappingDictionary zzahc;
    private final Parcel zzahj;
    private final int zzahk;
    private int zzahl;
    private int zzahm;

    static {
        CREATOR = new zze();
    }

    SafeParcelResponse(int versionCode, Parcel parcel, FieldMappingDictionary fieldMappingDictionary) {
        this.mVersionCode = versionCode;
        this.zzahj = (Parcel) zzx.zzw(parcel);
        this.zzahk = 2;
        this.zzahc = fieldMappingDictionary;
        if (this.zzahc == null) {
            this.mClassName = null;
        } else {
            this.mClassName = this.zzahc.zzpT();
        }
        this.zzahl = 2;
    }

    private SafeParcelResponse(SafeParcelable safeParcelable, FieldMappingDictionary dictionary, String className) {
        this.mVersionCode = 1;
        this.zzahj = Parcel.obtain();
        safeParcelable.writeToParcel(this.zzahj, 0);
        this.zzahk = 1;
        this.zzahc = (FieldMappingDictionary) zzx.zzw(dictionary);
        this.mClassName = (String) zzx.zzw(className);
        this.zzahl = 2;
    }

    private static HashMap<Integer, Entry<String, Field<?, ?>>> zzG(Map<String, Field<?, ?>> map) {
        HashMap<Integer, Entry<String, Field<?, ?>>> hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            hashMap.put(Integer.valueOf(((Field) entry.getValue()).zzpK()), entry);
        }
        return hashMap;
    }

    public static <T extends FastJsonResponse & SafeParcelable> SafeParcelResponse zza(T t) {
        String canonicalName = t.getClass().getCanonicalName();
        return new SafeParcelResponse((SafeParcelable) t, zzb(t), canonicalName);
    }

    private static void zza(FieldMappingDictionary fieldMappingDictionary, FastJsonResponse fastJsonResponse) {
        Class cls = fastJsonResponse.getClass();
        if (!fieldMappingDictionary.zzb(cls)) {
            Map zzpD = fastJsonResponse.zzpD();
            fieldMappingDictionary.zza(cls, zzpD);
            for (String str : zzpD.keySet()) {
                Field field = (Field) zzpD.get(str);
                Class zzpL = field.zzpL();
                if (zzpL != null) {
                    try {
                        zza(fieldMappingDictionary, (FastJsonResponse) zzpL.newInstance());
                    } catch (Throwable e) {
                        throw new IllegalStateException("Could not instantiate an object of type " + field.zzpL().getCanonicalName(), e);
                    } catch (Throwable e2) {
                        throw new IllegalStateException("Could not access object of type " + field.zzpL().getCanonicalName(), e2);
                    }
                }
            }
        }
    }

    private void zza(StringBuilder stringBuilder, int i, Object obj) {
        switch (i) {
            case R.styleable.AdsAttrs_adSize /*0*/:
            case R.styleable.LoadingImageView_imageAspectRatio /*1*/:
            case R.styleable.LoadingImageView_circleCrop /*2*/:
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_CENTER /*3*/:
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_LEFT /*4*/:
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_CENTER /*5*/:
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_RIGHT /*6*/:
                stringBuilder.append(obj);
            case Method.PATCH /*7*/:
                stringBuilder.append("\"").append(zzmu.zzcz(obj.toString())).append("\"");
            case BluetoothGattSupport.GATT_INSUF_AUTHENTICATION /*8*/:
                stringBuilder.append("\"").append(zzmk.zzi((byte[]) obj)).append("\"");
            case Place.TYPE_BAR /*9*/:
                stringBuilder.append("\"").append(zzmk.zzj((byte[]) obj));
                stringBuilder.append("\"");
            case Place.TYPE_BEAUTY_SALON /*10*/:
                zzmv.zza(stringBuilder, (HashMap) obj);
            case Place.TYPE_BICYCLE_STORE /*11*/:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown type = " + i);
        }
    }

    private void zza(StringBuilder stringBuilder, Field<?, ?> field, Parcel parcel, int i) {
        switch (field.zzpC()) {
            case R.styleable.AdsAttrs_adSize /*0*/:
                zzb(stringBuilder, (Field) field, zza(field, Integer.valueOf(zza.zzg(parcel, i))));
            case R.styleable.LoadingImageView_imageAspectRatio /*1*/:
                zzb(stringBuilder, (Field) field, zza(field, zza.zzk(parcel, i)));
            case R.styleable.LoadingImageView_circleCrop /*2*/:
                zzb(stringBuilder, (Field) field, zza(field, Long.valueOf(zza.zzi(parcel, i))));
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_CENTER /*3*/:
                zzb(stringBuilder, (Field) field, zza(field, Float.valueOf(zza.zzl(parcel, i))));
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_LEFT /*4*/:
                zzb(stringBuilder, (Field) field, zza(field, Double.valueOf(zza.zzn(parcel, i))));
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_CENTER /*5*/:
                zzb(stringBuilder, (Field) field, zza(field, zza.zzo(parcel, i)));
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_RIGHT /*6*/:
                zzb(stringBuilder, (Field) field, zza(field, Boolean.valueOf(zza.zzc(parcel, i))));
            case Method.PATCH /*7*/:
                zzb(stringBuilder, (Field) field, zza(field, zza.zzp(parcel, i)));
            case BluetoothGattSupport.GATT_INSUF_AUTHENTICATION /*8*/:
            case Place.TYPE_BAR /*9*/:
                zzb(stringBuilder, (Field) field, zza(field, zza.zzs(parcel, i)));
            case Place.TYPE_BEAUTY_SALON /*10*/:
                zzb(stringBuilder, (Field) field, zza(field, zzi(zza.zzr(parcel, i))));
            case Place.TYPE_BICYCLE_STORE /*11*/:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown field out type = " + field.zzpC());
        }
    }

    private void zza(StringBuilder stringBuilder, String str, Field<?, ?> field, Parcel parcel, int i) {
        stringBuilder.append("\"").append(str).append("\":");
        if (field.zzpN()) {
            zza(stringBuilder, field, parcel, i);
        } else {
            zzb(stringBuilder, field, parcel, i);
        }
    }

    private void zza(StringBuilder stringBuilder, Map<String, Field<?, ?>> map, Parcel parcel) {
        HashMap zzG = zzG(map);
        stringBuilder.append('{');
        int zzap = zza.zzap(parcel);
        Object obj = null;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            Entry entry = (Entry) zzG.get(Integer.valueOf(zza.zzbM(zzao)));
            if (entry != null) {
                if (obj != null) {
                    stringBuilder.append(",");
                }
                zza(stringBuilder, (String) entry.getKey(), (Field) entry.getValue(), parcel, zzao);
                obj = 1;
            }
        }
        if (parcel.dataPosition() != zzap) {
            throw new zza.zza("Overread allowed size end=" + zzap, parcel);
        }
        stringBuilder.append('}');
    }

    private static FieldMappingDictionary zzb(FastJsonResponse fastJsonResponse) {
        FieldMappingDictionary fieldMappingDictionary = new FieldMappingDictionary(fastJsonResponse.getClass());
        zza(fieldMappingDictionary, fastJsonResponse);
        fieldMappingDictionary.zzpR();
        fieldMappingDictionary.zzpQ();
        return fieldMappingDictionary;
    }

    private void zzb(StringBuilder stringBuilder, Field<?, ?> field, Parcel parcel, int i) {
        if (field.zzpI()) {
            stringBuilder.append("[");
            switch (field.zzpC()) {
                case R.styleable.AdsAttrs_adSize /*0*/:
                    zzmj.zza(stringBuilder, zza.zzv(parcel, i));
                    break;
                case R.styleable.LoadingImageView_imageAspectRatio /*1*/:
                    zzmj.zza(stringBuilder, zza.zzx(parcel, i));
                    break;
                case R.styleable.LoadingImageView_circleCrop /*2*/:
                    zzmj.zza(stringBuilder, zza.zzw(parcel, i));
                    break;
                case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_CENTER /*3*/:
                    zzmj.zza(stringBuilder, zza.zzy(parcel, i));
                    break;
                case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_LEFT /*4*/:
                    zzmj.zza(stringBuilder, zza.zzz(parcel, i));
                    break;
                case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_CENTER /*5*/:
                    zzmj.zza(stringBuilder, zza.zzA(parcel, i));
                    break;
                case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_RIGHT /*6*/:
                    zzmj.zza(stringBuilder, zza.zzu(parcel, i));
                    break;
                case Method.PATCH /*7*/:
                    zzmj.zza(stringBuilder, zza.zzB(parcel, i));
                    break;
                case BluetoothGattSupport.GATT_INSUF_AUTHENTICATION /*8*/:
                case Place.TYPE_BAR /*9*/:
                case Place.TYPE_BEAUTY_SALON /*10*/:
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                case Place.TYPE_BICYCLE_STORE /*11*/:
                    Parcel[] zzF = zza.zzF(parcel, i);
                    int length = zzF.length;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (i2 > 0) {
                            stringBuilder.append(",");
                        }
                        zzF[i2].setDataPosition(0);
                        zza(stringBuilder, field.zzpP(), zzF[i2]);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unknown field type out.");
            }
            stringBuilder.append("]");
            return;
        }
        switch (field.zzpC()) {
            case R.styleable.AdsAttrs_adSize /*0*/:
                stringBuilder.append(zza.zzg(parcel, i));
            case R.styleable.LoadingImageView_imageAspectRatio /*1*/:
                stringBuilder.append(zza.zzk(parcel, i));
            case R.styleable.LoadingImageView_circleCrop /*2*/:
                stringBuilder.append(zza.zzi(parcel, i));
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_CENTER /*3*/:
                stringBuilder.append(zza.zzl(parcel, i));
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_LEFT /*4*/:
                stringBuilder.append(zza.zzn(parcel, i));
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_CENTER /*5*/:
                stringBuilder.append(zza.zzo(parcel, i));
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_RIGHT /*6*/:
                stringBuilder.append(zza.zzc(parcel, i));
            case Method.PATCH /*7*/:
                stringBuilder.append("\"").append(zzmu.zzcz(zza.zzp(parcel, i))).append("\"");
            case BluetoothGattSupport.GATT_INSUF_AUTHENTICATION /*8*/:
                stringBuilder.append("\"").append(zzmk.zzi(zza.zzs(parcel, i))).append("\"");
            case Place.TYPE_BAR /*9*/:
                stringBuilder.append("\"").append(zzmk.zzj(zza.zzs(parcel, i)));
                stringBuilder.append("\"");
            case Place.TYPE_BEAUTY_SALON /*10*/:
                Bundle zzr = zza.zzr(parcel, i);
                Set<String> keySet = zzr.keySet();
                keySet.size();
                stringBuilder.append("{");
                int i3 = 1;
                for (String str : keySet) {
                    if (i3 == 0) {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append("\"").append(str).append("\"");
                    stringBuilder.append(UpsightEndpoint.SIGNED_MESSAGE_SEPARATOR);
                    stringBuilder.append("\"").append(zzmu.zzcz(zzr.getString(str))).append("\"");
                    i3 = 0;
                }
                stringBuilder.append("}");
            case Place.TYPE_BICYCLE_STORE /*11*/:
                Parcel zzE = zza.zzE(parcel, i);
                zzE.setDataPosition(0);
                zza(stringBuilder, field.zzpP(), zzE);
            default:
                throw new IllegalStateException("Unknown field type out");
        }
    }

    private void zzb(StringBuilder stringBuilder, Field<?, ?> field, Object obj) {
        if (field.zzpH()) {
            zzb(stringBuilder, (Field) field, (ArrayList) obj);
        } else {
            zza(stringBuilder, field.zzpB(), obj);
        }
    }

    private void zzb(StringBuilder stringBuilder, Field<?, ?> field, ArrayList<?> arrayList) {
        stringBuilder.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            zza(stringBuilder, field.zzpB(), arrayList.get(i));
        }
        stringBuilder.append("]");
    }

    public static HashMap<String, String> zzi(Bundle bundle) {
        HashMap<String, String> hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    public int describeContents() {
        zze com_google_android_gms_common_server_response_zze = CREATOR;
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        zzx.zzb(this.zzahc, (Object) "Cannot convert to JSON on client side.");
        Parcel zzpV = zzpV();
        zzpV.setDataPosition(0);
        StringBuilder stringBuilder = new StringBuilder(100);
        zza(stringBuilder, this.zzahc.zzcw(this.mClassName), zzpV);
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        zze com_google_android_gms_common_server_response_zze = CREATOR;
        zze.zza(this, out, flags);
    }

    protected Object zzcs(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    protected boolean zzct(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public Map<String, Field<?, ?>> zzpD() {
        return this.zzahc == null ? null : this.zzahc.zzcw(this.mClassName);
    }

    public Parcel zzpV() {
        switch (this.zzahl) {
            case R.styleable.AdsAttrs_adSize /*0*/:
                this.zzahm = zzb.zzaq(this.zzahj);
                zzb.zzI(this.zzahj, this.zzahm);
                this.zzahl = 2;
                break;
            case R.styleable.LoadingImageView_imageAspectRatio /*1*/:
                zzb.zzI(this.zzahj, this.zzahm);
                this.zzahl = 2;
                break;
        }
        return this.zzahj;
    }

    FieldMappingDictionary zzpW() {
        switch (this.zzahk) {
            case R.styleable.AdsAttrs_adSize /*0*/:
                return null;
            case R.styleable.LoadingImageView_imageAspectRatio /*1*/:
                return this.zzahc;
            case R.styleable.LoadingImageView_circleCrop /*2*/:
                return this.zzahc;
            default:
                throw new IllegalStateException("Invalid creation type: " + this.zzahk);
        }
    }
}

package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.upsight.mediation.mraid.properties.MRAIDResizeProperties;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import spacemadness.com.lunarconsole.R;

public final class ObjectTypeAdapter extends TypeAdapter<Object> {
    public static final TypeAdapterFactory FACTORY;
    private final Gson gson;

    /* renamed from: com.google.gson.internal.bind.ObjectTypeAdapter.2 */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken;

        static {
            $SwitchMap$com$google$gson$stream$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    static {
        FACTORY = new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
                if (type.getRawType() == Object.class) {
                    return new ObjectTypeAdapter(gson);
                }
                return null;
            }
        };
    }

    ObjectTypeAdapter(Gson gson) {
        this.gson = gson;
    }

    public Object read(JsonReader in) throws IOException {
        switch (AnonymousClass2.$SwitchMap$com$google$gson$stream$JsonToken[in.peek().ordinal()]) {
            case R.styleable.LoadingImageView_imageAspectRatio /*1*/:
                List<Object> list = new ArrayList();
                in.beginArray();
                while (in.hasNext()) {
                    list.add(read(in));
                }
                in.endArray();
                return list;
            case R.styleable.LoadingImageView_circleCrop /*2*/:
                Map<String, Object> map = new LinkedTreeMap();
                in.beginObject();
                while (in.hasNext()) {
                    map.put(in.nextName(), read(in));
                }
                in.endObject();
                return map;
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_CENTER /*3*/:
                return in.nextString();
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_LEFT /*4*/:
                return Double.valueOf(in.nextDouble());
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_CENTER /*5*/:
                return Boolean.valueOf(in.nextBoolean());
            case MRAIDResizeProperties.CUSTOM_CLOSE_POSITION_BOTTOM_RIGHT /*6*/:
                in.nextNull();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    public void write(JsonWriter out, Object value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        TypeAdapter<Object> typeAdapter = this.gson.getAdapter(value.getClass());
        if (typeAdapter instanceof ObjectTypeAdapter) {
            out.beginObject();
            out.endObject();
            return;
        }
        typeAdapter.write(out, value);
    }
}

package io.sentry.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.SpanContext;
import io.sentry.protocol.App;
import io.sentry.protocol.Browser;
import io.sentry.protocol.Contexts;
import io.sentry.protocol.Device;
import io.sentry.protocol.Gpu;
import io.sentry.protocol.OperatingSystem;
import io.sentry.protocol.SentryRuntime;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes2.dex */
public final class ContextsDeserializerAdapter implements JsonDeserializer<Contexts> {
    private final SentryOptions options;

    public ContextsDeserializerAdapter(SentryOptions sentryOptions) {
        this.options = sentryOptions;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.JsonDeserializer
    public Contexts deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement == null) {
            return null;
        }
        try {
            if (jsonElement.isJsonNull()) {
                return null;
            }
            Contexts contexts = new Contexts();
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (asJsonObject != null && !asJsonObject.isJsonNull()) {
                for (String str : asJsonObject.keySet()) {
                    switch (str) {
                        case "app":
                            App app = (App) parseObject(jsonDeserializationContext, asJsonObject, str, App.class);
                            if (app == null) {
                                break;
                            } else {
                                contexts.setApp(app);
                                break;
                            }
                            break;
                        case "browser":
                            Browser browser = (Browser) parseObject(jsonDeserializationContext, asJsonObject, str, Browser.class);
                            if (browser == null) {
                                break;
                            } else {
                                contexts.setBrowser(browser);
                                break;
                            }
                            break;
                        case "device":
                            Device device = (Device) parseObject(jsonDeserializationContext, asJsonObject, str, Device.class);
                            if (device == null) {
                                break;
                            } else {
                                contexts.setDevice(device);
                                break;
                            }
                            break;
                        case "os":
                            OperatingSystem operatingSystem = (OperatingSystem) parseObject(jsonDeserializationContext, asJsonObject, str, OperatingSystem.class);
                            if (operatingSystem == null) {
                                break;
                            } else {
                                contexts.setOperatingSystem(operatingSystem);
                                break;
                            }
                            break;
                        case "runtime":
                            SentryRuntime sentryRuntime = (SentryRuntime) parseObject(jsonDeserializationContext, asJsonObject, str, SentryRuntime.class);
                            if (sentryRuntime == null) {
                                break;
                            } else {
                                contexts.setRuntime(sentryRuntime);
                                break;
                            }
                            break;
                        case "gpu":
                            Gpu gpu = (Gpu) parseObject(jsonDeserializationContext, asJsonObject, str, Gpu.class);
                            if (gpu == null) {
                                break;
                            } else {
                                contexts.setGpu(gpu);
                                break;
                            }
                            break;
                        case "trace":
                            SpanContext spanContext = (SpanContext) parseObject(jsonDeserializationContext, asJsonObject, str, SpanContext.class);
                            if (spanContext == null) {
                                break;
                            } else {
                                contexts.setTrace(spanContext);
                                break;
                            }
                            break;
                        default:
                            JsonElement jsonElement2 = asJsonObject.get(str);
                            if (jsonElement2 != null && !jsonElement2.isJsonNull()) {
                                try {
                                    contexts.put(str, jsonDeserializationContext.deserialize(jsonElement2, Object.class));
                                } catch (JsonParseException e) {
                                    this.options.getLogger().log(SentryLevel.ERROR, e, "Error when deserializing the %s key.", str);
                                }
                                break;
                            } else {
                                break;
                            }
                            break;
                    }
                }
            }
            return contexts;
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, "Error when deserializing Contexts", th);
            return null;
        }
    }

    private <T> T parseObject(JsonDeserializationContext jsonDeserializationContext, JsonObject jsonObject, String str, Class<T> cls) throws JsonParseException {
        JsonObject asJsonObject = jsonObject.getAsJsonObject(str);
        if (asJsonObject == null || asJsonObject.isJsonNull()) {
            return null;
        }
        return (T) jsonDeserializationContext.deserialize(asJsonObject, cls);
    }
}

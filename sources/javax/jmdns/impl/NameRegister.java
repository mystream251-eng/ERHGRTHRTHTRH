package javax.jmdns.impl;

import java.net.InetAddress;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes2.dex */
public interface NameRegister {

    public enum NameType {
        HOST,
        SERVICE
    }

    boolean checkName(InetAddress inetAddress, String str, NameType nameType);

    String incrementName(InetAddress inetAddress, String str, NameType nameType);

    void register(InetAddress inetAddress, String str, NameType nameType);

    public static abstract class BaseRegister implements NameRegister {
        protected String incrementNameWithDash(String str) {
            StringBuilder sb = new StringBuilder(str.length() + 5);
            int iIndexOf = str.indexOf(".local.");
            int iLastIndexOf = str.lastIndexOf(45);
            int i = 1;
            if (iLastIndexOf < 0) {
                sb.append(str.substring(0, iIndexOf));
            } else {
                try {
                    int i2 = Integer.parseInt(str.substring(iLastIndexOf + 1, iIndexOf)) + 1;
                    sb.append(str.substring(0, iLastIndexOf));
                    i = i2;
                } catch (Exception unused) {
                    sb.append(str.substring(0, iIndexOf));
                }
            }
            sb.append('-');
            sb.append(i);
            sb.append(".local.");
            return sb.toString();
        }

        protected String incrementNameWithParentesis(String str) {
            StringBuilder sb = new StringBuilder(str.length() + 5);
            int iLastIndexOf = str.lastIndexOf(40);
            int iLastIndexOf2 = str.lastIndexOf(41);
            if (iLastIndexOf >= 0 && iLastIndexOf < iLastIndexOf2) {
                try {
                    sb.append(str.substring(0, iLastIndexOf));
                    sb.append('(');
                    sb.append(Integer.parseInt(str.substring(iLastIndexOf + 1, iLastIndexOf2)) + 1);
                    sb.append(')');
                } catch (NumberFormatException unused) {
                    sb.setLength(0);
                    sb.append(str);
                    sb.append(" (2)");
                }
            } else {
                sb.append(str);
                sb.append(" (2)");
            }
            return sb.toString();
        }
    }

    /* JADX INFO: renamed from: javax.jmdns.impl.NameRegister$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$jmdns$impl$NameRegister$NameType;

        static {
            int[] iArr = new int[NameType.values().length];
            $SwitchMap$javax$jmdns$impl$NameRegister$NameType = iArr;
            try {
                iArr[NameType.HOST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$NameRegister$NameType[NameType.SERVICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static class UniqueNamePerInterface extends BaseRegister {
        private final ConcurrentMap<InetAddress, String> _hostNames = new ConcurrentHashMap();
        private final ConcurrentMap<InetAddress, Set<String>> _serviceNames = new ConcurrentHashMap();

        @Override // javax.jmdns.impl.NameRegister
        public void register(InetAddress inetAddress, String str, NameType nameType) {
            int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$NameRegister$NameType[nameType.ordinal()];
        }

        @Override // javax.jmdns.impl.NameRegister
        public boolean checkName(InetAddress inetAddress, String str, NameType nameType) {
            Set<String> set;
            int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$NameRegister$NameType[nameType.ordinal()];
            if (i != 1) {
                return i == 2 && (set = this._serviceNames.get(inetAddress)) != null && set.contains(str);
            }
            String str2 = this._hostNames.get(inetAddress);
            return str2 != null && str2.equals(str);
        }

        @Override // javax.jmdns.impl.NameRegister
        public String incrementName(InetAddress inetAddress, String str, NameType nameType) {
            int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$NameRegister$NameType[nameType.ordinal()];
            if (i != 1) {
                return i != 2 ? str : incrementNameWithParentesis(str);
            }
            return incrementNameWithDash(str);
        }
    }

    public static class UniqueNameAcrossInterface extends BaseRegister {
        @Override // javax.jmdns.impl.NameRegister
        public void register(InetAddress inetAddress, String str, NameType nameType) {
            int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$NameRegister$NameType[nameType.ordinal()];
        }

        @Override // javax.jmdns.impl.NameRegister
        public boolean checkName(InetAddress inetAddress, String str, NameType nameType) {
            int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$NameRegister$NameType[nameType.ordinal()];
            return false;
        }

        @Override // javax.jmdns.impl.NameRegister
        public String incrementName(InetAddress inetAddress, String str, NameType nameType) {
            int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$NameRegister$NameType[nameType.ordinal()];
            if (i != 1) {
                return i != 2 ? str : incrementNameWithParentesis(str);
            }
            return incrementNameWithDash(str);
        }
    }

    public static class Factory {
        private static volatile NameRegister _register;

        public static void setRegistry(NameRegister nameRegister) throws IllegalStateException {
            if (_register != null) {
                throw new IllegalStateException("The register can only be set once.");
            }
            if (nameRegister != null) {
                _register = nameRegister;
            }
        }

        public static NameRegister getRegistry() {
            if (_register == null) {
                _register = new UniqueNamePerInterface();
            }
            return _register;
        }
    }
}

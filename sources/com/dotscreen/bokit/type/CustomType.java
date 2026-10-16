package com.dotscreen.bokit.type;

import com.apollographql.apollo.api.ScalarType;

/* JADX INFO: loaded from: classes.dex */
public enum CustomType implements ScalarType {
    ID { // from class: com.dotscreen.bokit.type.CustomType.1
        @Override // com.apollographql.apollo.api.ScalarType
        public String className() {
            return "java.lang.String";
        }

        @Override // com.apollographql.apollo.api.ScalarType
        public String typeName() {
            return "ID";
        }
    },
    MEDIATYPE { // from class: com.dotscreen.bokit.type.CustomType.2
        @Override // com.apollographql.apollo.api.ScalarType
        public String className() {
            return "java.lang.String";
        }

        @Override // com.apollographql.apollo.api.ScalarType
        public String typeName() {
            return "MediaType";
        }
    },
    URI { // from class: com.dotscreen.bokit.type.CustomType.3
        @Override // com.apollographql.apollo.api.ScalarType
        public String className() {
            return "java.lang.String";
        }

        @Override // com.apollographql.apollo.api.ScalarType
        public String typeName() {
            return "URI";
        }
    }
}

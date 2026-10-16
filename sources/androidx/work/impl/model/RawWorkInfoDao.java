package androidx.work.impl.model;

import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface RawWorkInfoDao {
    List<WorkSpec.WorkInfoPojo> getWorkInfoPojos(SupportSQLiteQuery query);

    LiveData<List<WorkSpec.WorkInfoPojo>> getWorkInfoPojosLiveData(SupportSQLiteQuery query);
}

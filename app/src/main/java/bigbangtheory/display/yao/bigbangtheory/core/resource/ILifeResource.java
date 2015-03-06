package bigbangtheory.display.yao.bigbangtheory.core.resource;

import android.content.Context;

import com.google.inject.Inject;

/**
 * 资源层，可以通过这一层获取本地资源
 * Created by JimmyandHurry on 2015/3/6.
 */
public class ILifeResource {
    private Context context;
    @Inject
    public ILifeResource(Context context) {
        this.context = context;
    }

    protected Context getApplicationContext() {
        return context;
    }
}

package bigbangtheory.display.yao.bigbangtheory.core.client;

import com.loopj.android.http.RequestHandle;

/**
 * Created by JimmyandHurry on 2015/2/2.
 */
public class BigBangHttpRequestHandler {
    private RequestHandle handler;
    public BigBangHttpRequestHandler(RequestHandle handler){
        this.handler = handler;
    }
    public boolean cancel(boolean mayInterruptIfRunning) {
       return handler.cancel(mayInterruptIfRunning);
    }

    /**
     * Returns true if this task completed. Completion may be due to normal termination, an
     * exception, or cancellation -- in all of these cases, this method will return true.
     *
     * @return true if this task completed
     */
    public boolean isFinished() {
       return handler.isFinished();
    }

    /**
     * Returns true if this task was cancelled before it completed normally.
     *
     * @return true if this task was cancelled before it completed
     */
    public boolean isCancelled() {
       return handler.isCancelled();
    }

    public boolean shouldBeGarbageCollected() {
       return handler.shouldBeGarbageCollected();
    }


}


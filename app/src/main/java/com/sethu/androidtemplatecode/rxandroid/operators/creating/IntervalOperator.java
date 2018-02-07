package com.sethu.androidtemplatecode.rxandroid.operators.creating;
import android.util.Log;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//interval operator:- create an Observable that emits a particular item multiple times
public class IntervalOperator {

    public static void getIntervalOperator(final TextView textView){
        Observable.interval(0,10,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(Long aLong) {
                        CreateOperator.observable.observeOn( AndroidSchedulers.mainThread()).subscribe(
                                new Observer() {
                                      @Override
                                      public void onSubscribe(Disposable d) {}
                                      @Override
                                      public void onNext(Object o) {
                                          Log.d("TAG", "onNext: "+o);
                                          textView.append(" "+o);
                                      }

                                      @Override
                                      public void onError(Throwable e) {}

                                      @Override
                                      public void onComplete() {}
                                });
                    };

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
//place this code in onCreate method of RxAndroid2DemoActivity to watch the output
//IntervalOperator.getIntervalOperator(textView);

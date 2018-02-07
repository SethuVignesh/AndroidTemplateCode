package com.sethu.androidtemplatecode.rxandroid.operators.creating;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

//timer operator:- create an Observable that emits a single item after a given delay

public class TimerOperator {
    public static void getTimerObservable(final TextView textView){
        Observable.timer(2000, TimeUnit.MILLISECONDS)
               .map(new Function() {
                   @Override
                   public Object apply(Object o) throws Exception {
                       return  "some operation done after 2 seconds ";
                   }
               }).subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(Object o) {
                       textView.append(" "+o);
                   }

                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onComplete() {

                   }
               });
    }
}
//place this code in onCreate method of RxAndroid2DemoActivity to watch the output
// TimerOperator.getTimerObservable( textView);
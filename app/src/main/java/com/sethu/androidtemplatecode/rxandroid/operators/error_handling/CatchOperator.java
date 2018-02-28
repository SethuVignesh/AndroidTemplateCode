package com.sethu.androidtemplatecode.rxandroid.operators.error_handling;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.BiPredicate;

//catch operator:- recover from an onError notification by continuing the sequence
// without error

public class CatchOperator {
    public static Observable catchOperator(){
      return   Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
               throw new Exception("testing on error item ");
            }}).retry(new BiPredicate<Integer, Throwable>() {
                  @Override
                  public boolean test(Integer integer, Throwable throwable) throws Exception {
                     if(integer == 2) {
                         new Handler(Looper.getMainLooper()).post(new Runnable() {
                             @Override
                             public void run() {
                                 Log.d("tag","retrying for error");
                             }
                         });
                         return false;
                     }
                     return true;
                  }})
              .onErrorReturnItem(new AbstractMethodError("Error caught in onErrorReturnItem"))
//.onExceptionResumeNext(Observable.create(new ObservableOnSubscribe<Object>() {
//          @Override
//          public void subscribe(ObservableEmitter<Object> e) throws Exception {
//              throw new Exception("testing on error item ");
//          }
//      }))
//             .onErrorResumeNext(Observable.just("resuming after onError"))
              //.retry(2)
              ;
    }
}

//place this code in onCreate method of RxAndroid2DemoActivity to watch the output
//    CatchOperator.catchOperator()
//            .observeOn(Schedulers.newThread())
//                    .subscribeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer() {
//                    @Override
//                    public void onSubscribe(Disposable d) { }
//
//                    @Override
//                    public void onNext(Object o) {
//                            textView.append("  "+o);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                            textView.append(" " + e);
//                    }
//
//                    @Override
//                    public void onComplete() { }
//            });
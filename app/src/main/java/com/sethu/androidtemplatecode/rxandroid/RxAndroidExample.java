package com.sethu.androidtemplatecode.rxandroid;
import android.widget.TextView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

//comments are take from official reactive website

public class RxAndroidExample {
    // Disposables is used to un-subscribe from observables
    private static Disposable disposables ;

    public static void sampleExample(TextView textView){
        //Observable emits the data as soon as observer is subscribed
        Observable<String> observable =  createObservable() ;
        // DisposableObserver observes the value emitted by observable
        DisposableObserver<String> observer = createDisposableObserver(textView);
        // specify the Scheduler on which an Observable will operate
                observable.subscribeOn(Schedulers.newThread())
        //specify the scheduler on which an observer will observe this Observable
                .observeOn(AndroidSchedulers.mainThread())
        //operate upon the emissions and notifications from an Observable
                .subscribe(observer);
        disposables = observer;
    }

    private static Observable<String> createObservable(){
        return Observable.just("one", "two", "three", "four", "five");
    }

    private static DisposableObserver<String> createDisposableObserver(final TextView textView){
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                textView.append("  "+ s);
            }
            @Override
            public void onError(Throwable e) {
                textView.append(" "+ e);
            }
            @Override
            public void onComplete() {
                textView.append("\n OnComplete:- just operator finished emitting data.\n");
            }
        };
    }

    public static void beforeDestroy(){
        if(disposables!=null)
            disposables.dispose();
    }
}
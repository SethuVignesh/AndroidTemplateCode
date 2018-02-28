package com.sethu.androidtemplatecode.rxandroid.operators.filtering;
import com.sethu.androidtemplatecode.rxandroid.operators.creating.FromCallableOperator;
import com.sethu.androidtemplatecode.rxandroid.operators.creating.JustOperator;
import io.reactivex.Observable;

//take operator:- emit only the first n or last n items emitted by an Observable

public class TakeOperator {
    public static Observable takeOperator(){
        return FromCallableOperator.fromCallableOperator().take(2);
    }
    public static Observable takeLastOperator(){
        return JustOperator.getJustObservable().takeLast(2);
    }
}

//place this code in onCreate method of RxAndroid2DemoActivity to watch the output
//    Observable<Integer> observable = TakeOperator.takeOperator();
//        observable.observeOn(Schedulers.newThread())
//                .subscribeOn(AndroidSchedulers.mainThread())
//            .subscribe(new Observer<Object>() {
//        @Override
//        public void onSubscribe(Disposable d) {
//
//        }
//
//        @Override
//        public void onNext(Object s) {
//            textView.append("  "+ s );
//        }
//
//        @Override
//        public void onError(Throwable e) {
//            textView.append(" error in merge operator "+e);
//        }
//
//        @Override
//        public void onComplete() {
//            textView.append("  Take operator completed \n");
//        }
//    });